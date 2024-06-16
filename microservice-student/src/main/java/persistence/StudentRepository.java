package persistence;

import com.microservice.student.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findAllByCourseId(Long idCourse);
}
