package gradle.lab;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import gradle.astro.AstroResponse;

import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public String getUrl() {
        return "http://api.open-notify.org/astros.json";
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        System.out.println(app.getGreeting());

        Gson gson = new Gson();

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(app.getUrl()));
        String rawResponse = request.execute()
                .parseAsString();
        System.out.println("\n---------\n");
        AstroResponse astroResponse = gson.fromJson(rawResponse, AstroResponse.class);
        System.out.println("There are " + astroResponse.number() + " people in space");
        astroResponse.people()
                .forEach(assignment ->
                        System.out.println(assignment.name() + " is on the " + assignment.craft()));
    }
}
