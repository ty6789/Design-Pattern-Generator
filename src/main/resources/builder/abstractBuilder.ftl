package 11package

// Abstract Builder Class
public abstract class ${targetProduct}Builder {

    protected ${targetProduct} m${targetProduct} = new ${targetProduct}();

<#list components as component>
    public abstract void build${component}();

</#list>
    public abstract ${targetProduct} create${targetProduct}();
}