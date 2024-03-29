package consoleTasks;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator {
    private TreeSet<Point2D> data = new TreeSet<>();

    public TreeSetInterpolation() {
    }

    public TreeSetInterpolation(TreeSet<Point2D> data) {
        this.data = data;
    }

    public void clear() {
        data.clear();
    }

    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    public Point2D getPoint(int i) {
        Iterator<Point2D> it = data.iterator();
        Point2D curr = null;
        for (int n = 0; it.hasNext() && n <= i; n++) {
            curr = it.next();
        }
        return curr;
    }

    public void setPoint(int i, Point2D pt) {
        data.add(pt);
    }

    public void removeLastPoint() {
        data.remove(data.last());
    }

    public int numPoints() {
        return data.size();
    }

    public void sort() {
    }

    public static void main(String[] args) {

        TreeSetInterpolation fun = new TreeSetInterpolation();
        int num;
        double x;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Кількість точок: ");
            num = scanner.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        System.out.println("Відсортований набір: ");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        System.out.println("Зберігаємо у файл");
        try {
            FileManager.writeToFile("dataTreeSet.csv", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Зчитуємо з файлу");
        fun.clear();
        try {
            FileManager.readFromFile("dataTreeSet.csv", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Дані з файлу: ");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " +
                fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " +
                fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " +
                Math.abs(fun.evalf(x) - Math.sin(x)));

        System.out.println("Готуємо дані для розрахунку");
        fun.clear();
        for (x = 1.0; x <= 7.0; x += 0.1) {
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        try {
            FileManager.writeToFile("TblFunTreeSet.csv", fun);
            FileManager.writeToFile("TblFunTreeSet.dat", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
