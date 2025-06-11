import com.example.shape_rectangle_task.entity.Point;
import com.example.shape_rectangle_task.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

// внутри RectangleValidator.java
public static List<Point> parsePoints(String line) throws InvalidDataException {
    String[] tokens = line.split(";");
    if (tokens.length != 4) {
        throw new InvalidDataException("A rectangle must have exactly 4 points.");
    }

    List<com.example.shape_rectangle_task.entity.Point> points = new ArrayList<>();
    for (String token : tokens) {
        String[] coords = token.trim().split(",");
        if (coords.length != 2) {
            throw new InvalidDataException("Invalid point format.");
        }

        try {
            int x = Integer.parseInt(coords[0].trim());
            int y = Integer.parseInt(coords[1].trim());
            points.add(new com.example.shape_rectangle_task.entity.Point(x, y));
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Coordinates must be integers.");
        }
    }

    return points;
}

        public void main() {
        }
