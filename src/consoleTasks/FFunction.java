package consoleTasks;

public class FFunction implements Evaluatable {
    private double paramFunc;

    public FFunction(double paramFunc) {
        this.paramFunc = paramFunc;
    }

    public FFunction() {
        this(1.0);
    }

    public double getParamFunc() {
        return paramFunc;
    }

    public void setParamFunc(double paramFunc) {
        this.paramFunc = paramFunc;
    }

    @Override
    public double evalf(double x) {
        return Math.exp(-paramFunc * x * x) * Math.sin(x);
    }

    public static void main(String[] args) {
        System.out.println("Перевірка класу FFunction");
        FFunction fun = new FFunction();
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.print("x Початок: ");
        double xBeg = in.nextDouble();
        System.out.print("x Кінець: ");
        double xEnd = in.nextDouble();
        System.out.print("x Шаг: ");
        double xStep = in.nextDouble();
        System.out.println("Параметр a: " + fun.getParamFunc());
        for (double x = xBeg; x <= xEnd; x += xStep)
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        System.out.print("--------------------");
        System.out.print("\nx: ");
        double x = in.nextDouble();
        System.out.print("a Початок: ");
        double aBeg = in.nextDouble();
        System.out.print("a Кінець: ");
        double aEnd = in.nextDouble();
        System.out.print("a Шаг: ");
        double aStep = in.nextDouble();
        System.out.println("Змінна x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            fun.setParamFunc(a);
            System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getParamFunc(), fun.evalf(x));
        }
    }
}