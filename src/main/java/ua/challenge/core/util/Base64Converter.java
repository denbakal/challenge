package ua.challenge.core.util;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by d.bakal on 03.03.2017.
 */
public class Base64Converter {
    @SneakyThrows
    public static String encode(File file) {
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
        return Base64.encodeBase64String(bytes);
    }

    @SneakyThrows
    public static void decode(String base64, String fileName) {
        byte[] bytes = Base64.decodeBase64(base64);
        FileUtils.writeByteArrayToFile(new File(fileName), bytes);
    }
}
