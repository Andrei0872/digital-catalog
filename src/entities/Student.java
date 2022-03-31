package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends SchoolPerson {
  private List<Grade> grades = new ArrayList<Grade>();
  
  public Student(String name, int age, String email) {
    super(name, age, email);
  }

  public void assignGrade(int gradeValue, LocalDate gradeDate, int teacherId, int classId) {
    grades.add(new Grade(gradeValue, gradeDate, teacherId, classId));
  };
}
