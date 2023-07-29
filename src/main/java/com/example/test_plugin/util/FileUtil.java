package com.example.test_plugin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileUtil {

    public static String readCodeFromFile(String dir, String codeFile) {
        codeFile = dir + "/" + codeFile;
        InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(codeFile);
        if (is == null) {
            return null; // 文件未找到
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + codeFile, e);
        }
    }

}
