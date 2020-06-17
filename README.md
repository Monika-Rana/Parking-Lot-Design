# Parking-Lot-Design

Question Asked for Interview.
A parking lot that can hold up to 'n' cars 
Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps.
The ticket documents the registration number color and slot number
At the exit the customer returns the ticket and slot is marked available.
the system  provide following  to find 
● Registration numbers of all cars of a particular colour. 
● Slot number in which a car with a given registration number is parked. 
● Slot numbers of all slots where a car of a particular colour is parked. 
system interact with command line which produce a specific output
Input and output formats are defined below, below format is expected from the program. 
Input :
$create_parking_lot 6 
Created a parking lot with 6 slots
$park KA-01-HH-1234 White 
$park KA-01-HH-9999 White 
$park KA-01-BB-0001 Black 
$park KA-01-HH-7777 Red 
$park KA-01-HH-2701 Blue 
$park KA-01-HH-3141 Black 
Allocated slot number: 1 Allocated slot number: 2 Allocated slot number: 3 
Allocated slot number: 4 Allocated slot number: 5 Allocated slot number: 6
$leave 4 
Slot number 4 is free 
$status 
Slot No. Registration No Color 
1 KA-01-HH-1234 White 2 KA-01-HH-9999 White 3 KA-01-BB-0001 Black 
5 KA-01-HH-2701 Blue 
6 KA-01-HH-3141 Black 
$park KA-01-P-333 White 
Allocated slot number: 4 
$park DL-12-AA-9999 White 
Sorry, parking lot is full 
$registration_numbers_for_cars_with_colour White 
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333
$slot_numbers_for_cars_with_colour White 
1, 2, 4
$slot_number_for_registration_number KA-01-HH-3141 
 6
$slot_number_for_registration_number MH-04-AY-1111 
Not found
