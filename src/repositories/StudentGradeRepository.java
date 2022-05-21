package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Database;
import entities.StudentGrade;

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

    public ArrayList<StudentGrade> getAllByStudentId (int studentId) {
        Connection conn = Database.getConnection();
        String stmtString = """
            select
                t.name as teacher_name,
                st.name as student_name,
                gr.value as grade_val,
                c.subject,
                gr.inserted_at as grade_inserted_at
            from student_grade sg
            join teacher t
                on t.id = sg.teacher_id
            join class c
                on c.id = sg.class_id
            join student st
                on st.id = sg.student_id
            join grade gr
                on gr.id = sg.grade_id
            where sg.student_id = ?
        """;

        ArrayList<StudentGrade> studentGrades = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(stmtString);
            
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                studentGrades.add(new StudentGrade
                    (
                        rs.getString("teacher_name"),
                        rs.getString("student_name"),
                        rs.getInt("grade_val"),
                        rs.getString("subject"),
                        rs.getString("grade_inserted_at")
                    )
                );
            }

            return studentGrades;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
