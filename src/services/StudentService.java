package services;

import java.util.ArrayList;

import entities.Student.Student;
import repositories.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;
    
    public StudentService () {
        this.studentRepository = new StudentRepository();
    }

    public void insertStudent (Student t) {
        boolean isOk = studentRepository.insertOne(t);
        System.out.println(isOk);
    }

    public ArrayList<Student> getAllStudents () {
        return studentRepository.getAll();
    }

    public void deleteStudent (Student t) {
        boolean isOk = studentRepository.deleteOne(t);
        System.out.println(isOk);
    }

    public void updateStudentById (int studentId, Student newStudent) {
        boolean isOk = studentRepository.updateOneById(studentId, newStudent);
        System.out.println(isOk);
    }
}
