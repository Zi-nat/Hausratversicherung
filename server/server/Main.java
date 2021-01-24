package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		// I have allocated the port number 5000, and it can be an Integer between zero
		// and 65535. This port number might be reserved or used by other applications.
		// Therefore a check for a free port might be necessary.

		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			Socket socket = serverSocket.accept();
			System.out.println("client connected");
			// When the client does connect to the server, the server use Input/OutputStream
			// to send and receive data
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Set the second argument as true, so that doesn't need to call flush() method
			// After each write to the Stream to ensure that data is sent.
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			// Remain the server to be alive until client needs it
			while (true) {
				String echoStringFromClient = input.readLine();

				// When the server gets the exit it will terminate
				if (echoStringFromClient.equals("exit")) {
					break;
				}
				output.println("Hier ist es dein Persönlisches Angebot, Versicherungssumme: " + echoStringFromClient
						+ "\u20AC");
			}

		} catch (IOException error) {
			// Handle specific exception
			System.out.println("Server exception: " + error.getMessage());
		}
	}
}
