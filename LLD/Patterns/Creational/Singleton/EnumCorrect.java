package LLD.Patterns.Creational.Singleton;

// Pros of EnumSingleton:
// 1. Thread safe , as JVM , or java guarentees that only one instance of enum is created even in multithreaded environment
// 2. It is concise and easy to implement

// Cons of EnumSingleton:
// 1. It's not always suitable, especially if you need to extend a class or if lazy initialization is a strict requirem

enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        // do something
    }
}