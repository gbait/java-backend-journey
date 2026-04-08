import java.util.Scanner; //Esta línea le dice a Java "ve a buscar el Scanner antes de empezar

public class Day07UserInputMonitoring { // clase ppal del programa

    public static void main(String[] args) { // punto de entrada del programa, java busca esta linea al ejecutar el archivo y empieza desde aqui

        Scanner scanner = new Scanner(System.in); // creamos el objeto Scanner

        System.out.print("Enter server name: "); // muestra texto en pantalla pero sin bajar de linea al final. Si usáramos println el cursor bajaría y quedaría raro visualmente.
        String serverName = scanner.nextLine(); // nextLine() pausa el programa y espera a que el usuario escriba algo y pulse enter. todo lo escrito se guarda en la variable serverName.
                                                // Usamos string por la variable puede contener letras, numeros y espacios

        System.out.print("Enter traffic (Mbps): "); // Igual que antes, muestra la pregunta en pantalla sin bajar de línea, esperando que el usuario escriba el número de tráfico.
        int traffic = Integer.parseInt(scanner.nextLine()); //scanner.nextLine() lee lo que escribe el usuario, por ejemplo "350" — esto es texto, un String
                                                            //Integer.parseInt(...) convierte ese texto al número entero 350  

        System.out.print("Enter location: "); // tercera pregunta al usuario, mismo patrón
        String location = scanner.nextLine(); // Lee el texto que escribe el usuario y lo guarda en location. Como usamos nextLine() para todo no hay problema con el buffer

        System.out.println("--- Server Report ---"); //Ahora si usamos println porque queremos que baje de linea
        System.out.println("Server:   " + serverName); //Imprime la palabra "Server: " seguida del valor que guardamos antes en serverName. El + une los dos textos. Si el usuario escribió WebServer-01, en pantalla aparece Server: WebServer-01.
        System.out.println("Location: " + location); // igual que la linea anterior 
        System.out.println("Status:   " + analyzeTraffic(traffic)); // Ocurren dos cosas, Se llama al método analyzeTraffic pasándole el número de tráfico
                                                                    //Ese método devuelve un texto como "HIGH TRAFFIC" o "NORMAL"
                                                                    //Ese texto se une con "Status: " y se imprime

        scanner.close(); // cerramos el Scanner para liberar recursos del sistema. Como si cerramos le grifo al terminar.
    } // cerramos método main

    public static String analyzeTraffic(int traffic) { //define un metodo separado fuera del main pero dentro de la clase
        if (traffic == 0)        return "PORT DOWN";
        else if (traffic <= 200) return "NORMAL";
        else if (traffic <= 500) return "HIGH TRAFFIC";
        else                     return "CRITICAL TRAFFIC"; // Comprueba el valor de traffic de arriba hacia abajo y devuelve el texto correspondiente en cuanto encuentra la condición que se cumple. En cuanto ejecuta un return el método termina y no sigue comprobando el resto.
    } //Cierra el método analyzeTraffic.
} //Cierra la clase Day07UserInputMonitoring.

