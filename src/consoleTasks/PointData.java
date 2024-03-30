package consoleTasks;

public class PointData implements Comparable<PointData> {

    private final double[] coords;

    public PointData(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Кількість точок повинна бути більше 0.");
        }
        this.coords = new double[num];
    }

    public void setCoord(int num, double x) {
        coords[num - 1] = x;
    }

    public double getCoord(int num) {
        return coords[num - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (double x : coords) {
            sb.append(x).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(")");
        return sb.toString();
    }

    public PointData(double x, double y) {
        this(2);
        setCoord(1, x);
        setCoord(2, y);
    }

    public PointData() {
        this(0, 0);
    }

    public double getX() {
        return getCoord(1);
    }

    public void setX(double x) {
        setCoord(1, x);
    }

    public double getY() {
        return getCoord(2);
    }

    public void setY(double y) {
        setCoord(2, y);
    }

    @Override
    public int compareTo(PointData pt) {
        return Double.compare(getX(), pt.getX());
    }

    public static void main(String[] args) {
        java.util.List<PointData> data = new java.util.ArrayList<>();
        int num;
        double x;
        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
            if(num <= 0)
                System.out.println("Кількість точок повинна бути більше 0.");
        } while (num <= 0);
        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * Math.random();
            data.add(new PointData(x, Math.sin(x)));
        }
        System.out.println("Несортовані дані: ");
        for (PointData pt : data)
            System.out.println(pt);
        System.out.println("\nВідсортовані дані: ");
        java.util.Collections.sort(data);
        for (PointData pt : data)
            System.out.println("x = " + pt.getX() + "\ty = " + pt.getY());
    }
}