package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

public class StudentGradeRepository {
    public boolean insertOne (int teacherId, int classId, int studentId, int gradeId) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO student_grade (teacher_id, class_id, student_id, grade_id) values (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, teacherId);
            stmt.setInt(2, classId);
            stmt.setInt(3, studentId);
            stmt.setInt(4, gradeId);

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

}
