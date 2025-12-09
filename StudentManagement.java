import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    private static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
        String user = "root";         // <--- change if needed
        String password = "password"; // <--- change to your MySQL password
        return DriverManager.getConnection(url, user, password);
    }

    public static void addStudent() {
        try (Scanner sc = new Scanner(System.in);
             Connection con = connect();
             PreparedStatement ps = con.prepareStatement("INSERT INTO students(name) VALUES(?)")) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            ps.setString(1, name);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student added successfully!" : "Insert failed.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public static void viewStudents() {
        String sql = "SELECT id, name FROM students ORDER BY id";
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- Student List ---");
            boolean any = false;
            while (rs.next()) {
                any = true;
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
            if (!any) System.out.println("No students found.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public static void updateStudent() {
        try (Scanner sc = new Scanner(System.in);
             Connection con = connect();
             PreparedStatement ps = con.prepareStatement("UPDATE students SET name=? WHERE id=?")) {

            System.out.print("Enter student ID to update: ");
            if (!sc.hasNextInt()) { sc.nextLine(); System.out.println("Invalid ID."); return; }
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Enter new name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }

            ps.setString(1, name);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student updated successfully!" : "No record with that ID.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public static void deleteStudent() {
        try (Scanner sc = new Scanner(System.in);
             Connection con = connect();
             PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?")) {

            System.out.print("Enter student ID to delete: ");
            if (!sc.hasNextInt()) { sc.nextLine(); System.out.println("Invalid ID."); return; }
            int id = sc.nextInt();

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted successfully!" : "No record with that ID.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1": addStudent(); break;
                case "2": viewStudents(); break;
                case "3": updateStudent(); break;
                case "4": deleteStudent(); break;
                case "5": System.out.println("Exiting..."); sc.close(); System.exit(0);
                default: System.out.println("Invalid choice, try again.");
            }
        }
    }
}
