public class PostfixDemo {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" TASK A3 - POSTFIX EXPRESSION EVALUATION");
        System.out.println("========================================");

        String expression = "5 3 + 2 *";
        System.out.println("Expression: " + expression);
        System.out.println();

        PostfixStack stack = new PostfixStack(50);
        double result = stack.evaluatePostfix(expression);

        System.out.println();
        System.out.println("RESULT: " + expression + " = " + result);
    }
}