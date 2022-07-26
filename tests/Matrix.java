
import java.util.ArrayList;

public class Matrix {

    public static ArrayList<vector> multip(ArrayList<vector> matrix1, ArrayList<vector> matrix2) {

        ArrayList<vector> output = new ArrayList<vector>();
        Rint out;
        for (int i = 0; i < matrix1.get(0).vect.size(); i++) {
            output.add(new vector());
            for (int j = 0; j < matrix2.size(); j++) {
                out = new Rint(false, 0);
                for (int k = 0; k < matrix1.size(); k++) {
                    out = out.add(matrix1.get(k).vect.get(i).multip(matrix2.get(j).vect.get(k)));
                }
                output.get(i).vect.add(out);
            }
        }
        return output;
    }

    public static ArrayList<vector> add(ArrayList<vector> matrix1, ArrayList<vector> matrix2) {
        ArrayList<vector> output = new ArrayList<vector>();

        for (int i = 0; i < matrix1.size(); i++) {
            output.add(new vector());
            for (int j = 0; j < matrix1.get(i).vect.size(); j++) {
                output.get(i).add(matrix1.get(i).vect.get(j).add(matrix2.get(i).vect.get(j)));
            }
        }
        return output;
    }

    public static ArrayList<ArrayList<Fraction>> RREF(ArrayList<vector> matrix) {
        ArrayList<ArrayList<Fraction>> output = new ArrayList<ArrayList<Fraction>>();
        // converts array into output array with fractions
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Fraction> tempList = new ArrayList<Fraction>();
            output.add(tempList);
            for (int j = 0; j < matrix.get(i).vect.size(); j++) {
                Fraction tempFraction = new Fraction(matrix.get(i).vect.get(j), new Rint(false, 1));
                output.get(i).add(tempFraction);
            }
        }

        int col = 0;
        for (int i = 0; i < output.get(0).size(); i++) {
            while (output.get(col).get(i).up.value == 0) {
                if (col == output.size() - 1) {
                    continue;
                } else {
                    col++;
                }
            }
            Fraction divisor = new Fraction(output.get(col).get(i));

            // divide pivot row
            for (int j = 0; j < output.size(); j++) {
                output.get(j).set(col, output.get(j).get(col).divide(divisor));
                output.get(j).get(col).simplify();
            }

            for (int j = 0; j < output.get(0).size(); j++) {
                if (i == j) {
                    continue;
                }

                Fraction leadingVal = new Fraction(output.get(col).get(j));
                // multiply row by leadingVal
                for (int k = col; k < output.size(); k++) {
                    output.get(k).set(j, output.get(k).get(j).sub(leadingVal.multip(output.get(col).get(i))));
                    output.get(k).get(j).simplify();
                }

            }
            if (col != output.size() - 1) {
                col++;
            }
        }
        return output;

    }

}
