package telran.sjava45.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import telran.sjava45.dto.ScoreDto;
import telran.sjava45.dto.StudentCreateDto;
import telran.sjava45.dto.StudentDto;
import telran.sjava45.dto.StudentUpdateDto;
import telran.sjava45.service.StudentService;


@RestController
@RequiredArgsConstructor
public class StudentController {
	
	final StudentService studentService;

	@PostMapping("/student")
	public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
		return studentService.addStudent(studentCreateDto);
	}
	
	@GetMapping("/student/{id}")
	public StudentDto findStudent(@PathVariable Integer id) {
		return studentService.findStudent(id);		
	}
	
	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.removeStudent(id);
	}
	
	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScored(id, scoreDto);
	}
	
	@GetMapping("/students/name/{name}")
	public List<StudentDto> findStudentById(@PathVariable String name){
			return studentService.findStudentByName(name);
		}
	
	@PutMapping("/student/{id}")
	public StudentCreateDto editStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
		return studentService.updateStudent(id, studentUpdateDto);
	}
	
	@PostMapping("/quantity/students")
	public Long getStudentsNamesQuantity(@RequestBody List<String> names) {
		return studentService.getStudentNamesQuantity(names);
	}
	
	@GetMapping("/students/exam/{exam}/minscore/{minScore}")
	List<StudentDto> getStudentsByMinScore(@PathVariable String exam,@PathVariable Integer minScore ){
		return studentService.getStudentsByExamMinScore(exam, minScore);
	}
}

