package LLD.Patterns.Structural.BridgePattern;

// Ref: https://refactoring.guru/design-patterns/bridge
// Bridge is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.
// Abstraction (also called interface) is a high-level control layer for some entity. This layer isn’t supposed to do any real work on its own. It should delegate the work to the implementation layer (also called platform).
// Note that we’re not talking about interfaces or abstract classes from your programming language. These aren’t the same things.

// Problem:
// Say you have a geometric Shape class with a pair of subclasses: Circle and Square. You want to extend this class hierarchy to incorporate colors, so you plan to create Red and Blue shape subclasses. However, since you already have two subclasses, you’ll need to create four class combinations such as BlueCircle and RedSquare.
// Solution:
// This problem occurs because we’re trying to extend the shape classes in two independent dimensions: by form and by color. That’s a very common issue with class inheritance.
// The Bridge pattern attempts to solve this problem by switching from inheritance to the object composition. What this means is that you extract one of the dimensions into a separate class hierarchy, so that the original classes will reference an object of the new hierarchy, instead of having all of its state and behaviors within one class.


// Real world analogy
// When talking about real applications, the abstraction can be represented by a graphical user interface (GUI), and the implementation could be the underlying operating system code (API) which the GUI layer calls in response to user interactions.

// How it works?
// 1. Abstraction provides high-level control logic.
// 2. declares the interface that’s common for all concrete implementations. An abstraction can only communicate with an implementation object via methods that are declared here.
// 3. Concrete implementation may contain platform specific code
// 4. Refined Abstractions provide variants of control logic. Like their parent, they work with different implementations via the general implementation interface.
// 5. Client(Main) in only interested in working with abstraction

// Example:
// This example illustrates how the Bridge pattern can help divide the monolithic code of an app that manages devices and their remote controls. The Device classes act as the implementation, whereas the Remotes act as the abstraction.

// Pros
// 1. You can create platform-independent classes and apps.
// 2. Open/Closed Principle. You can introduce new abstractions and implementations independently from each other.
// 3. Single Responsibility Principle. You can focus on high-level logic in the abstraction and on platform details in the implementation

// Cons
// 1. The client code works with high-level abstractions. It isn’t exposed to the platform details.
// 2. Complexity

interface Device {  // Implementation
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
    void printStatus();
}

class Radio implements Device { // Concrete implementation
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() { on = true; }

    @Override
    public void disable() { on = false; }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        if (volume > 100) {
            this.volume = 100;
        } else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() { return channel; }

    @Override
    public void setChannel(int channel) { this.channel = channel; }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| I'm radio.");
        System.out.println("| I'm " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current channel is " + channel);
        System.out.println("------------------------------------\n");
    }
}

class Tv implements Device { // Other concrete implementation
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() { on = true; }

    @Override
    public void disable() { on = false; }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int volume) {
        if (volume > 100) {
            this.volume = 100;
        } else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() { return channel; }

    @Override
    public void setChannel(int channel) { this.channel = channel; }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| I'm TV set.");
        System.out.println("| I'm " + (on ? "enabled" : "disabled"));
        System.out.println("| Current volume is " + volume + "%");
        System.out.println("| Current channel is " + channel);
        System.out.println("------------------------------------\n");
    }
}

interface Remote {
    void power();
    void volumeDown();
    void volumeUp();
    void channelDown();
    void channelUp();
}

class BasicRemote implements Remote {
    protected Device device;

    public BasicRemote() {}

    public BasicRemote(Device device) { this.device = device; }

    @Override
    public void power() {
        System.out.println("Remote: power toggle");
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    @Override
    public void volumeDown() { device.setVolume(device.getVolume() - 10); }

    @Override
    public void volumeUp() { device.setVolume(device.getVolume() + 10); }

    @Override
    public void channelDown() { device.setChannel(device.getChannel() - 1); }

    @Override
    public void channelUp() { device.setChannel(device.getChannel() + 1); }
}

class AdvancedRemote extends BasicRemote { // Refined Abstraction
    public AdvancedRemote(Device device) { super.device = device; }

    public void mute() { device.setVolume(0); }
}

class Main {
    public static void testDevice(Device device) { // Client interacting with abstraction
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }

    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }
}