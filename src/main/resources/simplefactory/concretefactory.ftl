package 11package

//Concrete Factory Class
public class ${FactoryName} {

    public ${ProductInterface} create${ProductInterface}(String type) {
        ${ProductInterface} product = null;
        <#list productTypes as productType>
        if("${productType}".toLowerCase().equals(type)) {
            product = new ${productType}();
        }
        </#list>
        return product;
    }
}
