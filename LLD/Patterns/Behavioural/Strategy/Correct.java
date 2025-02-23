package LLD.Patterns.Behavioural.Strategy;

// Strategy
// Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable

// Problem (Ref S)
// Imagine you are creating a vehicle simulation game. Now vehicle can have different drive types like passenger drive , sproty drive, off road drive etc.
// See more in Wrong.java

// Solution
// We can use Strategy pattern to solve this problem. We can create a interface DriveStrategy and then create different classes implementing this interface.
// Then we can create a Vehicle class which will have a reference to DriveStrategy interface. This way we can change the drive type of vehicle at runtime.

// How to implement(Ref R)
// 1. In the context class, identify an algorithm that’s prone to frequent changes
// 2. Declare the strategy interface common to all variants of the algorithm.
// 3. One by one, extract all algorithms into their own classes. They should all implement the strategy interface.
// 4. In the context class, add a field for storing a reference to a strategy object. Provide a setter for replacing values of that field. The context should work with the strategy object only via the strategy interface
// 5. Clients of the context must associate it with a suitable strategy that matches the way they expect the context to perform its primary job

interface DriveStrategy {
    public void drive();
}

class NormalDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Normal drive capability");
    }
}

class SportsDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Sports drive capability");
    }
}

//  Suppose in future we get any new capability like Xyz drive capability then we can create a new class implementing DriveStrategy interface
class XyzDriveStrategy implements DriveStrategy {
    @Override
    public void drive() {
        System.out.println("Xyz drive capability");
    }
}

// Context class
class Vehicle {
    DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive(){
        driveStrategy.drive();
    }
}

class OffRoadVehicle extends Vehicle {
    public OffRoadVehicle() {
        super(new SportsDriveStrategy());
    }
}

class PassengerVehicle extends Vehicle {
    public PassengerVehicle() {
        super(new NormalDriveStrategy());
    }
}

class SportsVehicle extends Vehicle {
    public SportsVehicle() {
        super(new SportsDriveStrategy());
    }
}

// Client
class Client {
    public static void main(String[] args) {
        Vehicle vehicle = new OffRoadVehicle(); // Or if new need sports vehicle then, vehicle = new SportsVehicle();
        vehicle.drive();
    }
}