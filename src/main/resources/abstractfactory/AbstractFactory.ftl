package 11package

//Abstract Factory Interface
public interface ${abstractFactory} {

<#list productInterfaces as productInterface>
    ${productInterface} create${productInterface}();

</#list>

}