package com.ypdaic.mymall.mymallsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(RestClientProperties.class)
@Configuration
public class ESConfig {

    @Autowired
    RestClientProperties properties;

    @Bean
    public RequestOptions requestOptions() {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 *1024 * 1024));
        RequestOptions build = builder.build();
        return build;
    }

    @Bean
    public RestClient restClient(RestClientBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestClientBuilder restClientBuilder() {
        HttpHost[] hosts = this.properties.getUris().stream().map(HttpHost::create)
                .toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(hosts);
        PropertyMapper map = PropertyMapper.get();
        map.from(this.properties::getUsername).whenHasText().to((username) -> {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            Credentials credentials = new UsernamePasswordCredentials(
                    this.properties.getUsername(), this.properties.getPassword());
            credentialsProvider.setCredentials(AuthScope.ANY, credentials);
            builder.setHttpClientConfigCallback((httpClientBuilder) -> httpClientBuilder
                    .setDefaultCredentialsProvider(credentialsProvider));
        });
        return builder;
    }
    @Bean
    public RestHighLevelClient restHighLevelClient(
            RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }

}
