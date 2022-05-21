package services;

import java.util.ArrayList;

import entities.Student.Student;
import repositories.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;
    
    private static StudentService instance;
    
    public static StudentService getInstance () {
        if (instance == null) {
            instance = new StudentService();
        }

        return instance;
    }

    public StudentService () {
        this.studentRepository = new StudentRepository();
    }

    public void insertStudent (Student st) {
        boolean isOk = studentRepository.insertOne(st);
        if (isOk) {
            System.out.println("Student successfully inserted!");
        } else {
            System.out.println(String.format("A problem occurred while adding the student(%s)!", st));
        }
    }

    public ArrayList<Student> getAllStudents () {
        return studentRepository.getAll();
    }

    public void deleteStudent (Student st) {
        boolean isOk = studentRepository.deleteOne(st);
        if (isOk) {
            System.out.println("Student successfully deleted!");
        } else {
            System.out.println(String.format("A problem occurred while deleting the student(%s)!", st));
        }
    }

    public void updateStudentById (int studentId, Student newStudent) {
        boolean isOk = studentRepository.updateOneById(studentId, newStudent);
        if (isOk) {
            System.out.println("Student successfully updated!");
        } else {
            System.out.println(String.format("A problem occurred while updating the student(%s)!", newStudent));
        }
    }

    public Student getStudentById (int id) {
        return studentRepository.getOneById(id);
    }
}
