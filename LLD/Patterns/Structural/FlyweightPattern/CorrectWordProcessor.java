package LLD.Patterns.Structural.FlyweightPattern;

import java.util.HashMap;
import java.util.Map;

// Reference: Shreyansh Jain (concept and coding yt channel)
interface ILetter {
    public void display(int row, int column); // getting extrinsic data through parameters
}

class DocumentCharacter implements ILetter { // intrinsic data hence no setter
    private char character;
    private String fontType;
    private int size;

    DocumentCharacter(char character, String fontType, int size) {
        this.character = character;
        this.fontType = fontType;
        this.size = size;
    }

    public char getCharacter() {
        return character;
    }

    public String getFontType() {
        return fontType;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void display(int row, int column) {
        // Displaying the character with particular fontType and size at the given row
        // and column
    }
}

class LetterFactory {
    private static Map<java.lang.Character, ILetter> characterCache = new HashMap<>();

    public static ILetter createLetter(char character, String fontType, int size) {
        if (!characterCache.containsKey(character)) {
            characterCache.put(character, new DocumentCharacter(character, fontType, size));
        }
        return characterCache.get(character);
    }
}

class Main {
    public static void main(String[] args) {
        // Also see WrongWordProcessor.java for comparison 

        ILetter char1 = LetterFactory.createLetter('t', "Arial", 100);
        char1.display(0, 0);

        ILetter char2 = LetterFactory.createLetter('h', "Arial", 100);
        char2.display(0, 1);

        // ... and so on for each character i.e. 58 times but actual objects created will be less than 58
    }
}