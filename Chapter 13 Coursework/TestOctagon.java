public class TestOctagon {
    public static void main(String[] args) {
        Octagon octagon1 = new Octagon(5.0);
        System.out.printf("Octagon 1 Area: %.2f\n", octagon1.getArea());
        System.out.printf("Octagon 1 Perimeter: %.2f\n", octagon1.getPerimeter());

        System.out.println("\nCloning Octagon 1 into Octagon 2...");
        
        Octagon octagon2 = (Octagon) octagon1.clone();

        int comparisonResult = octagon1.compareTo(octagon2);

        System.out.println("\n--- Comparison Result ---");
        if (comparisonResult == 0) {
            System.out.println("Success: Octagon 1 and Octagon 2 are identical in area.");
        } else if (comparisonResult > 0) {
            System.out.println("Octagon 1 is larger than Octagon 2.");
        } else {
            System.out.println("Octagon 1 is smaller than Octagon 2.");
        }
    }
}
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    protected GeometricObject() {}

    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public boolean isFilled() { return filled; }
    public void setFilled(boolean filled) { this.filled = filled; }

    public abstract double getArea();
    public abstract double getPerimeter();
}

class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    private double side;

    public Octagon() {
        this.side = 0.0;
    }

    public Octagon(double side) {
        this.side = side;
    }

    public double getSide() { return side; }
    public void setSide(double side) { this.side = side; }

    @Override
    public double getArea() {
        return (2 + (4 / Math.sqrt(2))) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 8 * side;
    }

    @Override
    public int compareTo(Octagon o) {
        if (this.getArea() > o.getArea()) {
            return 1;
        } else if (this.getArea() < o.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
