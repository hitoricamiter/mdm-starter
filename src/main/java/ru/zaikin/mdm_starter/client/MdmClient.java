package ru.zaikin.mdm_starter.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

package com.company.mdmclient.client;


import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.zaikin.mdm_starter.config.MdmProperties;
import ru.zaikin.mdm_starter.dto.MdmRequest;
import ru.zaikin.mdm_starter.dto.MdmResponse;
import ru.zaikin.mdm_starter.mapper.MdmMapper;
import ru.zaikin.mdm_starter.model.ClientInfo;

public class MdmClient {

    private final WebClient webClient;
    private final MdmMapper mapper;
    private final String lookupPath;

    // Конструктор
    public MdmClient(WebClient webClient, MdmMapper mapper, MdmProperties properties) {
        this.webClient = webClient;
        this.mapper = mapper;
        this.lookupPath = properties.getLookupPath();
    }

    // Метод поиска клиента по ID
    public ClientInfo findClientById(String clientId) {
        try {
            // Создаём объект запроса с clientId
            MdmRequest request = new MdmRequest(clientId);

            // Выполняем POST-запрос к MDM сервису
            MdmResponse response = webClient.post()
                    .uri(lookupPath)                       // Используем путь из конфигурации
                    .bodyValue(request)                    // Передаём тело запроса (JSON)
                    .retrieve()                           // Получаем ответ
                    .onStatus(HttpStatus::is4xxClientError,    // Если статус 4xx — клиент не найден
                            clientResponse -> Mono.error(new MdmNotFoundException("Клиент не найден в MDM")))
                    .onStatus(HttpStatus::is5xxServerError,    // Если статус 5xx — ошибка сервиса
                            clientResponse -> Mono.error(new MdmClientException("Ошибка MDM сервиса")))
                    .bodyToMono(MdmResponse.class)         // Преобразуем тело ответа в объект MdmResponse
                    .block();                             // Блокируем (ждём результат)

            // Преобразуем MdmResponse в удобный объект ClientInfo
            return mapper.toClientInfo(response);

        } catch (Exception e) {
            // В случае любых ошибок оборачиваем в собственное исключение
            throw new MdmClientException("Ошибка при вызове MDM", e);
        }
    }
}
