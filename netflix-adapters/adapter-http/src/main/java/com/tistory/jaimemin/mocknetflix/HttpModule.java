package com.tistory.jaimemin.mocknetflix;

import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:adapter-http-property.yml"})
public interface HttpModule {
}
