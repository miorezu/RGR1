package consoleTasks;

import java.io.*;
import java.util.StringTokenizer;

public abstract class FileManager {
    public void readFromFile(String fileName, ListInterpolation listInt) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s = in.readLine(); // читання рядка із заголовками стовпців
        listInt.clear();
        while ((s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            listInt.addPoint(new Point2D(x, y));
        }
        in.close();
    }

    public void writeToFile(String fileName, ListInterpolation listInt) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.printf("%9s%25s\n", "x", "y");
        for (int i = 0; i < listInt.numPoints(); i++) {
            out.println(listInt.getPoint(i).getX() + "\t" + listInt.getPoint(i).getY());
        }
        out.close();
    }
}