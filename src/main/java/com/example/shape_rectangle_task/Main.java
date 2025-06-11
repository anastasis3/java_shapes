import com.example.shape_rectangle_task.entity.Rectangle;
import com.example.shape_rectangle_task.exception.InvalidDataException;
import com.example.shape_rectangle_task.factory.RectangleFactory;
import com.example.shape_rectangle_task.repository.RectangleRepository;
import com.example.shape_rectangle_task.service.RectangleService;
import com.example.shape_rectangle_task.util.FileReaderUtil;
import com.example.shape_rectangle_task.validator.RectangleValidato;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        RectangleRepository repository = new RectangleRepository();
        RectangleService service = new RectangleService();
        RectangleFactory factory = new RectangleFactory();

        List<String> lines = FileReaderUtil.readLines(Paths.get("resources/input.txt"));

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

        repository.getAll().forEach(rect -> {
            double perimeter = service.calculatePerimeter(rect);
            double area = service.calculateArea(rect);
            logger.info("Rectangle {} - Perimeter: {}, Area: {}", rect.getId(), perimeter, area);
        });
    }
}
