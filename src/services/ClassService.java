package services;

import java.util.ArrayList;

import entities.Class;
import entities.TeacherClass;
import entities.Student.Student;
import repositories.ClassRepository;
import repositories.ClassStudentRepository;

public class ClassService {
    private final ClassRepository classRepository;
    private final ClassStudentRepository classStudentRepository;
    
    private static ClassService instance;
    
    public static ClassService getInstance () {
        if (instance == null) {
            instance = new ClassService();
        }

        return instance;
    }

    public ClassService () {
        this.classRepository = new ClassRepository();
        this.classStudentRepository = new ClassStudentRepository();
    }

    public void insertClass (Class cls) {
        boolean isOk = classRepository.insertOne(cls);
        System.out.println(isOk);
    }

    public ArrayList<Class> getAllClasses () {
        return classRepository.getAll();
    }

    public void deleteClass (Class cls) {
        boolean isOk = classRepository.deleteOne(cls);
        System.out.println(isOk);
    }

    public void updateClassById (int classId, Class newClass) {
        boolean isOk = classRepository.updateOneById(classId, newClass);
        System.out.println(isOk);
    }

    public Class getClassById (int id) {
        return classRepository.getOneById(id);
    }

    public String getAllClassesSerialized () {
        String classes = this.serializeClasses(this.getAllClasses());

        return classes;
    }

    public boolean doesClassExist (int id) {
        return this.getClassById(id) != null;
    }

    public String serializeClasses (ArrayList<Class> classes) {
        if (classes == null) {
            return "";
        }
        
        return classes
            .stream()
            .map(Object::toString)
            .reduce((acc, crt) -> acc + "\n" + crt)
            .orElse("There are no classes!");
    }

    public void addStudentToClass (int teacherId, int classId, int studentId) {
        boolean isOk = this.classStudentRepository.insertOne(teacherId, classId, studentId);
        if (isOk) {
            System.out.println("The student has been successfully added to the class!");
        } else {
            String msg = "A problem occurred while trying to add a student(ID = %s) to a class(Teacher ID = %s, Class ID = %s)";
            System.out.println(String.format(msg, studentId, teacherId, classId));
        }
    }

    public ArrayList<TeacherClass> getStudentClasses (int studentId) {
        return this.classStudentRepository.getAllByStudentId(studentId);
    }

    public ArrayList<Student> getStudentsFromClass (int teacherId, int classId) {
        return this.classStudentRepository.getAllByClass(teacherId, classId);
    }
}
