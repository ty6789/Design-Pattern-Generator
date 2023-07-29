package 11package

//Specific Command
public class ${concreteCommand} implements ${abstractCommand} {

    //Hold the receiver object.
    private ${receiver} receiver;

    public ${concreteCommand}(${receiver} receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //TODO :Invoke the receiver to complete the command task.
    }
}