public class Day06NetworkMonitoring { // define la clase del programa
  public static void main(String[] args) {  // Punto de inicio dle programa, java empieza ejecutando main

  int[] traffic = {100, 220, 0, 450, 700}   // crea un array con el trafico de red
      for (int i = 0; i < traffic.length; i++) {  // bucle que recorre rodos los puertos
    // i=0 empieza en el primer puerto
    // i < traffic.length mientras haya puertos
    // i++ pasa al siguiente

            int currentTraffic = traffic[i]; //guarda el trafico del puerto actual

            System.out.print("Port " + (i + 1) + " traffic: " + currentTraffic + " Mbps -> "); // imprime información del puerto

            if (currentTraffic == 0) { //comprueba si el puerto está caido
                System.out.println("PORT DOWN");

            } else if (currentTraffic <= 200) { //trafico normal
                System.out.println("NORMAL");

            } else if (currentTraffic <= 500) { //trafico alto
                System.out.println("HIGH TRAFFIC");

            } else { //trafico critico
                System.out.println("CRITICAL TRAFFIC");
            }

        }

    }
}
