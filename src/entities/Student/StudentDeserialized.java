package entities.Student;

import java.util.Collection;
import entities.Grade.GradeDeserialized;

public record StudentDeserialized (Student student, Collection<GradeDeserialized> grades) {
  @Override
  public String toString () {
    String result = student.toString() + "\n";

    for (GradeDeserialized grade : grades) {
      // result += String.format("\t %s, assigned by %s, at %s, subject: %s\n", grade.grade().value(), grade.teacher().toString(), grade.grade().assigned_at(), grade.cls().getSubject());
    }

    return result;
  }
}
