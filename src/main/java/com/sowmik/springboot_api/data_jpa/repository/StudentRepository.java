package com.sowmik.springboot_api.data_jpa.repository;

import com.sowmik.springboot_api.data_jpa.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//<Entity name, primary key's type>
public interface StudentRepository extends JpaRepository<Student, Long> {
}
