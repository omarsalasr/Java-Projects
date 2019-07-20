/*
 *   Team Members: Omar Salas-Rodriguez (osalas3)
 *                 Dominykas Sipelis    (dsipel2)
 *                 Rahul Chatterjee     (rchatt6)
 *
 * Name: Dominykas Sipelis
 * netid: dsipel2
 */
import java.util.Scanner;

public class CleanLineScanner{

    static String getCleanLine(Scanner sc){
        String result;
        while(sc.hasNextLine()){
            result = sc.nextLine();
            result = result.split("//")[0];
            if(result.length() > 0)
                return result;
        }
        return "";
    }

}