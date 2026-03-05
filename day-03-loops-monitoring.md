# Día 3 - Bucles y monitorización

## ¿Qué es un bucle?

Un bucle permite repetir una acción varias veces.
En un data center se usa para:
- Revisar racks
- Monitorizar temperatura
- Revisar servidores
- Calcular consumos

---

## Bucle for

Ejemplo:

for (int i = 1; i <= 5; i++) {
  System.out.println("Revisando rack" + i);
  }

  Explicación: recorre 5 racks uno por uno.

  ---

  ## Simulación de consumo

  int consumoPorRack = 12;
  int consumoTotal = 0;

  for (int i = 1; i <= 5; i++) {
    consumoTotal += consumoPorRack;
    }

Resultado esperado: 60 kW de consumo total

---

## Reflexión técnica

Los bucles permiten modelar sistemas repetitivos. 
La monitorización dde un DC funciona con lógica similar;
Revisar automáticamente el estado de los componentes.

---

## Pensamiento arquitectónico:

1)¿Qué pasa si un rack consume más?
2) ¿Que pasa si un Rack falla?
3) ¿Cómo se detectaría ese sistema?
4) ¿Donde está el punto único de fallo?


int [] consumos = {}



