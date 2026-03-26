import java.util.Scanner;

public class Day07UserInputMonitoring {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine();

        System.out.print("Enter traffic (Mbps): ");
        int traffic = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.println("--- Server Report ---");
        System.out.println("Server:   " + serverName);
        System.out.println("Location: " + location);
        System.out.println("Status:   " + analyzeTraffic(traffic));

        scanner.close();
    }

    public static String analyzeTraffic(int traffic) {
        if (traffic == 0)        return "PORT DOWN";
        else if (traffic <= 200) return "NORMAL";
        else if (traffic <= 500) return "HIGH TRAFFIC";
        else                     return "CRITICAL TRAFFIC";
    }
}

