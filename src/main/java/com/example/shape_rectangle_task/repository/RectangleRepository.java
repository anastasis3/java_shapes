package com.example.shape_rectangle_task.repository;


import com.example.shape_rectangle_task.entity.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectangleRepository {
    private final List<Rectangle> rectangles = new ArrayList<>();

    public void add(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    public List<Rectangle> getAll() {
        return Collections.unmodifiableList(rectangles);
    }
}
