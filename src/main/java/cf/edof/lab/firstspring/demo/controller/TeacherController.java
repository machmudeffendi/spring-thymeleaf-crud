package cf.edof.lab.firstspring.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.edof.lab.firstspring.demo.entity.Teacher;
import cf.edof.lab.firstspring.demo.repository.TeacherRepository;

@Controller
@RequestMapping("/teachers/")
public class TeacherController{

	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherController(TeacherRepository teacherRepository){
		this.teacherRepository = teacherRepository;
	}

	@GetMapping("teacher-signup")
	public String showTeachSignUpForm(Teacher teacher){
		return "add-teacher";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model){
		model.addAttribute("teachers",teacherRepository.findAll());
		return "list-teacher";
	}

	@PostMapping("add")
	public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "add-teacher";
		}

		teacherRepository.save(teacher);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model){
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Teacher id:"+id));
		model.addAttribute("teachers",teacher);
		return "update-teacher";
	}

	@PostMapping("update/{id}")
	public String updateTeacher(@PathVariable("id") long id, @Valid Teacher teacher, BindingResult result, Model model){
		if (result.hasErrors()) {
			teacher.setId(id);
			return "update-teacher";
		}

		teacherRepository.save(teacher);
		model.addAttribute("teachers", teacherRepository.findAll());
		return "list-teacher";
	}

	@GetMapping("delete/{id}")
	public String deleteTeacher(@PathVariable("id") long id, Model model){
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid teacher id:"+id));
		teacherRepository.delete(teacher);
		model.addAttribute("teachers",teacherRepository.findAll());
		return "list-teacher";
	}
}