# Product Name: Hausratversicherung

## Implementation requirements:

I have used JRE System Library[jdk1.8.0-211] for the Application and the Junit4 library for JUnit-Testing. I have added the library path to my project, so it can be run without any further installation.

I have choosen Eclipse IDE becouse I was familiar with that and know the envirenment better than other IDE. 

## How to run the program:
you have to first run the Server Main method as Java Application and then run the Main method in App-Class as Java Application.
it will automaticly open the console, then you can enter your order in console and press the Enter Keyboard to see the  results. It is possible to check prices for different products untill you type "exit" and it will close the application.

## Why local server:
I wanted to get familiar with Java networking. Server receives a string message from client and reply back in string format. I have alocated the port number 5000 to server communications, and it can be an Integer between zero and 65535. This port number might be reserved or used by other applications. Therefore a check for a free port might be necessary.
At the end, we want server to listen to the client on the port that we allocated.

## Why choosing "enum" for client.Product 
In Java, we can add variables, methods and constructors to "enum". The main objective of "enum" is to define our own data types(Enumerated Data Types).
