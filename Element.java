import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Element{
    // Create a string of full element names given a string of ' - ' deliniated element abbreviations
    public static String getElementsUsed(HashMap<String, String> elementList, String elementWord) {
        String elements = "";
        for (String abbreviation : elementWord.split(" - ")) {
            String fullElement = elementList.get(abbreviation.trim().toLowerCase());

            elements += (fullElement + " - ");
        }
        return elements.substring(0, elements.length()-2);
    }

    // Capitalize only the first letter in a provided string
    public static String capitalize(String word){
        if (word.length() > 1){
            return word.substring(0,1).toUpperCase() + word.substring(1);
        } else {
            return word.toUpperCase();
        }
    }

    public static String buildOutputString(HashMap<String, String> elementList, String elementWord, String originalWord){
        if (elementWord.equals("")) {
            return "Could not create name \"" + originalWord + "\" out of elements.";
        } else {
            String[] elements = elementWord.split(",");
             String output = "";
            for (String element : elements) {
                 output += capitalize(element) + " - ";
             }
             // return the concatenated string, minus the last '-' inserted
            return output.substring(0,output.length()-2);
        }
    }

    // Recursively build the element word from the back to the front of the supplied word
    // returns an empty string if no word can be crafted
    public static String buildElementString(HashMap<String, String> elementList, String fullWord, String buildWord) {
        if (fullWord.equals("")) {
            return buildWord;
        }
        int wordLength = fullWord.length();
        String oneLetter = fullWord.substring(wordLength-1, wordLength);
        String matched = elementList.get(oneLetter);

        if (matched == null){
            if (wordLength-2 < 0) {
                return "";
            }
            // then try with two letters
            String twoLetters = fullWord.substring(wordLength-2, wordLength);
            matched = elementList.get(twoLetters);

            if (matched == null) {
                // no match was found for either one or two letters
                return "";
            } else {
                // matched on two letters
                return buildElementString(elementList, fullWord.substring(0, wordLength-2), twoLetters+","+buildWord);
            }
        }
        // Found a match for one letter
        return buildElementString(elementList, fullWord.substring(0, wordLength-1), oneLetter+","+buildWord);
    }

    // Create a hashmap based on the list of elements, with the key as the
    // abbreviation of the element
    public static HashMap<String, String> initializeElementList(){
        String elementContent = "";
        HashMap<String, String> elements = new HashMap<String, String>();
        try {
            elementContent = new String(Files.readAllBytes(Paths.get("elements.txt")));
            for (String element : elementContent.toLowerCase().split("\\r?\\n")) {
                String[] splitElement = element.split(":");
                elements.put(splitElement[0], splitElement[1]);
            }
        } catch (Exception e) {
            System.out.println("Error reading elements.txt.\nExiting....");
            return null;
        }
        return elements;
    }

    public static boolean validInput(String[] arguments) {
        if ((arguments.length != 1) || (arguments[0].equals(""))) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args){
        if (!validInput(args)) {
            System.out.println("Usage: java Element <input file>");
            System.exit(1);
        }

        HashMap<String, String> elementList = initializeElementList();
        if (elementList == null) {
            System.exit(1);
        }

        String fileContents = "";
        try {
            // create a string from the byte array of the file
            fileContents = (new String(Files.readAllBytes(Paths.get(args[0]))));
        } catch (Exception e) {
            System.out.println("Enter a valid filename.");
            System.exit(1);
        }

        for(String line : fileContents.split("\\r?\\n")) {
            if (line.equals("")) {
                continue;
            }
            // ignore special characters
            String originalLine = line;
            String parsedLine = line.replaceAll("[^A-Za-z]", "");
            int length = parsedLine.length();

            // implement the element word-build algorithm
            String lineOutput = buildElementString(elementList, parsedLine.toLowerCase(), "");
            String finalOutput = buildOutputString(elementList, lineOutput, originalLine);
            String elementsUsed = getElementsUsed(elementList, finalOutput);

            // print the results
            System.out.println("Final Output: " + finalOutput);
            System.out.println("Element List: " + elementsUsed);
        }
    }
}
