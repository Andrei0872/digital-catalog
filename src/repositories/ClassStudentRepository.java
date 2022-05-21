package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database;

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

}
