package consoleTasks;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapInterpolation extends Interpolator {
    public TreeMapInterpolation(TreeMap<Double, Double> data) {
        this.data = data;
    }

    public TreeMapInterpolation() {
    }

    public void clear() {
        data.clear();
    }

    public int numPoints() {
        return data.size();
    }

    public void addPoint(Point2D pt) {
        data.put(pt.getX(), pt.getY());
    }

    public Point2D getPoint(int i) {
        if (i < 0 || data.size() <= i) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        Map.Entry<Double, Double> e = null;
        Iterator<Map.Entry<Double, Double>> it = data.entrySet().iterator();
        while (0 <= i--) {
            e = it.next();
        }
        double x = e.getKey();
        double y = e.getValue();
        return new Point2D(x, y);
    }

    public void setPoint(int i, Point2D pt) {
        data.put(pt.getX(), pt.getY());
    }

    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    public void sort() {
    }

    private TreeMap<Double, Double> data = new TreeMap<>();


    public static void main(String[] args) {

        TreeMapInterpolation fun = new TreeMapInterpolation();
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
            FileManager.writeToFile("dataTreeMap.csv", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Зчитуємо з файлу");
        fun.clear();
        try {
            FileManager.readFromFile("dataTreeMap.csv", fun);
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
            FileManager.writeToFile("TblFunTreeMap.csv", fun);
            FileManager.writeToFile("TblFunTreeMap.dat", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}