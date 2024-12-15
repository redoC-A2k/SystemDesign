package LLD.Patterns.Creational.Singleton;

// Threadsafe is similar to lazy singleton but it is also threadsafe
// Pros of ThreadSafeSingleton:
// 1. Thread safe
// 2. Lazy loading

// Cons of ThreadSafeSingleton:
// 1. Synchronized method can cause bottleneck if this method is getting called frequently

class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance; // singleton initially null

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}