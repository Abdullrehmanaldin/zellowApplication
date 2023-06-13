package com.example.zellowapplication.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ZelloAuthClient {

    private static final String BASE_URL = "https://api.zello.com/v2";
    private final HttpClient httpClient;

    public ZelloAuthClient() throws GeneralSecurityException {
        this.httpClient = createHttpClient();
    }

    private HttpClient createHttpClient() throws GeneralSecurityException {
        return HttpClient.newBuilder()
                .build();
    }

    public String authenticate(String username, String password, String apiKey) throws IOException, InterruptedException {
        String tokenUrl = BASE_URL + "/user/gettoken";
        String tokenQueryString = "username=" + encodeURIComponent(username);

        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl + "?" + tokenQueryString))
                .GET()
                .build();

        HttpResponse<String> tokenResponse = executeRequest(tokenRequest);

        String token = extractTokenFromResponse(tokenResponse.body());
        String hashedPassword = getMD5(getMD5(password) + token + apiKey);

        String loginUrl = BASE_URL + "/user/login";
        String loginQueryString = "username=" + encodeURIComponent(username) +
                "&password=" + encodeURIComponent(hashedPassword);

        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .POST(HttpRequest.BodyPublishers.ofString(loginQueryString))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> loginResponse = executeRequest(loginRequest);
        String sessionId = extractSessionIdFromResponse(loginResponse.body());
        return sessionId;
    }

    private HttpResponse<String> executeRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return response;
        } else {
            throw new IOException("Request failed with status code: " + statusCode);
        }
    }

    private String extractTokenFromResponse(String response) {
        // Parse the token from the response JSON
        // Implement the extraction logic according to your JSON parsing library
        return "";
    }

    private String extractSessionIdFromResponse(String response) {
        // Parse the session ID from the response JSON
        // Implement the extraction logic according to your JSON parsing library
        return "";
    }

    private String getMD5(String input) {
        // Implement the MD5 hashing logic
        return "";
    }

    private String encodeURIComponent(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
    }
}
