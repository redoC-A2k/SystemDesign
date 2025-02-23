package LLD.Patterns.Behavioural.Iterator;

import java.util.Arrays;
import java.util.List;

// Iterator
// Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).

// Real life example:
// Java collection framework is an example
// Look in Java.png , even if client changes from linkedhashset to some other collection lets say priorityQueue or stack . Client traversal logic will remain same

// Problem:
// Collection in java , each of the collection classes stores data differently. For example, ArrayList stores data in an array, LinkedList stores data in a linked list, and HashSet stores data in a hash table.
// Since these stores data differently , the way to traverse through these data structures will be different and if we keep on storing algorithm logic of traversing them in those classes itself then,
// The class will will contain the logic of storing data and also the logic of traversing data which is not a good design principle (Single Responsibility Principle) although the class should only be responsible for storing data as they are datastructure classes
// Client code will be tightly coupled with the data structure classes and if we want to change the data structure then we have to change the client code as well
// We can't have a common interface to traverse through all the data structures

// Solution:
// The main idea of the Iterator pattern is to extract the traversal behavior of a collection into a separate object called an iterator
// Usually, iterators provide one primary method for fetching elements of the collection. The client can keep running this method until it doesn’t return anything, which means that the iterator has traversed all of the elements.

// Components:
// 1. Iterator: The Iterator interface declares the operations required for traversing a collection: fetching the next element, retrieving the current position, restarting iteration, etc.
// 2. Aggregator: The Aggregator maintains the collection of elements and provides an iterator for traversing them. (For example ArrayList, LinkedList, HashSet)

// How to implement
// 1. Create an Iterator interface , it should at the very least have a method to fetch next element
// 2. Declare a aggregator interface and declare a method to return the iterator
// 3. Implement concrete iterator class for the collection that you want to traverse. An iterator object must be linked with a single collection instance. Usually, this link is established via the iterator’s constructor. (There can be more than one concrete iterator class for a single collection)
// 4. Implement concrete aggregator class and return the concrete iterator class in the method declared in the collection interface
// 5. Client code will use the aggregator interface to get the iterator and then use the iterator to traverse through the collection


// Example: (Ref: S)
class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}

// aggregator interface 
interface Aggregate {
    Iterator createIterator();
}

interface Iterator {
    boolean hasNext();
    Object next();
}

// Collection aggregate
class Library implements Aggregate{
    private List<Book> booksList;

    public Library(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public Iterator createIterator() {
        return new BookIterator(booksList);
    }
}
// Iterator concrete class
// Note here we are having just one iterator implementation we can have different iterator implmenetation for same class
class BookIterator implements Iterator {
    private List<Book> booksList;
    private int index = 0;

    public BookIterator(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public boolean hasNext() {
        return index < booksList.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()){
            return booksList.get(index++);
        }
        return null;
    }
}

// Client
class Client {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
            new Book("Science", 100),
            new Book("Maths", 200),
            new Book("English", 300),
            new Book("Drawing", 100)
        );    

        Library library = new Library(books); // At present we are using library aggregator but in future we might have some other aggregator even then we will not have to change aggregator traversal logic  
        Iterator iterator = library.createIterator();

        while (iterator.hasNext()){
            Book book = (Book) iterator.next();
            System.out.println("Book Title: " + book.getTitle() + " Book Price: " + book.getPrice());
        }
    }
}