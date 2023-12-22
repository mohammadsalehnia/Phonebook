# Phonebook in Java :coffee:

This is a simple **"Phonebook"** done with **Java** programming language.
## Compile program

To compile the "Phonebook" program, type the following in the **src** directory:

```console
javac Phonebook.java
```

## Excute the program

To execute the program, type this in the **src** directory:

```console
java Phonebook
```


## Menu Items
After running the program you can see this menu on the screen.
```console
Phonebook Application Started
------- Menu --------
1. Add new number
2. Print all numbers
3. Search contact
4. Edit contact
5. Delete contact
6. Exit
---------------------
Select a menu item:
```
Select **1** for adding new number, select **2** for print all numbers and select 3 for exit the application.

When you select 1 you can enter your name and number like this:
```console
Select a menu item: 1
Contact Type:
1. Personal
2. Business (or other numbers)
Select a menu item: 1
Enter Name: MyName
Enter Last Name: MyLastName
Phone Number types:
1. HOME
2. WORK
3. MOBILE
Select Phone Number Type: 1
select: 1
Enter phone number: 09211112233
HOME number added successfully
Do you want add more number? (enter y for yes and other wor no): n
New contact successfully added to Phonebook
```

After adding new number program shows the string `"New contact successfully added to Phonebook"`  on the screen.
