package LLD.Patterns.Creational.PrototypePattern;

// Useful when we need to create clone or copy of object

//  Problem:
// 1. In some cases accessing some property is not allowed neither we can make it public nor we can make it private and provide public getter method
// 2. Suppose copy method needs to know which fields to copy and which fields not to copy

// Solution: (Prototype Pattern)
// Class and its subclass all should have a clone method to return a new clone , now since clone method is itself a method of class so it can access private fields

interface Prototype {
    Prototype clone();
}

class Student implements Prototype {
    private int age;
    private int rollNumber;
    private String name;
    
    public Student() {}

    public Student(int age, int rollNumber, String name) {
        this.age = age;
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public Prototype clone() {
        return new Student(this.age, this.rollNumber, this.name);
    }
}

