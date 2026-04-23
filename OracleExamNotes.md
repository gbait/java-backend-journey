# Oracle Exam Key Notes
> Updated daily with the most important concepts and traps for the Oracle Java Certification exam (1Z0-829).

---

## General Rules

- Java is **case-sensitive** everywhere — `string` and `String` are completely different things
- Every statement ends with a semicolon `;`
- Every opening brace `{` needs its closing brace `}`
- The class name must match the filename exactly, including uppercase and lowercase
- `main` is always the entry point — Java always starts executing from here

---

## Data Types

### Primitives — always lowercase
| Type | What it stores | Example |
|---|---|---|
| `int` | Whole numbers | `int age = 25;` |
| `double` | Decimal numbers | `double price = 9.99;` |
| `boolean` | True or false only | `boolean connected = false;` |
| `char` | A single character | `char letter = 'A';` |

### Classes — always uppercase
| Type | What it stores | Example |
|---|---|---|
| `String` | Text | `String name = "Server";` |
| `Integer` | Whole number as object | `Integer.parseInt("350")` |
| `Double` | Decimal number as object | `Double.parseDouble("2.5")` |

> **Exam trap:** `string`, `integer`, `double` in lowercase do not exist as classes. Always uppercase.

---

## Type Conversion

| Converts to | Method | Example |
|---|---|---|
| `int` | `Integer.parseInt()` | `Integer.parseInt("350")` |
| `double` | `Double.parseDouble()` | `Double.parseDouble("2.5")` |
| `long` | `Long.parseLong()` | `Long.parseLong("123456")` |

---

## Integer Division and Casting

When dividing two integers Java rounds down and loses decimals:
```java
850 / 1000           // result: 0  ← decimals lost
(double) 850 / 1000  // result: 0.85 ← cast forces decimal division
```

> **Exam trap:** Oracle shows division operations and asks for the result. Always check if both values are integers.

---

## Variables

- Must be declared before use
- Name must match exactly everywhere, including uppercase and lowercase
- Variables declared inside a block `{}` only exist inside that block (local scope)
- Method parameters are local variables — they only exist inside that method

---

## Arrays

- Fixed size — once created the size cannot change
- Index starts at **0**, not 1
- Last element is always at index `array.length - 1`

```java
int[] numbers = {4, 9, 12, 17, 0};
// numbers[0] = 4  (first element)
// numbers[4] = 0  (last element)
// numbers.length = 5
```

> **Exam trap:** Accessing an index that does not exist throws `ArrayIndexOutOfBoundsException`

### Finding max and min in an array
Always initialise with the first element, never with 0:
```java
int max = array[0]; // correct
int max = 0;        // wrong — fails if all values are negative
```

---

## Operators

### Arithmetic
| Operator | Meaning | Example |
|---|---|---|
| `+` | Addition | `5 + 3 = 8` |
| `-` | Subtraction | `5 - 3 = 2` |
| `*` | Multiplication | `5 * 3 = 15` |
| `/` | Division | `5 / 2 = 2` (integer division) |
| `%` | Remainder | `5 % 2 = 1` |

### Increment and Decrement
```java
attempts++;   // returns value BEFORE adding 1
++attempts;   // returns value AFTER adding 1
```
> **Exam trap:** Oracle tests the difference between `x++` and `++x` inside expressions

### Logical
| Operator | Meaning | Example |
|---|---|---|
| `&&` | AND — both must be true | `x > 0 && x < 10` |
| `||` | OR — at least one must be true | `x == 0 || x == 5` |
| `!` | NOT — flips the value | `!connected` |

---

## String Comparison

**Never use `==` to compare Strings. Always use `.equals()`**

```java
// ❌ Wrong — compares memory addresses, not content
if (name == "Server")

// ✅ Correct — compares actual text content
if (name.equals("Server"))
```

> **Exam trap:** This is one of the most tested traps in the entire exam

---

## Scanner and User Input

Always import before the class:
```java
import java.util.Scanner;
```

### The buffer trap
**Never use `nextInt()` if you are going to use `nextLine()` afterwards.**

```java
// ❌ Dangerous — leaves \n in the buffer
int number = scanner.nextInt();
String text = scanner.nextLine(); // gets skipped

// ✅ Safe
int number = Integer.parseInt(scanner.nextLine());
String text = scanner.nextLine(); // works correctly
```

### `print` vs `println`
```java
System.out.print("text");    // cursor stays on the same line — use for user prompts
System.out.println("text");  // cursor moves to next line — use for results and headers
```

### Always close the Scanner
```java
scanner.close();
```

---

## Control Flow

### if / else if / else
Use when comparing **ranges** or **complex conditions**:
```java
if (traffic <= 200) {
    return "NORMAL";
} else if (traffic <= 500) {
    return "HIGH";
} else {
    return "CRITICAL";
}
```

---

## Loops

### The three loops compared
| Loop | Condition checked | Minimum executions |
|---|---|---|
| `for` | At the beginning | 0 times |
| `while` | At the beginning | 0 times |
| `do-while` | At the end | **Always 1 time** |

> **Exam trap:** Oracle asks "how many times does this block execute?"

### for loop — use when you know the exact number of repetitions
```java
for (int i = 0; i < array.length; i++) {
    // executes array.length times
}
```

### while loop — use when you do not know how many times
```java
String command = ""; // initialise before the loop
while (!command.equals("exit")) {
    command = scanner.nextLine();
}
```

### do-while loop — use when the block must execute at least once
```java
do {
    // always executes at least once
} while (condition);
```

---

## Switch Statement

Use when comparing **one variable against many exact values**:
```java
switch (variable) {
    case 1:
        // code
        break;
    default:
        // executes if no case matches
}
```

### Rules
- **Always use `break`** or fall-through occurs
- `default` can go anywhere, not just at the end
- `switch` accepts: `int`, `char`, `String`, `enum`
- `switch` does **NOT** accept: `double`, `float`, `long`

### Fall-through — most tested switch trap
```java
case 1:
    System.out.println("ONE");
    // no break — falls through to case 2
case 2:
    System.out.println("TWO");
    break;
```
If variable is `1`, output is `ONE` then `TWO`.

---

## Methods

### Declaring a method
```java
public static String analyzeTraffic(int traffic) {
    return "result";
}
```
- `public` — anyone can call this method
- `static` — required to call from `main`
- `String` — return type
- `(int traffic)` — parameter, local to this method

### Multiple parameters
```java
public static double calculateUsage(int total, int used) {
    return (double) used / total * 100;
}
```
- Parameters separated by commas
- Order matters when calling the method
- Each parameter is a local variable

### Code reuse — a method can call another method
```java
public static String getStatus(int total, int used) {
    double usage = calculateUsage(total, used); // calling another method
    // ...
}
```

### void methods — no return value
```java
public static void printReport() {
    System.out.println("Report");
}
```

> **Exam trap:** A method declared with a return type MUST return a value on every possible path

---

## Common Compilation Errors

| Error | Most likely cause |
|---|---|
| `';' expected` | Missing semicolon at end of line |
| `reached end of file while parsing` | Missing closing brace `}` |
| `cannot find symbol` | Variable name typo or wrong uppercase/lowercase |
| `unclosed string literal` | Missing closing quote `"` |
| `illegal start of statement` | Brace `}` in wrong place |
| `class expected` | Lowercase class name e.g. `double.parseDouble` |
