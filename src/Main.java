public class Main {
    public static void main(String[] args) {
// TODO Auto-generated method stub

        System.out.println("Перевірка класу FFunction");
        FFunction fun = new FFunction();
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.print("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.print("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.print("xStep: ");
        double xStep = in.nextDouble();
        System.out.println("Параметр a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += xStep)
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        System.out.print("x: ");
        double x = in.nextDouble();
        System.out.print("aBeg: ");
        double aBeg = in.nextDouble();
        System.out.print("aEnd: ");
        double aEnd = in.nextDouble();
        System.out.print("aStep: ");
        double aStep = in.nextDouble();
        System.out.println("Змінна x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            fun.setA(a);
            System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getA(), fun.evalf(x));
        }
    }
}