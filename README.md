# Project Planning — Continuously Updated Throughout Development

## Project Idea

A system for managing information about horses and handling bookings at a riding club.

## ## Abstract Super Class — Horse
Horse is abstract because it represents a general concept, not a concrete horse instance.  
Every horse in the system must belong to a specific subtype, and each subclass must provide
its own showDetails() implementation.
This prevents invalid objects from being created and supports polymorphism.

- Name:Horse
- Common fields: name, id, isBookable
- Common methods: showDetails(), book(), cancelBooking()

## Subclasses and Inheritance

All horse types in the system inherit from the base class Horse using extends.
Each subclass overrides specific behavior to implement its own booking rules and
display logic. This allows the program to use polymorphism: all horses are handled
through the base type (Horse), while the correct subclass implementation is chosen
at runtime.

### FullSizeHorse
- Extends: Horse
- Overrides: showDetails()
- Adds: age limit information in the details output

### DPony
- Extends: Horse
- Overrides: showDetails()
- Adds: weight limit information

### CPony
- Extends: Horse
- Overrides: showDetails()
- Adds: (both weight and) length limit

### PrivateHorse
- Extends: Horse
- Overrides: showDetails()
- Characteristics: privately owned, never Bookable
- Adds: owner information


## Interface

- Name: Bookable
- Method(s): book()
- Implemented by (at least two subclasses): FullSizeHorse, DPony, CPony

## Menu

1. Lista alla hästar
2. Lägg till nya häst
3. Ta bort hästar
4. Boka hästar
5. Avboka hästar
6. Avsluta


## Possible Error Cases

Minst två konkreta situationer i just ert program som kan gå fel
och som ni behöver hantera (inte generella exempel).

Already exists
Does not exist
Already booked
Incorrect input
...

## Domain Model
The horses in the system are created programmatically at startup.
They are not registered by the user, since horse management would unnecessarily expand the scope
of the application. The user interacts only with bookings, not with the creation or modification
of horse objects.

## Inheritance and Polymorphism
The project is built around an object-oriented hierarchy where Horse acts as the abstract base class.  
Specific horse types (DPony, CPony, LargeHorse, PrivateHorse) extend this class and override behavior where needed.

All horses are stored in an ArrayList<Horse>, allowing the program to use polymorphism:  
the system interacts with horses through the base type, while the correct subclass implementation is chosen at runtime.  
This makes the code flexible, scalable and easy to maintain.

## Rationale

This project structure was chosen to keep the domain clear, realistic, 
and easy to extend. The class hierarchy reflects real differences between
horse types, while the Bookable interface ensures that only appropriate 
horses can be booked. Responsibilities are separated so that the 
manager handles user interaction, while the horse classes contain
the domain rules. As the project evolves, this section will be updated 
to explain design decisions and alternatives considered.

---