package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pollub.cs.ptrwrbl.receptionist.exceptions.FileStorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final String UPLOAD_FOLDER = "/assets";
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    @Override
    public String store(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            String mimeType = Files.probeContentType(file.getResource().getFile().toPath());

            if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png")) {
                throw new FileStorageException(" Only jpg and png files are allowed.");
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                throw new FileStorageException("SFile size must be less than 10MB.");
            }

            if(fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence " + fileName);
            }

            String uniqueFileName = fileName;
            Path targetLocation;
            do {
                UUID uuid = UUID.randomUUID();
                uniqueFileName = uuid.toString() + "_" + fileName;
                targetLocation = Paths.get(UPLOAD_FOLDER).resolve(uniqueFileName);
            } while (Files.exists(targetLocation));

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
