import java.util.Scanner;

public class Day08NetworkDiagnostic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int maxAttempts = 3;
        boolean connected = false;

        System.out.println("=== NETWORK DIAGNOSTIC TOOL ===");

        do {
            attempts++;
            System.out.print("Attempt " + attempts + " - Enter server IP: ");
            String ip = scanner.nextLine();

            if (ip.equals("192.168.1.1")) {
                connected = true;
                System.out.println("SUCCESS: Connected to " + ip);
            } else {
                System.out.println("FAILED: Could not reach " + ip);
            }

        } while (!connected && attempts < maxAttempts);

        if (connected) {
            System.out.println("\n=== DIAGNOSTIC MENU ===");
            String command = "";

            while (!command.equals("exit")) {
                System.out.print("Enter command (ping / status / exit): ");
                command = scanner.nextLine();

                if (command.equals("ping")) {
                    System.out.println("Pinging server... OK");
                } else if (command.equals("status")) {
                    System.out.println("Server status: ONLINE");
                } else if (!command.equals("exit")) {
                    System.out.println("Unknown command: " + command);
                }
            }
            System.out.println("Disconnected. Goodbye.");
        } else {
            System.out.println("Max attempts reached. Server unreachable.");
        }

        scanner.close();
    }
}
