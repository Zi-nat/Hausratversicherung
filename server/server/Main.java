package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			Socket socket = serverSocket.accept();
			System.out.println("client connected");
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				String echoStringFromClient = input.readLine();
				
				if (echoStringFromClient.equals("exit")) {
					break;
				}
				output.println("Hier ist es dein Persönlisches Angebot, Versicherungssumme: " +  echoStringFromClient+ "\u20AC");
			}

		} catch (IOException error) {
			// handle specific exception
			System.out.println("Server exception: " + error.getMessage());
		}
	}
}
