# Day 10 - Methods with Multiple Parameters and Code Reuse

## What Did We Build Today?
A **network metrics calculator** that uses multiple methods, each with their own parameters and return values, to analyse the status of a server.

Until now our methods were simple — they received one value and returned text. Today methods perform real calculations, receive several parameters at once, and return different data types. This is the concept Oracle focuses on most before entering object oriented programming.

---

## Key Concepts

### Methods with Multiple Parameters
A method can receive several parameters separated by commas. Each parameter has its own type and name:

```java
public static double calculateUsage(int total, int used) {
    return (double) used / total * 100;
}
```

- Receives two parameters — `total` and `used`, both `int`
- Returns a `double` — the result can have decimals
- Parameters are local variables — they only exist inside this method

---

### Methods with Mixed Parameter Types
A method can mix different parameter types in the same signature:

```java
public static String evaluateQuality(double packetLoss, int ping) {
```

The first parameter is `double` and the second is `int`. Order matters — when calling the method you must pass values in the exact same order they are declared.

---

### Code Reuse — The Most Important Concept Today
A method can call another method inside itself. Instead of repeating the same calculation in multiple places, you write it once and reuse it:

```java
public static String getRecommendation(int total, int used, double packetLoss) {
    double usage = calculateUsage(total, used); // reusing method 1
}

public static String getStatus(int total, int used, double packetLoss, int ping) {
    double usage = calculateUsage(total, used);       // reusing method 1 again
    String quality = evaluateQuality(packetLoss, ping); // reusing method 2
}
```

If the formula inside `calculateUsage` ever changes, you only update it in one place and the entire program updates automatically.

---

### `Double.parseDouble()` — New Conversion Method
Just like `Integer.parseInt()` converts text to an integer, `Double.parseDouble()` converts text to a decimal:

```java
double packetLoss = Double.parseDouble(scanner.nextLine());
```

The three conversion methods to remember — all with the class in uppercase:

| Converts to | Method |
|---|---|
| `int` | `Integer.parseInt()` |
| `double` | `Double.parseDouble()` |
| `long` | `Long.parseLong()` |

---

### The `(double)` Cast — Forcing Decimal Division
When you divide two integers Java performs integer division and rounds down:

```java
int used = 850;
int total = 1000;

used / total           // result: 0    ← integer division, decimals lost
(double) used / total  // result: 0.85 ← cast forces decimal division
```

The `(double)` before `used` tells Java: "treat this number as a decimal before dividing". Without it `850 / 1000` gives `0` instead of `0.85`.

---

### Comparing Strings Inside Methods
When a method returns a String and we use it in a condition, we always use `.equals()`:

```java
String quality = evaluateQuality(packetLoss, ping); // returns "POOR", "GOOD"...

if (quality.equals("CRITICAL") || usage >= 90) { // always .equals() for Strings
    return "[CRITICAL]";
}
```

---

## Full Program

```java
import java.util.Scanner; // fetch Scanner before starting

public class Day10NetworkMetrics { // main class

    public static void main(String[] args) { // program entry point

        Scanner scanner = new Scanner(System.in); // create Scanner

        System.out.println("=== NETWORK METRICS CALCULATOR ===");

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine(); // read server name

        System.out.print("Enter total bandwidth (Mbps): ");
        int totalBandwidth = Integer.parseInt(scanner.nextLine()); // total bandwidth

        System.out.print("Enter used bandwidth (Mbps): ");
        int usedBandwidth = Integer.parseInt(scanner.nextLine()); // used bandwidth

        System.out.print("Enter packet loss (%): ");
        double packetLoss = Double.parseDouble(scanner.nextLine()); // packet loss

        System.out.print("Enter ping (ms): ");
        int ping = Integer.parseInt(scanner.nextLine()); // ping in milliseconds

        System.out.println("\n=== SERVER REPORT: " + serverName + " ===");
        System.out.println("Bandwidth usage:  " + calculateUsage(totalBandwidth, usedBandwidth) + "%");
        System.out.println("Network quality:  " + evaluateQuality(packetLoss, ping));
        System.out.println("Recommendation:   " + getRecommendation(totalBandwidth, usedBandwidth, packetLoss));
        System.out.println("Status:           " + getStatus(totalBandwidth, usedBandwidth, packetLoss, ping));

        scanner.close(); // free system resources
    }

    // method 1 — calculates bandwidth usage percentage
    public static double calculateUsage(int total, int used) {
        return (double) used / total * 100; // cast to avoid integer division
    }

    // method 2 — evaluates network quality based on packet loss and ping
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

    // method 3 — returns recommendation based on usage and packet loss
    public static String getRecommendation(int total, int used, double packetLoss) {
        double usage = calculateUsage(total, used); // reuse method 1
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

    // method 4 — combines everything to give the final server status
    public static String getStatus(int total, int used, double packetLoss, int ping) {
        double usage = calculateUsage(total, used); // reuse method 1
        String quality = evaluateQuality(packetLoss, ping); // reuse method 2

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

## Console Output Example
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

## Mistakes Made Today and What They Taught Us

- **`double.parsedouble()`** → must be `Double.parseDouble()` — classes always uppercase
- **Emojis in OnlineGDB** → OnlineGDB does not support emojis, use plain text like `[CRITICAL]`
- **Buffer issue in OnlineGDB** → with multiple Scanner inputs, enter all data in the `stdin` tab before running

---

## Oracle Exam Traps to Remember

- **Integer division** → `850 / 1000 = 0` in Java. Use `(double)` cast to get decimals
- **Parameter order matters** → if declared as `(int total, int used)` you must call the method in that exact order
- **Local variables** → method parameters only exist inside that method, not accessible from outside
- **A method can call another method** — Oracle tests code reuse and the flow of calls between methods
