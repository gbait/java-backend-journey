import java.util.Scanner; // busca el Scanner antes de empezar

public class Day08NetworkDiagnostic { // clase principal

    public static void main(String[] args) { // punto de entrada del programa

        Scanner scanner = new Scanner(System.in); // crea el Scanner, escucha el teclado
        int attempts = 0; // contador de intentos, empieza en 0
        int maxAttempts = 3; // límite máximo de intentos
        boolean connected = false; // bandera de conexión, empieza en false

        System.out.println("=== NETWORK DIAGNOSTIC TOOL ==="); // cabecera del programa

        do { // el código de dentro se ejecuta PRIMERO, condición se comprueba DESPUÉS
            attempts++; // suma 1 al contador, igual que attempts = attempts + 1
            System.out.print("Attempt " + attempts + " - Enter server IP: "); // pide la IP
            String ip = scanner.nextLine(); // lee la IP del usuario

            if (ip.equals("192.168.1.1")) { // .equals() para comparar Strings, nunca ==
                connected = true; // cambia la bandera, el bucle parará
                System.out.println("SUCCESS: Connected to " + ip);
            } else {
                System.out.println("FAILED: Could not reach " + ip);
            }

        } while (!connected && attempts < maxAttempts); // sigue mientras NO conectado Y intentos < 3

        if (connected) { // comprobamos si salimos por conexión o por agotar intentos
            System.out.println("\n=== DIAGNOSTIC MENU ===");
            String command = ""; // texto vacío para que el while pueda leerla desde el principio

            while (!command.equals("exit")) { // sigue hasta que el usuario escriba exit
                System.out.print("Enter command (ping / status / exit): ");
                command = scanner.nextLine(); // lee el comando del usuario

                if (command.equals("ping")) {
                    System.out.println("Pinging server... OK");
                } else if (command.equals("status")) {
                    System.out.println("Server status: ONLINE");
                } else if (!command.equals("exit")) { // evita mostrar "Unknown command" al salir
                    System.out.println("Unknown command: " + command);
                }
            }
            System.out.println("Disconnected. Goodbye.");
        } else {
            System.out.println("Max attempts reached. Server unreachable.");
        }

        scanner.close(); // libera recursos del sistema
    } // cierra el main
} // cierra la clase
