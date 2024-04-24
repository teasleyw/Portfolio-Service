package com.FrostMilano.Portfolio.services;

import com.FrostMilano.Portfolio.dtos.LinkedInUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class LinkedInService {

    public LinkedInUserInfoDto getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUrl = "https://api.linkedin.com/v2/userinfo";

        // Set the Authorization header with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Make the request to LinkedIn API
        ResponseEntity<LinkedInUserInfoDto> response = restTemplate.exchange(
                userInfoUrl,
                HttpMethod.GET,
                entity,
                LinkedInUserInfoDto.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            // Return the user info DTO
            return response.getBody();
        }

        // Handle other scenarios or return null if unsuccessful
        return null;
    }
}
