package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public Teacher getOneById (int id) {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from teacher where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return convertResultSetToTeacher(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Teacher> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from teacher";

        ArrayList<Teacher> teachers = new ArrayList<>();

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

    public boolean deleteOne (Teacher t) {
        try {
            if (!t.hasIdSet()) {
                throw new Exception("This teacher can't be deleted - it has no ID set!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        Connection conn = Database.getConnection();
        String stmtString = "DELETE FROM teacher where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, t.getId());

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

    public boolean updateOneById (int teacherId, Teacher newTeacher) {
        Connection conn = Database.getConnection();
        String stmtString = "UPDATE teacher SET email = ?, name = ?, age = ? where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, newTeacher.getEmail());
            stmt.setString(2, newTeacher.getName());
            stmt.setInt(3, newTeacher.getAge());
            stmt.setInt(4, teacherId);

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

    private Teacher convertResultSetToTeacher (ResultSet rs) throws SQLException {
        Teacher t = new Teacher(rs.getString("name"), rs.getInt("age"), rs.getString("email"));
        t.setId(rs.getInt("id"));

        return t;
    }
}
