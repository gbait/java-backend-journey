# Day 07 - How to Read User Input with Scanner

## What Did We Learn Today?
Until now all our programs worked with data we wrote directly in the code, like arrays with fixed numbers. Today we take an important step: **making the user enter data while the program is running.**

For that Java has a tool called `Scanner`.

---

## What is Scanner and Why Do We Need to Import It?

Think of Java as a toolbox. The hammer and screwdriver are always there. But Scanner is a special tool stored in a different drawer — you need to go get it before using it. That is exactly what `import` does:

```java
import java.util.Scanner;  // Tell Java: "go and fetch the Scanner"
```

This line always goes first, before the class declaration. Without it Java does not know what `Scanner` is and throws an error.

---

## Creating the Scanner

```java
Scanner scanner = new Scanner(System.in);
```

Here we are creating the Scanner object. There are three parts:
- `Scanner` — the type of object we are creating
- `scanner` — the name we give it (could be any name)
- `new Scanner(System.in)` — creates it and tells it to listen to the keyboard. `System.in` means "system input", which is the keyboard.

---

## `print` vs `println` — An Important Difference

When asking the user a question we use `print` instead of `println`:

```java
System.out.print("Enter server name: ");   // cursor stays on the same line
System.out.println("Enter server name: "); // cursor moves to the next line
```

With `print` the user types right after the colon, on the same line. With `println` the cursor would drop down and it would look odd. For user prompts, always use `print`.

---

## Reading What the User Types

```java
String serverName = scanner.nextLine();
```

`nextLine()` waits for the user to type something and press Enter. Everything they typed, including spaces, gets stored in the variable `serverName`.

---

## The Most Important Concept of the Day — The Buffer Trap

This is a concept **Oracle puts on the exam** that confuses a lot of people.

When the user types a number and presses Enter, Java actually receives two things:
- The number: `350`
- The newline character from the Enter key: `\n`

If you use `nextInt()` to read the number, Java takes the `350` but **leaves the `\n` sitting in the buffer**. When the next `nextLine()` comes along, instead of waiting for the user to type, it consumes that leftover `\n` and moves on. The result is that a question gets silently skipped.

The solution is to **never use `nextInt()` directly**. Instead always read with `nextLine()` and convert the text to a number manually:

```java
// ❌ Dangerous — leaves \n in the buffer
int traffic = scanner.nextInt();

// ✅ Safe — reads the full line and converts it
int traffic = Integer.parseInt(scanner.nextLine());
```

---

## What Does `Integer.parseInt()` Do?

Scanner always returns text, even if the user typed a number. `"350"` as text and `350` as a number are different things in Java — you cannot do math with text.

`Integer.parseInt()` takes that text and converts it to a real integer:

```java
// Step 1: scanner.nextLine() receives "350" → it is a String, plain text
// Step 2: Integer.parseInt("350") converts it → now it is the number 350
int traffic = Integer.parseInt(scanner.nextLine());
```

---

## Uppercase and Lowercase — Java Is Very Strict

Java distinguishes uppercase and lowercase in absolutely everything. These are the mistakes we made today:

| Wrong ❌ | Correct ✅ | Explanation |
|---|---|---|
| `integer.parseInt()` | `Integer.parseInt()` | `Integer` is a class and classes start with uppercase |
| `public static string` | `public static String` | Same reason, `String` is a class |
| `+ Location` | `+ location` | The variable was declared in lowercase, it must match exactly |

Easy rule to remember: primitive types are lowercase (`int`, `double`, `boolean`). When they are classes they are uppercase (`Integer`, `String`, `Boolean`).

---

## Closing the Scanner

```java
scanner.close();
```

When you are done using it you must close it to free up resources. Think of it like turning off a tap when you finish using it. Oracle asks about this in the exam under the concept of **resource management**.

---

## Extracting Logic Into a Separate Method

Instead of putting everything inside `main`, we moved the analysis logic into its own method. This keeps the code clean and easy to read:

```java
public static String analyzeTraffic(int traffic) {
    if (traffic == 0)        return "PORT DOWN";
    else if (traffic <= 200) return "NORMAL";
    else if (traffic <= 500) return "HIGH TRAFFIC";
    else                     return "CRITICAL TRAFFIC";
}
```

The method receives a number (`int traffic`) and returns a text (`String`) with the status. It has `static` because it is called from `main`, which is also `static`. Keep that rule for now — when we get to object oriented programming you will understand it fully.

---

## Full Program

```java
import java.util.Scanner;

public class Day07UserInputMonitoring {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter server name: ");
        String serverName = scanner.nextLine();

        System.out.print("Enter traffic (Mbps): ");
        int traffic = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.println("--- SERVER REPORT ---");
        System.out.println("Server:   " + serverName);
        System.out.println("Location: " + location);
        System.out.println("Status:   " + analyzeTraffic(traffic));

        scanner.close();
    }

    public static String analyzeTraffic(int traffic) {
        if (traffic == 0)        return "PORT DOWN";
        else if (traffic <= 200) return "NORMAL";
        else if (traffic <= 500) return "HIGH TRAFFIC";
        else                     return "CRITICAL TRAFFIC";
    }
}
```

---

## Console Output Example
