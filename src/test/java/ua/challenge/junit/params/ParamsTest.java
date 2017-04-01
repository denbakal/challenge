package ua.challenge.junit.params;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 01.04.2017.
 */
@RunWith(JUnitParamsRunner.class)
public class ParamsTest {
    @Test
    @Parameters({"17, false"})
    public void checkParams(int age, boolean valid) {
        assertThat(age).isEqualTo(17);
        assertThat(valid).isEqualTo(false);
    }
}
