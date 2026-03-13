# Day 06 -  Network Traffic Monitoring Simulation

## Objective

In this exercise i practiced analyzing network traffic data using java.
The program simulates traffic levels on network switches in Data Center and detects abnormal traffic.

---

## Concepts learned

- arrays
- loops
- conditional statements
- threshold detection
- basic network monitoring logic

---

## Scenario

Each value repreents the network traffic on a switch port in Mbps.
The system evlauates traffic levels and classifies them into diferent states.

| Traffic | State |
|---|---|
0 Mbps | Port down |
1–200 Mbps | Normal traffic |
201–500 Mbps | High traffic |
>500 Mbps | Critical traffic |

---

## Example data

100, 220, 0, 450, 700

Each number represents traffic on a switch port.

---

## Example output

Port 1 traffic: 100 Mbps -> NORMAL
Port 2 traffic: 220 Mbps -> HIGH
Port 3 traffic: 0 Mbps -> PORT DOWN
Port 4 traffic: 450 Mbps -> HIGH
Port 5 traffic: 700 Mbps -> CRITICAL

---

## REAL WORLD CONNECITON 
Network monitoring system analyze traffic metrics to detect problems such as:

- link failures
- traffic spikes
- network congesiton
- DDos attacks

Tools used in real infrastructure include:
- Wireshark
- Zabbix
- Prometheus
- Grafana

---

## What i practiced

- reaidng monitoring metrics
- classifying states
- applying conditional logic to infrastructure data
