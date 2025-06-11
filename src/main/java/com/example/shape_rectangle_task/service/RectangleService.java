package com.example.shape_rectangle_task.service;

import com.example.shape_rectangle_task.entity.Point;
import com.example.shape_rectangle_task.entity.Rectangle;

import java.util.List;

public class RectangleService {
    public double calculatePerimeter(Rectangle rectangle) {
        List<Point> p = rectangle.getPoints();
        return distance(p.get(0), p.get(1)) + distance(p.get(1), p.get(2)) +
                distance(p.get(2), p.get(3)) + distance(p.get(3), p.get(0));
    }

    public double calculateArea(Rectangle rectangle) {
        List<Point> p = rectangle.getPoints();
        return Math.abs(
                (p.get(0).getX() * p.get(1).getY() - p.get(1).getX() * p.get(0).getY()) +
                        (p.get(1).getX() * p.get(2).getY() - p.get(2).getX() * p.get(1).getY()) +
                        (p.get(2).getX() * p.get(3).getY() - p.get(3).getX() * p.get(2).getY()) +
                        (p.get(3).getX() * p.get(0).getY() - p.get(0).getX() * p.get(3).getY())
        ) / 2.0;
    }

    private double distance(Point a, Point b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}