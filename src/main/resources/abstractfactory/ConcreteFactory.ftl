package 11package

//Concrete Factory Class
public class ${concreteFactoryName} implements ${abstractFactoryName} {

<#list products as productInterface, concreteProduct>
    @Override
    public ${productInterface} create${productInterface}() {
        return new ${concreteProduct}();
    }

</#list>

}