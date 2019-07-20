/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
*/
import java.util.*;
public class potions extends Artifact{
	int health;

	potions(int health, Scanner sc, double v){
		super(sc, v);
		this.health = health;
	}

	void use(Character c, Place p){
		if(health>=0)
			c.health += health;
		else{
			c.health -= -health;
		}
	}

	void display(IO io) {
		if(health>=0){
			io.display("You gained " + health + "health");
		}

		else{

			io.display("You lost " + (-health) + "health");
		}
	}
}
