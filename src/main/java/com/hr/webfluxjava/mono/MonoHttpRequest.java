package com.hr.webfluxjava.mono;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

public class MonoHttpRequest {
    public static void main(String[] args) throws Exception {
        ObjectMapper objetMapper = new ObjectMapper();
        TypeReference<Map<String, String>> typeReference = new TypeReference<>() {};

        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(88)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        Mono.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
        ).map(response -> {
            try {
                return objetMapper.readValue(response.getBody(), typeReference);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).subscribe(
                data -> System.out.println("response: " + data),
                error -> error.printStackTrace(),
                () -> System.out.println("종료되었습니다.")
        );
    }
}
