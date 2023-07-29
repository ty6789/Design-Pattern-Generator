package 11package

//Director Class
public class ${targetProduct}BuilderDirector {

    private ${targetProduct}Builder mBuilder;

    public ${targetProduct}BuilderDirector(${targetProduct}Builder builder) {
        mBuilder = builder;
    }

    public ${targetProduct} construct() {
    <#list components as component>
        mBuilder.build${component}();
    </#list>
        return mBuilder.create${targetProduct}();
    }

}