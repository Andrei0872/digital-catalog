package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Database;
import entities.Class;
import entities.TeacherClass;

public class TeacherClassRepository {
    public boolean insertOne (int teacherId, int classId) {
        Connection conn = Database.getConnection();
        String stmtString = "INSERT INTO teacher_class (teacher_id, class_id) values (?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);

            stmt.setInt(1, teacherId);
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

    public ArrayList<Class> getAllByTeacherId (int teacherId) {
        Connection conn = Database.getConnection();
        String stmtString = """
            select c.*
            from teacher_class tc
            join class c
                on c.id = tc.class_id
            where tc.teacher_id = ?;
        """;

        ArrayList<Class> classes = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);
            
            stmt.setInt(1, teacherId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                classes.add(ClassRepository.convertResultSetToClass(rs));
            }

            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<TeacherClass> getAll () {
        Connection conn = Database.getConnection();
        String stmtString = """
            select *, c.id as class_id, t.id as teacher_id
            from teacher_class tc
            join class c
                on c.id = tc.class_id
            join teacher t
                on t.id = tc.teacher_id
        """;

        ArrayList<TeacherClass> teachersClasses = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
        
            ResultSet rs = stmt.executeQuery(stmtString);
            while (rs.next()) {
                teachersClasses.add(new TeacherClass
                    (
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getInt("class_id"),
                        rs.getString("subject")
                    )
                );
            }

            return teachersClasses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
