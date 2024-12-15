package LLD.Patterns.Creational.Singleton;

// Here we don't make whole method synchronized, only the part where we are creating instance
// Pros of Double checked lock
// 1. Thread safe
// 2. Lazy initialization
// 3. High performance as synchronized block is only , when instance is created

// Cons of Double checked lock
// 1. It is just a complex to implement


class DoubleCheckedSingleton {
    private static DoubleCheckedSingleton instance; // singleton initially null

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {

        // First check , if instance is null then only go inside synchronized block
        if (instance == null) {
            // Then synchronize the block if multiple threads are trying to create instance and pass first check
            synchronized (DoubleCheckedSingleton.class) {
                // Second check , it might be possible that multiple threads pass first check and only one thread will create instance due to synchronized block , as explained above
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}