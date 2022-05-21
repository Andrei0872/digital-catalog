package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.Database;
import entities.Grade.Grade;

public class GradeRepository {
    public boolean insertOne (Grade gr) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO grade (value, inserted_at, modified_at) values (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);
            
            var now = LocalDate.now();

            stmt.setInt(1, gr.getValue());
            stmt.setObject(2, now);
            stmt.setObject(3, now);

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

    public ArrayList<Grade> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from grade";

        ArrayList<Grade> grades = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(stmtString);

            while (rs.next()) {
                grades.add(convertResultSetToGrade(rs));
            }

            return grades;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOne (Grade gr) {
        Connection conn = Database.getConnection();
        String stmtString = "DELETE FROM grade where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, gr.getId());

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

    public boolean updateOneById (int gradeId, Grade newGrade) {
        Connection conn = Database.getConnection();
        String stmtString = "UPDATE grade SET value = ?, modified_at = ? where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            var now = LocalDate.now();

            stmt.setInt(1, newGrade.getValue());
            stmt.setObject(2, now);
            stmt.setInt(3, newGrade.getId());

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

    private Grade convertResultSetToGrade (ResultSet rs) throws SQLException {
        Grade gr = new Grade(rs.getInt("value"), rs.getString("inserted_at"), rs.getString("modified_at"));
        gr.setId(rs.getInt("id"));

        return gr;
    }
}
