# Día 13 - Manejo de Excepciones Básico

## ¿Qué hemos construido hoy?
Un **validador de configuración de red** que intenta leer y procesar datos de configuración y maneja de forma controlada todos los errores que pueden ocurrir — números mal introducidos, divisiones por cero, índices fuera de rango.

Hasta ahora cuando el programa encontraba un error simplemente explotaba y mostraba una pantalla de error fea. Con las excepciones el programa captura el error, muestra un mensaje útil y sigue funcionando. Esto es lo que distingue un programa profesional de uno amateur, y Oracle lo examina mucho.

---

## Conceptos clave

### ¿Qué es una excepción?
Una excepción es un objeto que Java crea automáticamente cuando algo sale mal durante la ejecución del programa. Si no la capturas el programa explota y para. Si la capturas con `try-catch` puedes controlar qué pasa cuando ocurre el error.

---

### El bloque `try`
Contiene el código que podría fallar. Java intenta ejecutarlo y si algo sale mal lanza una excepción:

```java
try {
    int port = Integer.parseInt(portInput); // puede fallar si no es número
}
```

---

### El bloque `catch`
Captura el error si ocurre y ejecuta el código de dentro:

```java
catch (NumberFormatException e) {
    System.out.println("ERROR: not a valid number");
}
```

La `e` es el objeto que contiene la información del error. Puedes usar `e.getMessage()` para ver el mensaje técnico del error.

---

### Múltiples `catch`
Un `try` puede tener varios `catch` para distintos tipos de error. Java los comprueba de arriba hacia abajo y entra en el primero que coincida. Solo se ejecuta uno:

```java
try {
    // código
} catch (NumberFormatException e) {
    System.out.println("Not a number");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of range");
}
```

---

### El bloque `finally`
Se ejecuta **siempre**, tanto si hubo error como si no. Se usa para liberar recursos o mostrar mensajes de cierre que deben aparecer pase lo que pase:

```java
try {
    // código
} catch (ArithmeticException e) {
    System.out.println("ERROR: Cannot divide by zero");
} finally {
    System.out.println("Validation complete"); // esto se ejecuta siempre
}
```

---

### Estructura completa
```java
try {
    // código que podría fallar
} catch (TipoDeError e) {
    // qué hacer si ocurre ese error
} catch (OtroTipoDeError e) {
    // qué hacer si ocurre este otro error
} finally {
    // esto se ejecuta siempre, haya error o no
}
```

---

### Las excepciones más importantes para el examen

| Excepción | Cuándo ocurre |
|---|---|
| `NumberFormatException` | `Integer.parseInt("abc")` — texto no es número |
| `ArrayIndexOutOfBoundsException` | Acceder a un índice que no existe en el array |
| `NegativeArraySizeException` | Crear un array con tamaño negativo |
| `ArithmeticException` | División por cero |
| `NullPointerException` | Usar un objeto que vale `null` |
| `ClassCastException` | Casting incorrecto entre tipos incompatibles |

---

## El programa completo

```java
import java.util.Scanner;

public class Day13ExceptionHandling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== NETWORK CONFIG VALIDATOR ===");

        // --- TRY-CATCH BÁSICO ---
        System.out.print("Enter port number: ");
        String portInput = scanner.nextLine();

        try {
            int port = Integer.parseInt(portInput);
            if (port < 1 || port > 65535) {
                System.out.println("ERROR: Port must be between 1 and 65535");
            } else {
                System.out.println("Port " + port + " is valid");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: '" + portInput + "' is not a valid port number");
        }

        // --- MÚLTIPLES CATCH ---
        System.out.print("\nEnter number of servers: ");
        String serversInput = scanner.nextLine();

        try {
            int servers = Integer.parseInt(serversInput);
            int[] serverLoad = new int[servers];
            serverLoad[servers] = 100;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter a valid number");
        } catch (NegativeArraySizeException e) {
            System.out.println("ERROR: Number of servers cannot be negative");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: Server index out of range");
        }

        // --- FINALLY ---
        System.out.print("\nEnter bandwidth to divide: ");
        String bandwidthInput = scanner.nextLine();

        try {
            int bandwidth = Integer.parseInt(bandwidthInput);
            int result = bandwidth / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ERROR: Cannot divide by zero");
        } finally {
            System.out.println("Validation complete");
        }

        scanner.close();
    }
}
```

---

## Lo que aparece en consola

**Con datos correctos:**
