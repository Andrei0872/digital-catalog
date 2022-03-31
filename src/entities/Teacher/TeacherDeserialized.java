package entities.Teacher;

import java.util.Collection;

import entities.Class;

public record TeacherDeserialized(Teacher teacher, Collection<Class> classes) {
  @Override
  public String toString () {
    String teacherStr = teacher.toString() + "\n";

    for (Class cls : classes) {
      teacherStr += "\t" + cls.toString() + "\n";
    }

    return teacherStr;
  }
}
