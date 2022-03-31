package entities;

import java.util.HashSet;
import java.util.Set;

import entities.Student.Student;
import utils.IHasIdentifier;

public class Class implements IHasIdentifier {
  private Set<Integer> studentsIDs = new HashSet<Integer>();

  private static int IDs = 0;
  private final int ID;

  private final String subject;

  public Class(String subject) {
    IDs++;
    this.ID = IDs;

    this.subject = subject;
  }

  public String getSubject () {
    return subject;
  }

  @Override
  public int getId() {
    return ID;
  }

  @Override
  public String toString() {
  return String.format("Class ID: %s, Subject: %s", ID, this.subject);
  }

  public void addStudentById(int studentId) {
    studentsIDs.add(studentId);
  };

  public boolean hasStudent (Student student) {
    return studentsIDs.contains(student.getId());
  }
}
