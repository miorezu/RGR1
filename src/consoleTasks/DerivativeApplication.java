package consoleTasks;

import java.io.*;

public class DerivativeApplication {

    public static void main(String[] args) throws IOException {
        Evaluatable functs[] = new Evaluatable[4];
        functs[0] = new FFunction(0.5);
        functs[1] = new ListInterpolation();
        functs[2] = new TreeMapInterpolation();
        functs[3] = new TreeSetInterpolation();
        try {
            FileManager.readFromFile("TblFunc.csv", (ListInterpolation) functs[1]);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        try {
            FileManager.readFromFile("TblFunTreeMap.csv", (TreeMapInterpolation) functs[2]);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        try {
            FileManager.readFromFile("TblFunTreeSet.csv", (TreeSetInterpolation) functs[3]);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        String fileName = "";
        for (Evaluatable functsElem : functs) {
            System.out.println("----------------------------------------");
            System.out.println("Функція: " + functsElem.getClass().getSimpleName());
            fileName = functsElem.getClass().getSimpleName() + ".csv";
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (double x = 1.5; x <= 6.5; x += 0.5) {
                System.out.println("x: " + x + "\tf: " + functsElem.evalf(x) + "\tf': " +
                        NumMethods.der(x, 1.0e-4, functsElem));
                out.printf("%16.6e%16.6e%16.6e\n", x, functsElem.evalf(x),
                        NumMethods.der(x, 1.0e-4, functsElem));
            }
            System.out.println("\n");
            out.close();
        }
    }
}