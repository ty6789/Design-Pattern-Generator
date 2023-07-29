package 11package

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//Proxy Factory, used to create proxy objects.
public class ProxyFactory {

    private Target target = new TargetImpl();

    public Target getProxyObject() {
        //Use Proxy to get the proxy object.
        /*
            Explanation of the parameters of the newProxyInstance() method:
                ClassLoader loader ： Class loader, used to load proxy class, the class loader of the real object can be used.
                Class<?>[] interfaces ：The interfaces implemented by the real object. In the proxy pattern, the real object
                                        and the proxy object implement the same interfaces.
                InvocationHandler h ：The invocation handler of the proxy object.
         */
        Target obj = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    /*
                        Explanation of parameters in the invoke method of InvocationHandler:
                            proxy: The proxy object
                            method: The Method instance corresponding to the interface method called on the proxy object
                            args: The actual parameters passed when the proxy object calls the interface method
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Before invoke");
                        Object result = method.invoke(target, args);
                        System.out.println("After invoke");
                        return result;
                    }
                });
        return obj;
    }
}