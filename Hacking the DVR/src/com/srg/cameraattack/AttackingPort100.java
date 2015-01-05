package com.srg.cameraattack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class AttackingPort100 {

	public static void main(String[] args) {
		System.out.println("Starting . . .");
		final String HOST_NAME = "simonsez180.no-ip.biz";
		final int PORT_NUMBER = 7050;

		try (Socket echoSocket = new Socket(HOST_NAME, PORT_NUMBER);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(
						new InputStreamReader(System.in))) {
			
			
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + HOST_NAME);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ HOST_NAME);
			System.exit(1);
		}
	}

}
