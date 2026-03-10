public class Day04ConditionsMonitoring {

    public static void main(String[] args) {

        int[] consumos = {4, 9, 12, 17, 0};

        for (int i = 0; i < consumos.length; i++) {

            int consumo = consumos[i];

            System.out.println("Rack " + (i + 1) + " -> " + consumo + " kW");

            if (consumo == 0) {
                System.out.println("Estado: FALLO o apagado");

            } else if (consumo <= 10) {
                System.out.println("Estado: consumo normal");

            } else if (consumo <= 16) {
                System.out.println("Estado: consumo alto");

            } else {
                System.out.println("Estado: CONSUMO CRÍTICO");
            }

            System.out.println("--------------------");
        }
    }
}
