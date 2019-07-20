package evil.hangman;



import java.util.ArrayList;     // Used to create ArrayLists dictionary use
import java.util.Scanner;
import java.io.*;               // Used for IOException, File
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {

    // Declare a dynamically allocated ArrayList of Strings for the dictionary.
    // The dictionary can hold any number of words.
    ArrayList<String> dictionary;
    private File dictionaryFile;

	// Constructor
	Dictionary()
	{
        // Define the instance of the dictionary ArrayList
        dictionary = new ArrayList<String>();
        // Now fill the dictionary array list with words from the dictionary file
        readInDictionaryWords();
	}//end Constructor
	

	//---------------------------------------------------------------------------------
    // Read in the words to create the dictionary.
    public void readInDictionaryWords() 
    {
        
//          
        FileReader r = null;
        try 
        {
            r = new FileReader("src/resources/Dictionary.txt");
            
        } 
        catch (FileNotFoundException ex) 
        {

                System.out.println("*** Error *** \n" +
                                   "Your dictionary file has the wrong name or is " +
                                   "in the wrong directory.  \n" +
                                   "Aborting program...\n\n");
                System.exit( -1);    // Terminate the program

        }
     Scanner f = new Scanner(r);

     while(f.hasNextLine())
     {
        dictionary.add(f.nextLine());
     }

    }//end createDictionary()
    
    
    //---------------------------------------------------------------------------------
    // Allow looking up a word in dictionary, returning a value of true or false
    public boolean wordExists( String wordToLookup)
    {
        if( dictionary.contains( wordToLookup.toUpperCase())) {
            return true;    // words was found in dictionary
        }
        else {
            return false;   // word was not found in dictionary    
        }
    }//end wordExists
    
    
    //---------------------------------------------------------------------------------
    // return number of words in dictionary
    public int numberOfWordsInDictionary()
    {
    	return dictionary.size();
    }

    //---------------------------------------------------------------------------------
    // return word at a particular position in dictionary
    public String wordAtIndex( int index)
    {
    	return dictionary.get( index);
    }
     
    public int indexOfWord(String wordToLookup)
    {
        for(int i = 0;i<dictionary.size();i++)
        {
            if (dictionary.get(i).equalsIgnoreCase(wordToLookup))
                return i;
        }
        return -1;
    }
    public ArrayList getDictionary()
    {
        return dictionary;
    }
    //returns an array of words that do not contain the letters
    //the words will be of a certain length only
    public ArrayList getArrayNoMatch(String letters,int len)
    {
        ArrayList<String> tempDict = new ArrayList();
        
        for(String word: dictionary)
            if(word.length() == len){
                tempDict.add(word);
                for(int i = 0; i < letters.length(); ++i){
                    String let = letters.charAt(i) + "";
                    for(int j = 0; j < word.length(); ++j)
                        if(let.equalsIgnoreCase(word.charAt(j) + ""))
                            tempDict.remove(word);
                }
            }
        
        
        
        /*
        letters = letters.toUpperCase
        because it makes all the letters upper case so there is no unequalness
        makes the program work no matter what
        3 loops
        
        notes:
        cat dog ape man
        abc guesses a single string
        does a = c? no
        does a = a? yes
        does a = t? no
        this is what you would write if you were to use conditions only
        */
        
          
        
        //return an array
        //consist of words that wont have any of the letters of the string that was passed
        //array wont have words other than the number of letters long
        //dont remove from the dictionary
        //destroying data
        //clone the dictionary array
        //remove
        //or make the array blank
        //add the words from the dictionary to the other array
        return tempDict;
    }
    
    

}//end class Dictionary