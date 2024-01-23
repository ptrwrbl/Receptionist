package pollub.cs.ptrwrbl.receptionist.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    Resource serve(String filename);

    String store(MultipartFile file);
}