public class Day05DataCenterAnalysis {
  
  public static void main (String[] args) {
    
    int[] consumos = {4, 9, 12, 17, 0};

    int totalConsumo = 0;
    int maxConsumo = consumos[0];
    int minConsumo = consumos[0];

    for (int i = 0; i < consumos.length; i++) {

      int consumoActual = consumos[i];

      totalConsumo = totalConsumo + consumoActual;

      if (consumoActual > maxConsumo) {
        maxConsumo = consumoActual;
      }
      if (consumoActual < minConsumo) {
        minConsumo = consumoActual;
      }
    }
    double promedio = (double) totalConsumo / consumos.length;
    System.out.println("Total consumption: " + totalConsumo + " kW");
    System.out.println("Average consumption: " + promedio + " kW");
    System.out.println("Highest consumption: " + maxConsumo + " kW");
    System.out.println("Lowest consumption: " + minConsumo + " kW");

  }
}
