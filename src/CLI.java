import java.util.Scanner;

import entities.Teacher.Teacher;
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
""";

    public CLI () {
        this.scanner = new Scanner(System.in);
    }

    public static void init () {
        new CLI().start();
    }

    private void start () {
        // TODO: maybe clear screen
        CLI.showMenu();
        
        System.out.print("Action: ");
        String action = this.scanner.nextLine();
        
        try {
            this.parseAction(action);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.scanner.close();
    }

    private static void showMenu () {
        System.out.println(menu);
    }

    private boolean parseAction (String rawAction) throws Exception {
        String[] options = rawAction.strip().split("\\.");
        
        boolean shouldExit = options[0] == "0";
        if (shouldExit) {
            return false;
        }
        
        switch (options[0]) {
            case "1": {
                return this.parseTeacherAction(options[1]);
            }
            case "2": {
                break;
            }
            case "3": {
                break;
            }
            default: {
                throw new Exception(String.format("The action %s has not been recognized!", options[0]));
            }
        }
        System.out.println(options[0]);
        // for (String a : options) {
        //     System.out.println(a);
        // }
        return true;
    }

    private boolean parseTeacherAction (String action) throws Exception {
        var teacherService = TeacherService.getInstance();
        switch (action) {
            case "1": {
                String teachers = teacherService.getAllTeachers()
                    .stream()
                    .map(Object::toString)
                    .reduce((acc, crt) -> acc + "\n" + crt)
                    .orElse("There are no teachers!");
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
}
