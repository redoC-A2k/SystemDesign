package LLD.Patterns.Structural.FlyweightPattern;

// Reference if S-> shreyansh jain (concept and coding yt channel) and if R -> refactoring.guru 

// Also known as: Cache (R)
// Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object (R)
// Problem (R): 
// Suppose you want to design a game , a shooting game . Now you have all elements of thrills in it . Like sharpnel pieces , bullet and missile.
// Now you have to create a lot of objects of these elements. But the problem is that these objects are heavy and consume a lot of memory.
// Solution (R):
// If you realize then you can see that all bullets are same in color and shape . Likewise so is the case for missile and sharpnel pieces.
// So you can divide all the objects into two parts : intrinsic and extrinsic.
// Intrinsic : Common part of the object i.e. common part of each bullet or common part of each missile and so on (eg color or shape)
// Extrinsic : Unique part of the object i.e. unique part of each bullet or unique part of each missile and so on (eg position or speed)
// Now you can create a factory class which will create the objects and store the intrinsic part in a map. If the object is already created then it will return the object from the map.
// This way you can save memory
// As you can create a bullet flyweight object (object with all the shared properties) and reuse it in all other bullet objects likewise for missile and sharpnel pieces

// When to use ? (S)
// 1. When memory is limited
// 2. When Objects share data
// - Intrinsic data : shared among objects and remain same once defined one value
// - Extrinsic data : changes based on client input and differs from obejct to object
// 3. Creation of object is expensive

// How it works? (S)
// 1. From object, remove all the Extrinsic data and keep only Intrinsic data (this object is called flyweight object)
// 2. This flyweight class should be immutable(i.e. no setter)
// 3. Extrinsic data can be passed as a parameter to the flyweight class in method parameter
// 4. Once the flyweight object is created, it is cached and reused whenever required

// Example: (S)
// Suppose we are creating a game where we need to create a army of robots (robots can be of type humanoid or dog)

class Sprites {}; // it is a bitmap graphic array for representing the robot image on screen

class Robot {
    private int coordinateX; // 4b
    private int coordinateY; // 4b
    private String type; // lets say 30b
    private Sprites body; // it is quite heavy object property // 10kb
    
    public Robot(int coordinateX, int coordinateY, String type, Sprites body) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.type = type;
        this.body = body;
    }
} // so total size of a object is lets say roughly 11kb

// Now suppose we have to create 5,00,000 robots
// So total memory required = 11kb * 5,00,000 = 5500mb = 5.5gb

class Main {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;

        for(int i=0; i<500000; i++){
            Sprites humanoidSprites = new Sprites();
            Robot humanoidRobot = new Robot(x, y, "HUMANOID", humanoidSprites); // 5.5gb memory will be consumed
        }

        for(int i=0; i<500000; i++){
            Sprites dogSprites = new Sprites();
            Robot dogRobot = new Robot(x, y, "ROBOTIC_DOG", dogSprites); // again here 5.5gb memory will be consumed
        }
    }
    // So in total we are consuming a lot of memory 11gb which is a lot
}
