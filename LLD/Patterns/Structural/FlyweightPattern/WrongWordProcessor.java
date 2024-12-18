package LLD.Patterns.Structural.FlyweightPattern;

class Character {
    char character;
    String fontType;
    int size;
    int row;
    int column;

    Character(char Character, String fontType, int size, int row, int column) {
        this.character = Character;
        this.fontType = fontType;
        this.size = size;
        this.row = row;
        this.column = column;
    }
}

class Main {
    public static void main(String[] args) {
    /*
     * "This is the data we want to write into the word processor" -> total 58 characters
     * Total : 58 times
     * t = 7 times (it is repeated 7 times)
     * h = 3 times
     * a = 3 times
     * ... and so on many characters are repeated
     */
    
     Character char1 = new Character('t', "Arial", 100, 0, 0);
     Character char2 = new Character('h', "Arial", 100, 0, 1);
     Character char3 = new Character('i', "Arial", 100, 0, 2);
     Character char4 = new Character('s', "Arial", 100, 0, 3);
    //  ... and so on for each character i.e. 58 times
    }

    // If you notice , character, fontType, size are repeated for each character such for 't' it is 7 times repeatition . So these 3 are intrinsic
}