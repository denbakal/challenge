package ua.challenge.googleapis.distance.matrix;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 18.04.2017.
 */
public class DistanceMatrixApiTest {
    private final static String KEY_API = "AIzaSyBRMWnDKIlZEHZH6YLLUCkxH51vl8QeMBQ";

    @Test
    @SneakyThrows
    public void getDistanceMatrixWithFakeKeyTest() {
        HttpResponse<JsonNode> response = Unirest
                .post("https://maps.googleapis.com/maps/api/distancematrix/json")
                    .queryString("units", "imperial")
                    .queryString("origins", "Washington,DC")
                    .queryString("destinations", "New+York+City,NY")
                    .queryString("key", "fake_key")
                .asJson();

        assertThat(response.getStatus()).isEqualTo(200);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(response.getBody()));
        String result = JsonPath.read(document, "$.error_message");
        assertThat(result).isEqualTo("The provided API key is invalid.");
    }

    @Test
    @SneakyThrows
    public void getDistanceMatrixTest() {
        HttpResponse<JsonNode> response = Unirest
                .post("https://maps.googleapis.com/maps/api/distancematrix/json")
                .queryString("units", "imperial")
                .queryString("origins", "Washington,DC")
                .queryString("destinations", "New+York+City,NY")
                .queryString("key", KEY_API)
                .asJson();

        assertThat(response.getStatus()).isEqualTo(200);
        System.out.println("response.getBody() = " + response.getBody());
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(response.getBody()));
        String result = JsonPath.read(document, "$.rows[0].elements[0].distance.text");
        assertThat(result).isEqualTo("225 mi");
    }

    @Test
    @SneakyThrows
    public void getDistanceMatrixWithUnitsMetricTest() {
        HttpResponse<JsonNode> response = Unirest
                .post("https://maps.googleapis.com/maps/api/distancematrix/json")
                .queryString("units", "metric")
                .queryString("origins", "Washington,DC")
                .queryString("destinations", "New+York+City,NY")
                .queryString("key", KEY_API)
                .asJson();

        assertThat(response.getStatus()).isEqualTo(200);
        System.out.println("response.getBody() = " + response.getBody());
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(String.valueOf(response.getBody()));
        String distanceText = JsonPath.read(document, "$.rows[0].elements[0].distance.text");
        assertThat(distanceText).isEqualTo("362 km");
        Integer distanceValue = JsonPath.read(document, "$.rows[0].elements[0].distance.value");
        assertThat(distanceValue).isEqualTo(361721);
    }

    @Test
    @SneakyThrows
    public void testWithGoogleLibrary() {
        GeoApiContext context = new GeoApiContext();
        context.setApiKey(KEY_API);
        context.setConnectTimeout(1, TimeUnit.SECONDS);
        context.setReadTimeout(1, TimeUnit.SECONDS);
        context.setWriteTimeout(1, TimeUnit.SECONDS);

        String[] origins = new String[]{
                "Perth, Australia"
        };
        String[] destinations = new String[]{
                "Uluru, Australia"
        };

        DistanceMatrix matrix = DistanceMatrixApi
                .getDistanceMatrix(context, origins, destinations)
                .units(Unit.METRIC)
                .mode(TravelMode.DRIVING)
                .await();

        assertThat(matrix.rows.length).isEqualTo(1);
        assertThat(matrix.rows[0].elements.length).isEqualTo(1);
        assertThat(matrix.rows[0].elements[0].status).isEqualTo(DistanceMatrixElementStatus.OK);

        Distance distance = new Distance();
        distance.inMeters = 3669808L;
        distance.humanReadable = "3,670 km";

        assertThat(matrix.rows[0].elements[0].distance.inMeters).isEqualTo(distance.inMeters);
        assertThat(matrix.rows[0].elements[0].distance.humanReadable).isEqualTo(distance.humanReadable);
    }
}
