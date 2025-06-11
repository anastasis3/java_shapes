package com.example.shape_rectangle_task.entity;


import java.util.List;
import java.util.UUID;

public class Rectangle {
    private final String id;
    private final List<Point> points;

    public Rectangle(List<Point> points) {
        this.id = UUID.randomUUID().toString();
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle other)) return false;
        return points.equals(other.points);
    }

    @Override
    public int hashCode() {
        return points.hashCode() * 31 + id.hashCode();
    }

    @Override
    public String toString() {
        return "Rectangle{" + "id='" + id + '\'' + ", points=" + points + '}';
    }
}
