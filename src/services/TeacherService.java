package services;

import java.util.ArrayList;

import entities.Teacher.Teacher;
import repositories.TeacherRepository;

public class TeacherService {
    private static TeacherService instance;
    
    private final TeacherRepository teacherRepository;

    public static TeacherService getInstance () {
        if (instance == null) {
            instance = new TeacherService();
        }

        return instance;
    }
    
    public TeacherService () {
        this.teacherRepository = new TeacherRepository();
    }

    public void insertTeacher (Teacher t) {
        boolean isOk = teacherRepository.insertOne(t);
        if (isOk) {
            System.out.println("Teacher successfully inserted!");
        } else {
            System.out.println(String.format("A problem occurred while adding the teacher(%s)!", t));
        }
    }

    public ArrayList<Teacher> getAllTeachers () {
        return teacherRepository.getAll();
    }

    public void deleteTeacher (Teacher t) {
        boolean isOk = teacherRepository.deleteOne(t);
        if (isOk) {
            System.out.println("Teacher successfully deleted!");
        } else {
            System.out.println(String.format("A problem occurred while deleting the teacher(%s)!", t));
        }
    }

    public void updateTeacherById (int teacherId, Teacher newTeacher) {
        boolean isOk = teacherRepository.updateOneById(teacherId, newTeacher);
        if (isOk) {
            System.out.println("Teacher successfully updated!");
        } else {
            System.out.println(String.format("A problem occurred while updating the teacher(%s)!", newTeacher));
        }
    }

    public Teacher getTeacherById (int id) {
        return teacherRepository.getOneById(id);
    }
}
