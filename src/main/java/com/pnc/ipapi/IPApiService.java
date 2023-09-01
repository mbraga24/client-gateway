package com.pnc.ipapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class IPApiService {

    private final RestTemplate restTemplate;

    public IPApiResponse ipAPICall(String userIp) {
        log.info("starting IP API CALL");
        String apiUrl = "http://ip-api.com/json/" + userIp;

        try {
            IPApiResponse apiResponse = restTemplate.getForObject(apiUrl, IPApiResponse.class);
            log.info("Received API Response: ==========> {}", apiResponse.toString());
            return apiResponse;
        } catch (Exception error) {
            log.error("API Request Error: ==========> {}", error.getMessage(), error);
            throw error;
        }
    }

}
