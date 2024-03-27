package consoleTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
    public class ListInterpolation extends Interpolator {
        private List<Point2D> data = null;
        public ListInterpolation(List<Point2D> data) {
// TODO Auto-generated constructor stub
        this.data = data;
    }


    public ListInterpolation() {
// TODO Auto-generated constructor stub
        data = new ArrayList<Point2D>();
    }
    public ListInterpolation(Point2D[] data) {
// TODO Auto-generated constructor stub
        this();
        for (Point2D pt : data)
            this.data.add(pt);
    }
    @Override
    public void clear() {
// TODO Auto-generated method stub
        data.clear();
    }
    @Override
    public int numPoints() {
// TODO Auto-generated method stub
        return data.size();
    }
    @Override
    public void addPoint(Point2D pt) {
// TODO Auto-generated method stub
        data.add(pt);
    }
    @Override
    public Point2D getPoint(int i) {
// TODO Auto-generated method stub
        return data.get(i);
    }
    @Override
    public void setPoint(int i, Point2D pt) {
// TODO Auto-generated method stub
        data.set(i, pt);
    }
    @Override
    public void removeLastPoint() {
// TODO Auto-generated method stub
        data.remove(data.size()-1);
    }
    @Override
    public void sort() {
// TODO Auto-generated method stub
        Collections.sort(data);
    }

    public static void main(String[] args) {
// TODO Auto-generated method stub
        ListInterpolation fun = new ListInterpolation();

/*самостійно додати перевірку неперевірених методів.
Перевірити, яким чином помилка інтерполяції залежить від того, на яку кількість
частин розбитий інтервал, на якому виконується інтерполяція функції.*/

        int num;
        double x;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);
        for (int i = 0; i < num; i++)
        {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));
        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " +
                fun.getPoint(fun.numPoints()-1).getX());
        x = 0.5*(fun.getPoint(0).getX() +
                fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " +
                fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " +
                Math.abs(fun.evalf(x)-Math.sin(x)));
    }
}