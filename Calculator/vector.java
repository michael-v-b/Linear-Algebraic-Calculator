
import java.util.ArrayList;

public class vector {
    public Fraction coeff = new Fraction(new Rint(false, 1), new Rint(false, 1));
    public ArrayList<Rint> vect;

    public vector(ArrayList<Rint> vectTemp) {
        vect = vectTemp;
    }

    public vector() {
        vect = new ArrayList<Rint>();
    }

    public vector(vector v) {
        coeff = new Fraction(v.coeff);
        vect = new ArrayList<Rint>();
        for (int i = 0; i < v.vect.size(); i++) {
            vect.add(new Rint(v.vect.get(i).radical, v.vect.get(i).coeff, v.vect.get(i).value));
        }
    }

    public void add(Rint i) {
        vect.add(i);
    }

    public void scalar(Rint j) {
        for (int i = 0; i < vect.size(); i++) {
            vect.set(i, vect.get(i).multip(j));
        }
    }

    Rint gcf(Rint a, Rint b) {
        if (!a.radical && !b.radical) {
            if (b.value == 0) {
                return new Rint(a.radical, Math.abs(a.coeff), Math.abs(a.value));
            } else {
                return gcf(b, new Rint(false, a.value % b.value));
            }
        } else if (a.radical && b.radical) {
            int tempCoeff = gcf(new Rint(false, a.coeff), new Rint(false, b.coeff)).value;
            int tempValue = gcf(new Rint(false, a.value), new Rint(false, b.value)).value;
            return new Rint(true, tempCoeff, tempValue);
        }
        Rint temp;
        if (a.radical) {
            temp = new Rint(false, a.coeff);
            return gcf(temp, b);
        } else {
            temp = new Rint(false, b.coeff);
            return gcf(temp, a);
        }
    }

    public void scalar(Fraction f) {
        if (f.up.value == 0) {
            for (int i = 0; i < vect.size(); i++) {
                vect.get(i).value = 0;
                vect.get(i).coeff = 0;
                vect.get(i).radical = false;
            }
        }
        coeff.up = coeff.up.multip(f.up);
        coeff.down = coeff.down.multip(f.down);
    }

    public Fraction norm() {
        int out = 0;
        for (int i = 0; i < vect.size(); i++) {
            out += Math.pow(vect.get(i).value, 2);
        }
        Rint output = new Rint(true, coeff.up.value, out);
        output.check();
        return new Fraction(output, coeff.down);

    }

    public void simplify() {
        int j = 0;
        while (j < vect.size() - 1 && vect.get(j).value == 0) {
            j++;
        }
        Rint temp = new Rint(vect.get(j).radical, vect.get(j).value);
        for (int i = j; i < vect.size(); i++) {
            if (vect.get(i).value != 0) {
                temp = gcf(vect.get(i), temp);
                temp.value = Math.abs(temp.value);
            } else {
                continue;
            }
        }
        if (temp.value > 1) {
            int tempVal = temp.value;
            for (int i = 0; i < vect.size(); i++) {
                vect.get(i).value = vect.get(i).value / tempVal;
            }
            // temp.value = tempVal;
            coeff.up = coeff.up.multip(temp);

        }
    }

    public vector normalize() {
        vector output = new vector(this);
        Fraction tempNorm = output.norm();
        output.coeff = coeff.divide(tempNorm);
        output.coeff.down = output.coeff.down.multip(coeff.up);
        output.coeff.simplify();
        return output;
    }

    public int getSize() {
        return vect.size();
    }

    public Rint getIndex(int i) {
        return vect.get(i);
    }

    public String toString() {

        String output = "" + coeff.toString() + " * [";
        for (int i = 0; i < vect.size(); i++) {
            output += "" + vect.get(i) + ", ";
        }
        output += "]";
        return output;
    }
}
