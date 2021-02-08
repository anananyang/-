package spring;

import httpClient.client.HttpClientManager;
import httpClient.connection.SocksProxyRule;
import httpClient.response.HttpResoponseHandler;
import httpClient.jdkProxy.HttpToolProxy;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class HttpToolFactoryBean<T> implements FactoryBean<T> {

    private Class<T> httpToolInterface;
    private HttpClientManager httpClientManager;
    private PropertiesResolver propertiesResolver;
    private SocksProxyRule proxyRule;


    public void setHttpToolInterface(Class<T> httpToolInterface) {
        this.httpToolInterface = httpToolInterface;
    }

    public void setHttpClientManager(HttpClientManager httpClientManager) {
        this.httpClientManager = httpClientManager;
    }

    public void setPropertiesResolver(PropertiesResolver propertiesResolver) {
        this.propertiesResolver = propertiesResolver;
    }

    public void setProxyRule(SocksProxyRule proxyRule) {
        this.proxyRule = proxyRule;
    }

    @Override
    public T getObject() throws Exception {
        HttpToolProxy httpToolProxy = new HttpToolProxy(httpToolInterface,
                httpClientManager,
                propertiesResolver,
                HttpResoponseHandler.getInstance(),
                proxyRule);

        return (T) Proxy.newProxyInstance(httpToolInterface.getClassLoader(),
                new Class[]{httpToolInterface},
                httpToolProxy);
    }

    @Override
    public Class<?> getObjectType() {
        return httpToolInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
