package com.example.zellowapplication.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

public class ZelloVoiceClient {

    private static final String BASE_URL = "https://api.zello.com/v2";
    private final HttpClient httpClient;

    public ZelloVoiceClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }

    public void sendVoiceMessage(String channelId, String accessToken, String audioFile) throws IOException, InterruptedException, URISyntaxException {
        String url = BASE_URL + "/channels/" + channelId + "/voice";
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + accessToken);

        Path filePath = Path.of(audioFile);
        if (Files.exists(filePath)) {
            byte[] audioData = Files.readAllBytes(filePath);
            HttpRequest request = requestBuilder
                    .POST(HttpRequest.BodyPublishers.ofByteArray(audioData))
                    .build();

            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
            if (response.statusCode() != 200) {
                throw new IOException("Failed to send voice message: " + response.statusCode() + " - " + response.body());
            }
        } else {
            throw new IOException("Audio file not found: " + audioFile);
        }
    }

    public static void main(String[] args) {
        ZelloVoiceClient zelloVoiceClient = new ZelloVoiceClient();
        System.out.println(zelloVoiceClient.toString());
    }
}
