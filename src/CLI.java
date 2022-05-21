import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entities.Class;
import entities.Grade.Grade;
import entities.Student.Student;
import entities.Teacher.Teacher;
import services.ClassService;
import services.GradeService;
import services.StudentService;
import services.TeacherService;

public class CLI {
    private Scanner scanner;

    private static final String menu = """
    0. Exit
    1. Teacher
        1.1 Select all
        1.2 Insert one
        1.3 Update one
        1.4 Delete one
    2. Student
        2.1 Select all
        2.2 Insert one
        2.3 Update one
        2.4 Delete one
    3. Class
        3.1 Select all
        3.2 Insert one
        3.3 Update one
        3.4 Delete one
    4. Assign a Class to a Teacher
    5. Get Classes assigned to a Teacher
    6. Add a Student to a Class
    7. Assign a Grade to a Student
    8. Print a Student's Grades
    9. Print a Student's Classes
    10. Print a Class' Students
""";

    private static final String SEPARATOR = "=".repeat(80);

    public CLI () {
        this.scanner = new Scanner(System.in);
    }

    public static void init () {
        new CLI().start();
    }

    private void start () {
        boolean shouldContinue = true;
        
        while (shouldContinue) {
            CLI.showMenu();
            System.out.print("Action: ");
            String action = this.scanner.nextLine();
            
            try {
                shouldContinue = this.parseAction(action);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (shouldContinue) {
                System.out.println(SEPARATOR);
            }
        }


        this.scanner.close();
    }

    private static void showMenu () {
        System.out.println(menu);
    }

    private boolean parseAction (String rawAction) throws Exception {
        String[] options = rawAction.strip().split("\\.");

        boolean shouldExit = options[0] == "0" || rawAction.strip().equals("0");
        if (shouldExit) {
            return false;
        }

        String[] compositeActions = new String[]{"1", "2", "3"};
        List<String> compositeActionsList = Arrays.asList(compositeActions);


        if (compositeActionsList.contains(options[0]) && options.length != 2) {
            throw new Exception("1, 2 and 3 are composite actions!");
        }
        
        switch (options[0]) {
            case "1": {
                return this.parseTeacherAction(options[1]);
            }
            case "2": {
                return this.parseStudentAction(options[1]);
            }
            case "3": {
                return this.parseClassAction(options[1]);
            }
            case "4": {
                return this.assignClassToTeacher();
            }
            case "5": {
                return this.getTeacherAssignedClasses();
            }
            case "6": {
                return this.addStudentToClass();
            }
            case "7": {
                return this.assignGradeToStudent();
            }
            case "8": {
                return this.printStudentGrades();
            }
            case "9": {
                return this.printStudentClasses();
            }
            case "10": {
                return this.printClassStudents();
            }
            default: {
                throw new Exception(String.format("The action %s has not been recognized!", options[0]));
            }
        }
    }

    private boolean printClassStudents () throws Exception {
        var teacherService = TeacherService.getInstance();
        var teachersClasses = teacherService.getAllTeachersAndAssignedClasses();

        StringBuilder teachersClassesSerialized = new StringBuilder();
        for (int i = 0; i < teachersClasses.size(); i++) {
            teachersClassesSerialized.append(String.format("%d. %s\n", i + 1, teachersClasses.get(i)));
        }

        System.out.println("Teachers and their classes:\n");
        System.out.println(teachersClassesSerialized);

        System.out.print("Chosen Index: ");
        int idx = Integer.parseInt(this.scanner.nextLine()) - 1;
        if (idx < 0 || idx >= teachersClasses.size()) {
            throw new Exception("Make sure the chosen index is valid!");
        }

        var chosenClass = teachersClasses.get(idx);
        
        var classService = ClassService.getInstance();
        var students = classService.getStudentsFromClass(chosenClass.teacherId(), chosenClass.classId());

        var studentsSerialized = new StringBuilder();

        for (int i = 0; i < students.size(); i ++) {
            studentsSerialized.append(
                String.format("%d. %s\n", i + 1, students.get(i))
            );
        }

        System.out.println(studentsSerialized);

        return true;
    }

    private boolean printStudentClasses () throws Exception {
        var studentService = StudentService.getInstance();
    
        String students = studentService.getAllStudentsSerialized();
        System.out.println("Students: \n" + students);
        
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(this.scanner.nextLine());
        if (!studentService.doesStudentExist(studentId)) {
            throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
        }

        var classService = ClassService.getInstance();
        var studentClasses = classService.getStudentClasses(studentId);
        if (studentClasses.isEmpty()) {
            System.out.println("This student is not part of any class!");
            return true;
        }

        var studentClassesSerialized = new StringBuilder();

        for (int i = 0; i < studentClasses.size(); i ++) {
            studentClassesSerialized.append(
                String.format("%d. %s\n", i + 1, studentClasses.get(i))
            );
        }

        System.out.println(studentClassesSerialized);

        return true;
    }

    private boolean printStudentGrades () throws Exception {
        var studentService = StudentService.getInstance();
    
        String students = studentService.getAllStudentsSerialized();
        System.out.println("Students: \n" + students);
        
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(this.scanner.nextLine());
        if (!studentService.doesStudentExist(studentId)) {
            throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
        }

        var studentGrades = studentService.getStudentGrades(studentId);
        if (studentGrades.size() == 0) {
            System.out.println("This student has no assigned grades!");
            return true;
        }

        var studentname = studentGrades.get(0).studentName();

        System.out.println(String.format("Grades of %s:", studentname));
        
        var studentGradesSerialized = new StringBuilder();
        for (int i = 0; i < studentGrades.size(); i++) {
            studentGradesSerialized.append(
                String.format("%d. %s\n", i + 1, studentGrades.get(i))
            );
        }

        System.out.println(studentGradesSerialized);

        return true;
    }

    private boolean assignGradeToStudent () throws Exception {
        var studentService = StudentService.getInstance();
    
        String students = studentService.getAllStudentsSerialized();
        System.out.println("Students: \n" + students);
        
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(this.scanner.nextLine());
        if (!studentService.doesStudentExist(studentId)) {
            throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
        }

        System.out.print("Grade value: ");
        var value = Integer.parseInt(this.scanner.nextLine());
        if (value < 1 || value > 10) {
            throw new Exception(String.format("Invalid grade value: %d", value));
        }

        var grade = new Grade(value);

        var gradeService = GradeService.getInstance();
        int lastGradeId = gradeService.insertGrade(grade);
        if (lastGradeId == -1) {
            throw new Exception("An error occurred while trying to insert the grade!");
        }

        var teacherService = TeacherService.getInstance();
        var teachersClasses = teacherService.getAllTeachersAndAssignedClasses();

        StringBuilder teachersClassesSerialized = new StringBuilder();
        for (int i = 0; i < teachersClasses.size(); i++) {
            teachersClassesSerialized.append(String.format("%d. %s\n", i + 1, teachersClasses.get(i)));
        }

        System.out.println("Teachers and their classes:\n");
        System.out.println(teachersClassesSerialized);

        System.out.print("Chosen Index: ");
        int idx = Integer.parseInt(this.scanner.nextLine()) - 1;
        if (idx < 0 || idx >= teachersClasses.size()) {
            throw new Exception("Make sure the chosen index is valid!");
        }

        var chosenClass = teachersClasses.get(idx);

        studentService.assignGradeToStudent(chosenClass.teacherId(), chosenClass.classId(), studentId, lastGradeId);

        return true;
    }

    private boolean addStudentToClass () throws Exception {
        var studentService = StudentService.getInstance();
    
        String students = studentService.getAllStudentsSerialized();
        System.out.println("Students: \n" + students);
        
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(this.scanner.nextLine());
        if (!studentService.doesStudentExist(studentId)) {
            throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
        }

        var teacherService = TeacherService.getInstance();
        var teachersClasses = teacherService.getAllTeachersAndAssignedClasses();

        StringBuilder teachersClassesSerialized = new StringBuilder();
        for (int i = 0; i < teachersClasses.size(); i++) {
            teachersClassesSerialized.append(String.format("%d. %s\n", i + 1, teachersClasses.get(i)));
        }

        System.out.println("Teachers and their classes:\n");
        System.out.println(teachersClassesSerialized);

        System.out.print("Chosen Index: ");
        int idx = Integer.parseInt(this.scanner.nextLine()) - 1;
        if (idx < 0 || idx >= teachersClasses.size()) {
            throw new Exception("Make sure the chosen index is valid!");
        }

        var chosenClass = teachersClasses.get(idx);

        var classService = ClassService.getInstance();
        classService.addStudentToClass(chosenClass.teacherId(), chosenClass.classId(), studentId);

        return true;
    }

    private boolean getTeacherAssignedClasses () throws Exception {
        var teacherService = TeacherService.getInstance();
    
        String teachers = teacherService.getAllTeachersSerialized();
        System.out.println("Teachers: \n" + teachers);

        System.out.print("Teacher ID: ");
        int teacherId = Integer.parseInt(this.scanner.nextLine());
        if (!teacherService.doesTeacherExist(teacherId)) {
            throw new Exception(String.format("The teacher with ID = %s does not exist!", teacherId));
        }

        var classes = teacherService.getAssignedClasses(teacherId);
        var classService = ClassService.getInstance();
        System.out.println("The classes are:\n" + classService.serializeClasses(classes));

        return true;
    }

    private boolean assignClassToTeacher () throws Exception {
        var teacherService = TeacherService.getInstance();
    
        String teachers = teacherService.getAllTeachersSerialized();
        System.out.println("Teachers: \n" + teachers);

        System.out.print("Teacher ID: ");
        int teacherId = Integer.parseInt(this.scanner.nextLine());
        if (!teacherService.doesTeacherExist(teacherId)) {
            throw new Exception(String.format("The teacher with ID = %s does not exist!", teacherId));
        }

        var classService = ClassService.getInstance();

        String classes = classService.getAllClassesSerialized();
        System.out.println("Classes: \n" + classes);

        System.out.print("Class ID: ");
        int classId = Integer.parseInt(this.scanner.nextLine());
        if (!classService.doesClassExist(classId)) {
            throw new Exception(String.format("The class with ID = %s does not exist!", classId));
        }

        teacherService.assignClassToTeacher(teacherId, classId);

        return true;
    }

    private boolean parseTeacherAction (String action) throws Exception {
        var teacherService = TeacherService.getInstance();
        switch (action) {
            case "1": {
                String teachers = teacherService.getAllTeachersSerialized();
                System.out.println(teachers);
                break;
            }
            case "2": {
                System.out.print("Name: ");
                var name = this.scanner.nextLine();

                System.out.print("Age: ");
                var age = this.scanner.nextLine();

                System.out.print("Email: ");
                var email = this.scanner.nextLine();

                var t = new Teacher(name, Integer.parseInt(age), email);
                teacherService.insertTeacher(t);

                break;
            }
            case "3": {
                System.out.print("The ID of the teacher that is to be updated: ");
                var teacherId = Integer.parseInt(this.scanner.nextLine());

                var crtTeacher = teacherService.getTeacherById(teacherId);
                if (crtTeacher == null) {
                    throw new Exception(String.format("The teacher with ID = %s does not exist!", teacherId));
                }

                System.out.print(String.format("New name(default = %s): ", crtTeacher.getName()));
                var name = this.scanner.nextLine();
                name = name.isEmpty() ? crtTeacher.getName() : name;

                System.out.print(String.format("New age(default = %s): ", crtTeacher.getAge()));
                var age = this.scanner.nextLine();
                age = age.isEmpty() ? Integer.toString(crtTeacher.getAge()) : age;

                System.out.print(String.format("New email(default = %s): ", crtTeacher.getEmail()));
                var email = this.scanner.nextLine();
                email = email.isEmpty() ? crtTeacher.getEmail() : email;

                var t = new Teacher(name, Integer.parseInt(age), email);
                teacherService.updateTeacherById(teacherId, t);

                break;
            }
            case "4": {
                System.out.print("The ID of the teacher that is to be deleted: ");
                var teacherId = Integer.parseInt(this.scanner.nextLine());

                var crtTeacher = teacherService.getTeacherById(teacherId);
                if (crtTeacher == null) {
                    throw new Exception(String.format("The teacher with ID = %s does not exist!", teacherId));
                }

                teacherService.deleteTeacher(crtTeacher);
                break;
            }
            
        }

        return true;
    }

    private boolean parseStudentAction (String action) throws Exception {
        var studentService =  StudentService.getInstance();
        switch (action) {
            case "1": {
                String students = studentService.getAllStudents()
                    .stream()
                    .map(Object::toString)
                    .reduce((acc, crt) -> acc + "\n" + crt)
                    .orElse("There are no students!");
                System.out.println(students);
                break;
            }
            case "2": {
                System.out.print("Name: ");
                var name = this.scanner.nextLine();

                System.out.print("Age: ");
                var age = this.scanner.nextLine();

                System.out.print("Email: ");
                var email = this.scanner.nextLine();

                var st = new Student(name, Integer.parseInt(age), email);
                studentService.insertStudent(st);

                break;
            }
            case "3": {
                System.out.print("The ID of the student that is to be updated: ");
                var studentId = Integer.parseInt(this.scanner.nextLine());

                var crtStudent = studentService.getStudentById(studentId);
                if (crtStudent == null) {
                    throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
                }

                System.out.print(String.format("New name(default = %s): ", crtStudent.getName()));
                var name = this.scanner.nextLine();
                name = name.isEmpty() ? crtStudent.getName() : name;

                System.out.print(String.format("New age(default = %s): ", crtStudent.getAge()));
                var age = this.scanner.nextLine();
                age = age.isEmpty() ? Integer.toString(crtStudent.getAge()) : age;

                System.out.print(String.format("New email(default = %s): ", crtStudent.getEmail()));
                var email = this.scanner.nextLine();
                email = email.isEmpty() ? crtStudent.getEmail() : email;

                var st = new Student(name, Integer.parseInt(age), email);
                studentService.updateStudentById(studentId, st);

                break;
            }
            case "4": {
                System.out.print("The ID of the student that is to be deleted: ");
                var studentId = Integer.parseInt(this.scanner.nextLine());

                var crtStudent = studentService.getStudentById(studentId);
                if (crtStudent == null) {
                    throw new Exception(String.format("The student with ID = %s does not exist!", studentId));
                }

                studentService.deleteStudent(crtStudent);
                break;
            }
            
        }

        return true;
    }

    private boolean parseClassAction (String action) throws Exception {
        var classService = ClassService.getInstance();
        switch (action) {
            case "1": {
                String classes = classService.getAllClasses()
                    .stream()
                    .map(Object::toString)
                    .reduce((acc, crt) -> acc + "\n" + crt)
                    .orElse("There are no classes!");
                System.out.println(classes);
                break;
            }
            case "2": {
                System.out.print("Subject: ");
                var subject = this.scanner.nextLine();

                var cls = new Class(subject);
                classService.insertClass(cls);

                break;
            }
            case "3": {
                System.out.print("The ID of the class that is to be updated: ");
                var classId = Integer.parseInt(this.scanner.nextLine());

                var crtClass = classService.getClassById(classId);
                if (crtClass == null) {
                    throw new Exception(String.format("The class with ID = %s does not exist!", classId));
                }

                System.out.print(String.format("New subject(default = %s): ", crtClass.getSubject()));
                var subject = this.scanner.nextLine();
                subject = subject.isEmpty() ? crtClass.getSubject() : subject;

                var cls = new Class(subject);
                classService.updateClassById(classId, cls);

                break;
            }
            case "4": {
                System.out.print("The ID of the class that is to be deleted: ");
                var classId = Integer.parseInt(this.scanner.nextLine());

                var crtClass = classService.getClassById(classId);
                if (crtClass == null) {
                    throw new Exception(String.format("The class with ID = %s does not exist!", classId));
                }

                classService.deleteClass(crtClass);
                break;
            }
            
        }

        return true;
    }
}
