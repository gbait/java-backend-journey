# Day 09 - Switch Statement and Fall-Through

## What Did We Build Today?
A **network alert management system** that receives an error code and depending on the code displays the alert type, priority and recommended action.

We built this with `switch` instead of `if/else if` because when you have many concrete cases to compare against a single value, `switch` is cleaner and more readable. This is exactly the situation Oracle uses to justify when to choose one over the other.

---

## What is a Switch?
The `switch` is a control structure that compares a variable against a list of concrete values called `case`. It is the alternative to `if/else if` when you have many exact cases to check.

```java
switch (errorCode) {
    case 1:
        // code if errorCode equals 1
        break;
    case 2:
        // code if errorCode equals 2
        break;
    default:
        // code if no case matches
}
```

Java compares the variable from top to bottom until it finds a matching `case`. Once found, it executes the code in that block.

---

## Key Concepts

### The `break` — The Most Important Thing Today
`break` tells Java "stop here and exit the switch". Without it Java does not stop — it keeps executing the next `case` even if it does not match. This is called **fall-through**.

```java
case 1:
    System.out.println("CONNECTION LOST");
    break; // without this, case 2 would also execute
case 2:
    System.out.println("HIGH LATENCY");
    break;
```

---

### Fall-Through — Classic Exam Trap
If we remove the `break` from `case 1`, this is what happens when the user enters `1`:

```java
case 1:
    System.out.println("CONNECTION LOST");
    // no break — falls through to the next case
case 2:
    System.out.println("HIGH LATENCY");
    break;
```

Console output:
CONNECTION LOST
HIGH LATENCY

Java does not stop at `case 1` because there is no `break`, so it falls straight into `case 2` and executes it as well. Oracle asks "what does this code print?" knowing that students forget about fall-through.

---

### The `default`
This is the equivalent of `else` in an `if`. It executes when no `case` matches the variable value. It does not need a `break` because it is the last block and the switch ends on its own.

```java
default:
    System.out.println("UNKNOWN ERROR");
```

---

### `switch` vs `if/else` — When to Use Each One

| Situation | Use |
|---|---|
| Comparing a value against concrete cases | `switch` |
| Comparing ranges like `traffic <= 200` | `if/else` |
| Complex conditions with `&&` or `||` | `if/else` |

`switch` can only compare exact equality, not ranges. That is why in Day 07 we used `if/else` for traffic — `traffic <= 200` cannot go inside a `case`.

---

## Full Program

```java
import java.util.Scanner; // fetch Scanner before starting

public class Day09AlertSystem { // main class

    public static void main(String[] args) { // program entry point

        Scanner scanner = new Scanner(System.in); // create Scanner, listen to keyboard

        System.out.println("=== NETWORK ALERT SYSTEM ===");
        System.out.print("Enter error code (1-5): "); // ask for error code
        int errorCode = Integer.parseInt(scanner.nextLine()); // read code as integer

        System.out.println("\n--- ALERT REPORT ---");

        switch (errorCode) { // compare errorCode against each case
            case 1:
                System.out.println("Type:     CONNECTION LOST");
                System.out.println("Priority: CRITICAL");
                System.out.println("Action:   Check physical cables");
                break; // without break, case 2 would also execute
            case 2:
                System.out.println("Type:     HIGH LATENCY");
                System.out.println("Priority: HIGH");
                System.out.println("Action:   Check bandwidth usage");
                break;
            case 3:
                System.out.println("Type:     PACKET LOSS");
                System.out.println("Priority: HIGH");
                System.out.println("Action:   Run network diagnostics");
                break;
            case 4:
                System.out.println("Type:     DNS FAILURE");
                System.out.println("Priority: MEDIUM");
                System.out.println("Action:   Check DNS configuration");
                break;
            case 5:
                System.out.println("Type:     LOW SIGNAL");
                System.out.println("Priority: LOW");
                System.out.println("Action:   Check antenna position");
                break;
            default: // if the code does not match any case
                System.out.println("Type:     UNKNOWN ERROR");
                System.out.println("Priority: UNKNOWN");
                System.out.println("Action:   Contact support");
        }

        scanner.close(); // free system resources
    }
}
```

---

## Console Output Examples

**If you enter 1:**
=== NETWORK ALERT SYSTEM ===
Enter error code (1-5): 1
--- ALERT REPORT ---
Type:     CONNECTION LOST
Priority: CRITICAL
Action:   Check physical cables

**If you enter 7:**
=== NETWORK ALERT SYSTEM ===
Enter error code (1-5): 7
--- ALERT REPORT ---
Type:     UNKNOWN ERROR
Priority: UNKNOWN
Action:   Contact support

---

## Common Mistakes to Avoid

- **Forgetting `break`** → causes fall-through, the code keeps executing cases that do not match
- **Using ranges in a `case`** → `case traffic <= 200` does not exist in Java, use `if/else` for ranges
- **Forgetting `default`** → if no case matches and there is no default, the switch does nothing and gives no warning

---

## Oracle Exam Traps to Remember

- **Fall-through:** Oracle shows code without `break` and asks what it prints. Always read the full switch looking for `break` statements
- **`default` does not have to be last:** it can go anywhere inside the switch, although by convention it goes at the end
- **`switch` accepts:** `int`, `char`, `String` and `enum`. It does NOT accept `double`, `float` or `long`. Oracle asks this directly
- **No matching `case` and no `default`:** the switch is skipped completely, no code runs and no error is thrown
