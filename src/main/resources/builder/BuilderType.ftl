package 11package

//Concrete Builder Class
public class ${type} extends ${targetProduct}Builder {

<#list components as component>
    @Override
    public void build${component}() {

    }

</#list>
    @Override
    public ${targetProduct} create${targetProduct}() {
        return mBike;
    }
}