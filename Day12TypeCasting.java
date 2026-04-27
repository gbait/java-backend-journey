public class Day12TypeCasting { // clase principal, no necesita Scanner

    public static void main(String[] args) { // punto de entrada

        // --- WIDENING CASTING (automático) ---
        // Java convierte automáticamente de tipo pequeño a tipo grande
        int bytes = 1500;
        double bytesAsDouble = bytes; // int → double, Java lo hace solo
        System.out.println("=== WIDENING CASTING (automatic) ===");
        System.out.println("int bytes:         " + bytes);
        System.out.println("double bytes:      " + bytesAsDouble);

        // --- NARROWING CASTING (manual) ---
        // Java NO convierte automáticamente de tipo grande a tipo pequeño
        // hay que forzarlo con (tipo)
        double mbps = 99.9;
        int mbpsAsInt = (int) mbps; // double → int, hay que forzarlo
        System.out.println("\n=== NARROWING CASTING (manual) ===");
        System.out.println("double mbps:       " + mbps);
        System.out.println("int mbps:          " + mbpsAsInt); // pierde los decimales

        // --- CONVERSIONES DE UNIDADES DE RED ---
        System.out.println("\n=== NETWORK UNIT CONVERTER ===");
        int totalBits = 125000000; // 125 millones de bits

        double toBytes     = (double) totalBits / 8;
        double toKilobytes = toBytes / 1024;
        double toMegabytes = toKilobytes / 1024;
        double toGigabytes = toMegabytes / 1024;

        System.out.println("Bits:      " + totalBits);
        System.out.println("Bytes:     " + toBytes);
        System.out.println("Kilobytes: " + toKilobytes);
        System.out.println("Megabytes: " + toMegabytes);
        System.out.println("Gigabytes: " + toGigabytes);

        // --- CONVERSIÓN DE TIPOS STRING ---
        System.out.println("\n=== STRING CONVERSIONS ===");
        String speedText = "1000";
        int speedInt     = Integer.parseInt(speedText);    // String → int
        double speedDouble = Double.parseDouble(speedText); // String → double
        String backToString = String.valueOf(speedInt);     // int → String

        System.out.println("String to int:    " + speedInt);
        System.out.println("String to double: " + speedDouble);
        System.out.println("Int to String:    " + backToString);

        // --- OVERFLOW ---
        System.out.println("\n=== OVERFLOW ===");
        int maxInt = Integer.MAX_VALUE; // el número más grande que cabe en un int
        System.out.println("Max int value:    " + maxInt);
        System.out.println("Max int + 1:      " + (maxInt + 1)); // overflow — da negativo
    }
}
