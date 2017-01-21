package ua.challenge.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import ua.challenge.security.util.TokenGenerator;

import java.math.BigInteger;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 10.12.2016.
 */
public class GsonTest {

//    private Gson gson = new Gson(); // not have the inner state
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson;

    @Before
    public void setup() {
        gson = builder.create();
    }

    @Test
    public void toJsonTest() {
        Custom custom = new Custom(new Date(), BigInteger.TEN);
        String json = gson.toJson(custom);
        assertThat(json).isNotEmpty();
    }

    @Test
    public void fromJsonTest() {
        String json = "{\"date\":\"Dec 10, 2016 10:27:39 PM\",\"integer\":10}";
        Custom custom = gson.fromJson(json, Custom.class);
        assertThat(custom.getInteger()).isEqualTo(BigInteger.TEN);
    }
}
