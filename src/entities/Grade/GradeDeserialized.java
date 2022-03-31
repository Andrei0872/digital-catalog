package entities.Grade;

import entities.Teacher.Teacher;
import entities.Class;

public record GradeDeserialized(Grade grade, Teacher teacher, Class cls) {}
