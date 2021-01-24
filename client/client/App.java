package client;

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
		// The address of the host and port number need to be specified. In case local
		// host doesn't worked "127.0.0.1" can be used as a local address
		try (Socket socket = new Socket("localhost", 5000)) {
			BufferedReader echos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter echoToServer = new PrintWriter(socket.getOutputStream(), true);

			Scanner productInputScanner = new Scanner(System.in);
			String echoStringFromConsole;
			String areaCompactInput;
			String areaOptimalInput;
			// do-while gives the client more chance to see also the other "Angebot"
			do {
				System.out.println("Dein Produkttype: Kompakt oder Optimal?");
				echoStringFromConsole = productInputScanner.nextLine();
				// It asks the user to enter the string and then validate the input via
				// isValid-function
				if (isValid(echoStringFromConsole)) {
					// We have access to each product via switch-case
					switch (echoStringFromConsole.toLowerCase()) {
					case "kompakt":
						System.out.println("Deine Wohnfläsche in Quadratmeter: ");
						Scanner inputKopmactScanner = new Scanner(System.in);
						echoStringFromConsole = inputKopmactScanner.nextLine();
						double areaCompact = Double.parseDouble(echoStringFromConsole);
						// Call the insurance method from client.Products to receive the
						// "Versicherungssumme"
						areaCompactInput = Products.COMPACT.insurance(areaCompact);
						// and then send it backs to the server
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
						break;

					default:
						System.out.println("Ungültige Eingabe, gib bitte dein Produkt ein");

						break;
					}
				} else {
					// If the entry is not valid, the user will be informed
					System.out.println("Ungültige Eingabe");
				}

			} while (!echoStringFromConsole.equals("exit"));

			productInputScanner.close();
		} catch (IOException error) {
			// Handle specific exception
			System.out.println("client Error:" + error.getMessage());
		}

	}

    //isValid-function will control the input, that comes from user and 
	//checks to ensure that it is just a string of letters e.g not a number
	public static boolean isValid(String inputString) {
		Pattern groupPattern = Pattern.compile("^.[A-Za-z]+");
		Matcher groupMatcher = groupPattern.matcher(inputString);
		groupMatcher.reset();
		return groupMatcher.matches();
	}

}