package services;

import java.util.ArrayList;

import entities.Class;
import entities.Teacher.Teacher;
import repositories.TeacherClassRepository;
import repositories.TeacherRepository;

public class TeacherService {
    private static TeacherService instance;
    
    private final TeacherRepository teacherRepository;
    private final TeacherClassRepository teacherClassRepository;

    public static TeacherService getInstance () {
        if (instance == null) {
            instance = new TeacherService();
        }

        return instance;
    }
    
    public TeacherService () {
        this.teacherRepository = new TeacherRepository();
        this.teacherClassRepository = new TeacherClassRepository();
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

    public String getAllTeachersSerialized () {
        String teachers = this.getAllTeachers()
            .stream()
            .map(Object::toString)
            .reduce((acc, crt) -> acc + "\n" + crt)
            .orElse("There are no teachers!");

        return teachers;
    }

    public boolean doesTeacherExist (int id) {
        return this.getTeacherById(id) != null;
    }

    public void assignClassToTeacher (int teacherId, int classId) {
        boolean isOk = this.teacherClassRepository.insertOne(teacherId, classId);
        if (isOk) {
            System.out.println("The class has been assigned successfully!");
        } else {
            System.out.println(String.format("An error occurred while trying to assign a class(ID = %S) to a teacher(ID = %)", classId, teacherId));
        }
    }

    public ArrayList<Class> getAssignedClasses (int teacherId) {
        return this.teacherClassRepository.getAllByTeacherId(teacherId);
    }
}
