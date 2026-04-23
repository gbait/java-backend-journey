import java.util.Scanner; // busca el Scanner antes de empezar

public class Day09AlertSystem { // clase principal

    public static void main(String[] args) { // punto de entrada del programa

        Scanner scanner = new Scanner(System.in); // crea el Scanner, escucha el teclado

        System.out.println("=== NETWORK ALERT SYSTEM ===");
        System.out.print("Enter error code (1-5): "); // pide el código de error
        int errorCode = Integer.parseInt(scanner.nextLine()); // lee el código como entero

        System.out.println("\n--- ALERT REPORT ---");

        // switch tradicional con break
        switch (errorCode) {
            case 1:
                System.out.println("Type:     CONNECTION LOST");
                System.out.println("Priority: CRITICAL");
                System.out.println("Action:   Check physical cables");
                break; // sin break, seguiría ejecutando el caso 2
            case 2:
                System.out.println("Type:     HIGH LATENCY");
                System.out.println("Priority: HIGH");
                System.out.println("Action:   Check bandwidth usage");
                break;
            case 3:
                System.out.println("Type:     PACKET LOSS");
                System.out.println("Priority: HIGH");
                System.out.println("Action:   Run network diagnostics");
                break;
            case 4:
                System.out.println("Type:     DNS FAILURE");
                System.out.println("Priority: MEDIUM");
                System.out.println("Action:   Check DNS configuration");
                break;
            case 5:
                System.out.println("Type:     LOW SIGNAL");
                System.out.println("Priority: LOW");
                System.out.println("Action:   Check antenna position");
                break;
            default: // si el código no es ninguno de los anteriores
                System.out.println("Type:     UNKNOWN ERROR");
                System.out.println("Priority: UNKNOWN");
                System.out.println("Action:   Contact support");
        }

        scanner.close(); // libera recursos
    }
}
