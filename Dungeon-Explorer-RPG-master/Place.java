/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netID: dsipel2
 * Description:
 * 	Holds a collection of directions from the place as well as artifacts in the place.
 * 	Also holds a static array of places to access by ID.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Place {
	
	static ArrayList<Place> places = new ArrayList<Place>();

	private int ID;
	private String name;
	private String desc;
	private ArrayList<Direction> dirs;
	private ArrayList<Artifact> artifacts;
	private ArrayList<Character> people;
	
	Place(int ID, String name, String description){
		this.ID = ID;
		this.name = name;
		this.desc = description;
		initializeData();
	}

	Place(Scanner sc){
		//get ID and name from gdf
		getIDAndName(sc);
		//get description from gdf
		getGDFDescription(sc);
		//initialize direction, artifacts, and people arrays
		initializeData();
	}

	//temporary constructor using int version for compatibility
	Place(Scanner sc, int version){
		this(sc);
	}
	
	//returns the name of the place
	String name() {
		return name;
	}
	
	int getID(){
		return ID;
	}

	//displays the name, description, and artifacts in place
	void display(IO io) {
		io.display(name + "\n");
		io.display(desc + "\n");
		for(int i = 0; i < artifacts.size(); i++) {
			io.display("You see a ");
			artifacts.get(i).display(io);
			io.display("\n");
		}
		for(int i = 0; i < people.size(); i++) {
			io.display("You see " + people.get(i).getName() + " here.\n");
		}
	}
	
	//locks/unlocks all doors witch match the key pattern in a
	void useKey(Artifact a) {
		for(Direction d : dirs) {
			d.useKey(a);
		}
	}
	
	//adds a direction to dirs array
	void addDirection(Direction dir) {
		dirs.add(dir);
	}
	
	//adds an artifact to array
	void addArtifact(Artifact a) {
		artifacts.add(a);
	}
	
	//adds a character to array
	void addCharacter(Character c) {
		people.add(c);
	}
	
	//removes a character
	void removeCharacter(Character c) {
		people.remove(c);
	}
	
	//returns true if place is the exit
	boolean isExit() {
		return ID == 1;
	}
	
	//returns requested artifact and removes that artifact from array
	Artifact getArtifact(String a, IO io) {
		for( Artifact b : artifacts) {
			if(a.equalsIgnoreCase(b.name())) {
				artifacts.remove(b);
				return b;
			}
		}
		if(io != null)
			io.display("No matching artifact.\n");
		return null;
	}
	
	//returns next place in given direction
	Place followDirection(String dir, IO io) {
		//throws runtime exception if no directions as this would indicate bad map data
		if(dirs.size() == 0) {
			throw new RuntimeException("MAP ERROR: " + name + " has no directions.");
		}
		for(int i = 0; i < dirs.size(); i++) {
			if (dirs.get(i).match(dir)){
				Place p = dirs.get(i).follow(io);
				if(p.isExit()){
					io.display("You have exited the game!\n");
					System.exit(0);
				}
				return p;
			}
		}
		return this;
	}
	
	//prints ID, name, desc, and all directions from the place.
	void print() {
		System.out.println();
		System.out.println("ID: " + ID);
		System.out.println("name: " + name);
		System.out.println("desc: \n" + desc + "\n");
		System.out.println("Directions: ");
		for(int i = 0; i < dirs.size(); i++) {
			dirs.get(i).print();
		}
		System.out.println("\nArtifacts: ");
		if(artifacts.size() == 0) {
			System.out.println("None\n");
		}
		else {
			for(int i = 0; i < artifacts.size(); i++) {
				artifacts.get(i).print();
			}
		}
		System.out.println("Characters: ");
		if(people.size() == 0) {
			System.out.println("None\n");
		}
		else {
			for(int i = 0; i < people.size(); i++) {
				people.get(i).print();
			}
		}
	}
	
	static Place getPlaceByID(int id) {
		for(Place p : places) {
			if(p.ID == id) {
				return p;
			}
		}
		throw new IllegalArgumentException("Invalid Place ID: " + id);
	}

	static int getRandPlaceID(){
		return getRandPlace().getID();
	}
	
	static Place getRandPlace() {
		Random r = new Random();
		while(true) {
			int random = r.nextInt(places.size() - 2) + 2;
			if(places.get(random).ID != 0 && places.get(random).ID != 1)
				return places.get(random);
		}
	}

	static int getStartingID(){
		return places.get(0).getID();
	}
	
	static void printAll() {
		for(int i = 0; i < places.size(); i++) {
			places.get(i).print();
		}
	}

	void addBoss(Character c){}

	// Modified public interface slightly by Omar
	// Changed return type from boolean to Character
	// Changed method name
	// Added condition for Ally
	public Character getRandomPlayer(){
		for(Character c:people){
			if(c instanceof Player || c instanceof Ally){
				return c;
			}
		}
		return null;
	}

	//helper function for scanner constructor. Sets ID and name
	private void getIDAndName(Scanner sc){
		//initialize new scanner for first place line
		Scanner temp = new Scanner(CleanLineScanner.getCleanLine(sc));
		//read ID of place
		if(temp.hasNextInt()) {
			this.ID = temp.nextInt();
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect File Format in place ID");
		}
		this.name = "";
		//read name of place
		if(temp.hasNextLine()) {
			while(temp.hasNext()) {
				this.name = this.name + temp.next() + " ";
			}
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect File Format in place name");
		}
		//close scanner of first place line
		temp.close();
	}

	//helper function for scanner constructor. Sets the description
	private void getGDFDescription(Scanner sc){
		//read how many lines there are in the description
		Scanner temp = new Scanner(CleanLineScanner.getCleanLine(sc));
		int count;
		if(temp.hasNextInt()) {
			count = temp.nextInt();
		}
		else {
			temp.close();
			throw new RuntimeException("Incorrect File Format in place desc count");
		}
		temp.close();
		//read in description
		this.desc = "";
		for(int i = 0; i < count; i++) {
			this.desc += sc.nextLine() + "\n";
		}
	}

	//helper function for constructor. Initializes arrays
	private void initializeData(){
		//initialize direction, artifacts, and people arrays
		this.dirs = new ArrayList<Direction>();
		this.artifacts = new ArrayList<Artifact>();
		this.people = new ArrayList<Character>();
		//add itself to static list of places
		places.add(this);
	}

	// Added by Omar
	public Character getRandomEnemy(){
		for(Character c:people){
			if(c instanceof Enemy){
				return c;
			}
		}
		return null;
	}
}
