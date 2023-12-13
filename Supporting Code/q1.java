import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class q1{

    // retrives and returns a list of valid words from Input219.txt in an arraylist
    // dictionaryWords argument is a list of words 
    // which must contain each valid word from our input file 
    // in order to be considered valid 
    public static ArrayList<String> getValidInput(ArrayList<String> dictionaryWords){
        // inputWords var will be the arraylist of inputted words from the file 
        ArrayList<String> inputWords = new ArrayList<String>();

        try {
            File input = new File("Input219.txt");
            Scanner in = new Scanner(input);
            while (in.hasNextLine()){
                for(String word : in.nextLine().split("[\\s]")){
                    // Breaks up a line of the document into words seperated by any number of spaces
                    // Then format each word to remove any correctly placed punctuation
    
                    String wordTrimmed = word.replaceFirst("^[\\p{Punct}]{1}", "");
                    // Removes one punctuation char at the start of a word
    
                    wordTrimmed = wordTrimmed.replaceFirst("[\\p{Punct}]{1}$", ""); 
                    // Removes one punctuation char at the end of a word

                    wordTrimmed = wordTrimmed.toLowerCase();
                    // Formats word as lower case for dictionary comparison

                    if(dictionaryWords.contains(wordTrimmed)){
                        inputWords.add(word);
                    }
                }
            }
            in.close();
        } catch (FileNotFoundException e){
            System.out.println("File Input219.txt was not found in the current directory. Please check the file exists and is the correct file type, then try again.");
        }

        return inputWords;
    }

    // retrieves and returns an arraylist of words from google-10000-english-no-swears.txt
    public static ArrayList<String> getVocab(){
        
        // dictionaryWords var will be the list of words retrieved from the google-10000-english-no-swears.txt file in an arraylist
        ArrayList<String> dictionaryWords = new ArrayList<String>();

        try{
            File dictionary = new File("google-10000-english-no-swears.txt");
            Scanner in = new Scanner(dictionary);

            // in order to make no assumptions about the format of the dictionary file, we break the lines up as if it was plaintext
            while (in.hasNextLine()){
                for(String word : in.nextLine().split("[\\s]")){
                    // Breaks up a line of the document into words seperated by any number of spaces
                    // Then format each word to remove any correctly placed punctuation
    
                    word = word.replaceFirst("^[\\p{Punct}]{1}", "");
                    // Removes one punctuation char at the start of a word
    
                    word = word.replaceFirst("[\\p{Punct}]{1}$", ""); 
                    // Removes one punctuation char at the end of a word

                    word = word.toLowerCase();
                    // Formats word as lower case for dictionary comparison

                    dictionaryWords.add(word);
                }
            }
            in.close();

        } catch (FileNotFoundException e){
            System.out.println("File google-10000-english-no-swears.txt was not found in the current directory. Please check the file exists and is the correct type, then try again.");
        }                
        return dictionaryWords;
    }

    public static void main(String[] args){
        System.out.println(getValidInput(getVocab()).toString());
        // prints out the valid words from the input file, filtered by the words in the dictionary file
    }
}