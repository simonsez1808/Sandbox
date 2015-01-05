package com.srg.cameraattack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;

public class CreatePasswordFileForHydra {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f = new File("c:\\Users\\Simon\\IntegersForHydra.txt");
		PrintWriter w = new PrintWriter(f);

		for (Long i = 0L; i < 10000000L; i++) {
			w.println(i.toString());
		}
		
		w.flush();
		w.close();
		
		System.out.println("Finished.");
	}
}
