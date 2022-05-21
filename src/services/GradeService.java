package services;

import java.util.ArrayList;

import entities.Grade.Grade;
import repositories.GradeRepository;


public class GradeService {
    private final GradeRepository gradeRepository;
    
    private static GradeService instance;
    
    public static GradeService getInstance () {
        if (instance == null) {
            instance = new GradeService();
        }

        return instance;
    }

    public GradeService () {
        this.gradeRepository = new GradeRepository();
    }

    public int insertGrade (Grade t) {
        return gradeRepository.insertOne(t);
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
