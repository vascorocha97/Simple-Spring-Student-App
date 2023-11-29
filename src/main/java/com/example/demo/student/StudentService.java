package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
       if(studentOptional.isPresent()){
           throw new IllegalStateException("email taken");
       }
       studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException("student does not exist");
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student does not exist"));
        if (name!=null && !name.isEmpty() && !Objects.equals(foundStudent.getName(), name)){
            foundStudent.setName(name);
        }
        if (email!=null && !email.isEmpty() && !Objects.equals(foundStudent.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            foundStudent.setEmail(email);
        }
    }
}
