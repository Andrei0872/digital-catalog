package services;

import java.util.ArrayList;
import java.util.List;

import entities.Teacher.Teacher;
import repositories.TeacherRepository;

public class TeacherService {
    private final TeacherRepository teacherRepository;
    
    public TeacherService () {
        this.teacherRepository = new TeacherRepository();
    }

    public void insertTeacher (Teacher t) {
        boolean isOk = teacherRepository.insertOne(t);
        System.out.println(isOk);
    }

    public ArrayList<Teacher> getAllTeachers () {
        return teacherRepository.getAll();
    }

    public void deleteTeacher (Teacher t) {
        boolean isOk = teacherRepository.deleteOne(t);
        System.out.println(isOk);
    }

    public void updateTeacherById (int teacherId, Teacher newTeacher) {
        boolean isOk = teacherRepository.updateOneById(teacherId, newTeacher);
        System.out.println(isOk);
    }
}
