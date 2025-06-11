package com.example.shape_rectangle_task.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class FileReaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileReaderUtil.class);

    public static List<String> readLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            logger.error("Failed to read input file: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}