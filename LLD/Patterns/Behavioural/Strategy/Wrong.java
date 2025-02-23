package LLD.Patterns.Behavioural.Strategy;

// Strategy
// Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable

// Problem
// Imagine you are creating a vehicle simulation game. Now vehicle can have different drive types like passenger drive , sproty drive, off road drive etc.
// Now as you can see below we have created different classes for each type of drive
// But this is not good design as you can see we are having duplicate functionality for offroadvehicle and sprotsvehicle . This is the problem , and it becomes more evident as we have more and more parent-child relationship

class Vehicle {
    public void drive(){
        System.out.println("Normal drive capability");
    }
}

class SportsVehicle extends Vehicle {
    public void drive(){
        System.out.println("Sports drive capability");
    }
}

class PassengerVehicle extends Vehicle {
    // Using base class drive functionality
}

class OffroadVehicle extends Vehicle {
    public void drive(){
        System.out.println("Sports drive capability");
    }
}

class GoodsVehicle extends Vehicle {
    // Using base class drive functionality
}

