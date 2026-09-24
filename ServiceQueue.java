public class ServiceQueue {

    private static class QNode {
        Student data;
        QNode next;
        QNode(Student data) { this.data = data; this.next = null; }
    }

    private QNode front;
    private QNode rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Student student) {
        QNode newNode = new QNode(student);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + student);
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty - no student to serve.");
            return null;
        }
        Student removed = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return removed;
    }

    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() { return front == null; }
    public int size() { return size; }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("[Queue is empty]");
            return;
        }
        System.out.println("--- Waiting Queue (front to rear) ---");
        QNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
    }
}