Day 07 - User Input with Scanner & Method Extraction
Concepts Covered

Importing external classes with import
Reading user input with Scanner
The nextLine() vs nextInt() buffer trap
Converting Strings to integers with Integer.parseInt()
Extracting logic into separate methods
Java is case-sensitive: String vs string, Integer vs integer


Key Theory
The Scanner Class
Scanner is not available by default. It must be imported before the class declaration:
javaimport java.util.Scanner;
To create a Scanner that reads from the keyboard:
javaScanner scanner = new Scanner(System.in);
System.in tells the Scanner to listen to keyboard input. Always close it when finished:
javascanner.close();
print vs println
MethodBehaviourSystem.out.print()Prints text, cursor stays on the same lineSystem.out.println()Prints text, cursor moves to the next line
Use print for input prompts so the user types on the same line as the question.
The Buffer Trap — Why Always Use nextLine()
nextInt() reads the number but leaves the \n (Enter key) sitting in the buffer. The next nextLine() call consumes that leftover \n instead of waiting for the user, silently skipping the question.
The safe pattern — always use nextLine() and convert manually:
javaint traffic = Integer.parseInt(scanner.nextLine());
This reads the full line including the Enter, so the buffer is always clean.
Integer.parseInt()
Converts a String to an int. Works from the inside out:
java// Step 1: scanner.nextLine() returns "350" as a String
// Step 2: Integer.parseInt("350") converts it to the int 350
int traffic = Integer.parseInt(scanner.nextLine());
Case Sensitivity — A Common Source of Errors
Java distinguishes uppercase and lowercase everywhere. These are the most common mistakes:
Wrong ❌Correct ✅WhystringStringIt is a class, not a primitiveintegerIntegerIt is a class, not a primitiveLocationlocationMust match the variable declaration exactly
Method Extraction
Logic can be moved into a separate method to keep main clean and readable. A method outside main but inside the class must be static to be callable from main:
javapublic static String analyzeTraffic(int traffic) {
    if (traffic == 0)        return "PORT DOWN";
    else if (traffic <= 200) return "NORMAL";
    else if (traffic <= 500) return "HIGH TRAFFIC";
    else                     return "CRITICAL TRAFFIC";
}

The Program
javaimport java.util.Scanner;

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

Console Output Example
Enter server name: WebServer-01
Enter traffic (Mbps): 350
Enter location: Madrid-DC

--- SERVER REPORT ---
Server:   WebServer-01
Location: Madrid-DC
Status:   HIGH TRAFFIC

Errors Made Today & Lessons Learned

integer.parseInt() → must be Integer.parseInt() — classes are always capitalised
string as return type → must be String — same reason
Location instead of location → Java is case-sensitive, variable names must match exactly
Missing } in Day06 → always check that every opening brace has its closing brace
Missing ; in Day06 array declaration → every statement ends with a semicolon


Oracle Exam Traps to Remember

nextInt() leaves \n in the buffer — always prefer nextLine() + Integer.parseInt()
String and Integer are classes (uppercase). int, double, boolean are primitives (lowercase)
A method called from main must be static if it lives in the same class
scanner.close() is good practice — expect questions about resource management in the exam
