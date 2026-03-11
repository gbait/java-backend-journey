# Day 05 -  Data Center Power analysis

## Objective
In this exercise y practiced basic data analysis using java.
The goal is to simulate the analysis of power consumption in server racks inside a data center.

The program calculates:
- total power consumption
- rack with highest consumption
- rack with lowest consumption

---

## Concepts learned
- arrays
- loops
- variables for tracking values
- basic data analysis
- comparison logic

---

## Scenario
Each rack in the data center has a power consumption value.
The system analyses these values to determine:
- total power used by the infrastructure
- the average rack consumption
- which rack consumes the most power
- which rack consumes the least power

## Example data
4, 9, 12, 17, 0.   ---> Each number represents the power consumption of a rack in kW

## Example output
- Total consumption: 42 kW
- Average consumption: 8.4 kW
- Highest consumption rack: 17 kW
- Lowest consumption rack: 0 kW

---

## Real World Connection
Data anlysis like this is commonly used in infrastructure monitoring systems.
Typical metrics analysed include:
- power consumption
- cpu usage
- memory usage
- network traffic
- temperature

---

## Monitoring platforms

Example of tools that analyze typo of infrastrucutre data:
- zabbix
- prometheus
- grafana
- nagios

---

## What i practiced

In this exercise i learned how to:

- iterate through monitoring data
- calculate totals and averages
- detect maximum and minimum values
- simulate infrastructure monitoring logic
