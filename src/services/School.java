package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Class;
import entities.Student;
import entities.Teacher;

public class School {
  private final String name;

  private Map<Integer, Class> classes = new HashMap<Integer, Class>();
  private Map<Integer, Teacher> teachers = new HashMap<Integer, Teacher>();
  private Map<Integer, Student> students = new HashMap<Integer, Student>();

  public School(String name) {
    this.name = name;
  }

  public String getName () {
    return this.name;
  }

  public void addStudentToClass(Student student, Class cls) {
    this.ensureClassExists(cls);
    this.ensureStudentExists(student);

    int studentId = student.getId();
    cls.addStudentById(studentId);
  };

  public void addTeacherToClass(Teacher teacher, Class cls) {
    this.ensureTeacherExists(teacher);
    this.ensureClassExists(cls);

    teacher.addClassById(cls.getId());
  };

  public Collection<Teacher> getTeachers () {
    return teachers.values();
  }

  public Collection<Student> getStudents() {
    return students.values();
  }

  public Collection<Class> getClasses() {
    return classes.values();
  }


  private void ensureClassExists (Class cls) {
    int classId = cls.getId();
    boolean doesClassExist = classes.containsKey(classId);
    if (!doesClassExist) {
      classes.put(cls.getId(), cls);
    }
  };

  private void ensureStudentExists (Student student) {
    int studentId = student.getId();
    boolean doesStudentExist = students.containsKey(studentId);
    if (doesStudentExist) {
      this.students.put(studentId, student);
    }
  };

  private void ensureTeacherExists (Teacher teacher) {
    int teacherId = teacher.getId();
    boolean doesTeacherExist = teachers.containsKey(teacherId);
    if (doesTeacherExist) {
      this.teachers.put(teacherId, teacher);
    }
  }
}
