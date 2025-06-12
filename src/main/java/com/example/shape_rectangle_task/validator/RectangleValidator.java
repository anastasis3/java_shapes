package com.example.shape_rectangle_task.validator;

import com.example.shape_rectangle_task.entity.Point;
import com.example.shape_rectangle_task.entity.Rectangle;
import com.example.shape_rectangle_task.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class RectangleValidator {

    // Пример строки: "0,0 0,2 3,2 3,0"
    public static List<Point> parsePoints(String line) throws InvalidDataException {
        // Разбиваем строку по точке с запятой
        String[] tokens = line.trim().split("\\s*;\\s*"); // учитываем пробелы вокруг ;
        if (tokens.length != 4) {
            throw new InvalidDataException("Rectangle must have 4 points");
        }

        List<Point> points = new ArrayList<>();
        for (String token : tokens) {
            String[] coords = token.split(",");
            if (coords.length != 2) {
                throw new InvalidDataException("Invalid coordinate format: " + token);
            }
            try {
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                points.add(new Point(x, y));
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Invalid number format: " + token);
            }
        }
        return points;
    }


    public static boolean isValidRectangle(Rectangle rectangle) {
        if (rectangle == null || rectangle.getPoints().size() != 4) {
            return false;
        }

        List<Point> p = rectangle.getPoints();

        // Проверим, что противоположные стороны равны и углы прямые
        double d1 = distance(p.get(0), p.get(1));
        double d2 = distance(p.get(1), p.get(2));
        double d3 = distance(p.get(2), p.get(3));
        double d4 = distance(p.get(3), p.get(0));

        // Диагонали
        double diag1 = distance(p.get(0), p.get(2));
        double diag2 = distance(p.get(1), p.get(3));

        return almostEqual(d1, d3) &&
                almostEqual(d2, d4) &&
                almostEqual(diag1, diag2);
    }

    private static double distance(Point a, Point b) {
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

    private static boolean almostEqual(double a, double b) {
        final double EPSILON = 1e-6;
        return Math.abs(a - b) < EPSILON;
    }
}
