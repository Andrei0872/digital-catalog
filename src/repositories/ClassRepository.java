package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Database;
import entities.Class;

public class ClassRepository {
    public boolean insertOne (Class cls) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO class (subject) values (?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, cls.getSubject());

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

    public ArrayList<Class> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = "SELECT * from class";

        ArrayList<Class> classes = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(stmtString);

            while (rs.next()) {
                classes.add(convertResultSetToClass(rs));
            }

            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOne (Class cls) {
        Connection conn = Database.getConnection();
        String stmtString = "DELETE FROM class where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, cls.getId());

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

    public boolean updateOneById (int classId, Class newClass) {
        Connection conn = Database.getConnection();
        String stmtString = "UPDATE class SET subject = ? where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setString(1, newClass.getSubject());
            stmt.setInt(2, classId);

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

    private Class convertResultSetToClass (ResultSet rs) throws SQLException {
        Class cls = new Class(rs.getString("subject"));
        cls.setId(rs.getInt("id"));

        return cls;
    }
}
