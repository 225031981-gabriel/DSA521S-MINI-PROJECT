public class ArrayStatistics {

    public static void computeStatistics(int[] serviceTimes) {
        if (serviceTimes.length == 0) {
            System.out.println("No students served yet - no statistics available.");
            return;
        }

        int totalStudents = serviceTimes.length;
        int totalTime = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longerThan10 = 0;

        for (int i = 0; i < serviceTimes.length; i++) {
            int time = serviceTimes[i];
            totalTime = totalTime + time;
            if (time > highest) highest = time;
            if (time < lowest) lowest = time;
            if (time > 10) longerThan10++;
        }

        double average = (double) totalTime / totalStudents;

        System.out.println("--- Daily Statistics ---");
        System.out.println("Total students served     : " + totalStudents);
        System.out.println("Total service time (min)  : " + totalTime);
        System.out.println("Average service time (min): " + String.format("%.2f", average));
        System.out.println("Highest service time (min): " + highest);
        System.out.println("Lowest service time (min) : " + lowest);
        System.out.println("Services longer than 10min: " + longerThan10);
    }
}