import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static ServiceQueue waitingQueue = new ServiceQueue();
    private static StudentLinkedList records = new StudentLinkedList();
    private static DynamicIntArray dailyServiceTimes = new DynamicIntArray();

    public static void main(String[] args) {
        boolean running = true;


        printMenu();


        System.out.println("--- Initializing Sample Data ---");
        seedSampleArrivals();
        System.out.println("--------------------------------\n");


        while (running) {
            int choice = readInt("Select option: ");

            switch (choice) {
                case 1: addStudentToQueue(); break;
                case 2: serveNextStudent(); break;
                case 3: waitingQueue.displayQueue(); break;
                case 4: addServiceRecord(); break;
                case 5: records.displayStudents(); break;
                case 6: searchRecord(); break;
                case 7: removeRecord(); break;
                case 8: ArrayStatistics.computeStatistics(dailyServiceTimes.toArray()); break;
                case 9: sortServiceTimes(); break;
                case 10: SortingExperiment.runExperiment(); break;
                case 11:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-11.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println(" CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student (remove from queue)");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record (Linked List)");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
    }

    private static void addStudentToQueue() {
        System.out.print("Student No: ");
        String no = sc.next();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Service Type: ");
        String type = sc.nextLine();
        int time = readInt("Estimated Service Time (min): ");
        waitingQueue.enqueue(new Student(no, name, type, time));
    }

    private static void serveNextStudent() {
        Student served = waitingQueue.dequeue();
        if (served != null) {
            System.out.println("Now serving: " + served);
            dailyServiceTimes.add(served.getServiceTime());
        }
    }

    private static void addServiceRecord() {
        System.out.print("Student No: ");
        String no = sc.next();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Service Type: ");
        String type = sc.nextLine();
        int time = readInt("Estimated Service Time (min): ");
        int position = readInt("Insert position (0 = end, or 1,2,3... for a specific position): ");
        records.insertStudent(no, name, type, time, position);
    }

    private static void searchRecord() {
        System.out.print("Enter Student No to search: ");
        String no = sc.next();
        records.searchStudent(no);
    }

    private static void removeRecord() {
        System.out.print("Enter Student No to remove: ");
        String no = sc.next();
        records.deleteStudent(no);
    }


    private static void sortServiceTimes() {

        int[] partBArray = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("--- PART B: Sorting Algorithm Challenge ---");
        System.out.println("Original Array: " + Arrays.toString(partBArray));

        System.out.println("\n--- B1: Selection Sort ---");
        int[] selCopy = Arrays.copyOf(partBArray, partBArray.length);
        SortAlgorithms.selectionSortDemo(selCopy);

        System.out.println("\n--- B2: Insertion Sort ---");
        int[] insCopy = Arrays.copyOf(partBArray, partBArray.length);
        SortAlgorithms.insertionSortDemo(insCopy);

        System.out.println("\n--- B3: Merge Sort ---");
        int[] mergeCopy = Arrays.copyOf(partBArray, partBArray.length);
        SortAlgorithms.mergeSortDemo(mergeCopy);

        System.out.println("\n--- B4: Quick Sort ---");
        int[] quickCopy = Arrays.copyOf(partBArray, partBArray.length);
        SortAlgorithms.quickSortDemo(quickCopy);
    }

    private static void seedSampleArrivals() {
        waitingQueue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        waitingQueue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        waitingQueue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        waitingQueue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        waitingQueue.enqueue(new Student("224012233", "Helena", "Academic Enquiry", 15));
        waitingQueue.enqueue(new Student("225098876", "Petrus", "Registration", 9));
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            sc.next();
        }
        int value = sc.nextInt();
        return value;
    }
}