package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import entities.Teacher.Teacher;

public class TeacherRepository {
    public boolean insertOne (Teacher t) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO teacher (email, name, age) values (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, t.getEmail());
            stmt.setString(2, t.getName());
            stmt.setInt(3, t.getAge());

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

    public List<Teacher> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from teacher";

        List<Teacher> teachers = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(stmtString);

            while (rs.next()) {
                teachers.add(convertResultSetToTeacher(rs));
            }

            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Teacher convertResultSetToTeacher (ResultSet rs) throws SQLException {
        Teacher t = new Teacher(rs.getString("name"), rs.getInt("age"), rs.getString("email"));
        t.setId(rs.getInt("id"));

        return t;
    }
}
