package com.example.shape_rectangle_task.factory;

import com.example.shape_rectangle_task.entity.Point;
import com.example.shape_rectangle_task.entity.Rectangle;

import java.util.List;

public class RectangleFactory {
    public Rectangle createRectangle(List<Point> points) {
        return new Rectangle(points);
    }
}