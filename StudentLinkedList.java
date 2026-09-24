public class StudentLinkedList {

    private static class LLNode {
        String studentNo;
        String name;
        String serviceType;
        int serviceTime;
        LLNode next;

        LLNode(String studentNo, String name, String serviceType, int serviceTime) {
            this.studentNo = studentNo;
            this.name = name;
            this.serviceType = serviceType;
            this.serviceTime = serviceTime;
            this.next = null;
        }

        @Override
        public String toString() {
            return "[" + studentNo + " | " + name + " | " + serviceType + " | " + serviceTime + " min]";
        }
    }

    private LLNode head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    public void insertAtBeginning(String studentNo, String name, String serviceType, int serviceTime) {
        LLNode newNode = new LLNode(studentNo, name, serviceType, serviceTime);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Inserted at beginning: " + newNode);
    }

    public void insertAtEnd(String studentNo, String name, String serviceType, int serviceTime) {
        LLNode newNode = new LLNode(studentNo, name, serviceType, serviceTime);
        if (head == null) {
            head = newNode;
        } else {
            LLNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Inserted at end: " + newNode);
    }

    public void insertAtPosition(int position, String studentNo, String name, String serviceType, int serviceTime) {
        if (position <= 1 || head == null) {
            insertAtBeginning(studentNo, name, serviceType, serviceTime);
            return;
        }
        LLNode newNode = new LLNode(studentNo, name, serviceType, serviceTime);
        LLNode current = head;
        int count = 1;
        while (count < position - 1 && current.next != null) {
            current = current.next;
            count++;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Inserted at position " + position + ": " + newNode);
    }

    public void insertStudent(String studentNo, String name, String serviceType, int serviceTime, int position) {
        if (position == 0) {
            insertAtEnd(studentNo, name, serviceType, serviceTime);
        } else {
            insertAtPosition(position, studentNo, name, serviceType, serviceTime);
        }
    }

    public boolean deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty - nothing to delete.");
            return false;
        }
        if (head.studentNo.equals(studentNo)) {
            System.out.println("Deleted: " + head);
            head = head.next;
            size--;
            return true;
        }
        LLNode current = head;
        while (current.next != null && !current.next.studentNo.equals(studentNo)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Student " + studentNo + " not found.");
            return false;
        }
        System.out.println("Deleted: " + current.next);
        current.next = current.next.next;
        size--;
        return true;
    }

    public boolean searchStudent(String studentNo) {
        LLNode current = head;
        int position = 1;
        while (current != null) {
            if (current.studentNo.equals(studentNo)) {
                System.out.println("Found at position " + position + ": " + current);
                return true;
            }
            current = current.next;
            position++;
        }
        System.out.println("Student " + studentNo + " not found.");
        return false;
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("[No student records]");
            return;
        }
        System.out.println("--- Student Service Records ---");
        LLNode current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current);
            current = current.next;
            position++;
        }
    }

    public int size() { return size; }
}