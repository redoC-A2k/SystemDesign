package LLD.Patterns.Structural.FlyweightPattern;
// Reference if S-> shreyansh jain (concept and coding yt channel) and if R -> refactoring.guru 

import java.util.HashMap;
import java.util.Map;

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

interface IRobot {
    void display(int x, int y);
}

class HumanoidRobot implements IRobot { // only Intrinsic data // 1.
    private String type;
    private Sprites body;
    
    public HumanoidRobot(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public String getType() { // 2. 
        return type;
    }

    public Sprites getBody() { // 2. 
        return body;
    }

    @Override
    public void display(int x, int y) { //3. getting extrinsic data coordinates x and y as parameter
        // use the humanoid sprites object
        // and X and Y coordinate to render the image
    }
}

class RoboticDog implements IRobot { // only Intrinsic data
    private String type;
    private Sprites body;
    
    public RoboticDog(String type, Sprites body) {
        this.type = type;
        this.body = body;
    }

    public String getType() { // 2.
        return type;
    }

    public Sprites getBody() { // 2.
        return body;
    }

    @Override
    public void display(int x, int y) { // 3. getting extrinsic data coordinates x and y as parameter
        // use the dog sprites object
        // and X and Y coordinate to render the image
    }
}

class RoboticFactory {
    private static Map<String, IRobot> roboticObjectCache = new HashMap<>();
    
    public static IRobot createRobot(String robotType) {
        if (roboticObjectCache.containsKey(robotType)) {
            return roboticObjectCache.get(robotType);
        } else if (robotType == "HUMANOID") {
            Sprites humanoidSprites = new Sprites();
            IRobot humanoidRobot = new HumanoidRobot("HUMANOID", humanoidSprites);
            roboticObjectCache.put(robotType, humanoidRobot);
            return humanoidRobot;
        } else if (robotType == "ROBOTOIC_DOG") {
            Sprites dogSprites = new Sprites();
            IRobot dogRobot = new RoboticDog("ROBOTIC_DOG", dogSprites);
            roboticObjectCache.put(robotType, dogRobot);
            return dogRobot;
        } else return null;
    }
}

class Main {
    public static void main(String[] args) {
        IRobot humanoidRobot1 = RoboticFactory.createRobot("HUMANOID"); // Robot object created
        humanoidRobot1.display(1, 2);

        IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID"); // Robot object reused
        humanoidRobot2.display(3, 4); // it is just that it is being displayed at different location

        IRobot roboticDog1 = RoboticFactory.createRobot("ROBOTIC_DOG"); // Robot object created
        roboticDog1.display(5, 6);

        IRobot roboticDog2 = RoboticFactory.createRobot("ROBOTIC_DOG"); // Robot object reused
        roboticDog2.display(7, 8); // it is just that it is being displayed at different location
    }
}