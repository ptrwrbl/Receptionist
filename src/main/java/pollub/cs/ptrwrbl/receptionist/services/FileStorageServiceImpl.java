package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pollub.cs.ptrwrbl.receptionist.exceptions.FileStorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final String UPLOAD_FOLDER = "assets";
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Override
    public Resource serve(String filename) {
        Path filePath = Paths.get(UPLOAD_FOLDER).resolve(filename).normalize();

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new FileStorageException("File not found " + filename);
            }

            return resource;
        } catch (MalformedURLException ex) {
            throw new FileStorageException("File not found " + filename, ex);
        }
    }

    @Override
    public String store(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            String fileExtension = StringUtils.getFilenameExtension(fileName).toLowerCase();
            if (!Arrays.asList("jpg", "png").contains(fileExtension)) {
                throw new FileStorageException("Only jpg and png files are allowed.");
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                throw new FileStorageException("File size must be less than 10MB.");
            }

            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence " + fileName);
            }

            String uniqueFileName = fileName;
            Path targetLocation;
            do {
                UUID uuid = UUID.randomUUID();
                uniqueFileName = uuid.toString() + "_" + fileName;
                targetLocation = Paths.get(UPLOAD_FOLDER).resolve(uniqueFileName);
            } while (Files.exists(targetLocation));

            // Use getInputStream() instead of getFile()
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
