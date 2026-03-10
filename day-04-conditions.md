# Day 04 - Conditional Logic for Rack monitoring

## Objective
In this exercise i practiced using conditional logic in Java (if, else if, else) to classify the power consumption of server Racks in a simulated CPD.
The goal is to simulate how monitoring system detect diferent operational states

---

## Concepts Learned

- Array
- Loops ('for')
- Conditional statements ('if, else if, else')
- Basic monitoring logic

---

## Scenario
Each rack in data center has power consumption value.
The program evaluates the state of each rack based on the following thresholds:

| Power Consumption | Status |
|---|---|
0 kW | Failure / Rack offline |
1–10 kW | Normal operation |
11–16 kW | High consumption |
>16 kW | Critical consumption |

---

## Example console output:

Rack 1 -> 4 kW
Estado: consumo normal

Rack 2 -> 9 kW
Estado: consumo normal

Rack 3 -> 12 kW
Estado: consumo alto

Rack 4 -> 17 kW
Estado: CONSUMO CRÍTICO

Rack 5 -> 0 kW
Estado: FALLO o apagado

---

## Real world connection
This kind of logic is similar to how monitoring systems classify infrastrcuture states:
- OK
- Warning
- Critical
- Down

Monitoring tools apply threshold rules to metrics such as:
- power consumption
- temperature
- network traffic
- CPU usage

---

## Technologies Related

Examples of rmonitoring tools that use similar logic:
- Zabbix
- Prometheus
- Nagios
- Grafana

---

What i practiced
In this exercise I learned how to:
-  iterate through data using loops
-  Apply multiple ocnditions with 'else if'
-  Classify systems states base on thresholds
-  Print structured monitoring output


