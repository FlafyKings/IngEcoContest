package ingkonkurs;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class AtmServiceTest {

    private static final String BASE_URL = "http://localhost:8080";

    public String readJsonFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        return Files.readString(path);
    }

    @Test
    public void testService() throws Exception {
        // Create an instance of the HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        //Wczytaj JSON z pliku
        String requestBody = readJsonFromFile("zadania/atmservice/example_1_request.json");
        String expectedResponseBody = readJsonFromFile("zadania/atmservice/example_1_response.json");

        // Prepare the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/atms/calculateOrder"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Verify the response
        int statusCode = response.statusCode();
        String responseBody = response.body();

        System.out.println(responseBody);  // Print the response body for debugging purposes
        System.out.println(expectedResponseBody);
        assertEquals(200, statusCode);  // Replace with your expected status code
        assertEquals(expectedResponseBody, responseBody);

    }
}

