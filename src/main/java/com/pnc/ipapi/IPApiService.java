package com.pnc.ipapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class IPApiService {

    private final WebClient.Builder webClientBuilder;

    public Mono<IPApiResponse> ipAPICall(String userIp) {
        log.info("starting IP API CALL"); // this line get printed on my console, the log configuration is correct.
        String apiUrl = "http://ip-api.com/json/" + userIp;
        return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(IPApiResponse.class)
                .doOnNext(apiResponse -> { // nothing inside doOnNext() gets printed
                    log.info("Received API Response: ==========> {}", apiResponse.toString());
                })
                .doOnError(error -> { // nothing inside doOnError() gets printed
                    log.error("API Request Error: ==========> {}", error.getMessage(), error);
                });

    }

}
