/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
*/
import java.util.*;
public class Artifact {
	Scanner scannerA;
	private int value;
	private int id;
	private int weight;
	private String name;
	private String description = "";
	private Place c;
	private int keyPattern;
	private int placeOrCharID;

	Artifact(Scanner s, double version){
		scannerA = s;
		if(version == 3.1) {
			String str = scannerA.nextLine();
			while(str.length() == 0) {
				if(str.equals("")) {
					str = scannerA.nextLine();
					if(str.length() == 0)
						str = scannerA.nextLine();
				}
				if(str.length() == 0)
					str = scannerA.nextLine();
			}
			//Sets location of this artifact to the place from the given place id
			Place.getPlaceByID(Integer.parseInt(str)).addArtifact(this);
			str = scannerA.nextLine();
			//Splits string into multiple part which are split by tab
			String [] multiple_str = str.split("\t");
			id = Integer.parseInt(multiple_str[0]);
			value = Integer.parseInt(multiple_str[1]);
			weight = Integer.parseInt(multiple_str[2]); //Same as mobility
			keyPattern = Integer.parseInt(multiple_str[3]);
			name = multiple_str[4];
			int ctr = Integer.parseInt(scannerA.nextLine());
			for(int i=0;i<ctr;i++) {
				description += scannerA.nextLine() + "\n";
			}
		}

		if(version == 4.0) {
			String str = scannerA.nextLine();
			while(str.length() == 0) {
				if(str.equals("")) {
					str = scannerA.nextLine();
					if(str.length() == 0)
						str = scannerA.nextLine();
				}
				if(str.length() == 0)
					str = scannerA.nextLine();
			}
			//Sets location of this artifact to the place from the given place id
			Place.getPlaceByID(Integer.parseInt(str)).addArtifact(this);
			str = scannerA.nextLine();
			String [] porId = str.split("\t");
			placeOrCharID = Integer.parseInt(porId[0]);
			if(placeOrCharID < 0){
				Character.getCharacterByID(-placeOrCharID).addArtifact(this);
			}

			else if(placeOrCharID == 0){
				Place.getRandPlace().addArtifact(this);
			}

			else{
				Place.getPlaceByID(placeOrCharID).addArtifact(this);
			}
			str = scannerA.nextLine();
			//Splits string into multiple part which are split by tab
			String [] multiple_str = str.split("\t");
			id = Integer.parseInt(multiple_str[0]);
			value = Integer.parseInt(multiple_str[1]);
			weight = Integer.parseInt(multiple_str[2]); //Same as mobility
			keyPattern = Integer.parseInt(multiple_str[3]);
			name = multiple_str[4];
			int ctr = Integer.parseInt(scannerA.nextLine());
			for(int i=0;i<ctr;i++) {
				description += scannerA.nextLine() + "\n";
			}
		}

		if(version == 51) {
			String str = scannerA.nextLine();
			while(str.length() == 0) {
				if(str.equals("")) {
					str = scannerA.nextLine();
					if(str.length() == 0)
						str = scannerA.nextLine();
				}
				if(str.length() == 0)
					str = scannerA.nextLine();
			}
			if(str.equals(""))
				str = scannerA.nextLine();
			//Sets location of this artifact to the place from the given place id
			placeOrCharID = Integer.parseInt(str);
			if(placeOrCharID < 0){
				placeOrCharID = -1 * placeOrCharID;

				if(Character.getCharacterByID(placeOrCharID) == null){
					Place.getRandPlace().addArtifact(this);
				}
				else {
					Character.getCharacterByID(placeOrCharID).addArtifact(this);
				}
			}

			else if(placeOrCharID == 0){
				Place.getRandPlace().addArtifact(this);
			}

			else{
				if(Place.getPlaceByID(placeOrCharID) == null){
					Place.getRandPlace().addArtifact(this);
				}
				else {
					Place.getPlaceByID(placeOrCharID).addArtifact(this);
				}
			}
			str = scannerA.nextLine();
			//Splits string into multiple part which are split by tab
			String [] multiple_str = str.split("\t");
			id = Integer.parseInt(multiple_str[0]);
			value = Integer.parseInt(multiple_str[1]);
			weight = Integer.parseInt(multiple_str[2]); //Same as mobility
			keyPattern = Integer.parseInt(multiple_str[3]);
			name = multiple_str[4];
			int type = Integer.parseInt(scannerA.nextLine());

				int ctr = Integer.parseInt(scannerA.nextLine());
				for(int i=0;i<ctr;i++) {
					description += scannerA.nextLine() + "\n";
				}

			if(type == 0){
				potions p = new potions(10, scannerA, version);
			}

			else if(type == 1){
				wand w = new wand(true, false, scannerA, version);
			}

			else if(type == -1){
				int d = Integer.parseInt(scannerA.nextLine());
				int h = Integer.parseInt(scannerA.nextLine());
				weapons wp = new weapons(d, h, false, scannerA, version);
			}
			else {

			}

		}
	}

	int value() {
		return value;
	}

	int key() {
		return keyPattern;
	}

	int weight() {
		return weight;
	}

	String name() {
		return name;
	}

	String description() {
		return description;
	}

	void use() {
		c.useKey(this);
	}


	void use(Character c, Place p){
		p.useKey(this);
	}

	void print() {
		System.out.println("Name: " + name + ", Value: " + value + ", Weight: " + weight + ", Description: " + description + "\n");
	}
	
	public String print1() {
		return ("Name: " + name + ", Value: " + value + ", Weight: " + weight + ", Description: " + description + "\n");
	}


	void display(IO io){
		io.display(name + "\n");
		io.display(description);
	}


	boolean match(String s){
		return s.equalsIgnoreCase(name);
	}

}
