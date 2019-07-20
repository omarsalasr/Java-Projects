/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
*/
import java.util.*;
public class weapons extends Artifact{
	int damage = 0;
	int health = 0;
	boolean broken = false;

	weapons(int d, int h, boolean b, Scanner sc, double v){
		super(sc, v);
		damage = d;
		health = h;
		broken = b;
	}

	void dulling(){
		damage = damage - 5;
		health = health - 10;
	}

	int getDamage(){
		return damage;
	}

	boolean isBroken(){
		if(damage<=0 || health<=0)
			broken = true;
		return broken;
	}

	void display(IO io) {
		if(broken){
			io.display("Your weapon is broken");
		}

		else{
			io.display("Your weapon health: " + health);
			io.display("Your weapon damage: " + damage);
		}
	}
}
