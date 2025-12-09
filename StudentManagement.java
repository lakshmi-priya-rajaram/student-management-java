import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n== Student Management ==");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(sc); break;
                case 4: deleteStudent(sc); break;
                case 5: System.exit(0);
            }
        }
    }

    static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studentdb",
            "root",
            "password"   // change password
        );
    }

    static void addStudent(Scanner sc) throws Exception {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter grade: ");
        String grade = sc.next();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO students(name, age, grade) VALUES(?, ?, ?)"
        );

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, grade);
        ps.executeUpdate();

        System.out.println("Student Added!");
    }

    static void viewStudents() throws Exception {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        System.out.println("\nID | Name | Age | Grade");
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " | " +
                rs.getString(2) + " | " +
                rs.getInt(3) + " | " +
                rs.getString(4)
            );
        }
    }

    static void updateStudent(Scanner sc) throws Exception {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        System.out.print("New name: ");
        String name = sc.next();
        System.out.print("New age: ");
        int age = sc.nextInt();
        System.out.print("New grade: ");
        String grade = sc.next();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE students SET name=?, age=?, grade=? WHERE id=?"
        );

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, grade);
        ps.setInt(4, id);
        ps.executeUpdate();

        System.out.println("Updated!");
    }

    static void deleteStudent(Scanner sc) throws Exception {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "DELETE FROM students WHERE id=?"
        );

        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Deleted!");
    }
}
