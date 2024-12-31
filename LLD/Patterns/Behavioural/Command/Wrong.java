package LLD.Patterns.Behavioural.Command;

// Command
// Also known as Action , Transaction
// Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. (Ref: R)
// This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations (Ref: R)

class Main {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.turnOnAC();
        airConditioner.setTemperature(23);
        airConditioner.turnOffAC();

        // Client needs to be aware of how to turn on/off bulb
        Bulb bulb = new Bulb();
        bulb.turnOnBulb();
        bulb.turnOffBulb();
    }
}

// Disadvantages of this approach:
// 1. Lack of abstraction : Currently , process of turning on AC is simple it is just a single method Call . But what if in future we there are multiple methods to be called before turning on AC then client needs to be aware of all those methods
// 2. Undo / Redo functionality : We do not have undo redo functionality in this approach , and we can't expect AC to have undo redo functionality as AC in itself is a dumb object 
// 3. Difficulty in code maintainability : What if we want to support more devices such as bulb or fan , then the client will be need to be aware of how to turn on Bulb or Fan . Client gets tightly coupled with the devices