package LLD.Patterns.Structural.Decorator;

// Decorator is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.
// aka wrapper pattern
// Problem : 
// Notification library
// Imagine that you’re working on a notification library which lets other programs notify their users about important events.
// The initial version of the library was based on the Notifier class that had only a few fields, a constructor and a single send method to notify through mail.
// Suppose now you want to also notify by other means like SMS, Facebook, Slack etc. You can extend the Notifier class and add new methods for each new way of sending notifications.
// So now you have SMSNotifier , FacebookNotifier and SlackNotifier. Now some users of library want to send notifications through multiple channels. So you need to create a new class for each combination like FacebookSMSNotifier(Facebook+SMS), FacebookSlackNotifier(Facebook+Slack) or FacebookSMSSlackNotifier(Facebook+slack+SMS) etc.
// This approach is not scalable and maintainable as you might need to create many subclasses and their combination subclasses and so on and on.
// https://refactoring.guru/images/patterns/diagrams/decorator/problem3.png

// Real world analogy
// pizza shop or winter cloths

// How it works ?
// 1. The Component declares the common interface for both wrappers and wrapped objects.
// 2. The Concrete Component is a class of objects being wrapped.
// 3. The Base Decorator has a field for referencing a wrapped object. The field’s type should be declared as the component interface to allow linking to concrete components as well as decorators.
// 4. Concrete Decorators contain extra behaviors that can be added to components dynamically.
// 5. The client code works with all objects using the component interface.

// Pros: 
// 1. You can extend an object’s behavior without making a new subclass.
// 2. You can add or remove responsibilities from an object at runtime.
// 3. Single Responsibility Principle. You can divide a monolithic class that implements many possible variants of behavior into several smaller classes.

// Cons:
// 1. It’s hard to remove a specific wrapper from the wrappers stack.
// 2. It’s hard to implement a decorator in such a way that its behavior doesn’t depend on the order in the decorators stack.

abstract class BasePizza { // 1.
    abstract int getCost();
}

class VegDelight extends BasePizza { // 2.
    @Override
    int getCost() {
        return 120;
    }
}

class FarmHouse extends BasePizza { // 2.
    @Override
    int getCost() {
        return 200;
    }
}

class Margherita extends BasePizza { // 2.
    @Override
    int getCost() {
        return 100;
    }
}

abstract class ToppingDecorator extends BasePizza { // 3. (It need not to be abstract)
    BasePizza basePizza;

    ToppingDecorator(BasePizza basePizza) {
        this.basePizza = basePizza;
    }
}

class Cheese extends ToppingDecorator { // 4.
    Cheese(BasePizza basePizza) {
        super(basePizza);
    }

    @Override
    int getCost() {
        return basePizza.getCost() + 20;
    }
}

class Paneer extends ToppingDecorator { // 4.
    Paneer(BasePizza basePizza) {
        super(basePizza);
    }

    @Override
    int getCost() {
        return basePizza.getCost() + 30;
    }
}

class Main {
    public static void main(String[] args) {
        // suppose we want margherita and extracheeze pizza
        BasePizza pizza = new Cheese(new Margherita()); // 5.
        System.out.println(pizza.getCost()); // 5.

        // now we suppose want mushroom also on above pizza then
        pizza = new Paneer(pizza); // 5.
        System.out.println(pizza.getCost()); // 5.
    }
}
