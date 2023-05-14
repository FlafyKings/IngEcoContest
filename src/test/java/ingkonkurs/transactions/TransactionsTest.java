package ingkonkurs.transactions;

import ingkonkurs.utils.JsonUtils;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test
    public void testService() throws Exception {
        // Create an instance of the HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        //Wczytaj JSON z pliku
        String requestBody = JsonUtils.readJsonFromFile("zadania/transactions/example_request.json");
        String expectedResponseBody = JsonUtils.readJsonFromFile("zadania/transactions/example_response.json");

        // Przygotowanie zapytania
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/transactions/report"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Wysłanie zapytania i przechowanie odpowiedzi w zmiennej
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Sprawdzenie odpowiedzi
        int statusCode = response.statusCode();
        String responseBody = response.body();

        assertEquals(200, statusCode);  //Sprawdzenie statusu odpowiedzi
        assertEquals(expectedResponseBody, responseBody); //Sprawdzenie treści odpowiedzi
    }
}
