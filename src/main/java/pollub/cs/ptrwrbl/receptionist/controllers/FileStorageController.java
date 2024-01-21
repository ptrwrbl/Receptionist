package pollub.cs.ptrwrbl.receptionist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pollub.cs.ptrwrbl.receptionist.services.FileStorageServiceImpl;

@RequiredArgsConstructor
@RequestMapping({"/files"})
@RestController
public class FileStorageController {
    private final FileStorageServiceImpl fileService;
    @PostMapping(name = "/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam("name") String name) {
        String imagePath = fileService.store(file);
        System.out.println("hrhe");
        return new ResponseEntity<>(imagePath, HttpStatus.CREATED);
    }
}