# Day 13 - Basic Exception Handling

## What Did We Build Today?
A **network configuration validator** that attempts to read and process configuration data and handles all errors that can occur in a controlled way — badly entered numbers, division by zero, out of range indexes.

Until now when the program encountered an error it simply crashed and showed an ugly error screen. With exceptions the program catches the error, shows a useful message and keeps running. This is what distinguishes a professional program from an amateur one, and Oracle tests this heavily.

---

## Key Concepts

### What is an Exception?
An exception is an object Java creates automatically when something goes wrong during program execution. If you do not catch it the program crashes and stops. If you catch it with `try-catch` you can control what happens when the error occurs.

---

### The `try` Block
Contains the code that could fail. Java attempts to execute it and if something goes wrong it throws an exception:

```java
try {
    int port = Integer.parseInt(portInput); // can fail if not a number
}
```

---

### The `catch` Block
Catches the error if it occurs and executes the code inside:

```java
catch (NumberFormatException e) {
    System.out.println("ERROR: not a valid number");
}
```

`e` is the object that contains the error information. You can use `e.getMessage()` to see the technical error message.

---

### Multiple `catch` Blocks
One `try` can have several `catch` blocks for different error types. Java checks them from top to bottom and enters the first one that matches. Only one executes:

```java
try {
    // code
} catch (NumberFormatException e) {
    System.out.println("Not a number");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of range");
}
```

---

### The `finally` Block
Executes **always**, whether there was an error or not. Used to free resources or show closing messages that must appear no matter what:

```java
try {
    // code
} catch (ArithmeticException e) {
    System.out.println("ERROR: Cannot divide by zero");
} finally {
    System.out.println("Validation complete"); // always executes
}
```

---

### Full Structure
```java
try {
    // code that could fail
} catch (ErrorType e) {
    // what to do if this error occurs
} catch (OtherErrorType e) {
    // what to do if this other error occurs
} finally {
    // always executes, error or not
}
```

---

### Most Important Exceptions for the Exam

| Exception | When it occurs |
|---|---|
| `NumberFormatException` | `Integer.parseInt("abc")` — text is not a number |
| `ArrayIndexOutOfBoundsException` | Accessing an index that does not exist in the array |
| `NegativeArraySizeException` | Creating an array with a negative size |
| `ArithmeticException` | Division by zero |
| `NullPointerException` | Using an object that is `null` |
| `ClassCastException` | Incorrect casting between incompatible types |

---

## Full Program

```java
import java.util.Scanner;

public class Day13ExceptionHandling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== NETWORK CONFIG VALIDATOR ===");

        // --- BASIC TRY-CATCH ---
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

        // --- MULTIPLE CATCH ---
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

## Console Output Examples

**With correct data:**
=== NETWORK CONFIG VALIDATOR ===
Enter port number: 8080
Port 8080 is valid
Enter number of servers: 3
ERROR: Server index out of range
Enter bandwidth to divide: 100
ERROR: Cannot divide by zero
Validation complete

**With text where a number is expected:**
=== NETWORK CONFIG VALIDATOR ===
Enter port number: abc
ERROR: 'abc' is not a valid port number
Enter number of servers: dos
ERROR: Please enter a valid number
Enter bandwidth to divide: cien
ERROR: Cannot divide by zero
Validation complete

---

## Oracle Exam Traps to Remember

- **`finally` always executes** — even if there is a `return` inside `try` or `catch`
- **Only one `catch` executes** — the first one that matches the error
- **Order of `catch` blocks matters** — if you put a parent exception before a child, the child will never execute
- **`e.getMessage()`** returns the error message as a String
- **A `try` without `catch` is valid if it has `finally`** — Oracle asks if this compiles
