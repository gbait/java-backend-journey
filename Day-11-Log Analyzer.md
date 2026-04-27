# Day 11 - Strings and Their Main Methods

## What Did We Build Today?
A **network log analyzer** that receives a line of text just as it would appear in a real log file, analyzes it using String methods, and extracts useful information from it.

A log is a text file that systems generate automatically to record everything that happens. In real life these files have thousands of lines and the String methods we learned today are exactly the tools used to filter and analyze them.

---

## What is a String?
A String is simply a chain of characters — letters, numbers, spaces and symbols. In Java Strings always go inside double quotes:

```java
String log = "[2024-01-15] ERROR - Connection timeout";
```

---

## Key Concept — Strings are Immutable
In Java Strings **cannot be modified**. Every method that appears to modify a String actually returns a new copy. The original String always stays intact.

```java
String log = "error";
log.toUpperCase(); // this does NOT modify log
System.out.println(log); // still prints "error"

String logUpper = log.toUpperCase(); // you must save the result
System.out.println(logUpper); // now prints "ERROR"
```

> This is one of the most repeated traps in the Oracle exam.

---

## String Methods Explained

### `length()` — String length
Returns the number of characters including spaces:
```java
"ERROR".length()      // returns 5
"ERROR 404".length()  // returns 9
```

---

### `toUpperCase()` and `toLowerCase()` — change case
Return a copy with all letters in uppercase or lowercase:
```java
"error".toUpperCase()  // returns "ERROR"
"ERROR".toLowerCase()  // returns "error"
```

Useful for comparing text regardless of how the user typed it:
```java
String logUpper = log.toUpperCase();
if (logUpper.contains("ERROR")) { // detects "error", "Error" and "ERROR"
```

---

### `trim()` — remove leading and trailing spaces
Returns a copy without spaces at the beginning or end:
```java
"  ERROR  ".trim()  // returns "ERROR"
```

---

### `contains()` — search for text inside the String
Returns `true` if the searched text appears anywhere:
```java
"Connection ERROR on port 443".contains("ERROR")  // true
"Connection ERROR on port 443".contains("error")  // false — case sensitive
```

---

### `startsWith()` and `endsWith()` — check beginning and end
Return `true` if the String starts or ends with the given text:
```java
"[2024-01-15] ERROR".startsWith("[")  // true
"[2024-01-15] ERROR".endsWith("]")    // false
```

---

### `replace()` — substitute text
Returns a copy with all occurrences of the first text replaced by the second:
```java
"ERROR 404".replace("ERROR", "ALERT")  // returns "ALERT 404"
```

---

### `indexOf()` — position of a text
Returns the position where the searched text begins. Returns `-1` if not found:
```java
"ERROR 404".indexOf("ERROR")  // returns 0
"ERROR 404".indexOf("404")    // returns 6
"ERROR 404".indexOf("xyz")    // returns -1
```

Positions start at `0` just like arrays.

---

### `substring()` — extract a piece of the String
Returns a piece of the String from one position to another:
```java
"ERROR 404".substring(0, 5)  // returns "ERROR"
"ERROR 404".substring(6, 9)  // returns "404"
```

The first number is **inclusive** and the second is **exclusive** — the character at the end position is not included. Classic exam trap.

---

### `isEmpty()` — check if empty
Returns `true` if the String has no characters:
```java
"".isEmpty()      // true
"ERROR".isEmpty() // false
```

---

### `equals()` — compare Strings
Compares the exact content of two Strings. Always use instead of `==`:
```java
"ERROR".equals("ERROR")  // true
"ERROR".equals("error")  // false — case sensitive
```

---

## Summary Table for the Exam

