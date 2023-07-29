package 11package

//Abstract Handler
public abstract class ${abstractHandler} {

    private ${abstractHandler} nextHandler;

    //Set Next Handler
    public void setNextHandler(${abstractHandler} nextHandler){
        this.nextHandler = nextHandler;
    }

    //Chain Submission Method
    public final void submit(){
        // TODO: Decide whether to call the current handler and the next handler according to the specific scenario
        // this.handle();
        // if (this.nextHandler != null) this.nextHandler.submit();
    }

    //Method that needs to be implemented by subclasses
    protected abstract void handle();
}