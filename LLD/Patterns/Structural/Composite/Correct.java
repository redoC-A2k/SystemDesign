package LLD.Patterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

// Composite is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.
// Using the Composite pattern makes sense only when the core model of your app can be represented as a tree.
// Problem:
// For example, imagine that you have two types of objects: Products and Boxes. A Box can contain several Products as well as a number of smaller Boxes. These little Boxes can also hold some Products or even smaller Boxes, and so on.
// [https://refactoring.guru/images/patterns/diagrams/composite/problem-en.png]
// Solution: 
// The Composite pattern suggests that you work with Products and Boxes through a common interface which declares a method for calculating the total price.

// Real world analogy
// Armies of most countries are structured as hierarchies. [https://refactoring.guru/images/patterns/diagrams/composite/live-example.png]

// How it works ?
// 1. The component interface describe operations that are common to both simple and complex elements of the tree.
// 2. The Leaf is a basic element
// 3. The container aka composite is an element that has subelement
// 4. The client works with all elements through the component interface, hence client can work with both simple and complex elements of the tree in same way

// Pros:
// 1. Work with complex tree structures more conveniently: use polymorphism and recursion to your advantage.
// 2. Open/Closed Principle. You can introduce new element types into the app without breaking the existing code

// Cons:
// 1. It might be difficult to provide a common interface for classes whose functionality differs too much. In certain scenarios, you’d need to overgeneralize the component interface, making it harder to comprehend


// Example: FileSystem
interface FileSystemComponent {
    void ls();
}

class File implements FileSystemComponent {
    String filename;
    
    public File(String filename) { this.filename = filename; }

    public void ls() { System.out.println(filename); }
}

class Directory implements FileSystemComponent {
    String directoryName;
    List<FileSystemComponent> objectList ; 
    
    public Directory(String name) { this.directoryName = name; objectList = new ArrayList<>(); }

    public void add(FileSystemComponent o) { objectList.add(o); }

    public void ls() {
        System.out.println("Directory: " + directoryName);
        for (FileSystemComponent o : objectList) {
            o.ls();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Directory movieDirectory = new Directory("Movies");
        FileSystemComponent border = new File("Border");
        movieDirectory.add(border);

        Directory comedyDirectory = new Directory("Comedy");
        FileSystemComponent hulchul = new File("Hulchul");        
        comedyDirectory.add(hulchul);
        movieDirectory.add(comedyDirectory);

        movieDirectory.ls();    
    }
}