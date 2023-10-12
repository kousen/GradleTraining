package gradle.astro;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import java.io.IOException;

public class AstroService {
    private static final String URL = "http://api.open-notify.org/astros.json";

    private final Gson gson = new Gson();

    private final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

    public AstroResponse getAstronauts() {
        HttpRequest request = null;
        try {
            request = requestFactory.buildGetRequest(new GenericUrl(URL));
            String rawResponse = request.execute().parseAsString();
            return gson.fromJson(rawResponse, AstroResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
