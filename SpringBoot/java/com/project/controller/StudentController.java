package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exception.ResourceNotFoundException;
import com.project.model.Student;
import com.project.repository.StudentRepository;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/s1/")
public class StudentController {
	@Autowired
	private StudentRepository resp;
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return resp.findAll();
	}
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return resp.save(student);
	}
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Student student=resp.findById(id).
				orElseThrow( ()-> new ResourceNotFoundException("Student not exist with id : "+id));
		return ResponseEntity.ok(student);
	}
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student studentDetails){
		Student student=resp.findById(id).
				orElseThrow( ()-> new ResourceNotFoundException("Student not exist with id : "+id));
		student.setName(studentDetails.getName());
		student.setPhoneno(studentDetails.getPhoneno());
		student.setEmail(studentDetails.getEmail());
		Student updateStudent=resp.save(student);
		return ResponseEntity.ok(updateStudent);
	}
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteStudent(@PathVariable int id){
		Student student=resp.findById(id).
				orElseThrow( ()-> new ResourceNotFoundException("Student not exist with id : "+id));
		resp.delete(student);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
