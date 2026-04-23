import java.util.Scanner; // busca el Scanner antes de empezar

public class Day11LogAnalyzer { // clase principal

    public static void main(String[] args) { // punto de entrada

        Scanner scanner = new Scanner(System.in); // crea el Scanner

        System.out.println("=== NETWORK LOG ANALYZER ===");
        System.out.print("Enter log line: ");
        String log = scanner.nextLine(); // lee la línea de log completa

        System.out.println("\n--- ANALYSIS ---");
        System.out.println("Original:    " + log);
        System.out.println("Length:      " + log.length() + " characters");
        System.out.println("Uppercase:   " + log.toUpperCase());
        System.out.println("Lowercase:   " + log.toLowerCase());
        System.out.println("Trimmed:     " + log.trim());
        System.out.println("Contains ERROR: " + log.contains("ERROR"));
        System.out.println("Starts with [: " + log.startsWith("["));
        System.out.println("Ends with ]: "   + log.endsWith("]"));
        System.out.println("Replace ERROR->ALERT: " + log.replace("ERROR", "ALERT"));
        System.out.println("Index of ERROR: " + log.indexOf("ERROR"));
        System.out.println("Substring (0,10): " + log.substring(0, 10));
        System.out.println("Is empty: " + log.isEmpty());

        System.out.println("\n--- SEVERITY CHECK ---");
        String logUpper = log.toUpperCase(); // convertimos a mayúsculas para comparar sin importar si el usuario escribe en minúsculas
        if (logUpper.contains("CRITICAL")) {
            System.out.println("Severity: CRITICAL - Immediate action required");
        } else if (logUpper.contains("ERROR")) {
            System.out.println("Severity: ERROR - Needs attention");
        } else if (logUpper.contains("WARNING")) {
            System.out.println("Severity: WARNING - Monitor closely");
        } else if (logUpper.contains("INFO")) {
            System.out.println("Severity: INFO - No action required");
        } else {
            System.out.println("Severity: UNKNOWN - Check log format");
        }

        scanner.close(); // libera recursos
    }
}
