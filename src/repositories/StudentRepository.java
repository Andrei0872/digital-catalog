package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Database;
import entities.Student.Student;

public class StudentRepository {
    public boolean insertOne (Student st) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO student (email, name, age) values (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, st.getEmail());
            stmt.setString(2, st.getName());
            stmt.setInt(3, st.getAge());

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

    public Student getOneById (int id) {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from student where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return convertResultSetToStudent(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Student> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from student";

        ArrayList<Student> students = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(stmtString);

            while (rs.next()) {
                students.add(convertResultSetToStudent(rs));
            }

            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOne (Student st) {
        try {
            if (!st.hasIdSet()) {
                throw new Exception("This student can't be deleted - it has no ID set!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        Connection conn = Database.getConnection();
        String stmtString = "DELETE FROM student where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, st.getId());

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

    public boolean updateOneById (int studentId, Student newStudent) {
        Connection conn = Database.getConnection();
        String stmtString = "UPDATE student SET email = ?, name = ?, age = ? where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, newStudent.getEmail());
            stmt.setString(2, newStudent.getName());
            stmt.setInt(3, newStudent.getAge());
            stmt.setInt(4, studentId);

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

    public static Student convertResultSetToStudent (ResultSet rs) throws SQLException {
        Student st = new Student(rs.getString("name"), rs.getInt("age"), rs.getString("email"));
        st.setId(rs.getInt("id"));

        return st;
    }
}
