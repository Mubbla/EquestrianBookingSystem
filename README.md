# Equestrian Booking System

## Project Idea
A system for managing information about horses and handling bookings at a riding club.
The domain is intentionally small and focused, to keep the project within a realistic
scope while still demonstrating object‑oriented programming principles.
The project is built around an object-oriented hierarchy where Horse acts
as the abstract base class. Specific horse types (FullSizeHorse, Pony, PrivateHorse)
extend this class and override behaviour where needed.

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
The system relies entirely on Java’s standard libraries.
User input is handled through java.util.Scanner, and the horse 
collection is stored in an ArrayList from java.util.

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

The system interacts with horses through the base type, while the correct subclass
implementation is chosen at runtime.

## Rationale
This project structure was chosen to keep the domain clear, realistic,
and easy to extend. The class hierarchy reflects real differences between
horse types, while the Bookable interface ensures that only appropriate
horses can be booked. Responsibilities are separated so that

- the UI (ConsoleUI) handles user interaction and all printed output
- BookingHandler manages horse storage and booking logic
- horse classes contain domain rules

The Person class is included to represent the owner of a PrivateHorse, making 
the domain model more realistic and allowing ownership details to be handled 
cleanly within the object structure.

This design also ensures that new horse types, booking rules, or menu
actions can be added without modifying existing classes, which makes
the system easy to maintain and extend over time.

As the project evolves, this section will be updated
to explain design decisions and alternatives considered.

## Reflection
During the development of this project, I made several design choices to
keep the system structured, easy to understand, and easy to extend.
I separated responsibilities between classes so that each class has one clear purpose:
the UI handles interaction, the horse classes contain domain rules, and the booking
logic is isolated in BookingHandler. This makes the code easier to maintain and prevents
changes in one part of the program from affecting others.

### Design decision 1: Person class
One design decision I made during the project was changing the owner field from a simple
string into a dedicated Person class. Initially, a string was enough to display the owner’s 
name, but it quickly became clear that this approach would limit the system as it grows.
By introducing a Person class, ownership becomes a real part of the domain model instead
of just text.

This change makes the system more flexible and future‑proof.
If I later want to add more information about people — such as phone numbers, roles,
contact preferences, or even different categories of users — I can do so without rewriting
existing code. If I had kept the owner as a string, every new feature involving people 
would require structural changes and duplicated logic.

Switching to a Person class therefore improves maintainability, supports extension, and
keeps the domain model realistic. It also demonstrates the value of object‑oriented 
thinking: representing real‑world concepts as objects makes the system easier to evolve
over time.

### Design decision 2: BookingHandler class
One important design choice in this project was separating the booking logic into a 
dedicated BookingHandler class instead of placing it inside the menu or the horse classes.
At first, it might seem simpler to let the menu handle bookings directly, but this quickly 
leads to duplicated code, unclear responsibilities, and a menu class that becomes difficult
to maintain.

By introducing a booking manager, all booking‑related rules are kept in one place.
This makes the system easier to understand and prevents the UI from mixing interaction logic
with domain logic. It also means that the horse classes only contain information and rules that
belong to the horses themselves, while BookingHandler focuses on how bookings are processed.

This design choice improves flexibility: if booking rules change or new booking types are added,
they can be implemented inside BookingHandler without rewriting the menu or the horse classes.
It also makes the program more robust, because the booking flow is centralized and easier to 
validate and test.



