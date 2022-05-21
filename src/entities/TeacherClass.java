package entities;

public record TeacherClass (int teacherId, String teacherName, int classId, String classSubject) {
    @Override
    public String toString() {
        return String.format("Teacher ID: %s, Teacher name: %s, Class ID: %s, Subject: %s", teacherId, teacherName, classId, classSubject);
    }
}
