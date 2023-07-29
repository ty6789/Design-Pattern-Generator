package 11package

//Concrete Handler
public class ${thisHandler} extends ${abstractHandler} {

    public ${thisHandler}() {
        setNextHandler(<#if nextHandler??>new ${nextHandler}()<#else>null</#if>);
    }

    @Override
    protected void handle() {
        // TODO: Task for the current handler
    }
}