package services;

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

}
