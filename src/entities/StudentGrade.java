package entities;


public record StudentGrade (String teacherName, String studentName, int gradeValue, String classSubject, String gradeInsertedAt) {
    @Override
    public String toString() {
        return String.format("%s on %s from %s, added at %s", gradeValue, classSubject, teacherName, gradeInsertedAt);
    }
}
