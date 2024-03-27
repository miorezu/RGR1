public class FFunction implements Evaluatable {
    private double a;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public FFunction(double a) {
        this.a = a;
    }
    public FFunction() {
        this(1.0);
    }
    @Override
    public double evalf(double x) {
// TODO Auto-generated method stub
        return Math.exp(-a*x*x)*Math.sin(x);

    }

    public static void main(String[] args) {
// TODO Auto-generated method stub
    }
}