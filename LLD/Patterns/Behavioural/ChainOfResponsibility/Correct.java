package LLD.Patterns.Behavioural.ChainOfResponsibility;

// Also known as chain of command
// Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers (Ref : R)

// When is used ?
// When there are multiple objects that can handle a request and the handler is not known a prior and we do not know which handler will handle our request

// Real world examples
// ATM or vending machine (Ref: S) -> Suppose we entered the request to withdraw 2000 Rs (and this ATM has first 2000 Rs handler , then 500 Rs handler and then 100 Rs handler) then if first handler (2000 Rs) can handle the request then it will fullfill the request . If not then it will pass the request to next (500 Rs) handler and so on. If in the end request can't be fullfilled then we will send insufficient funds message
// Designing Logger (Ref: S)

// Pros:
// You can control the order of request handling.
// Single Responsibility Principle. You can decouple classes that invoke operations from classes that perform operations.
// Open/Closed Principle. You can introduce new handlers into the app without breaking the existing client code

// Cons:
// Some requests may end up unhandled.

// Example : Logger system

abstract class LogProcessor {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    LogProcessor nextLogProcessor;

    public LogProcessor(LogProcessor logProcessor) { this.nextLogProcessor = logProcessor; }

    public void log(int level, String message) {
        if (this.nextLogProcessor != null) {
            this.nextLogProcessor.log(level, message);
        }
    }
}

class InfoLogProcessor extends LogProcessor {
    public InfoLogProcessor (LogProcessor logProcessor) { super(logProcessor); }

    @Override
    public void log(int level, String message) {
        if(level == INFO) {
            System.out.println("Info : " + message);
        } else {
            super.log(level, message);
        }
    }
}

class DebugLogProcessor extends LogProcessor {
    DebugLogProcessor(LogProcessor logProcessor) { super(logProcessor); }

    @Override
    public void log(int level, String message) {
        if(level == DEBUG) {
            System.out.println("Debug : " + message);
        } else {
            super.log(level, message);
        }
    }
}

class ErrorLogProcessor extends LogProcessor {
    ErrorLogProcessor(LogProcessor logProcessor) { super(logProcessor); }

    @Override
    public void log(int level, String message) {
        if(level == ERROR) {
            System.out.println("Error : " + message);
        } else {
            super.log(level, message);
        }
    }
}

class Main {
    public static void main(String[] args) {
        LogProcessor logProcessor = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null))); // Here we defined order : Info -> Debug -> Error
        logProcessor.log(LogProcessor.INFO, "This is info message"); // handled by InfoLogProcessor
        logProcessor.log(LogProcessor.DEBUG, "This is debug message"); // handled by DebugLogProcessor
        logProcessor.log(LogProcessor.ERROR, "This is error message"); // handled by ErrorLogProcessor
    }
}