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
            // get the element name based on the abbreviation
            String fullElement = elementList.get(abbreviation.trim().toLowerCase());
            if (fullElement == null) {
                return "";
            }
            // concatenate the string to the existing elements already processed, with proper formatting
            elements = elements + capitalize(fullElement) + " - ";
        }
        // return the concatenated string, minus the last dash
        return elements.substring(0, elements.length()-2);
    }

    // Build the string to be output by separating the element abbreviations by hyphens
    // as well as capitalizing them for proper input
    public static String buildOutputString(HashMap<String, String> elementList, String elementWord, String originalWord){
        if ((elementWord == null) || (elementWord.equals(""))) {
            // No combination of abbreviations could build the words, so return that message
            return "Could not create name \"" + originalWord + "\" out of elements.";
        } else {
            // an abbreviation word was properly built, now format it
            String[] elements = elementWord.split("-");
            String output = "";

            // capitalize each abbreviation
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
        HashMap<String, String> elements = new HashMap<String, String>();
        elements.put("ac", "actinium");
        elements.put("ag", "silver");
        elements.put("al", "aluminum");
        elements.put("am", "americium");
        elements.put("ar", "argon");
        elements.put("as", "arsenic");
        elements.put("at", "astatine");
        elements.put("au", "gold");
        elements.put("b", "boron");
        elements.put("ba", "barium");
        elements.put("be", "beryllium");
        elements.put("bh", "bohrium");
        elements.put("bi", "bismuth");
        elements.put("bk", "berkelium");
        elements.put("br", "bromine");
        elements.put("c", "carbon");
        elements.put("ca", "calcium");
        elements.put("cd", "cadmium");
        elements.put("ce", "cerium");
        elements.put("cf", "californium");
        elements.put("cl", "chlorine");
        elements.put("cm", "curium");
        elements.put("cn", "copernicium");
        elements.put("co", "cobalt");
        elements.put("cr", "chromium");
        elements.put("cs", "cesium");
        elements.put("cu", "copper");
        elements.put("db", "dubnium");
        elements.put("ds", "darmstadtium");
        elements.put("dy", "dysprosium");
        elements.put("er", "erbium");
        elements.put("es", "einsteinium");
        elements.put("eu", "europium");
        elements.put("f", "fluorine");
        elements.put("fe", "iron");
        elements.put("fl", "flerovium");
        elements.put("fm", "fermium");
        elements.put("fr", "francium");
        elements.put("ga", "gallium");
        elements.put("gd", "gadolinium");
        elements.put("ge", "germanium");
        elements.put("h", "hydrogen");
        elements.put("he", "helium");
        elements.put("hf", "hafnium");
        elements.put("hg", "mercury");
        elements.put("ho", "holmium");
        elements.put("hs", "hassium");
        elements.put("i", "iodine");
        elements.put("in", "indium");
        elements.put("ir", "iridium");
        elements.put("k", "potassium");
        elements.put("kr", "krypton");
        elements.put("la", "lanthanum");
        elements.put("li", "lithium");
        elements.put("lr", "lawrencium");
        elements.put("lu", "lutetium");
        elements.put("lv", "livermorium");
        elements.put("mc", "moscovium");
        elements.put("md", "mendelevium");
        elements.put("mg", "magnesium");
        elements.put("mn", "manganese");
        elements.put("mo", "molybdenum");
        elements.put("mt", "meitnerium");
        elements.put("n", "nitrogen");
        elements.put("na", "sodium");
        elements.put("nb", "niobium");
        elements.put("nd", "neodymium");
        elements.put("ne", "neon");
        elements.put("nh", "nihonium");
        elements.put("ni", "nickel");
        elements.put("no", "nobelium");
        elements.put("np", "neptunium");
        elements.put("o", "oxygen");
        elements.put("og", "oganesson");
        elements.put("os", "osmium");
        elements.put("p", "phosphorus");
        elements.put("pa", "protactinium");
        elements.put("pb", "lead");
        elements.put("pd", "palladium");
        elements.put("pm", "promethium");
        elements.put("po", "polonium");
        elements.put("pr", "praseodymium");
        elements.put("pt", "platinum");
        elements.put("pu", "plutonium");
        elements.put("ra", "radium");
        elements.put("rb", "rubidium");
        elements.put("re", "rhenium");
        elements.put("rf", "rutherfordium");
        elements.put("rg", "roentgenium");
        elements.put("rh", "rhodium");
        elements.put("rn", "radon");
        elements.put("ru", "ruthenium");
        elements.put("s", "sulfur");
        elements.put("sb", "antimony");
        elements.put("sc", "scandium");
        elements.put("se", "selenium");
        elements.put("sg", "seaborgium");
        elements.put("si", "silicon");
        elements.put("sm", "samarium");
        elements.put("sn", "tin");
        elements.put("sr", "strontium");
        elements.put("ta", "tantalum");
        elements.put("tb", "terbium");
        elements.put("tc", "technetium");
        elements.put("te", "tellurium");
        elements.put("th", "thorium");
        elements.put("ti", "titanium");
        elements.put("tl", "thallium");
        elements.put("tm", "thulium");
        elements.put("ts", "tennessine");
        elements.put("u", "uranium");
        elements.put("v", "vanadium");
        elements.put("w", "tungsten");
        elements.put("xe", "xenon");
        elements.put("y", "yttrium");
        elements.put("yb", "ytterbium");
        elements.put("zn", "zinc");
        elements.put("zr", "zirconium");
        return elements;
    }

    // Determine if the input arguments are valid, e.g. they exist and there
    // is exactly one of them
    public static boolean validInput(String[] arguments) {
        if ((arguments == null) || (arguments.length != 1) || (arguments[0].equals(""))) {
            return false;
        } else {
            return true;
        }
    }

    // Initiate the algorithm and control the flow of the analysis
    public static String analyzeLine(HashMap<String,String> elementList, String line){
        // ignore special characters and make the line lowercase for consistency
        String parsedLine = line.replaceAll("[^A-Za-z]", "");
        String lineOutput = buildElementString(elementList, parsedLine.toLowerCase());
        String finalOutput = buildOutputString(elementList, lineOutput, line);
        String fullElementsUsed = getElementsUsed(elementList, finalOutput);

        return finalOutput + "\n" + fullElementsUsed;
    }

    // Read in the file and make it a string, throwing an exception if
    // any error is encountered
    public static String readFile(String file){
        try {
            // create a string from the byte array of the file
            return (new String(Files.readAllBytes(Paths.get(file))));
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args){
        if (!validInput(args)) {
            System.out.println("Usage: java Element <input file>");
            System.exit(1);
        }

        HashMap<String, String> elementList = initializeElementList();

         String fileContents = readFile(args[0]);
        if (fileContents == null){
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
            String lineOutput = analyzeLine(elementList, line);

            // print the results
            System.out.println(lineOutput+"\n");
        }
    }
}
