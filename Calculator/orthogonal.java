
import java.util.Scanner;
import java.util.ArrayList;
//javac orthogonal.java
//java orthogonal

public class orthogonal {

    public static Fraction dotProduct(vector vector1, vector vector2) {
        Rint u = vector1.coeff.up.multip(vector2.coeff.up);
        Rint d = vector1.coeff.down.multip(vector2.coeff.down);
        Rint temp = new Rint(false, 0);
        for (int i = 0; i < vector1.vect.size(); i++) {
            temp = temp.add(vector1.vect.get(i).multip(vector2.vect.get(i)));
        }
        Fraction output = new Fraction(new Rint(true, u.multip(temp).value), new Rint(true, d.value));
        return output;
    }

    static vector subVect(vector tempVect1, vector tempVect2) {

        vector vect1 = new vector(tempVect1);
        vector vect2 = new vector(tempVect2);
        if (vect2.coeff.up.value == 0 || vect2.coeff.up.coeff == 0) {
            return tempVect1;
        }
        vect2.scalar(vect1.coeff.down);
        vect2.scalar(vect2.coeff.up);
        vect1.scalar(vect2.coeff.down);
        vect1.scalar(vect1.coeff.up);

        vector output = new vector(new ArrayList<Rint>());
        output.coeff.down = vect1.coeff.down.multip(vect2.coeff.down);
        for (int i = 0; i < vect1.vect.size(); i++) {
            output.add(vect1.vect.get(i).sub(vect2.vect.get(i)));
        }
        return output;
    }

    public static ArrayList<vector> orthonormalize(ArrayList<vector> matrix) {

        ArrayList<vector> oNormal = orthogonalize(matrix);

        for (int i = 0; i < oNormal.size(); i++) {

            oNormal.set(i, oNormal.get(i).normalize());
        }
        return oNormal;
    }

    public static ArrayList<vector> orthogonalize(ArrayList<vector> matrix) {
        if (matrix.size() == 1) {
            return matrix;
        }
        ArrayList<vector> output = new ArrayList<vector>();

        for (int i = 0; i < matrix.size(); i++) {
            vector u = matrix.get(i);
            output.add(u);
            for (int j = 0; j < i; j++) {
                vector v = new vector(output.get(j));
                Fraction tempDotProduct = dotProduct(u, v);

                Fraction tempNorm = v.norm();
                tempNorm.simplify();
                tempNorm = tempNorm.multip(tempNorm);
                Fraction tempFraction = new Fraction(tempDotProduct, tempNorm);
                tempFraction.simplify();
                v.scalar(tempFraction);
                output.set(i, subVect(output.get(i), v));
            }
            output.get(i).simplify();
            output.get(i).coeff.simplify();

        }

        return output;

    }

    static void QRFactorization() {

    }
}