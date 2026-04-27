# Day 12 - Type Casting and Conversion

## What Did We Build Today?
A **network unit converter** that takes measurements in different units and converts between them — bits to bytes, bytes to kilobytes, megabytes to gigabytes.

We built this because network conversions are the perfect scenario to understand casting — you are constantly working with numbers that mix integers and decimals, and you need to control exactly what data type you have at each moment.

---

## Key Concepts

### What is Casting?
Casting is converting a value from one data type to another. In Java there are two types:

| Type | Name | Automatic? | Direction |
|---|---|---|---|
| Small → large | Widening | ✅ Yes | `int → double` |
| Large → small | Narrowing | ❌ No, manual | `double → int` |

---

### Widening Casting — automatic
Java automatically converts from a smaller type to a larger one because there is no risk of losing information:

```java
int bytes = 1500;
double bytesAsDouble = bytes; // Java does it automatically
```

The order of type sizes in Java from smallest to largest:
byte → short → int → long → float → double
Going right is automatic. Going left must be forced manually.

---

### Narrowing Casting — manual
Java does NOT automatically convert from a larger type to a smaller one because information can be lost. You must force it by writing the target type in parentheses before the value:

```java
double mbps = 99.9;
int mbpsAsInt = (int) mbps; // force the conversion with (int)
// result: 99 — decimals are truncated, NOT rounded
```

> **Exam trap:** narrowing truncates, it does not round. `(int) 9.9` gives `9`, not `10`.

---

### Network Unit Conversions
We use the `(double)` cast to force decimal division before doing the conversions:

```java
int totalBits = 125000000;
double toBytes     = (double) totalBits / 8;
double toKilobytes = toBytes / 1024;
double toMegabytes = toKilobytes / 1024;
double toGigabytes = toMegabytes / 1024;
```

Without `(double)` the first division would be integer division and the error would propagate to all subsequent calculations.

---

### Conversions Between String and Numbers
The four most important conversions for the exam:

```java
String speedText = "1000";

int speedInt        = Integer.parseInt(speedText);   // String → int
double speedDouble  = Double.parseDouble(speedText); // String → double
String backToString = String.valueOf(speedInt);      // int → String
```

`String.valueOf()` is the complement of `parseInt` — it converts any primitive type to a String.

---

### Overflow — When a Number Exceeds Its Range
Every type has a maximum and minimum limit. If you exceed the maximum the number wraps around and becomes the most negative value possible:

```java
int maxInt = Integer.MAX_VALUE; // 2,147,483,647
System.out.println(maxInt + 1); // result: -2,147,483,648
```

This is called **overflow** and is a classic Oracle exam trap.

Limit constants Oracle asks about directly:
- `Integer.MAX_VALUE` → `2147483647`
- `Integer.MIN_VALUE` → `-2147483648`

---

## Full Program

```java
public class Day12TypeCasting { // main class, no Scanner needed

    public static void main(String[] args) { // program entry point

        // --- WIDENING CASTING (automatic) ---
        int bytes = 1500;
        double bytesAsDouble = bytes; // int → double, Java does it automatically
        System.out.println("=== WIDENING CASTING (automatic) ===");
        System.out.println("int bytes:         " + bytes);
        System.out.println("double bytes:      " + bytesAsDouble);

        // --- NARROWING CASTING (manual) ---
        double mbps = 99.9;
        int mbpsAsInt = (int) mbps; // double → int, must be forced
        System.out.println("\n=== NARROWING CASTING (manual) ===");
        System.out.println("double mbps:       " + mbps);
        System.out.println("int mbps:          " + mbpsAsInt); // decimals lost

        // --- NETWORK UNIT CONVERSIONS ---
        System.out.println("\n=== NETWORK UNIT CONVERTER ===");
        int totalBits = 125000000;

        double toBytes     = (double) totalBits / 8;
        double toKilobytes = toBytes / 1024;
        double toMegabytes = toKilobytes / 1024;
        double toGigabytes = toMegabytes / 1024;

        System.out.println("Bits:      " + totalBits);
        System.out.println("Bytes:     " + toBytes);
        System.out.println("Kilobytes: " + toKilobytes);
        System.out.println("Megabytes: " + toMegabytes);
        System.out.println("Gigabytes: " + toGigabytes);

        // --- STRING CONVERSIONS ---
        System.out.println("\n=== STRING CONVERSIONS ===");
        String speedText    = "1000";
        int speedInt        = Integer.parseInt(speedText);
        double speedDouble  = Double.parseDouble(speedText);
        String backToString = String.valueOf(speedInt);

        System.out.println("String to int:    " + speedInt);
        System.out.println("String to double: " + speedDouble);
        System.out.println("Int to String:    " + backToString);

        // --- OVERFLOW ---
        System.out.println("\n=== OVERFLOW ===");
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Max int value:    " + maxInt);
        System.out.println("Max int + 1:      " + (maxInt + 1));
    }
}
```

---

## Console Output Example
=== WIDENING CASTING (automatic) ===
int bytes:         1500
double bytes:      1500.0
=== NARROWING CASTING (manual) ===
double mbps:       99.9
int mbps:          99
=== NETWORK UNIT CONVERTER ===
Bits:      125000000
Bytes:     1.5625E7
Kilobytes: 15258.789...
Megabytes: 14.9...
Gigabytes: 0.014...
=== STRING CONVERSIONS ===
String to int:    1000
String to double: 1000.0
Int to String:    1000
=== OVERFLOW ===
Max int value:    2147483647
Max int + 1:      -2147483648

---

## Mistakes Made Today and What They Taught Us

- **`mps` instead of `mbps`** → variable name must match exactly everywhere
- **`toKiloBytes` with capital B** → Java is case-sensitive, `toKiloBytes` and `toKilobytes` are different variables
- **Missing closing quote** in a `println` → every String must open and close with `"`

---

## Oracle Exam Traps to Remember

- **Narrowing truncates, does not round** → `(int) 9.9` gives `9`, not `10`
- **Widening is automatic** → Oracle shows code without cast and asks if it compiles. Small to large compiles, large to small gives a compilation error without cast
- **Overflow** → `Integer.MAX_VALUE + 1` gives the most negative value possible
- **`Integer.MAX_VALUE` and `Integer.MIN_VALUE`** are constants Oracle asks about directly
- **`String.valueOf()`** converts any primitive to String
- **Variable names are case-sensitive** → `toKiloBytes` and `toKilobytes` are two different variables
