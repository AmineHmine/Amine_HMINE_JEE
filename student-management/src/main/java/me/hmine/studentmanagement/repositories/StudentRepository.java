package me.hmine.studentmanagement.repositories;

import me.hmine.studentmanagement.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findStudentByNomContains(String key, Pageable pageable);
}
