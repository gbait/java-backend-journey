import java.util.Scanner; // busca el Scanner antes de empezar, sin esta linea el programa no sabe que es Scanner y no funciona

public class Day10NetworkMetrics { // clase ppal, nombre igual al del archivo .java

    public static void main(String[] args) { // punto d eentrada del programa. java empieza ejecutando desde aquí

        Scanner scanner = new Scanner(System.in); // crea el Scanner y le dice que escuche al teclado con System.in. Mismmo patrón que siempre

        System.out.println("=== NETWORK METRICS CALCULATOR ==="); // imprime la cabecera del programa. Usamos println porque no vamos a pedir nada al usuario justo después - el cursos puede bajar de linea

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine(); //print sin ln para que el cursor se quede en la misma línea esperando que el usuario escriba. nextLine() lee todo lo que escribe el usuario hasta que pulsa enter y lo guarda ens serverName

        System.out.print("Enter total bandwidth (Mbps): ");
        int totalBandwidth = Integer.parseInt(scanner.nextLine()); //pide el ancho de banda total. usamos Integer.parseInt(scanner.nextLine()) leemos como texto con nextLine() y convertimos en entero con Integer.parseInt()
        
        System.out.print("Enter used bandwidth (Mbps): "); 
        int usedBandwidth = Integer.parseInt(scanner.nextLine()); //igual que la linea anterior pero para el ancho de banda usado

        System.out.print("Enter packet loss (%): ");                 //pide el % de perdida de paquetes. Aqui usamos double en vez d eint porque el % puede tener decimales como 2.5. 
        double packetLoss = Double.parseDouble(scanner.nextLine());  //el metodo de conversion es Double.parseDouble() - igual que Integer.parseInt() pero para decimales. Importante mayusculas para las clases

        System.out.print("Enter ping (ms): ");
        int ping = Integer.parseInt(scanner.nextLine()); //pide el ping en milisegundos. Es siempr eun numero entero asi que volvemos a usar integer.parseInt()

        System.out.println("\n=== SERVER REPORT: " + serverName + " ==="); // Imprime cabecera del informe. El \n al principio añade una linea vacia antes para separar visualmente la spreguntas del resultado.
        System.out.println("Bandwidth usage:  " + calculateUsage(totalBandwidth, usedBandwidth) + "%"); //Aquí ocurren dos cosas antes de imprimir. Primero se llama al método calculateUsage() pasándole los dos valores de ancho de banda. Ese método hace el cálculo y devuelve un número double. Ese número se une con el texto "Bandwidth usage: " y el "%" usando el operador +, y el resultado completo se imprime en pantalla.
        System.out.println("Network quality:  " + evaluateQuality(packetLoss, ping)); //Llama al método evaluateQuality() pasándole la pérdida de paquetes y el ping. Ese método devuelve un texto como "EXCELLENT" o "POOR" que se une con "Network quality: " y se imprime.
        System.out.println("Recommendation:   " + getRecommendation(totalBandwidth, usedBandwidth, packetLoss)); //Llama al método getRecommendation() pasándole tres parámetros — el ancho de banda total, el usado y la pérdida de paquetes. Devuelve un texto con la recomendación y se imprime.
        System.out.println("Status:           " + getStatus(totalBandwidth, usedBandwidth, packetLoss, ping)); //Llama al método getStatus() pasándole cuatro parámetros. Es el método más completo — combina los resultados de los otros métodos para dar el estado final del servidor.

        scanner.close(); //cerramo el Scanner para liberar recursos del sistema. Siempre al final cunando ya no lo necesitamos
    }

    public static double calculateUsage(int total, int used) { //Define el primer metodo fuera del main pero dentro de la clase.
        return (double) used / total * 100;
    }

    public static String evaluateQuality(double packetLoss, int ping) { //Segundo método. Recibe dos parámetros de tipos distintos — un double para la pérdida de paquetes y un int para el ping. Devuelve un String con la calidad de la red.
        if (packetLoss == 0 && ping <= 50) {
            return "EXCELLENT";
        } else if (packetLoss <= 1 && ping <= 100) {
            return "GOOD";
        } else if (packetLoss <= 5 && ping <= 200) {
            return "POOR";
        } else {
            return "CRITICAL";
        }
    } // Comprueba las condiciones de arriba hacia abajo. Usa && que significa Y — las dos condiciones deben cumplirse a la vez. En cuanto encuentra una que se cumple ejecuta el return y el método termina. El else final captura cualquier caso que no haya coincidido arriba.

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
    } // Tercer método, recibe tres parámetros. La primera línea dentro del método llama a calculateUsage() que ya escribimos antes — reutilizamos el cálculo en vez de repetirlo. Esto se llama reutilización de código y es una práctica fundamental.

    public static String getStatus(int total, int used, double packetLoss, int ping) { //Cuarto método, el más completo. Recibe cuatro parámetros. Reutiliza los dos primeros métodos para obtener el porcentaje de uso y la calidad. Guarda los resultados en variables locales para usarlos en las condiciones de abajo.
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
} //Combina la calidad y el uso para dar el estado final. El || significa O — basta con que una de las dos condiciones se cumpla para entrar en ese bloque. Fíjate que para comparar quality usamos .equals() porque es un String — nunca == para Strings.
