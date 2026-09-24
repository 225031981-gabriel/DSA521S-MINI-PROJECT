public class PostfixStack {

    private double[] data;
    private int top;
    private int capacity;

    public PostfixStack(int capacity) {
        this.capacity = capacity;
        this.data = new double[capacity];
        this.top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == capacity - 1; }

    public void push(double value) {
        if (isFull()) {
            System.out.println("Stack overflow - cannot push " + value);
            return;
        }
        top++;
        data[top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow - cannot pop.");
            return Double.NaN;
        }
        double value = data[top];
        top--;
        return value;
    }

    public double peek() {
        if (isEmpty()) return Double.NaN;
        return data[top];
    }

    public void printStack() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(data[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("]");
        System.out.println("Stack: " + sb);
    }

    public double evaluatePostfix(String expression) {
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                double value = Double.parseDouble(token);
                push(value);
                System.out.println("Pushed number " + value);
            } else if (isOperator(token)) {
                double operandB = pop();
                double operandA = pop();
                double result = applyOperator(token, operandA, operandB);
                push(result);
                System.out.println("Applied " + operandA + " " + token + " " + operandB + " = " + result);
            } else {
                System.out.println("Skipping unknown token: " + token);
                continue;
            }
            printStack();
        }

        double finalResult = pop();
        System.out.println("Final result = " + finalResult);
        return finalResult;
    }

    private boolean isNumber(String token) {
        try { Double.parseDouble(token); return true; }
        catch (NumberFormatException e) { return false; }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
                || token.equals("x") || token.equals("÷");
    }

    private double applyOperator(String op, double a, double b) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*":
            case "x": return a * b;
            case "/":
            case "÷":
                if (b == 0) { System.out.println("Division by zero!"); return 0; }
                return a / b;
            default: return 0;
        }
    }
}