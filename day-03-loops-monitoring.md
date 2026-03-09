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

EJEMPLO EN JAVA:

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

Previamente el consumo dado era fijo de 12 kW, como en la realidad, cada rack puede tener diferentes cargas; 
Si un rack consume más:
- La suma total de la carga (consumo) cambia y podría sobrepasas la capacidad eléctrica del CPD.
- Genera desequilibrios en fases si la distribución eléctrica ea AC.
- Además podrían dispararse alertas y sobrecargas del sistema.
Como en programación no podemos tener una variable fija para todos los racks necesitaremos una erstructura que represente múltiples variables.

EJEMPLO EN JAVA:
int[] consumos = {6, 15, 10, 18, 7}; // kW por rack
int consumoTotal = 0;

for(int i = 0; i < consumos.lengh; i++){
  consumoTotal += consumos[i];
  }
System.out.println("Consumo total: " + consumoTotal +" kW");

Ahora cada rack tiene un valor individual, sumamos los consumos reaales y relfejamos la complejidad del CPD


2) ¿Que pasa si un Rack falla?

En el mundo real un rack puede apagarse, fallar o desconectarse.
Esto genera:
* Consumo menor al esperado: podría afectar a la eficiencia de la UPS o sistemas de climatización
* Alarmas de sistemas de monitorización.
En programación:
* Deebemos estar preparados para valores nulos, cero o errores de sensor.
* Podemos añadir condicionales que detecten consumos inusuales.
EJEMPLO EN JAVA:

for(int 1= 0; i < consumos.lengh; i++){
  if(consumos[i] <= 0){
      System.out.println("Alerta: Rack" + (i+1) + "Fuera de línea o sin consumo");
      }
      consumoTotal += consumos[i];
Ahora el sistema no solo acumula si no que detecta anomalías --> [PENSAMIENTO ARQUITECTÓNICO APLICADO A SOFTWARE]


3) ¿Cómo se detectaría ese sistema?

El código debe ser escalable.
No podemos volver a escribir variables para cada rack.
Debemos usar estructuras dinámicas (arrays, listas) y bucles que funcionen con cuañquier número de racks.
EJEMPLO EN JAVA:

int[] consumosNuevos = {8, 15, 10, 18, 7, 20, 12}; // 7 racks ahora
int total = 0;

for (int c : consumosNuevos){
  total += C;
  }
System.put.println("Consumo total actualizado: " + total + "kW");

Hemos usado bucles for each para mayor claridad.
Preparacion para escalabilidad (importante en sistemas CPD)


4) ¿Donde está el punto único de fallo?

Cada rack o la suma total no debe superar la capacidad de la instalación.
Si hay límnites, el programa debe detectarlo.
Lo siguiente simula monitoreo en tiempo real (muy usado en data centers)

EJEMPLO EN JAVA:

int limiteTotal = 70; // kW
int consumo total = 0;

for (int c : consumos){
  consumoTotal += c;
  }
if(consumoTotal > limiteTotal){
  System.out.println("Alerta consumo total supera el límite: " + consumoTotal + "kW");
  } else {
  System.out.p`rintln("Consumo total dentro del límite: " + consumoTotal + "kW");
  }


  Ahora Introducimos reglas y límites. Es lógica de negocio real
  Preparo el camino hacia sistemas de monitorización y control de infraestructuras.

  A continuación presentaré una version del programa combinada de todo lo anterior.
  Cada rack como consumo variable, detecta racks apagados, calcula total, lanza alertas si algún rack o el total excede el límite.
  </> JAVA
  
  
public class DataCenterEnergySimulator {

  public class SimulacionDataCenter {

    public static void main(String[] args) {

        int[] consumosRack = {8, 15, 0, 18, 7}; 

        int limiteRack = 16;
        int limiteTotal = 60;

        int consumoTotal = 0;

        for (int i = 0; i < consumosRack.length; i++) {

            int consumoActual = consumosRack[i];

            System.out.println("Rack " + (i + 1) + " consumo: " + consumoActual + " kW");

            if (consumoActual == 0) {
                System.out.println("ALERTA: Rack " + (i + 1) + " está apagado o ha fallado");
            }

            if (consumoActual > limiteRack) {
                System.out.println("ALERTA: Rack " + (i + 1) + " supera el límite permitido");
            }

            consumoTotal = consumoTotal + consumoActual;
        }

        System.out.println("Consumo total del Data Center: " + consumoTotal + " kW");

        if (consumoTotal > limiteTotal) {
            System.out.println("ALERTA CRÍTICA: El consumo total supera la capacidad del CPD");
        } else {
            System.out.println("Consumo total dentro del límite permitido");
        }

    }
}

        System.out.println("Consumo total del día: " + totalConsumption + " kW");

    }
}
