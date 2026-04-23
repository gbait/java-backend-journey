# Día 10 - Métodos con Múltiples Parámetros y Reutilización de Código

## ¿Qué hemos construido hoy?
Un **sistema de cálculo de métricas de red** que usa múltiples métodos, cada uno con sus propios parámetros y valores de retorno, para analizar el estado de un servidor.

Hasta ahora nuestros métodos eran simples — recibían un dato y devolvían un texto. Hoy los métodos hacen cálculos reales, reciben varios parámetros a la vez y devuelven distintos tipos de datos. Es el concepto que Oracle más trabaja antes de entrar en orientación a objetos.

---

## Conceptos clave

### Métodos con múltiples parámetros
Un método puede recibir varios parámetros separados por coma. Cada parámetro tiene su tipo y su nombre:

```java
public static double calculateUsage(int total, int used) {
    return (double) used / total * 100;
}
```

- Recibe dos parámetros — `total` y `used`, ambos `int`
- Devuelve un `double` — el resultado puede tener decimales
- Los parámetros son variables locales — solo existen dentro de este método

---

### Métodos con parámetros de tipos distintos
Un método puede mezclar tipos de parámetros distintos en la misma firma:

```java
public static String evaluateQuality(double packetLoss, int ping) {
```

Aquí el primer parámetro es `double` y el segundo es `int`. El orden importa — cuando llamas al método debes pasar los valores en el mismo orden en que están declarados.

---

### Reutilización de código — el concepto más importante del día
Un método puede llamar a otro método dentro de sí mismo. En vez de repetir el mismo cálculo en varios sitios, lo escribes una vez y lo reutilizas:

```java
public static String getRecommendation(int total, int used, double packetLoss) {
    double usage = calculateUsage(total, used); // reutilizamos el método 1
    // ...
}

public static String getStatus(int total, int used, double packetLoss, int ping) {
    double usage = calculateUsage(total, used);  // reutilizamos el método 1 de nuevo
    String quality = evaluateQuality(packetLoss, ping); // reutilizamos el método 2
    // ...
}
```

Si la fórmula de `calculateUsage` cambia algún día, solo la tocas en un sitio y todo el programa se actualiza. Esto es fundamental en programación y es la base de lo que verás en orientación a objetos.

---

### `Double.parseDouble()` — nuevo método de conversión
Igual que `Integer.parseInt()` convierte texto a entero, `Double.parseDouble()` convierte texto a decimal:

```java
double packetLoss = Double.parseDouble(scanner.nextLine());
```

Los tres métodos de conversión que debes recordar — todos con la clase en mayúscula:

| Convierte a | Método |
|---|---|
| `int` | `Integer.parseInt()` |
| `double` | `Double.parseDouble()` |
| `long` | `Long.parseLong()` |

---

### El cast `(double)` — forzar división decimal
Cuando divides dos enteros Java hace división entera y redondea hacia abajo:

```java
int used = 850;
int total = 1000;

used / total           // resultado: 0  ← división entera, pierde los decimales
(double) used / total  // resultado: 0.85 ← cast fuerza la división decimal
```

El `(double)` antes de `used` le dice a Java: "trata este número como decimal antes de dividir". Sin él `850 / 1000` da `0` en vez de `0.85`.

---

### Comparar Strings dentro de métodos
Cuando un método devuelve un String y lo usamos en una condición, seguimos usando `.equals()`:

```java
String quality = evaluateQuality(packetLoss, ping); // devuelve "POOR", "GOOD"...

if (quality.equals("CRITICAL") || usage >= 90) { // .equals() siempre para Strings
    return "[CRITICAL]";
}
```

---

## El programa completo

