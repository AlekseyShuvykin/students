package telran.sjava45.service;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import telran.sjava45.dto.ScoreDto;
import telran.sjava45.dto.StudentCreateDto;
import telran.sjava45.dto.StudentDto;
import telran.sjava45.dto.StudentUpdateDto;
import telran.sjava45.dto.exceptions.StudentNotFoundException;
import telran.sjava45.student.dao.StudentRepository;
import telran.sjava45.student.model.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	final	StudentRepository studentRepository;
	final   ModelMapper modelMapper;
	
	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
	if ( studentRepository.findById(studentCreateDto.getId()).isPresent()) {
		return false;
	}
//	Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(), studentCreateDto.getPassword());

	Student student = modelMapper.map(studentCreateDto, Student.class);
	studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {	
	Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));

	StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getScores());
	return studentDto;
	}

	@Override
	public StudentDto removeStudent(Integer id) {
	Student student =	studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));

	StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getScores());
	studentRepository.deleteById(id);
	return studentDto;
	}

	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		
	if(studentUpdateDto.getName() != null) {
		student.setName(studentUpdateDto.getName());
	}
	if(studentUpdateDto.getPassword() != null) {
		student.setName(studentUpdateDto.getPassword());
	}

	studentRepository.save(student);
	return modelMapper.map(student, StudentCreateDto.class);
	//		return StudentCreateDto.builder()
//				.id(id)
//				.name(student.getName())
//				.password(student.getPassword())
//				.build();
	}
	

	@Override
	public Boolean addScored(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
	
		student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return true ;
	}

	@Override
	public List<StudentDto> findStudentByName(String name) {
		return studentRepository.findByNameIgnoreCase(name)
				.map(s -> modelMapper.map(s, StudentDto.class))
				//			.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores())) 
				.collect(Collectors.toList());	
	//	return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
	//			.filter(s -> name.equalsIgnoreCase(s.getName()))
	//			.map(s -> new StudentDto(s.getId(),s.getName(),s.getScores()))
	//			.collect(Collectors.toList());
		
	}

	
	
	
	
	@Override
	public Long getStudentNamesQuantity(List<String> names) {
//		//TODO Homework
		return studentRepository.countByNameInIgnoreCase(names);
//		return StreamSupport.stream(studentRepository.findAll().spliterator(),false)
//				.filter(s-> names.contains(s.getName()))
//				.count();
	}

	
	
	
	@Override
	public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
		return studentRepository.findByExamAndScoreGreaterThan(exam, minScore)
				.map(s -> modelMapper.map(s, StudentDto.class))
				//.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores())) 
				.collect(Collectors.toList());
		}
		
		//	return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
	//	.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > minScore)
	//	.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
	//	.collect(Collectors.toList());

	
	
	
	
	
	
	
	
	
	
	
}
