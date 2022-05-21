package services;

import java.util.ArrayList;

import entities.StudentGrade;
import entities.Student.Student;
import repositories.StudentGradeRepository;
import repositories.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentGradeRepository studentGradeRepository;
    
    private static StudentService instance;
    
    public static StudentService getInstance () {
        if (instance == null) {
            instance = new StudentService();
        }

        return instance;
    }

    public StudentService () {
        this.studentRepository = new StudentRepository();
        this.studentGradeRepository = new StudentGradeRepository();
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

    public String getAllStudentsSerialized () {
        String teachers = this.getAllStudents()
            .stream()
            .map(Object::toString)
            .reduce((acc, crt) -> acc + "\n" + crt)
            .orElse("There are no students!");

        return teachers;
    }

    public boolean doesStudentExist (int id) {
        return this.getStudentById(id) != null;
    }

    public void assignGradeToStudent (int teacherId, int classId, int studentId, int gradeId) {
        boolean isOk = this.studentGradeRepository.insertOne(teacherId, classId, studentId, gradeId);
        if (isOk) {
            System.out.println("The grade has been successfully assigned to the student!");
        } else {
            String msg = String.format("An error occurred while trying to assign a grade(ID = %s) to a student(ID = %) from a class(Teacher = %s, Class = %s)", gradeId, studentId, teacherId, classId);
            System.out.println(msg);
        }
    }

    public ArrayList<StudentGrade> getStudentGrades (int studentId) {
        return this.studentGradeRepository.getAllByStudentId(studentId);
    }
}
