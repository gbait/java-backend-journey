public class Day04ConditionsMonitoring {  // definimos la clase del programa
    public static void main(String[] args) { // Punto donde empiez a aejecutarse el programa (accesible desde fuera, no hace falta crear objeto, no devuelv valor, recibe argumentos strings)
        int[] consumos = {4, 9, 12, 17, 0};  // array con el consumo de los racks

        for (int i = 0; i < consumos.length; i++) { //este bucle recorre todos los racks uno a uno
            int consumo = consumos[i]; // guarda el consumo dle rack actual
        
        System.out.println( "Rack " + (i + 1) + " -> " + consumo + "kW"); //muestra consumo de rack en pantalla

    if (consumo == 0) { // comprueba si el rack está apagado o ha fallado
        System.out.println("Estado: FALLO o apagado");

    } else if (consumo <= 10) {  // si no está apagado, comrpueba que el consumo es normal
        System.out.println("Estado: consumo normal");

    } else if (consumo <= 16) {  // detecta consumo alto peor todavía permitido
        System.out.println("Estado: consumo alto");

    } else { // si ninguna condición anterior se cumnple, es decir el consumo supera los 16 kW
        System.out.println("Estado: consumo CRÍTICO");
    }

    System.out.println("------------------------------");
        }
    }
}
