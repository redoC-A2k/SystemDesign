package LLD.Patterns.Creational.Singleton;

// Pros of LazySingleton:
// 1. Instance is created only when it is needed i.e. lazy loading

// Cons of LazySingleton:
// 1. Not threadsafe

class LazySingleton {
    private static LazySingleton instance; // singleton initially null 

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
