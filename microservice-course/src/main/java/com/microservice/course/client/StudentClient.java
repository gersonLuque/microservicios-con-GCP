package com.microservice.course.client;

import com.microservice.course.dto.StudentDTO;
import org.apache.catalina.LifecycleState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="msvc-student", url = "localhost:8090/api/student") // nombre del microservicio que vamos a consultar
public interface StudentClient {
    @GetMapping("/search-ny-course/{idCourse}") // peticion http
    List<StudentDTO> findAllStudentByCourse(@PathVariable Long idCourse);
 }
