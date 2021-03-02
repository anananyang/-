package httpClient.annotation;

import httpClient.constants.HttpEntityType;
import httpClient.constants.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface HttpReqConfig {
    String httpMethod() default HttpMethod.UNKNOWN;

    String entityType() default HttpEntityType.STRING;

    String path() default "";

    int connectTimeout() default -1;

    int socketTimeout() default -1;
}
