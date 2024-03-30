package consoleTasks;

import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FunctionParam {

    public static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static String function = null;
    public static double start = 0, stop = 0, step = 0;


    public static void withParam(String funStr, double start, double stop, double step) {
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        System.out.println("1) Функція з параметром");
        Variable par = new Variable("a");
        Variable var = new Variable("x");
        parser.add(var);
        parser.add(par);
        Expression fun = parser.parse(funStr);
        Expression der = fun.derivative(var);
        System.out.println("f(x) = " + fun.toString());
        System.out.println("f'(x) = " + der.toString());
        par.setVal(1.0);
        for (double x = start; x <= stop; x += step) {
            var.setVal(x);
            System.out.println(x + "\t" + fun.getVal() + "\t" + der.getVal());
        }
    }

    public static void withoutParam(String funStr, double start, double stop, double step) {
        Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
        System.out.println("2) Функція без параметру");
        Variable var = new Variable("x");
        parser.add(var);
        var funs = parser.parse(funStr);
        Expression ders = funs.derivative(var);
        System.out.println("f(x) = " + funs.toString());
        System.out.println("f'(x) = " + ders.toString());
        for (double x = start; x <= stop; x += step) {
            var.setVal(x);
            System.out.println(x + "\t" + funs.getVal() + "\t" + ders.getVal());
        }
    }

    public static int scanInteger() {
        int num = 0;
        boolean check = true;
        while (check) {
            check = false;
            try {
                num = Integer.parseInt(in.readLine());
            } catch (NumberFormatException | IOException e) {
                check = true;
                System.err.println("Невірний формат");
            }
        }
        return num;
    }

    public static double scanDouble() {
        double num = 0;
        boolean check = true;
        while (check) {
            check = false;
            try {
                num = Double.parseDouble(in.readLine());
            } catch (NumberFormatException | IOException e) {
                check = true;
                System.err.println("Невірний формат");
            }
        }
        return num;
    }

    public static String scanLine() {
        String check = "";
        try {
            check = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static void scanInfo() {
        System.out.println("Function: ");
        function = scanLine();
        do {
            System.out.println("Start:");
            start = scanDouble();
            System.out.println("Stop:");
            stop = scanDouble();
        } while (start >= stop);
        System.out.println("Step: ");
        step = scanDouble();
    }

    public static void main(String[] args) {

        int choice;

        while (true) {

            System.out.println("""
                    1) Функція з параметром
                    2) Функція без параметру
                    3) Вийти з програми
                    >>""");
            choice = scanInteger();

            switch (choice) {
                case 1:
                    scanInfo();
                    withParam(function, start, stop, step);
                    break;
                case 2:
                    scanInfo();
                    withoutParam(function, start, stop, step);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Помилка");
            }
        }
    }
}