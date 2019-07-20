/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 * Description:
 * 	Acts as doorway between two places.
 * 	contains private enum class.
 * Functions:
 * 	match, lock, unlock, isLocked, follow, useKey, print
 */
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Direction {
	
	private enum DirType{
		NONE("NONE", "NONE"),
		U("U", "Up"),
		D("D", "Down"),
		N("N", "North"),
		E("E", "East"),
		S("S", "South"),
		W("W", "West"),
		NE("NE", "North-East"),
		NW("NW", "North-West"),
		SE("SE", "South-East"),
		SW("SW", "South-West"),
		NNE("NNE", "North-North-East"),
		NNW("NNW", "Noth-North-West"),
		ENE("ENE", "East-North-East"),
		WNW("WNW", "West-North-West"),
		ESE("ESE", "East-South-East"),
		WSW("WSW", "West-South-West"),
		SSE("SSE", "South-South-East"),
		SSW("SSW", "South-South-West");
		
		private final String abb;
		private final String word;
		DirType(String abb, String word){
			this.abb = abb;
			this.word = word;
		}
		
		public String toString() {
			return word;
		}
		
		public boolean match(String s) {
			return s.equalsIgnoreCase(abb) || s.equalsIgnoreCase(word);
		}
	}
	
	private int ID;
	private Place from;
	private Place to;
	private DirType dir;
	private boolean locked;
	private int lockPattern;

	Direction(int ID, Place from, Place to, String dir){
		this.ID = ID;
		this.from = from;
		this.to = to;
		this.locked = false;
		for(DirType d : DirType.values()) {
			if(d.match(dir)) {
				this.dir = d;
			}
		}
		this.lockPattern = 0;
	}
	
	Direction(Scanner sc){
		Scanner temp = new Scanner(CleanLineScanner.getCleanLine(sc));
		//get ID
		if(temp.hasNextInt()) {
			this.ID = temp.nextInt();
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect file format in directions");
		}
		//add self to corresponding place
		if(temp.hasNextInt()) {
			this.from = Place.getPlaceByID(temp.nextInt());
			from.addDirection(this);
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect file format in directions");
		}
		//read direction name
		if(temp.hasNext()) {
			String dir = temp.next();
			for(DirType d : DirType.values()) {
				if(d.match(dir)) {
					this.dir = d;
				}
			}
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect file format in directions");
		}
		//read destination of direction
		if(temp.hasNextInt()) {
			int id = temp.nextInt();
			//lock if negative destination id
			if(id <= 0) {
				this.locked = true;
				id = -id;
			}
			else {
				this.locked = false;
			}
			this.to = Place.getPlaceByID(id);
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect file format in directions");
		}
		//read Lock Pattern
		if(temp.hasNextInt()) {
			this.lockPattern = temp.nextInt();
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect file format in directions");
		}
		temp.close();
	}

	//temporary constructor using int version for compatibility
	Direction(Scanner sc, int version){
		this(sc);
	}
	
	boolean match(String s){
		return dir.match(s);
	}
	
	void lock() {
		this.locked = true;
	}
	
	void unlock() {
		this.locked = false;
	}
	
	boolean isLocked() {
		return locked;
	}
	
	Place follow(IO io) {
		if(locked) {
			if(io != null)
				io.display("Door is Locked\n");
			return from;
		}
		return to;
	}
	
	String getDir() {
		return dir.toString();
	}
	
	int getID() {
		return ID;
	}
	
	//toggles locked status if given artifacts key pattern matches lockpattern
	void useKey(Artifact a) {
		if(lockPattern == 0) {
			return;
		}
		int key = a.key();
		if(key > 0 && key == lockPattern) {
			if(locked) {
				unlock();
				System.out.println(dir + " door unlocked");
			}
			else {
				lock();
				System.out.println(dir + " door locked");
			}
		}
	}
	
	//prints ID, from place, to place, direction name, and lock status
	void print() {
		System.out.print("ID: " + ID + " ");
		System.out.print("From: " + from.name() + " ");
		System.out.print("To: " + to.name() + " ");
		System.out.print("Dir: " + dir + " ");
		System.out.println("Locked: " + locked);
		System.out.println("LockPattern: " + lockPattern);
	}

	// Function moved from AI to Direction by Omar
	// Helper function used for the direction decision
    // Returns a random direction
    public static String getRandDirection() {
        Random rand = new Random();
        int decision = rand.nextInt(18);
        if(decision == 0)
            return "N";
        else if(decision == 1)
            return "S";
        else if(decision == 2)
            return "E";
        else if(decision == 3)
            return "W";
        else if(decision == 4)
            return "U";
        else if(decision == 5)
            return "D";
        else if(decision == 6)
            return "NE";
        else if(decision == 7)
            return "NW";
        else if(decision == 8)
            return "SE";
        else if(decision == 9)
            return "SW";
        else if(decision == 10)
            return "NNE";
        else if(decision == 11)
            return "NNW";
        else if(decision == 12)
            return "ENE";
        else if(decision == 13)
            return "WNW";
        else if(decision == 14)
            return "ESE";
        else if(decision == 15)
            return "WSW";
        else if(decision == 16)
            return "SSE";
        else if(decision == 17)
            return "SSW";
        return "NONE";
    }

	
}
