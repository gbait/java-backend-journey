# Data Center Notes

## What is a Data Center?

A data center is a facility designed to house computing systems and associated components such as telecommunications and storage systems.

It includes:
- Power supply systems
- Backup generators
- Cooling systems
- Networking equipment
- Physical security

---

## Power Architecture Basics

Typical power flow:

Utility Grid → Transformer → UPS → Batteries → Generator → PDU → Rack

### Redundancy Concepts
- N
- N+1
- 2N

N+1 means there is at least one independent backup component.

---

## Cooling Concepts

- Cold aisle / Hot aisle
- Free cooling
- CRAC vs CRAH

Heat is one of the biggest risks in a data center.
---

## Redundancy Explained (My Understanding)

### N
N represents the minimum required capacity to support the total IT load.

Example:
If the data center needs 1 MW to operate, then N = 1 MW.

If any component fails, the system stops.

### N+1
N+1 means the required capacity plus one extra backup component.

Example:
If 4 UPS units are required to support the load, N+1 means installing 5 UPS units.

If one UPS fails, the system continues operating.

### 2N
2N means having two completely independent systems capable of supporting 100% of the load.

Example:
If the data center requires 1 MW, then two separate 1 MW systems are installed.

This provides full fault tolerance.

### Tier III

Tier III data centers are designed with N+1 redundancy and allow maintenance without shutting down operations.

They offer high availability and are commonly used by enterprises.
