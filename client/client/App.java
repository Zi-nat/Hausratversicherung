package client;
//Aufgabe: Berechnung Versicherungssumme Hausratversicherung

//Vorgaben: 2 Versicherungsprodukte mit unterschiedlichem Preis pro m²		
//* Kompakt: 650€ pro m²		
//* Optimal: 700€ pro m²
//Eingabe>> Produkt: "Kompakt" oder "Optimal" Und Wohnfläche in m²
//Ausgabe>> Versicherungssumme

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 5000)) {
			BufferedReader echos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter echoToServer = new PrintWriter(socket.getOutputStream(), true);

			Scanner productInputScanner = new Scanner(System.in);
			String echoStringFromConsole;
			String areaCompactInput;
			String areaOptimalInput;

			do {
				System.out.println("Dein Produkttype: Kompakt oder Optimal?");
				echoStringFromConsole = productInputScanner.nextLine();

				if (isValid(echoStringFromConsole)) {

					switch (echoStringFromConsole.toLowerCase()) {
					case "kompakt":
						System.out.println("Deine Wohnfläsche in Quadratmeter: ");
						Scanner inputKopmactScanner = new Scanner(System.in);
						echoStringFromConsole = inputKopmactScanner.nextLine();
						double areaCompact = Double.parseDouble(echoStringFromConsole);
						areaCompactInput = Products.COMPACT.insurance(areaCompact);//
						echoToServer.println(areaCompactInput);
						System.out.println(echos.readLine());
						break;

					case "optimal":
						System.out.println("Deine Wohnfläsche in Quadratmeter: ");
						Scanner inputOptimalScanner = new Scanner(System.in);
						echoStringFromConsole = inputOptimalScanner.nextLine();
						double areaOptimal = Double.parseDouble(echoStringFromConsole);
						areaOptimalInput = Products.OPTIMAL.insurance(areaOptimal);
						echoToServer.println(areaOptimalInput);
						System.out.println(echos.readLine());
						break;
					case "exit":
						System.out.println("Auf Wiedersehen :)");
						echoToServer.println(echoStringFromConsole);
						//System.out.println(echos.readLine());
						break;

					default:
						System.out.println("Ungültige Eingabe, gib bitte dein Produkt ein");

						break;
					}
				} else {
					System.out.println("Ungültige Eingabe");
				}

			} while (!echoStringFromConsole.equals("exit"));

			// productInputScanner.close();
		} catch (IOException error) {
			// handle specific exception
			System.out.println("client Error:" + error.getMessage());
		}

	}

	public static boolean isValid(String inputString) {

		Pattern grouPattern = Pattern.compile("^.[A-Za-z]+");
		Matcher prouMatcher = grouPattern.matcher(inputString);
		prouMatcher.reset();
		return prouMatcher.matches();
	}

}