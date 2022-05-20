package services;

import java.util.ArrayList;

import entities.Class;
import repositories.ClassRepository;

public class ClassService {
    private final ClassRepository classRepository;
    
    public ClassService () {
        this.classRepository = new ClassRepository();
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
}
