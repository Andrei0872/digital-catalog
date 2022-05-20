package entities;

import java.util.HashSet;
import java.util.Set;

import entities.Student.Student;
import utils.IHasIdentifier;

public class Class implements IHasIdentifier {
  private Set<Integer> studentsIDs = new HashSet<Integer>();

  private int ID = -1;

  private String subject;

  public Class(String subject) {
    this.subject = subject;
  }

  public String getSubject () {
    return subject;
  }

  public void setSubject (String newSubject) {
    this.subject = newSubject;
  }

  @Override
  public int getId() {
    return ID;
  }

  public void setId (int newId) {
    this.ID = newId;
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
