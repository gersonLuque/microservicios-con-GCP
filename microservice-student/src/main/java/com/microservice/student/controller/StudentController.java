package com.microservice.student.controller;

//import com.microservice.student.entities.Student;
import com.microservice.student.service.GoogleCloudStorageService;
//import com.microservice.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "https://my-node-app-uqo7ih4ihq-uc.a.run.app")
@RestController
@RequestMapping("/api/student")
public class StudentController {
//    @Autowired
//    private IStudentService studentService;
//
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    private void saveStudent(@RequestBody Student student){
//        studentService.save(student);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<?> findAllStudent(){
//        return ResponseEntity.ok(studentService.findAll());
//    }
//
//    @GetMapping("search/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id){
//        return ResponseEntity.ok(studentService.findById(id));
//    }
//
//    @GetMapping("/search-by-course/{idCourse}")
//    public ResponseEntity<?> findByIdCourse(@PathVariable Long idCourse){
//        return ResponseEntity.ok(studentService.findByCourseId(idCourse));
//    }
    @GetMapping("/file/{fileName}")
    public ResponseEntity<InputStreamResource> getStudentFile(@PathVariable String fileName) throws IOException {

        GoogleCloudStorageService googleCloudStorageService = new GoogleCloudStorageService();
        String destFilePath = "/tmp/" + fileName;

        try  {
            InputStream inputStream = googleCloudStorageService.obtenerArchivoComoInputStream(fileName);
            InputStreamResource resource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {

            // Subir el archivo a Google Cloud Storage
            GoogleCloudStorageService googleCloudStorageService = new GoogleCloudStorageService();
            googleCloudStorageService.uploadFile(file);

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<String>> listFiles() throws IOException {
        GoogleCloudStorageService googleCloudStorageService = new GoogleCloudStorageService();
        List<String> fileNames = googleCloudStorageService.listFiles();
        return ResponseEntity.ok(fileNames);
    }
}
