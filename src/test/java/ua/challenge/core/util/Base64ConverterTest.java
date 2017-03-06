package ua.challenge.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 03.03.2017.
 */
public class Base64ConverterTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encode() throws Exception {
        String pathFile = getClass().getClassLoader().getResource("test/test-file.pdf").getFile();
        File testFile = new File(pathFile);
        String result = Base64Converter.encode(testFile);
        assertThat(result).isNotEmpty();

        System.out.println("Result: " + result);
        System.out.println("Hash of result: " + DigestUtils.sha256Hex(result));

        Base64Converter.decode(result, "test-file.pdf");
        File testFileAfterDecode = new File("test-file.pdf");
        byte[] bytes = FileUtils.readFileToByteArray(testFileAfterDecode);

        String hashSum = DigestUtils.sha256Hex(bytes).toUpperCase();
        System.out.println("Hash of file: " + hashSum);
        assertThat(hashSum).isEqualTo("0F0BED85AF2F1565CA3DE7BF3E878603B847D9B93C86965D1F4384DE7A9E0B02");
    }

    @Test
    public void decode() throws Exception {
    }

}