package com.FrostMilano.Portfolio.controllers;

import com.FrostMilano.Portfolio.dtos.LinkedInAccessTokenDto;
import com.FrostMilano.Portfolio.dtos.LinkedInUserInfoDto;
import com.FrostMilano.Portfolio.services.LinkedInService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LinkedInController {
    private final LinkedInService linkedInService;

    @Value("${linkedin.client-id}")
    private String clientId;

    @Value("${linkedin.client-secret}")
    private String clientSecret;

    @Value("${linkedin.redirect-uri}")
    private String redirectUri;

//    @GetMapping("/linkedin/login")
//    public String redirectToLinkedIn() {
//        String authorizeUrl = "https://www.linkedin.com/oauth/v2/authorization";
//        String scope = "r_liteprofile"; // Specify the scopes you need
//        String state = "random_state"; // Optionally include a state parameter for CSRF protection
//
//        String url = UriComponentsBuilder.fromHttpUrl(authorizeUrl)
//                .queryParam("response_type", "code")
//                .queryParam("client_id", clientId)
//                .queryParam("redirect_uri", redirectUri)
//                .queryParam("scope", scope)
//                .queryParam("state", state)
//                .build().toUriString();
//
//        return "redirect:" + url;
//    }

    @GetMapping("/linkedin/callback")
    @ResponseBody
    public LinkedInAccessTokenDto handleLinkedInCallback(@RequestParam("code") String code) {
        // Exchange authorization code for an access token
        String tokenUrl = "https://www.linkedin.com/oauth/v2/accessToken";

        String grantType = "authorization_code";

        // Construct the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("code", code);
        requestBody.add("redirect_uri", redirectUri);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);

        // Send POST request to exchange code for access token
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LinkedInAccessTokenDto> response = restTemplate.postForEntity(tokenUrl, requestBody, LinkedInAccessTokenDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {


            // Handle successful response (e.g., extract access token)

            return response.getBody();
        } else {
            // Handle error response
            return null;
        }
    }
    @GetMapping("/linkedin/userinfo")
    @ResponseBody
    public LinkedInUserInfoDto handleLinkedInUserInfo(@RequestParam("accesstoken") String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String userInfoUrl = "https://api.linkedin.com/v2/userinfo";
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

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
        else{
            return null;
        }

    }
}