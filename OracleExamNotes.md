# Oracle Exam Key Notes
> This file is updated daily with the most important concepts and traps for the Oracle Java Certification exam.

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
| `Boolean` | True or false as object | rarely used directly |

> **Exam trap:** `string` does not exist. `String` does. Same with `integer` vs `Integer`.

---

## Variables

- Must be declared before use
- Name must match exactly everywhere, including uppercase and lowercase
- Declaring inside a block `{}` means it only exists inside that block

```java
int x = 5;      // declared and initialised
int y;           // declared but not initialised — cannot be used until assigned
y = 10;          // now it can be used
```

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
| `/` | Division | `5 / 2 = 2` (integer division, no decimals) |
| `%` | Remainder | `5 % 2 = 1` |

> **Exam trap:** `5 / 2 = 2` in Java, not `2.5`. To get decimals use `(double) 5 / 2 = 2.5`

### Increment and Decrement
```java
attempts++;   // same as attempts = attempts + 1, returns value BEFORE adding
++attempts;   // same as attempts = attempts + 1, returns value AFTER adding
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

### The buffer trap — most important rule
**Never use `nextInt()` if you are going to use `nextLine()` afterwards.**
`nextInt()` reads the number but leaves `\n` in the buffer. The next `nextLine()` consumes it silently, skipping the question.

```java
// ❌ Dangerous
int number = scanner.nextInt();
String text = scanner.nextLine(); // this gets skipped

// ✅ Safe — always use nextLine() and convert manually
int number = Integer.parseInt(scanner.nextLine());
String text = scanner.nextLine(); // works correctly
```

### `print` vs `println`
```java
System.out.print("text");    // cursor stays on the same line
System.out.println("text");  // cursor moves to the next line
```

### Always close the Scanner
```java
scanner.close(); // frees system resources — Oracle asks about resource management
```

---

## Control Flow

### if / else if / else
```java
if (condition) {
    // executes if condition is true
} else if (otherCondition) {
    // executes if otherCondition is true
} else {
    // executes if nothing above was true
}
```
Use when comparing **ranges** or **complex conditions**.

---

## Loops

### The three loops compared
| Loop | Condition checked | Minimum executions |
|---|---|---|
| `for` | At the beginning | 0 times |
| `while` | At the beginning | 0 times |
| `do-while` | At the end | **Always 1 time** |

> **Exam trap:** Oracle asks "how many times does this block execute?" — the answer depends on which loop is used

### for loop
Use when you know exactly how many times to repeat:
```java
for (int i = 0; i < array.length; i++) {
    // executes array.length times
}
```

### while loop
Use when you do not know how many times — repeats while condition is true:
```java
while (!command.equals("exit")) {
    command = scanner.nextLine();
}
```
Always initialise the variable before the loop:
```java
String command = ""; // must exist before the while reads it
```

### do-while loop
Use when the block must execute at least once:
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
    case 2:
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
Without `break`, Java keeps executing the next case even if it does not match:
```java
case 1:
    System.out.println("ONE");
    // no break — falls through
case 2:
    System.out.println("TWO");
    break;
```
If variable is `1`, output is:
ONE
TWO

### switch vs if/else
| Situation | Use |
|---|---|
| Exact value comparison | `switch` |
| Range comparison like `x <= 200` | `if/else` |
| Complex conditions with `&&` or `||` | `if/else` |

---

## Methods

### Declaring a method
```java
public static String analyzeTraffic(int traffic) {
    // code
    return "result";
}
```
- `public` — anyone can call this method
- `static` — required to call it from `main` which is also static
- `String` — the type of value the method returns
- `analyzeTraffic` — the method name
- `(int traffic)` — the parameter it receives

### void methods
If a method does not return anything, use `void`:
```java
public static void printReport() {
    System.out.println("Report");
    // no return needed
}
```

> **Exam trap:** A method declared with a return type MUST always return a value on every possible path

---

## Common Compilation Errors and Their Causes

| Error | Most likely cause |
|---|---|
| `';' expected` | Missing semicolon at end of line |
| `reached end of file while parsing` | Missing closing brace `}` |
| `cannot find symbol` | Variable name typo or wrong uppercase/lowercase |
| `unclosed string literal` | Missing closing quote `"` |
| `illegal start of statement` | Brace `}` in wrong place or missing |
| `'else' without 'if'` | Missing closing brace `}` before else |