| Method | Returns | Example |
|---|---|---|
| `length()` | Number of characters | `"ERROR".length()` → `5` |
| `toUpperCase()` | Uppercase copy | `"error".toUpperCase()` → `"ERROR"` |
| `toLowerCase()` | Lowercase copy | `"ERROR".toLowerCase()` → `"error"` |
| `trim()` | Copy without edge spaces | `"  hi  ".trim()` → `"hi"` |
| `contains()` | `true` / `false` | `"ERROR".contains("ERR")` → `true` |
| `startsWith()` | `true` / `false` | `"ERROR".startsWith("ERR")` → `true` |
| `endsWith()` | `true` / `false` | `"ERROR".endsWith("OR")` → `true` |
| `replace()` | Copy with substitution | `"ERROR".replace("E","A")` → `"ARROR"` |
| `indexOf()` | Position or `-1` | `"ERROR".indexOf("R")` → `2` |
| `substring()` | Piece of the String | `"ERROR".substring(1,3)` → `"RR"` |
| `isEmpty()` | `true` / `false` | `"".isEmpty()` → `true` |
| `equals()` | `true` / `false` | `"ERROR".equals("error")` → `false` |

---

## Full Program

```java
import java.util.Scanner; // fetch Scanner before starting

public class Day11LogAnalyzer { // main class

    public static void main(String[] args) { // program entry point

        Scanner scanner = new Scanner(System.in); // create Scanner

        System.out.println("=== NETWORK LOG ANALYZER ===");
        System.out.print("Enter log line: ");
        String log = scanner.nextLine(); // read the full log line

        System.out.println("\n--- ANALYSIS ---");
        System.out.println("Original:             " + log);
        System.out.println("Length:               " + log.length() + " characters");
        System.out.println("Uppercase:            " + log.toUpperCase());
        System.out.println("Lowercase:            " + log.toLowerCase());
        System.out.println("Trimmed:              " + log.trim());
        System.out.println("Contains ERROR:       " + log.contains("ERROR"));
        System.out.println("Starts with [:        " + log.startsWith("["));
        System.out.println("Ends with ]:          " + log.endsWith("]"));
        System.out.println("Replace ERROR->ALERT: " + log.replace("ERROR", "ALERT"));
        System.out.println("Index of ERROR:       " + log.indexOf("ERROR"));
        System.out.println("Substring (0,10):     " + log.substring(0, 10));
        System.out.println("Is empty:             " + log.isEmpty());

        System.out.println("\n--- SEVERITY CHECK ---");
        String logUpper = log.toUpperCase(); // normalise to compare regardless of case
        if (logUpper.contains("CRITICAL")) {
            System.out.println("Severity: CRITICAL - Immediate action required");
        } else if (logUpper.contains("ERROR")) {
            System.out.println("Severity: ERROR - Needs attention");
        } else if (logUpper.contains("WARNING")) {
            System.out.println("Severity: WARNING - Monitor closely");
        } else if (logUpper.contains("INFO")) {
            System.out.println("Severity: INFO - No action required");
        } else {
            System.out.println("Severity: UNKNOWN - Check log format");
        }

        scanner.close(); // free system resources
    }
}
```

---

## Console Output Example
=== NETWORK LOG ANALYZER ===
Enter log line: [2024-01-15] ERROR - Connection timeout on port 443
--- ANALYSIS ---
Original:             [2024-01-15] ERROR - Connection timeout on port 443
Length:               50 characters
Uppercase:            [2024-01-15] ERROR - CONNECTION TIMEOUT ON PORT 443
Lowercase:            [2024-01-15] error - connection timeout on port 443
Trimmed:              [2024-01-15] ERROR - Connection timeout on port 443
Contains ERROR:       true
Starts with [:        true
Ends with ]:          false
Replace ERROR->ALERT: [2024-01-15] ALERT - Connection timeout on port 443
Index of ERROR:       13
Substring (0,10):     [2024-01-1
Is empty:             false
--- SEVERITY CHECK ---
Severity: ERROR - Needs attention

---

## Oracle Exam Traps to Remember

- **Strings are immutable** — `toUpperCase()` and all other methods return a new copy. If you do not save the result it is lost
- **`substring(start, end)`** — start is inclusive, end is exclusive. `"ERROR".substring(1,3)` → `"RR"` not `"RRO"`
- **`indexOf()` returns `-1`** when the text is not found, it does not throw an error
- **`contains()` is case sensitive** — `"error".contains("ERROR")` → `false`
- **`length()` needs parentheses** — arrays use `array.length` without parentheses, Strings use `string.length()` with parentheses
- **Never `==` to compare Strings** — always `.equals()`
