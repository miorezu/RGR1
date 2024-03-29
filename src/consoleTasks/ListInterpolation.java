package consoleTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListInterpolation extends Interpolator {
    private List<Point2D> data = null;

    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation() {
        data = new ArrayList<Point2D>();
    }

    public ListInterpolation(Point2D[] data) {
        this();
        for (Point2D pt : data)
            this.data.add(pt);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    @Override
    public void sort() {Collections.sort(data);}

    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();

        int num;
        double x;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        fun.clear();
        System.out.println("Після видалення всіх точок: кількість точок = " + fun.numPoints());

        double minError = Double.MAX_VALUE;
        int minParts = -1;

        for (int parts = 2; parts <= 10; parts++) {
            fun.clear();
            for (int i = 0; i < num; i++) {
                x = 1.0 + (5.0 - 1.0) * i / (num - 1);  // Розбиваємо інтервал на частини
                fun.addPoint(new Point2D(x, Math.sin(x)));
            }
            fun.sort();

            x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
            double interpolationResult = fun.evalf(x);
            double exactValue = Math.sin(x);
            double error = Math.abs(interpolationResult - exactValue);

            System.out.println("Кількість частин: " + parts);
            System.out.println("Значення інтерполяції: " + interpolationResult);
            System.out.println("Точне значення: " + exactValue);
            System.out.println("Помилка: " + error);

            if (error < minError) {
                minError = error;
                minParts = parts;
            }
        }
        System.out.println("Мінімальна помилка (" + minError + ") досягнута при " + minParts + " частинах.");
    }
}