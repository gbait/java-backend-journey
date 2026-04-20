# Day 08 - While, Do-While Loops and Boolean Flags

## What Did We Build Today?
A **network diagnostic tool** that simulates what a systems technician would do when a server is not responding. The program has two phases:

**Phase 1 — Connection attempts:** The program asks for a server IP and checks if it is correct. You have 3 attempts. If all three fail, the program ends with an unreachable message.

**Phase 2 — Command menu:** If the connection succeeds, a menu opens where you can run commands like `ping` or `status`. The menu stays active until you type `exit`.

We built this program with two different loops because each one fits a different situation — and that difference is exactly what Oracle tests in the exam.

---

## The Three Java Loops — When to Use Each One

| Loop | Condition checked | Minimum executions |
|---|---|---|
| `for` | At the beginning | 0 times |
| `while` | At the beginning | 0 times |
| `do-while` | At the end | **Always 1 time** |

The key exam question is: **how many times does this block execute?** The answer depends entirely on which loop you are using.

---

## Key Concepts

### The `do-while` Loop
The code inside runs **first** and the condition is checked **afterwards**. This guarantees the block always executes at least once, no matter what.

```java
do {
    attempts++;
    // ask for IP and check connection
} while (!connected && attempts < maxAttempts);
```

We use `do-while` for the connection attempts because it always makes sense to try at least once. There is no point checking if you are connected before you have even tried.

---

### The `while` Loop
The condition is checked **before** the block runs. If the condition is false from the start, the block executes zero times.

```java
while (!command.equals("exit")) {
    // read and process command
}
```

We use `while` for the command menu because the user might type `exit` immediately without running any commands. The loop must be able to execute zero times.

---

### The `boolean` Flag
A `boolean` can only be `true` or `false`. We use it as a flag — it starts as `false` and flips to `true` when the connection succeeds.

```java
boolean connected = false; // starts as not connected

if (ip.equals("192.168.1.1")) {
    connected = true; // flip the flag when connection succeeds
}
```

---

### The `++` Operator
`attempts++` is a shortcut for `attempts = attempts + 1`. We put it as the first line inside the loop so the first attempt counts as attempt 1, not attempt 0.

---

### Comparing Strings with `.equals()`
In Java you **never use `==` to compare Strings**. This is a classic Oracle exam trap.

```java
// ❌ Wrong — compares memory addresses, not content
if (ip == "192.168.1.1")

// ✅ Correct — compares the actual text content
if (ip.equals("192.168.1.1"))
```

---

### Initialising Variables Before a `while` Loop
The condition of a `while` loop needs the variable to exist before the loop starts.

```java
String command = ""; // must exist before the while condition reads it

while (!command.equals("exit")) {
    command = scanner.nextLine();
}
```

---

### The `!` Operator
`!` means NOT. It flips a boolean value:
- `!connected` means "NOT connected" → `true` when `connected` is `false`
- `!command.equals("exit")` means "command is NOT exit"

---

## Full Program

```java
import java.util.Scanner; // fetch Scanner before starting

public class Day08NetworkDiagnostic { // main class

    public static void main(String[] args) { // program entry point

        Scanner scanner = new Scanner(System.in); // create Scanner, listen to keyboard
        int attempts = 0; // attempt counter, starts at 0
        int maxAttempts = 3; // maximum attempts allowed
        boolean connected = false; // connection flag, starts as false

        System.out.println("=== NETWORK DIAGNOSTIC TOOL ==="); // program header

        do { // code runs FIRST, condition checked AFTERWARDS
            attempts++; // add 1 to counter
            System.out.print("Attempt " + attempts + " - Enter server IP: "); // ask for IP
            String ip = scanner.nextLine(); // read IP from user

            if (ip.equals("192.168.1.1")) { // .equals() to compare Strings, never ==
                connected = true; // flip the flag, loop will stop
                System.out.println("SUCCESS: Connected to " + ip);
            } else {
                System.out.println("FAILED: Could not reach " + ip);
            }

        } while (!connected && attempts < maxAttempts); // keep going while NOT connected AND attempts < 3

        if (connected) { // check if we exited because of connection or exhausted attempts
            System.out.println("\n=== DIAGNOSTIC MENU ===");
            String command = ""; // empty text so the while can read it from the start

            while (!command.equals("exit")) { // keep going until user types exit
                System.out.print("Enter command (ping / status / exit): ");
                command = scanner.nextLine(); // read command from user

                if (command.equals("ping")) {
                    System.out.println("Pinging server... OK");
                } else if (command.equals("status")) {
                    System.out.println("Server status: ONLINE");
                } else if (!command.equals("exit")) { // avoids showing "Unknown command" when exiting
                    System.out.println("Unknown command: " + command);
                }
            }
            System.out.println("Disconnected. Goodbye.");
        } else {
            System.out.println("Max attempts reached. Server unreachable.");
        }

        scanner.close(); // free system resources
    } // closes main
} // closes class
```

---

## Console Output Examples

**Failing once then connecting:**
