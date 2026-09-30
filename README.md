# Planeringsmall — Projektskiss

Fyll i denna mall innan ni börjar koda. Skissen är ett första utkast, inte ett facit — det är både normalt och förväntat att klassnamn och struktur ändras när ni väl börjar implementera. Spara den ifyllda mallen som README i er första commit, tillsammans med namn på den/de som jobbar i projektet.

## Project Idea


A system for managing information about horses and handling bookings at a riding club.

## Super Class

- Name:Horse
- Common fields: name, id, isBookable
- Common methods: showDetails(), register(), unregister()

## Sub Classes (minst tre)

1. Namn — vad gör den annorlunda, vilka metoder overridas?
1. FullSizeHorse - showDetails() includes age limit
2. DPony — showDetails includes weight limit
3. CPony — showDetails includes weight and length limits
4. PrivateHorse (privately owned) - not Bookable, includes owner

## Interface

- Name: Bookable
- Method(s): book()
- Implemented by (at least two subclasses): FullSizeHorse, DPony, CPony

## Menu

Lista minst fyra åtgärder kopplade till samlingen
(t.ex. lägga till, ta bort, söka, samt en egen åtgärd som passar er domän).
1. Register
2. Unregister
3. Search
4. Book
5. Change Booking
6. Cancel Booking
7. Change Owner

## Possible Error Cases

Minst två konkreta situationer i just ert program som kan gå fel
och som ni behöver hantera (inte generella exempel).

Already booked
Does not exist
Incorrect input
...

## Rationale

This project structure was chosen to keep the domain clear, realistic, 
and easy to extend. The class hierarchy reflects real differences between
horse types, while the Bookable interface ensures that only appropriate 
horses can be booked. Responsibilities are separated so that the 
manager handles user interaction, while the horse classes contain
the domain rules. As the project evolves, this section will be updated 
to explain design decisions and alternatives considered.

---