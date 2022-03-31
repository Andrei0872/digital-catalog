package entities.Teacher;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import entities.Class;
import entities.SchoolPerson;
import entities.Student.Student;

public class Teacher extends SchoolPerson {
  private Set<Integer> classesIds = new HashSet<Integer>();

  public Teacher(String name, int age, String email) {
    super(name, age, email);
  }

  public void addClassById(int classId) {
    classesIds.add(classId);
  }

  public Set<Integer> getClassesIds () {
    return classesIds;
  }

  public void assignGradeToStudent(int gradeValue, LocalDate gradeDate, Class cls, Student student) throws Exception {
    boolean teacherHasClass = classesIds.contains(cls.getId());
    if (!teacherHasClass) {
      String errMessage = String.format("The teacher(%s) does not have this class(%s)!", this.toString(), cls.toString());
      throw new Exception(errMessage);
    }

    if (!cls.hasStudent(student)) {
      String errMessage = String.format("The student(%s) does not belong to this class(%s)!", student.toString(), cls.toString());
      throw new Exception(errMessage);
    }

    student.assignGrade(gradeValue, gradeDate, this.getId(), cls.getId());
  };
}
