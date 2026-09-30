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

## Motivering

När ni kommit igång och gjort några ändringar: skriv kort varför strukturen ser ut som den gör, och om ni övervägde ett annat sätt att lösa det på. Detta behöver inte fyllas i redan i första commiten.

---

## Exempel (ifyllt) — Biblioteksystem

**Projektidé:** Ett system för att hantera ett biblioteks samling av utlåningsbara medier och vilka som är utlånade.

**Superklass**
- Namn: `Media`
- Gemensamma fält: title, id, isBorrowed (true/false)
- Gemensamma metoder: `showInfo()`, `borrow()`

**Subklasser**
1. `Book` — overridar `showInfo()` för att även visa författare
2. `Magazine` — overridar `borrow()` eftersom tidskrifter bara får lånas i en vecka
3. `Movie` — overridar `showInfo()` för att visa åldersgräns

**Interface**
- Namn: `Reservable`
- Metod: `reserve()`
- Implementeras av: `Book`, `Movie`

**Meny**
1. Lägga till medium
2. Ta bort medium
3. Söka på titel
4. Låna ut/lämna tillbaka
5. Reservera

**Felscenarion**
- Försök att låna ut ett medium som redan är utlånat
- Försök att skapa ett medium med tomt titel-fält