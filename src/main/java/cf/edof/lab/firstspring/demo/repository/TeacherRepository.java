package cf.edof.lab.firstspring.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cf.edof.lab.firstspring.demo.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	List<Teacher> findByName(String name);
}
