package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Class;
import entities.Grade.GradeDeserialized;
import entities.Student.Student;
import entities.Student.StudentDeserialized;
import entities.Teacher.Teacher;
import entities.Teacher.TeacherDeserialized;

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

  public TeacherDeserialized getTeacherDeserialized (int teacherId) {
    Teacher teacher = teachers.get(teacherId);
    Collection<Class> classes = teacher.getClassesIds().stream().map(classId -> this.classes.get(classId)).toList();

    return new TeacherDeserialized(teacher, classes);
  }

  // public StudentDeserialized getStudentDeserialized (int studentId) {
  //   Student student = students.get(studentId);
  //   Collection<GradeDeserialized> grades = student.getGrades()
  //     .stream()
  //     // .map(
  //       // grade -> new GradeDeserialized(grade, this.teachers.get(grade.assignedBy()), this.classes.get(grade.classId()))
  //     // )
  //     .toList();

  //   return new StudentDeserialized(student, grades);
  // }

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
    if (!doesStudentExist) {
      this.students.put(studentId, student);
    }
  };

  private void ensureTeacherExists (Teacher teacher) {
    int teacherId = teacher.getId();
    boolean doesTeacherExist = teachers.containsKey(teacherId);
    if (!doesTeacherExist) {
      this.teachers.put(teacherId, teacher);
    }
  }
}
