package pollub.cs.ptrwrbl.receptionist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pollub.cs.ptrwrbl.receptionist.services.FileStorageServiceImpl;

@RequiredArgsConstructor
@RequestMapping({"/assets"})
@RestController
@CrossOrigin
public class FileStorageController {
    private final FileStorageServiceImpl fileService;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serve(@PathVariable String filename) {
        Resource image = fileService.serve(filename);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).body(image);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String imagePath = fileService.store(file);
        return new ResponseEntity<>(imagePath, HttpStatus.CREATED);
    }
}