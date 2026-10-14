# Equestrian Booking System

## Project Idea
A system for managing information about horses and handling bookings at a riding club.
The domain is intentionally small and focused, to keep the project within a realistic
scope while still demonstrating object‑oriented programming principles.

## Abstract Super Class — Horse
Horse is abstract because it represents a general concept, not a concrete horse instance.
Every horse in the system must belong to a specific subtype, and each subclass provides 
its own implementation of:

getDetails()
getBookingRequirement()
getType()

This prevents invalid objects from being created and supports polymorphism.

Common fields:
name
age
isAvailable

Common methods (abstract):
getDetails()
getType()
getBookingRequirement()

## Subclasses and Inheritance
All horse types in the system inherit from the base class Horse using extends.
Each subclass overrides specific behavior to implement its own booking rules 
and display logic and (when applicable) booking rules. This allows the program 
to use polymorphism: all horses are handled through the base type (Horse), while
the correct subclass implementation is chosen at runtime.

### FullSizeHorse
- Extends: Horse
- Implements: Bookable
- Overrides: getDetails(), getBookingRequirement(), getType()
- Adds: minimum rider age requirement
- Booking logic: rider must meet age requirement

### Pony
- Extends: Horse
- Implements: Bookable
- Overrides: getDetails(), getBookingRequirement(), getType()
- Adds: minimum rider height requirement
- Booking logic: rider must meet height requirement

### PrivateHorse
- Extends: Horse
- Does not implement Bookable
- Overrides: getDetails(), getBookingRequirement(), getType()
- Characteristics: privately owned, cannot be booked via the system
- Adds: owner information + contactability


## Interface
- Name: Bookable  
- Methods:
  * book(String requirement)
  * cancelBooking()

- Implemented by:
  * FullSizeHorse
  * Pony

BookingHandler uses the interface polymorphically, allowing
any Bookable horse to be booked through the same method

## Menu (Console program)
The interactive menu allows the user to manage the horse collection and booking system.

1. Lista alla
2. Lista alla bokningskrav
3. Lägg till
4. Ta bort
5. Boka
6. Avboka
0. Avsluta

1. List all
2. List all booking requirements
3. Add new
4. Delete
5. Book 
6. Cancel
0. End program

The menu runs in a loop and cannot crash due to invalid input.

## Possible Error Cases
Concrete examples from this project:

- Trying to book a horse that is already booked
- Trying to book a horse that does not meet rider requirements
- Trying to delete a horse that does not exist
- Trying to cancel a booking for a horse that is not booked
- Incorrect input (letters instead of numbers, empty input, invalid menu choice)

All of these are handled through validation and controlled loops.

## Domain Model
Horses are created programmatically at startup and stored in an ArrayList<Horse>.
The user interacts with the system through:

listing horses
listing booking requirements
adding horses
deleting horses
booking
canceling bookings

The domain is intentionally limited to keep the project manageable.

## Inheritance and Polymorphism
The project is built around an object-oriented hierarchy where Horse acts 
as the abstract base class. Specific horse types (FullSizeHorse, Pony, PrivateHorse)
extend this class and override behaviour where needed.

All horses are stored in an ArrayList<Horse>, allowing the program to use polymorphism:  
the system interacts with horses through the base type, while the correct subclass
implementation is chosen at runtime. 

Only Pony and FullSizeHorse implement the Bookable interface, enabling polymorphic
booking logic. PrivateHorse is a non‑bookable subclass and excluded from the booking flow.

## Rationale
This project structure was chosen to keep the domain clear, realistic,
and easy to extend. The class hierarchy reflects real differences between
horse types, while the Bookable interface ensures that only appropriate
horses can be booked. Responsibilities are separated so that

- the UI (ConsoleUI) handles user interaction and all printed output
- BookingHandler manages horse storage and booking logic
- horse classes contain domain rules

As the project evolves, this section will be updated
to explain design decisions and alternatives considered.