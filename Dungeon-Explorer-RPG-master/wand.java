/*
*   Team Members: Omar Salas-Rodriguez (osalas3)
*                 Dominykas Sipelis    (dsipel2)
*                 Rahul Chatterjee     (rchatt6)
*
*/
import java.util.*;
public class wand extends Artifact {
	boolean haslight = false;
	boolean broken = false;

	wand(boolean haslight, boolean broken, Scanner sc, double v){
		super(sc, v);
		this.haslight = haslight;
		this.broken = broken;
	}

	void flipBroken(){
		broken = !broken;
	}

	boolean isLight(){
		return haslight;
	}
}
