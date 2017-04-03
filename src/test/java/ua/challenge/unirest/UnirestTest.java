package ua.challenge.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import lombok.SneakyThrows;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 03.04.2017.
 */
public class UnirestTest {
    @Test
    @SneakyThrows
    public void post() {
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
            .queryString("name", "Mark")
            .field("last", "Polo")
                .asJson();
        assertThat(response.getStatus()).isEqualTo(200);
    }
}
