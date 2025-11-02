import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    double marks;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    void display() {
        System.out.println("Roll No: " + rollNo + ", Name: " + name + ", Marks: " + marks);
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[50]; // max 50 students
        int count = 0;

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Roll No");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    students[count++] = new Student(roll, name, marks);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- Student List ---");
                    for (int i = 0; i < count; i++) {
                        students[i].display();
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll No to Search: ");
                    int searchRoll = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (students[i].rollNo == searchRoll) {
                            System.out.println("Student Found:");
                            students[i].display();
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        System.out.println("No student found with that roll number!");
                    break;

                case 4:
                    // Bubble Sort by marks
                    for (int i = 0; i < count - 1; i++) {
                        for (int j = 0; j < count - i - 1; j++) {
                            if (students[j].marks > students[j + 1].marks) {
                                Student temp = students[j];
                                students[j] = students[j + 1];
                                students[j + 1] = temp;
                            }
                        }
                    }
                    System.out.println("Students sorted by marks!");
                    System.out.println("ROLL\t Name\t Marks");
                    for (int i = 0; i < count; i++) {
                        students[i].display();
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
