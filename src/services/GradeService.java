package services;

import java.util.ArrayList;

import entities.Grade.Grade;
import repositories.GradeRepository;


public class GradeService {
    private final GradeRepository gradeRepository;
    
    public GradeService () {
        this.gradeRepository = new GradeRepository();
    }

    public void insertGrade (Grade t) {
        boolean isOk = gradeRepository.insertOne(t);
        System.out.println(isOk);
    }

    public ArrayList<Grade> getAllGrades () {
        return gradeRepository.getAll();
    }

    public void deleteGrade (Grade t) {
        boolean isOk = gradeRepository.deleteOne(t);
        System.out.println(isOk);
    }

    public void updateGradeById (int gradeId, Grade newGrade) {
        boolean isOk = gradeRepository.updateOneById(gradeId, newGrade);
        System.out.println(isOk);
    }
}
