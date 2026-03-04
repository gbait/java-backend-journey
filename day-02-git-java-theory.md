# Día 2 – Git y Fundamentos Java

## ¿Qué es Git?
Sistema de control de versiones que guarda versiones del proyecto.

Conceptos:
- Repository → Carpeta del proyecto
- Commit → Fotografía del proyecto
- Branch → Línea paralela de trabajo

---

## ¿Qué es Java?
Lenguaje de programación orientado a objetos.

Se usa en:
- Backend
- Aplicaciones empresariales
- Banca
- Data Centers (monitorización, automatización)

---

## Fundamentos aprendidos

### Variables
Una variable guarda un dato.

Ejemplo:
int edad = 26;

### Operadores
Suma: +
Resta: -
Multiplicación: *
División: /

### Condicional
if (edad > 30) {
   // código
} else {
   // otro código
}
---

## Relación con Data Centers

Como diseñador eléctrico, entiendo:

- N → capacidad justa
- N+1 → redundancia con un equipo extra
- 2N → duplicación completa del sistema

Un rack puede consumir 10-15 kW.
La refrigeración es uno de los mayores retos técnicos.
El mayor riesgo real en un DC es el calor, no los hackers.
---

## Ejercicio Java Día 2

Código:

public class Principal {
    public static void main(String[] args) {

        int edad = 26;

        if (edad > 30) {
            System.out.println("Mayor de 30");
        } else {
            System.out.println("Menor o igual a 30");
        }

    }
}

### Explicación

public class Principal
→ Define una clase.

public static void main
→ Punto de entrada del programa.

int edad = 26;
→ Declaración de variable tipo entero.

if (...) { }
→ Estructura condicional.
