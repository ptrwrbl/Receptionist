package pollub.cs.ptrwrbl.receptionist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pollub.cs.ptrwrbl.receptionist.services.FileStorageServiceImpl;

@RequiredArgsConstructor
@RestController
public class FileStorageController {
    private final FileStorageServiceImpl fileService;
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String imagePath = fileService.store(file);
        return new ResponseEntity<>(imagePath, HttpStatus.CREATED);
    }
}