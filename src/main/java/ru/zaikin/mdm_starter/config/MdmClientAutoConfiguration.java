package ru.zaikin.mdm_starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ru.zaikin.mdm_starter.mapper.MdmMapper;

@Configuration
@EnableConfigurationProperties(MdmProperties.class)
public class MdmClientAutoConfiguration {


    /*
    @EnableConfigurationProperties(MdmProperties.class) — подключает класс с конфигурацией, чтобы поля заполнялись из application.yml.
    mdmWebClient создаёт WebClient с базовым URL из конфигурации.
    Остальные бины создаются, если таких еще нет (чтобы не пересоздавать, если сервис захочет переопределить).
    MdmLookupDelegate — бин для Camunda (реализую потом).*/


    @Bean
    @ConditionalOnMissingBean
    public WebClient mdmWebClient(MdmProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    /*
    * @Bean
@ConditionalOnMissingBean
public WebClient mdmWebClient(MdmProperties properties) {
    return WebClient.builder()
            .baseUrl(properties.getBaseUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-Request-Id", UUID.randomUUID().toString()) // трассировка
            .defaultHeader("X-Source-System", "credit-core")              // свой идентификатор системы
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getToken())
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                    .responseTimeout(Duration.ofSeconds(5))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)))
            .filter(logRequest())      // логирование запроса
            .filter(logResponse())     // логирование ответа
            .build();
}

    *
    * */

    @Bean
    @ConditionalOnMissingBean
    public MdmMapper mdmMapper() {
        return new MdmMapper();
    }

    /*@Bean
    @ConditionalOnMissingBean
    public MdmLookupDelegate mdmLookupDelegate(MdmClient mdmClient) {
        return new MdmLookupDelegate(mdmClient);
    }*/

}
