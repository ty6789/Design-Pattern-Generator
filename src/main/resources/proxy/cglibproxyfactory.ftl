package 11package

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

//Proxy Factory
public class ProxyFactory implements MethodInterceptor {

    private TargetImpl target = new TargetImpl();

    public TargetImpl getProxyObject() {
        //Create an Enhancer object, similar to the Proxy class in JDK dynamic proxy, the next step is to set a few parameters.
        Enhancer enhancer =new Enhancer();
        //Set the bytecode object of the parent class.
        enhancer.setSuperclass(target.getClass());
        //Set the callback function.
        enhancer.setCallback(this);
        //Create proxy object.
        TargetImpl obj = (TargetImpl) enhancer.create();
        return obj;
    }

    /*
        Explanation of the parameters in the "intercept" method.：
            o ： Proxy object.
            method ： Instance of Method from the real object.
            args ： Actual parameters.
            methodProxy ：Method instance of the method in the proxy object.
     */
    public TargetImpl intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method");
        TargetImpl result = (TargetImpl) methodProxy.invokeSuper(o, args);
        System.out.println("After method");
        return result;
    }
}