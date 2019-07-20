/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 */

import java.util.Scanner;

public class KeyboardScanner{
	private static Scanner sc;
	
	private KeyboardScanner() {
		
	}
	
	public static Scanner getKeyboardScanner() {
		if(sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}
}