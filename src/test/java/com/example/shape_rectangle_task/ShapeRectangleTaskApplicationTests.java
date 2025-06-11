// test/service/RectangleServiceTest.java
package test.service;

import by.example.geometry.entity.Point;
import by.example.geometry.entity.Rectangle;
import by.example.geometry.service.RectangleService;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

public class RectangleServiceTest {

    @Test
    public void testArea() {
        Rectangle rect = new Rectangle("R1", List.of(
                new Point(0,0), new Point(2,0),
                new Point(2,1), new Point(0,1)
        ));
        RectangleService service = new RectangleService();
        assertEquals(service.calculateArea(rect), 2.0, 0.01);
    }
}
