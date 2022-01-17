package com.nab.icommerce.purchaseorderservice.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestUtils {

    public static String toString(String fileLocation) throws IOException {
        return FileUtils.readFileToString(new ClassPathResource(fileLocation).getFile(), Charset.defaultCharset());
    }
}