```java
import java.util.Scanner; // busca el Scanner antes de empezar

public class Day10NetworkMetrics { // clase principal

    public static void main(String[] args) { // punto de entrada

        Scanner scanner = new Scanner(System.in); // crea el Scanner

        System.out.println("=== NETWORK METRICS CALCULATOR ===");

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine(); // lee el nombre del servidor

        System.out.print("Enter total bandwidth (Mbps): ");
        int totalBandwidth = Integer.parseInt(scanner.nextLine()); // ancho de banda total

        System.out.print("Enter used bandwidth (Mbps): ");
        int usedBandwidth = Integer.parseInt(scanner.nextLine()); // ancho de banda usado

        System.out.print("Enter packet loss (%): ");
        double packetLoss = Double.parseDouble(scanner.nextLine()); // pérdida de paquetes

        System.out.print("Enter ping (ms): ");
        int ping = Integer.parseInt(scanner.nextLine()); // ping en milisegundos

        System.out.println("\n=== SERVER REPORT: " + serverName + " ===");
        System.out.println("Bandwidth usage:  " + calculateUsage(totalBandwidth, usedBandwidth) + "%");
        System.out.println("Network quality:  " + evaluateQuality(packetLoss, ping));
        System.out.println("Recommendation:   " + getRecommendation(totalBandwidth, usedBandwidth, packetLoss));
        System.out.println("Status:           " + getStatus(totalBandwidth, usedBandwidth, packetLoss, ping));

        scanner.close(); // libera recursos
    }

    // método 1 — calcula el porcentaje de uso del ancho de banda
    public static double calculateUsage(int total, int used) {
        return (double) used / total * 100; // cast para evitar división entera
    }

    // método 2 — evalúa la calidad según pérdida de paquetes y ping
    public static String evaluateQuality(double packetLoss, int ping) {
        if (packetLoss == 0 && ping <= 50) {
            return "EXCELLENT";
        } else if (packetLoss <= 1 && ping <= 100) {
            return "GOOD";
        } else if (packetLoss <= 5 && ping <= 200) {
            return "POOR";
        } else {
            return "CRITICAL";
        }
    }

    // método 3 — devuelve recomendación basada en uso y pérdida de paquetes
    public static String getRecommendation(int total, int used, double packetLoss) {
        double usage = calculateUsage(total, used); // reutiliza el método 1
        if (usage >= 90) {
            return "Upgrade bandwidth immediately";
        } else if (usage >= 70 && packetLoss > 1) {
            return "Monitor closely and reduce traffic";
        } else if (packetLoss > 5) {
            return "Check network hardware";
        } else {
            return "No action required";
        }
    }

    // método 4 — combina todo para dar el estado final del servidor
    public static String getStatus(int total, int used, double packetLoss, int ping) {
        double usage = calculateUsage(total, used); // reutiliza el método 1
        String quality = evaluateQuality(packetLoss, ping); // reutiliza el método 2

        if (quality.equals("CRITICAL") || usage >= 90) {
            return "[CRITICAL]";
        } else if (quality.equals("POOR") || usage >= 70) {
            return "[WARNING]";
        } else {
            return "[ONLINE]";
        }
    }
}
```

---

## Lo que aparece en consola
=== NETWORK METRICS CALCULATOR ===
Enter server name: WebServer-01
Enter total bandwidth (Mbps): 1000
Enter used bandwidth (Mbps): 850
Enter packet loss (%): 2.5
Enter ping (ms): 120
=== SERVER REPORT: WebServer-01 ===
Bandwidth usage:  85.0%
Network quality:  POOR
Recommendation:   Monitor closely and reduce traffic
Status:           [WARNING]

---

## Errores cometidos hoy y lecciones aprendidas

- **`double.parsedouble()`** → debe ser `Double.parseDouble()` — clase en mayúscula siempre
- **Emojis en OnlineGDB** → OnlineGDB no soporta emojis, usar texto plano como `[CRITICAL]`
- **Error de buffer en OnlineGDB** → con múltiples Scanner seguidos hay que introducir todos los datos en la pestaña `stdin` antes de ejecutar

---

## Trampas del examen Oracle

- **División entera** → `850 / 1000 = 0` en Java. Para obtener decimales usa `(double)` antes de dividir
- **El orden de los parámetros importa** → si declaras `(int total, int used)` debes llamar al método en ese mismo orden
- **Variables locales** → los parámetros de un método solo existen dentro de ese método, no son accesibles desde fuera
- **Un método puede llamar a otro método** — Oracle examina la reutilización de código y el flujo de llamadas entre métodos
