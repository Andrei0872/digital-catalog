package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Database;
import entities.TeacherClass;
import entities.Student.Student;

public class ClassStudentRepository {
    public boolean insertOne (int teacherId, int classId, int studentId) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO class_student (teacher_id, class_id, student_id) values (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, teacherId);
            stmt.setInt(2, classId);
            stmt.setInt(3, studentId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TeacherClass> getAllByStudentId (int studentId) {
        Connection conn = Database.getConnection();
        String stmtString = """
            select *, c.id as class_id, t.id as teacher_id
            from class_student cs
            join class c
                on c.id = cs.class_id
            join teacher t
                on t.id = cs.teacher_id
            where cs.student_id = ?
        """;

        ArrayList<TeacherClass> studentClasses = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);
            
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                studentClasses.add(TeacherClassRepository.convertResultSetToTeacherClass(rs));
            }

            return studentClasses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Student> getAllByClass (int teacherId, int classId) {
        Connection conn = Database.getConnection();
        String stmtString = """
            select s.*
            from class_student cs
            join student s
                on s.id = cs.student_id
            where cs.teacher_id = ? and cs.class_id = ?
        """;;

        ArrayList<Student> students = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);
            
            stmt.setInt(1, teacherId);
            stmt.setInt(2, classId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(StudentRepository.convertResultSetToStudent(rs));
            }

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
