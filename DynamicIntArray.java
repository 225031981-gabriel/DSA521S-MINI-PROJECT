public class DynamicIntArray {
    private int[] data;
    private int size;

    public DynamicIntArray() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    private void grow() {
        int[] bigger = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            bigger[i] = data[i];
        }
        data = bigger;
    }

    public int[] toArray() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }

    public int size() { return size; }
}