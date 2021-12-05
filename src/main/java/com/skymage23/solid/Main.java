package com.skymage23.solid;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.Authenticator;
import java.net.http.HttpClient.Version;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){

        //throw new NotImplementedException();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000"))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, List<String>> headerMap = response.headers().map();
            Map<String, String> linkMap = SolidClient.parseLinkHeadersIntoUriRelTypeMap(headerMap.get("Link"));

            System.out.printf("Links: %s", linkMap.toString());

        } catch (java.lang.Exception except){
            System.err.printf("Uh oh. Something went wrong:\nName: %s,\nMessage: %s\n\n", except.getClass().getName(), except.getMessage() );
        }
    }
}
