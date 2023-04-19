package telran.sjava45.service;

import java.util.List;

import telran.sjava45.dto.ScoreDto;
import telran.sjava45.dto.StudentCreateDto;
import telran.sjava45.dto.StudentDto;
import telran.sjava45.dto.StudentUpdateDto;

public interface StudentService {
Boolean addStudent(StudentCreateDto studentCreateDto);

StudentDto findStudent(Integer id);

StudentDto removeStudent(Integer id);

StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

Boolean addScored(Integer id, ScoreDto scoreDto);

List<StudentDto> findStudentByName(String name);

Long getStudentNamesQuantity(List<String> names);

List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore);





}
