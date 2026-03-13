public class Day05DataCenterAnalysis { // definimos la clase ppal del programa
  
  public static void main (String[] args) { //punto donde comienza la ejecución dle programa
    
    int[] consumos = {4, 9, 12, 17, 0}; // lista de array con los consumos eletricos de los racks

    int totalConsumo = 0; //variabñe que acumula el consumo total
    int maxConsumo = consumos[0]; //inicializa el consumo máximo con el primer rack
    int minConsumo = consumos[0]; // inicializa el consumo mínimo

    for (int i = 0; i < consumos.length; i++) { // bucle que recorre todos los racks

      int consumoActual = consumos[i]; // obtiene el consumo dle rack actual

      totalConsumo = totalConsumo + consumoActual; // va sumando los consumos

      if (consumoActual > maxConsumo) { //comprueba si el rack actual tiene más consumo que el máximo guardado
        maxConsumo = consumoActual; //actualiza el máximo si sí
      }
      if (consumoActual < minConsumo) { //comprueba si el rack tiene menos consumo que le mínimo guardado
        minConsumo = consumoActual; //actualiza el mínimo
      }
    }
    double promedio = (double) totalConsumo / consumos.length;    // calcula el consumo medio se usa double porque puede tener decimales
    System.out.println("Total consumption: " + totalConsumo + " kW");
    System.out.println("Average consumption: " + promedio + " kW");
    System.out.println("Highest consumption: " + maxConsumo + " kW");
    System.out.println("Lowest consumption: " + minConsumo + " kW");

  }
}
