package cf.edof.lab.firstspring.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cf.edof.lab.firstspring.demo.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	List<Student> findByName(String name);
}