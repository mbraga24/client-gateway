package com.pnc.ipapi;

import com.pnc.exception.custom.IPApiException;
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
        String apiUrl = "http://ip-api.com/json/" + userIp;

        try {
            IPApiResponse apiResponse = restTemplate
                    .getForObject(apiUrl, IPApiResponse.class);

            log.info("Received API :: {}", apiResponse.toString());
            return apiResponse;
        } catch (Exception error) {
            log.error("IP API Request Error :: {}", error.getMessage(), error);
            throw new IPApiException("We're experiencing trouble connecting to our services. Please try again in a few minutes.");
        }
    }

}
