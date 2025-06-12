package com.example.shape_rectangle_task;

import com.example.shape_rectangle_task.entity.Point;
import com.example.shape_rectangle_task.entity.Rectangle;
import com.example.shape_rectangle_task.exception.InvalidDataException;
import com.example.shape_rectangle_task.factory.RectangleFactory;
import com.example.shape_rectangle_task.repository.RectangleRepository;
import com.example.shape_rectangle_task.service.RectangleService;
import com.example.shape_rectangle_task.util.FileReaderUtil;
import com.example.shape_rectangle_task.util.FileWriterUtil;
import com.example.shape_rectangle_task.validator.RectangleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;

public class ShapeRectangleTaskApplication {
    private static final Logger logger = LoggerFactory.getLogger(ShapeRectangleTaskApplication.class);

    public static void main(String[] args) {
        RectangleRepository repository = new RectangleRepository();
        RectangleService service = new RectangleService();
        RectangleFactory factory = new RectangleFactory();

        // Читаем строки из файла input.txt
        List<String> lines = FileReaderUtil.readLines(Paths.get("src/main/resources/input.txt"));

        // Парсим и валидируем прямоугольники
        lines.forEach(line -> {
            try {
                List<Point> points = RectangleValidator.parsePoints(line);
                Rectangle rectangle = factory.createRectangle(points);
                if (RectangleValidator.isValidRectangle(rectangle)) {
                    repository.add(rectangle);
                    logger.info("Successfully added rectangle: {}", rectangle);
                } else {
                    logger.warn("Invalid rectangle (geometry issue): {}", line);
                }
            } catch (InvalidDataException e) {
                logger.error("Invalid input data: {}", line);
            }
        });

        // Формируем результат в StringBuilder
        StringBuilder outputBuilder = new StringBuilder();
        repository.getAll().forEach(rect -> {
            double perimeter = service.calculatePerimeter(rect);
            double area = service.calculateArea(rect);
            String resultLine = String.format("Rectangle %d - Perimeter: %.2f, Area: %.2f%n", rect.getId(), perimeter, area);
            logger.info(resultLine.trim());
            outputBuilder.append(resultLine);
        });

        // Записываем результат в output.txt
        FileWriterUtil.writeToFile(outputBuilder.toString());

        System.out.println("Результаты записаны в output.txt");
    }
}
