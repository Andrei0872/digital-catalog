import java.time.LocalDate;
import java.util.Collection;

import entities.Class;
import entities.Student.Student;
import entities.Teacher.Teacher;
import services.School;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("hello!");
        App.intialize();
    }

    private static void intialize () throws Exception {
        School school = new School("Awesome school nr.1");

        Teacher t1 = new Teacher("Teacher1", 25, "teacher1@test.com");
        Teacher t2 = new Teacher("Teacher2", 41, "teacher2@test.com");
        // Teacher t3 = new Teacher("Teacher3", 35, "teacher3@test.com");
        // Teacher t4 = new Teacher("Teacher4", 52, "teacher4@test.com");


        Class c1 = new Class("Math");
        Class c2 = new Class("English");
        // Class c3 = new Class("Web development");
        // Class c4 = new Class("German");
        // Class c5 = new Class("AI");

        Student s1 = new Student("Student1", 20, "student1@test.com");
        Student s2 = new Student("Student2", 21, "student2@test.com");
        Student s3 = new Student("Student3", 20, "student3@test.com");
        // Student s4 = new Student("Student4", 19, "student4@test.com");
        // Student s5 = new Student("Student5", 20, "student5@test.com");
        // Student s6 = new Student("Student6", 19, "student6@test.com");
        // Student s7 = new Student("Student7", 20, "student7@test.com");
        // Student s8 = new Student("Student8", 20, "student8@test.com");
        // Student s9 = new Student("Student9", 22, "student9@test.com");

        school.addTeacherToClass(t1, c1);
        school.addTeacherToClass(t2, c1);
        school.addTeacherToClass(t1, c2);

        school.addStudentToClass(s1, c1);
        school.addStudentToClass(s2, c1);
        school.addStudentToClass(s3, c1);
        school.addStudentToClass(s1, c2);

        t1.assignGradeToStudent(10, LocalDate.of(2022, 4, 19), c1, s1);
        t1.assignGradeToStudent(5, LocalDate.of(2022, 3, 19), c1, s1);
        t2.assignGradeToStudent(8, LocalDate.of(2022, 5, 19), c1, s1);
        t1.assignGradeToStudent(9, LocalDate.of(2022, 5, 19), c2, s1);

        // Collection<Student> sts = school.getStudents();
        // System.out.println(sts.size());

        // for (Student s : sts) {
        //     System.out.println(s.toString());
        // }

        // System.out.println(school.getTeacherDeserialized(t1.getId()));
        System.out.println(school.getStudentDeserialized(s1.getId()));
    }
}
