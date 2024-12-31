package LLD.Patterns.Behavioural.Command;

import java.util.Stack;

// Command
// Also known as Action , Transaction
// Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. (Ref: R)
// This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations (Ref: R)

// Disadvantages of this approach:
// 1. Lack of abstraction : Currently , process of turning on AC is simple it is just a single method Call . But what if in future we there are multiple methods to be called before turning on AC then client needs to be aware of all those methods
// 2. Undo / Redo functionality : We do not have undo redo functionality in this approach , and we can't expect AC to have undo redo functionality as AC in itself is a dumb object 
// 3. Difficulty in code maintainability : What if we want to support more devices such as bulb or fan , then the client will be need to be aware of how to turn on Bulb or Fan . Client gets tightly coupled with the devices

// How Command Pattern solves these problems:
// It saperates the logic of Receiver, Invoker and Command
// Receiver : The object that performs the actual action (like AirConditioner, Bulb)
// Invoker aka Sender : The object that invokes the Command . So invoker generates the command and sends it to the receiver. (Client uses invoker to generate the Command)
// Command : Logically -> It contains the code for perfoming actual action (like turning on or off) . Codewise -> It is a interface that declares a common method "execute" , Further there will be multiple concrete classes implementing that interface for each action .

// Command Interface
interface ICommand {
    void execute();
}

// Concrete Command
class TurnACOnCommand implements ICommand {
    AirConditioner airConditioner;
    
    TurnACOnCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOnAC();
    }
}

class TurnACOffCommand implements ICommand {
    AirConditioner airConditioner;
    
    TurnACOffCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOffAC();
    }
}


// Invoker
class RemoteControl {
    ICommand command;
    
    RemoteControl(){}

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}

// client
class Main {
    public static void main(String[] args) {
        // AC Object
        AirConditioner airConditioner = new AirConditioner();
        
        // Remote
        RemoteControl remoteControl = new RemoteControl();
        
        // Create the command and press button
        remoteControl.setCommand(new TurnACOnCommand(airConditioner));
        remoteControl.pressButton();

        // Again same -> create the command and press button
        remoteControl.setCommand(new TurnACOffCommand(airConditioner));
        remoteControl.pressButton();
    }
}

// ---------------- IF we want to implement undo functionality -------------
interface _ICommand { // underscore because same interface already defined
    public void execute();
    public void undo();
}

class _TurnACOnCommand implements _ICommand { // changed concrete command
    AirConditioner airConditioner;

    public _TurnACOnCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOnAC();
    }

    @Override
    public void undo() {
        airConditioner.turnOffAC();        
    }
}

class _TurnACOffCommand implements _ICommand { // changed concrete command
    AirConditioner airConditioner;

    _TurnACOffCommand(AirConditioner airConditioner){
        this.airConditioner = airConditioner;
    }

    @Override
    public void execute() {
        airConditioner.turnOffAC();
    }

    @Override
    public void undo() {
        airConditioner.turnOnAC();
    }
}

class _RemoteControl { // changed invoker
    Stack<_ICommand> acCommandHistory = new Stack<>();
    _ICommand command;

    public void setCommand(_ICommand command){
        this.command = command;
    }

    public void pressButton(){
        command.execute();
        acCommandHistory.push(command);
    }
     
    public void undo(){
        if(!acCommandHistory.isEmpty()){
            _ICommand lastCommand = acCommandHistory.pop();
            lastCommand.undo();
        }
    }
}