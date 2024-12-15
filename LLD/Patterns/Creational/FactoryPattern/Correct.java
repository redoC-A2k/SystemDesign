package LLD.Patterns.Creational.FactoryPattern;

// Useful when we need to create object based on some condition i.e. if condition1 fullfilled then create object1 , if condition2 fullfilled then create object2 and so on
// See here also ( a great article ) - https://refactoring.guru/design-patterns/factory-method 
// Excerpt from above article:
// Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
// Problem:
// Imagine that you’re creating a logistics management application. The first version of your app can only handle transportation by trucks, so the bulk of your code lives inside the Truck class.
// After a while, your app becomes pretty popular. Each day you receive dozens of requests from sea transportation companies to incorporate sea logistics into the app.

// Great news, right? But how about the code? At present, most of your code is coupled to the Truck class. Adding Ships into the app would require making changes to the entire codebase. Moreover, if later you decide to add another type of transportation to the app, you will probably need to make all of these changes again.
// As a result, you will end up with pretty nasty code, riddled with conditionals that switch the app’s behavior depending on the class of transportation objects

// Solution:
// The Factory Method pattern suggests that you replace direct object construction calls (using the new operator) with calls to a special factory method. Don’t worry: the objects are still created via the new operator, but it’s being called from within the factory method. Objects returned by a factory method are often referred to as products.

interface Shape {
    void draw();
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class ShapeFactory {
    Shape getShape(String input) {
        switch (input) {
            case "Rectangle":
                return new Rectangle();
            case "Circle":
                return new Circle();
            case "Square":
                return new Square();
            default:
                return null;
        }
    }
}

class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape1 = shapeFactory.getShape("Rectangle"); // If condition is rectangle then create rectangle object
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("Circle"); // If condition is circle then create circle object
        shape2.draw();
        Shape shape3 = shapeFactory.getShape("Square"); // so on
        shape3.draw();
    }
}