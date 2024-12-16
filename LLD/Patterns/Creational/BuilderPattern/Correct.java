package LLD.Patterns.Creational.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

// Builder design pattern helps in creating object , but such object which have many optional properties
// Problems while creating object with many optional properties:
// 1. Big constructor with many properties
// 2. No of constructors can become huge as many properties are optional

// Solution : Builder Design Pattern
// Here we create object step by step , object is said to be in builder form in between those steps .
// We return final object in build method of builder class , all other methods return object in builder or intermediary form
// Example : StringBuilder
// In some cases we might have director methods which will be responsible for calling methods in specific order

// Disadvantages:
// Some code duplicacy , as student will take studentbuilder as argument , so student will have some duplicated properties (see example below).

class Student {
    int rollNumber;
    int age;
    String name;
    String fatherName;
    String motherName;
    List<String> subjects;

    public Student(StudentBuilder studentBuilder) {
        this.rollNumber = studentBuilder.rollNumber;
        this.age = studentBuilder.age;
        this.name = studentBuilder.name;
        this.fatherName = studentBuilder.fatherName;
        this.motherName = studentBuilder.motherName;
        this.subjects = studentBuilder.subjects;
    }

    @Override
    public String toString() {
        return "Student [rollNumber=" + rollNumber + ", age=" + age + ", name=" + name + ", fatherName=" + fatherName + ", motherName=" + motherName + ", subjects=" + subjects + "]";
    }
}

class StudentBuilder {
    int rollNumber;
    int age;
    String name;
    String fatherName;
    String motherName;
    List<String> subjects;

    public StudentBuilder setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }
    
    public StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    
    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }
    
    public StudentBuilder setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }
    
    public StudentBuilder setMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public StudentBuilder setSubjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Student build() {
        return new Student(this);
    }
}

class MBAStudentBuilder extends StudentBuilder {
    @Override
    public StudentBuilder setSubjects(List<String> subjects) {
        subjects.add("Micro Economics");
        subjects.add("Business studies");
        subjects.add("Operations Management");
        this.subjects = subjects;
        return this;
    }
}

class EngineeringStudentBuilder extends StudentBuilder {
    @Override
    public StudentBuilder setSubjects(List<String> subjects) {
        subjects.add("DSA");
        subjects.add("OS");
        subjects.add("Computer Networks");
        this.subjects = subjects;
        return this;
    }
}

class Director {
    StudentBuilder studentBuilder;

    Director(StudentBuilder studentBuilder) {
        this.studentBuilder = studentBuilder;
    }

    private Student createMBAStudent(){
        // This ensures setting properties in specific order
        return studentBuilder.setRollNumber(2).setAge(24).setName("xj").setFatherName("abc").setMotherName("pqr").setSubjects(new ArrayList<>()).build();
    }

    public Student createEngineeringStudent() {
        // This ensures setting properties in specific order
        return studentBuilder.setRollNumber(2).setAge(22).setName("zx").setSubjects(new ArrayList<>()).build();
    }

    public Student createStudent() {
        if (studentBuilder instanceof MBAStudentBuilder) {
            return createMBAStudent();
        } else if (studentBuilder instanceof EngineeringStudentBuilder) {
            return createEngineeringStudent();
        } else return null;
    }
}

class Main {
    public static void main(String[] args) {
        Director directorObj1 = new Director(new EngineeringStudentBuilder());
        Director directorObj2 = new Director(new MBAStudentBuilder());

        Student engineeringStudent = directorObj1.createStudent();
        Student mbaStudent = directorObj2.createStudent();
        
        System.out.println(engineeringStudent);
        System.out.println(mbaStudent);
    }
}