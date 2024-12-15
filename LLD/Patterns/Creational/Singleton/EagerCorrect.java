package LLD.Patterns.Creational.Singleton;

// Pros of EagerSingleton:
// 1. Simple to implement
// 2. Thread safe as JVM guarentees that instance is created before any thread accesses it
// 3. Thread safe without explicit synchronization (as said in 2nd point above)

// Cons of EagerSingleton:
// 1. Instance is created even if it is not used
// 2. And so it is not lazy loaded 

class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
