import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter ID, Name, Age, Department:");
                    Student s = new Student(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    dao.addStudent(s);
                    break;

                case 2:
                    System.out.println("Enter Student ID and New Name:");
                    dao.updateStudent(sc.nextInt(), sc.next());
                    break;

                case 3:
                    System.out.println("Enter Student ID to delete:");
                    dao.deleteStudent(sc.nextInt());
                    break;

                case 4:
                    System.out.println("Enter Student ID to view:");
                    dao.viewStudent(sc.nextInt());
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}
