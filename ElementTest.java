import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;

public class ElementTest {

    // Test that the capitalization function works as expected for a string that
    // is not already capitalized
    @Test
    public void testCapitalizationUncapitalized() {
        assertEquals("This test", Element.capitalize("this test"));
    }

    // Test that the capitalization function has no effect on a word that
    // is already capitalized
    @Test
    public void testCapitalizationCapitalized() {
        assertEquals("NO CHANGE", Element.capitalize("NO CHANGE"));
    }

    // Test that the getElementsUsed function returns a properly formatted
    // string when provided with a string of element value
    @Test
    public void testGetElementsUsed() {
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");
        String testString = "o - o - b - kr ";

        assertEquals("Oxygen - Oxygen - Boron - Krypton ", Element.getElementsUsed(testMap, testString));
    }

    // Test that the getElementsUsed function returns an empty string when given
    // invalid (non-elemental/unformated) input
    @Test
    public void testGetElementsUsedInvalid() {
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");

        assertEquals("", Element.getElementsUsed(testMap, "unformatted"));
    }

    // Given a imited test HashMap, test that the buildOutputString function produces
    // a string of properly spaced, capitalized element abbreviations
    @Test
    public void testBuildOutputString() {
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");
        testMap.put("n", "nitrogen");
        testMap.put("s", "sulfur");
        testMap.put("la", "lanthanum");

        String elementAbbrevs = "la - b - o - o - n - s";
        String returned = Element.buildOutputString(testMap, elementAbbrevs, "Laboons");

        assertEquals("La - B - O - O - N - S ", returned);
    }

    // Test that the correct messasge is returned by the buildOutputString function if
    // the provided elementList string is empty or null
    @Test
    public void testBuildOutputStringEmptyString(){
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");
        testMap.put("n", "nitrogen");
        testMap.put("s", "sulfur");
        testMap.put("la", "lanthanum");

        String expectedMsg = "Could not create name \"Laboons\" out of elements.";
        assertEquals(expectedMsg, Element.buildOutputString(testMap, null, "Laboons"));
        assertEquals(expectedMsg, Element.buildOutputString(testMap, "",   "Laboons"));
    }

    // Test the correct element abbreviation combination is returned by the buildElementString
    // function when provided with a given map and valid input for that map
    @Test
    public void testBuildElementString() {
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");
        testMap.put("n", "nitrogen");
        testMap.put("s", "sulfur");
        testMap.put("la", "lanthanum");

        String expected = "la - b - o - o - n - s";
        String actual = Element.buildElementString(testMap, "laboons");
        assertEquals(expected, actual);
    }

    // Test that the buildElementString function returns a null when no match could
    // possibly be found for a given word
    @Test
    public void testBuildElementStringNoMatch() {
        HashMap<String, String> testMap = new HashMap<String, String>();
        testMap.put("o", "oxygen");
        testMap.put("b", "boron");
        testMap.put("kr", "krypton");
        testMap.put("n", "nitrogen");
        testMap.put("s", "sulfur");
        testMap.put("la", "lanthanum");

        assertNull(Element.buildElementString(testMap, "nope"));
    }

    // Test that the initializeElementList function creates/returns a HashMap
    // with exactly the correct number of elements (118)
    @Test
    public void testElementListSize() {
        assertEquals(118, Element.initializeElementList().size());
    }

    // Test some values that are expected to be in the element HashMap to ensure
    // that it is properly storing key,value pairs as abbreviation,element
    @Test
    public void testElementListKeyValuePairs(){
        HashMap<String,String> testMap = Element.initializeElementList();
        // Making very similar assertions here
        assertEquals("actinium", testMap.get("ac"));
        assertEquals("cesium", testMap.get("cs"));
        assertEquals("livermorium", testMap.get("lv"));
        assertEquals("yttrium", testMap.get("y"));
    }

    // Test that the validInput function properly returns false on null
    @Test
    public void testNullArgs() {
        assertFalse(Element.validInput(null));
    }

    // Test that the validInput fucntion properly returns false when more than one argument
    // is provided
    @Test
    public void testMoreArgs() {
        String[] more = {"one", "two"};
        assertFalse(Element.validInput(more));
    }

    // Test that validInput returns false when no arguments are provided
    @Test
    public void testNoArgs() {
        String[] none = {};
        assertFalse(Element.validInput(none));
    }

    // Test that validInput returns true when passes a String array with a
    // single value
    @Test
    public void testProperArgs() {
        String[] correct = {"Correct"};
        assertTrue(Element.validInput(correct));
    }
}
