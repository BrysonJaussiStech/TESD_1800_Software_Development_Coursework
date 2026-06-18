import java.util.Date;

public class TestColorTriangle {
    public static void main(String[] args) {
        Triangle myTriangle = new Triangle(3.0, 4.0, 5.0);
        myTriangle.setColor("Red");
        myTriangle.setFilled(true);

        System.out.println("Area: " + myTriangle.getArea());
        System.out.println("Perimeter: " + myTriangle.getPerimeter());
        System.out.println("Color: " + myTriangle.getColor());
        
        System.out.print("How to color: ");
        myTriangle.howToColor();
    }
}
interface Colorable {
    void howToColor();
}

abstract class GeometricObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;

    protected GeometricObject() {
        dateCreated = new Date();
    }

    protected GeometricObject(String color, boolean filled) {
        dateCreated = new Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public boolean isFilled() { return filled; }
    public void setFilled(boolean filled) { this.filled = filled; }
    public Date getDateCreated() { return dateCreated; }

    public abstract double getArea();
    public abstract double getPerimeter();
}

class Triangle extends GeometricObject implements Colorable {
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    public Triangle() {
    }

    public Triangle(double side1, double double2, double side3) {
        this.side1 = side1;
        this.side2 = double2;
        this.side3 = side3;
    }

    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public void howToColor() {
        System.out.println("Color all three sides.");
    }

    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
    }
}