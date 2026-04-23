import java.util.Scanner;

public class Day10NetworkMetrics {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== NETWORK METRICS CALCULATOR ===");

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine();

        System.out.print("Enter total bandwidth (Mbps): ");
        int totalBandwidth = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter used bandwidth (Mbps): ");
        int usedBandwidth = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter packet loss (%): ");
        double packetLoss = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter ping (ms): ");
        int ping = Integer.parseInt(scanner.nextLine());

        System.out.println("\n=== SERVER REPORT: " + serverName + " ===");
        System.out.println("Bandwidth usage:  " + calculateUsage(totalBandwidth, usedBandwidth) + "%");
        System.out.println("Network quality:  " + evaluateQuality(packetLoss, ping));
        System.out.println("Recommendation:   " + getRecommendation(totalBandwidth, usedBandwidth, packetLoss));
        System.out.println("Status:           " + getStatus(totalBandwidth, usedBandwidth, packetLoss, ping));

        scanner.close();
    }

    public static double calculateUsage(int total, int used) {
        return (double) used / total * 100;
    }

    public static String evaluateQuality(double packetLoss, int ping) {
        if (packetLoss == 0 && ping <= 50) {
            return "EXCELLENT";
        } else if (packetLoss <= 1 && ping <= 100) {
            return "GOOD";
        } else if (packetLoss <= 5 && ping <= 200) {
            return "POOR";
        } else {
            return "CRITICAL";
        }
    }

    public static String getRecommendation(int total, int used, double packetLoss) {
        double usage = calculateUsage(total, used);
        if (usage >= 90) {
            return "Upgrade bandwidth immediately";
        } else if (usage >= 70 && packetLoss > 1) {
            return "Monitor closely and reduce traffic";
        } else if (packetLoss > 5) {
            return "Check network hardware";
        } else {
            return "No action required";
        }
    }

    public static String getStatus(int total, int used, double packetLoss, int ping) {
        double usage = calculateUsage(total, used);
        String quality = evaluateQuality(packetLoss, ping);

        if (quality.equals("CRITICAL") || usage >= 90) {
            return "[CRITICAL]";
        } else if (quality.equals("POOR") || usage >= 70) {
            return "[WARNING]";
        } else {
            return "[ONLINE]";
        }
    }
}
