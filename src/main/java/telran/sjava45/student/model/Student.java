package telran.sjava45.student.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

///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//password git hub
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Student {
	@Id
	int id;
	@Setter
	String name;
	@Setter
	String password;
	Map<String, Integer> scores = new HashMap<>();
	
	public Student(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Boolean addScore(String exam, Integer score) {
		return scores.put(exam, score) == null;
	}
}
