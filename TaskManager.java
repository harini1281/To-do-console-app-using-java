import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Task Manager!");
        // Main loop for the task manager
        while(true)
        {
             System.out.println("\nTask Manager Menu:");
             System.out.println("Choose an action:");
             System.out.println("1. Add Task");
             System.out.println("2. View Tasks");
             System.out.println("3. Mark Task as Done");
             System.out.println("4. Delete Task");
             System.out.println("5. Sort Tasks by Due Date");
             System.out.println("6. Search Tasks by Title");
             System.out.println("7. Exit");
             System.out.print("Enter your choice: ");
             int choice= sc.nextInt();
             sc.nextLine(); // Consume the newline character
             //starting the switch case for user choice
             switch(choice) {
                    case 1:
                        addtask(sc);
                        break;
                    case 2:
                        viewTasks();
                        break;
                    case 3:
                        markTaskAsDone(sc);
                        break;
                    case 4:
                        deleteTask(sc);
                        break;
                    case 5:
                        sortTasksByDueDate();
                        break;
                    case 6:
                        searchTasksByTitle(sc);
                        break;
                    case 7:
                        System.out.println("Exiting Task Manager. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }    
        }
    }
    private static void addtask(Scanner sc) {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD) or leave blank for no due date: ");
            String input = sc.nextLine();
            if (input.isEmpty()) {
                break; // No due date
            }
            try {
                dueDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        tasks.add(new Task(title, dueDate));
        System.out.println("Task added successfully.");
    }
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
    private static void markTaskAsDone(Scanner sc) {
        System.out.print("Enter task title to mark as done: ");
        String title = sc.nextLine();
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                if (!task.isDone()) {
                    task.markAsDone();
                    System.out.println("Task marked as done.");
                } else {
                    System.out.println("Task is already done.");
                }
                return;
            }
        }
        System.out.println("Task not found.");
    }
    private static void deleteTask(Scanner sc) {
        System.out.print("Enter task title to delete: ");
        String title = sc.nextLine();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)) {
                tasks.remove(i);
                System.out.println("Task deleted successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }
    private static void sortTasksByDueDate() {
        tasks.sort((t1, t2) -> {
            if (t1.getDueDate() == null && t2.getDueDate() == null) return 0;
            if (t1.getDueDate() == null) return 1;
            if (t2.getDueDate() == null) return -1;
            return t1.getDueDate().compareTo(t2.getDueDate());
        });
        System.out.println("Tasks sorted by due date.");
    }
    private static void searchTasksByTitle(Scanner sc) {
        System.out.print("Enter title to search for: ");
        String title = sc.nextLine();
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with the given title.");
        }
    }

    // Removed invalid static markAsDone method; markAsDone should be defined in Task class.

}
