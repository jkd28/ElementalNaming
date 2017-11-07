import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Element{
    // Capitalize only the first letter in a provided string
    public static String capitalize(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    // Create a string of full element names given a string of ' - ' deliniated element abbreviations
    public static String getElementsUsed(HashMap<String, String> elementList, String elementWord) {
        String elements = "";
        for (String abbreviation : elementWord.split("-")) {
            String fullElement = elementList.get(abbreviation.trim().toLowerCase());
            if (fullElement == null) {
                return "";
            }
            elements = elements + capitalize(fullElement) + " - ";
        }
        return elements.substring(0, elements.length()-2);
    }

    // Build the string to be output by separating the element abbreviations by hyphens
    public static String buildOutputString(HashMap<String, String> elementList, String elementWord, String originalWord){
        if ((elementWord == null) || (elementWord.equals(""))) {
            return "Could not create name \"" + originalWord + "\" out of elements.";
        } else {
            String[] elements = elementWord.split("-");
            String output = "";
            for (String element : elements) {
                element = capitalize(element.trim());
                output = output + element + " - ";
             }
             // return the concatenated string, minus the last '-' inserted
            return output.substring(0,output.length()-2);
        }
    }

    // Recursively determine if it is possible to build the word from a combination of the
    // element abbreviations provided
    public static String buildElementString(HashMap<String,String> elementList, String fullWord) {
        // Check all remaining characters in the hash table, and return them if they work
        if (elementList.get(fullWord) != null) {
            return fullWord;
        }

        // Iterate through combinations of one and two letter, backtrackign when no match is found
        for (int i = 1; i <= 2; i++) {
            String possibleElement = fullWord.substring(0, i);

            if (elementList.get(possibleElement) != null) {
                // if there is a match here, try the rest of the word.
                String elementWord = buildElementString(elementList, fullWord.substring(i, fullWord.length()));

                if (elementWord != null) {
                    // If there's a match later, concatenate the current abbreviation to the
                    // combination of later abbreviations
                    return possibleElement + " - " + elementWord;
                }
            }
        }
        // If no match was found for any substing length 1 or 2, then there is no possible way to make a word
        // out of the elements
        return null;
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

    // Determine if the input arguments are valid, e.g. they exist and there
    // is exactly one of them
    public static boolean validInput(String[] arguments) {
        if ((arguments.length != 1) || (arguments[0].equals(""))) {
            return false;
        } else {
            return true;
        }
    }

    public static String analyzeLine(HashMap<String,String> elementList, String line){
        String parsedLine = line.replaceAll("[^A-Za-z]", "");
        String lineOutput = buildElementString(elementList, parsedLine.toLowerCase());
        String finalOutput = buildOutputString(elementList, lineOutput, line);
        String fullElementsUsed = getElementsUsed(elementList, finalOutput);

        return finalOutput + "\n" + fullElementsUsed;
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
            System.out.println("ERROR: Enter a valid filename.");
            System.exit(1);
        }

        // Iterate through every line of the file
        for(String line : fileContents.split("\\r?\\n")) {
            if (line.equals("")) {
                continue;
            }
            // ignore special characters
            String originalLine = line;

            // implement the element word-build algorithm
            String output = analyzeLine(elementList, line);

            // print the results
            System.out.println(output);
        }
    }
}
