import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Element{

    // Builds a string from the element abbreviations recursively
    public static String buildElementString(HashMap<String, String[]> elementList, int position, String toMatch,
                                            String elementString, boolean foundMatch){
        // Base case if the position will be invalid
        if (position-2 < 0) {
            return elementString;
        }

        System.out.println("Trying: " + toMatch.substring(position-2, position));

        String[] matched = getMatchingElement(elementList, toMatch.substring(position-2, position));

        if (matched == null) {
            //TODO figure out what to do on a miss for position - 2, maybe start with position-1 and work way up to 2
            return "Failed";
        }
        elementString = elementString + matched[1];
        return buildElementString(elementList, position-2, toMatch, elementString, true);
    }


    // Return the
    public static String[] getMatchingElement(HashMap<String, String[]> elementList, String matchPortion) {
        return elementList.get(matchPortion);
    }

    // Creates a hashmap based on the list of elements, with the key as the
    // abbreviation of the element
    public static HashMap<String, String[]> initializeElementList(){
        String elementContent = "";
        HashMap<String, String[]> elements = new HashMap<String, String[]>();
        try {
            elementContent = new String(Files.readAllBytes(Paths.get("elements.txt")));
            for (String element : elementContent.split("\\r?\\n")) {
                String[] splitElement = element.split(":");
                elements.put(splitElement[0], splitElement);
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

        HashMap<String, String[]> elementList = initializeElementList();
        if (elementList == null) {
            System.exit(1);
        }

        String fileContents = "";
        try {
            // create a string from the byte array of the file
            fileContents = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (Exception e) {
            System.out.println("Enter a valid filename.");
            System.exit(1);
        }

        for(String line : fileContents.split("\\r?\\n")) {
            // ignore special characters
            String originalLine = line;
            String parsedLine = line.replaceAll("[^A-Za-z]", "");

            // implement the element detection algorithm
            System.out.println("Final " + buildElementString(elementList, parsedLine.length(), parsedLine, "", true));
        }
    }
}
