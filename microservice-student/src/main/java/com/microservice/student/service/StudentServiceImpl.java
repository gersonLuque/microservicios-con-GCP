package com.microservice.student.service;

import com.microservice.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return studentRepository.findAllByCourseId(idCourse);
    }
}
