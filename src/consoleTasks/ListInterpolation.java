package consoleTasks;

import java.io.IOException;
import java.util.*;

public class ListInterpolation extends Interpolator {
    private List<PointData> data = null;

    public ListInterpolation(List<PointData> data) {
        this.data = data;
    }

    public ListInterpolation() {
        data = new ArrayList<PointData>();
    }

    public ListInterpolation(PointData[] data) {
        this();
        this.data.addAll(Arrays.asList(data));
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
    public void addPoint(PointData point) {
        data.add(point);
    }

    @Override
    public PointData getPoint(int i) {
        return data.get(i);
    }

    @Override
    public void setPoint(int i, PointData point) {
        data.set(i, point);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    @Override
    public void sort() {
        Collections.sort(data);
    }

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
            fun.addPoint(new PointData(x, Math.sin(x)));
        }

        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));

        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " +
                fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Зберігаємо у файл");
        try {
            FileManager.writeToFile("data.csv", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        fun.clear();
        System.out.println("Після видалення всіх точок: кількість точок = " + fun.numPoints());

        double minError = Double.MAX_VALUE;
        int minParts = -1;

        for (int parts = 2; parts <= 10; parts++) {
            fun.clear();
            for (int i = 0; i < num; i++) {
                x = 1.0 + (5.0 - 1.0) * i / (num - 1);  // Розбиваємо інтервал на частини
                fun.addPoint(new PointData(x, Math.sin(x)));
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

        System.out.println("Зчитуємо з файлу");
        fun.clear();
        try {
            FileManager.readFromFile("data.csv", fun);
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
        x = 0.5 * (fun.getPoint(0).getX() +
                fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " +
                fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " +
                Math.abs(fun.evalf(x) - Math.sin(x)));
        System.out.println("Готуємо дані для розрахунку");
        fun.clear();
        for (x = 1.0; x <= 7.0; x += 0.1) {
            fun.addPoint(new PointData(x, Math.sin(x)));
        }
        try {
            FileManager.writeToFile("TblFunc.csv", fun);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}