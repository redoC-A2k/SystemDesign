package LLD.Patterns.Creational.Singleton;

// It is similar to eager , but here we can also handle exception while creating instance
// Pros of StaticBlockSingleton:
// 1. Thread safe
// 2. Can handle exception

// Cons of StaticBlockSingleton:
// 1. Not lazy loading 

class StaticBlockSingleton {
    private static StaticBlockSingleton instance;
    private StaticBlockSingleton() {}

    // Static block to handle exception
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}