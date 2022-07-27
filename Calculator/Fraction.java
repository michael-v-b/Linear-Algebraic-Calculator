
public class Fraction {
    public Rint up;
    public Rint down;

    public Fraction(Fraction f) {
        up = f.up;
        down = f.down;
    }

    public Fraction(Rint i, Rint j) {
        up = i;
        down = j;
    }

    public Fraction(Fraction i, Rint j) {
        up = i.up;
        down = j.multip(i.down);
    }

    public Fraction(Rint i, Fraction j) {
        up = i.multip(j.down);
        down = i.multip(j.up);
    }

    public Fraction(Fraction i, Fraction j) {
        up = i.up.multip(j.down);
        down = i.down.multip(j.up);
    }

    // returns fraction as double!
    public double getFract() {
        return (double) up.value / down.value;
    }

    // returns greatest common factor
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

    // simplifies
    public void simplify() {
        if (up.value == 0 || up.coeff == 0) {
            down.coeff = 0;
            down.value = 0;
            up.check();
            down.check();
            return;
        }
        Rint temp = gcf(up, down);

        if (up.value < 0 && down.value < 0) {
            up.value *= -1;
            down.value *= -1;
        }

        if (temp.radical) {
            up.coeff /= temp.coeff;
            down.coeff /= temp.coeff;
            up.check();
            down.check();
            return;
        }
        // niether are radical
        if (!up.radical && !down.radical) {
            up.value /= temp.value;
            down.value /= temp.value;
            return;
            // down is radical
        } else if (up.radical) {
            up.coeff /= temp.value;
            down.value /= temp.value;
            return;
            // up is radical
        } else if (down.radical) {
            up.value /= temp.value;
            down.coeff /= temp.value;
            return;
            // both are radical
        } else {
            up.coeff /= temp.value;
            down.coeff /= temp.value;
            return;
        }

    }

    // multiply fraction by fraction
    public Fraction multip(Fraction i) {
        Fraction output = new Fraction(this);
        output.up = up.multip(i.up);
        output.down = down.multip(i.down);
        return output;
    }

    // multiply fraction by Rint
    public Fraction multip(Rint i) {
        Fraction output = new Fraction(this);
        output.up = up.multip(i);
        return output;
    }

    // divide fraction by fraction
    public Fraction divide(Fraction i) {
        Fraction output = new Fraction(this);
        output.up = up.multip(i.down);
        output.down = down.multip(i.up);
        output.simplify();
        return output;
    }

    // divide fraction by Rint
    public Fraction divide(Rint i) {
        Fraction output = new Fraction(this);
        output.up = up.multip(i);
        output.simplify();
        return output;
    }

    public Fraction sub(Fraction i) {
        Fraction output = new Fraction(this);
        output.up = up.multip(i.down);
        output.up = output.up.sub(i.up.multip(down));
        output.down = i.down;
        return output;
    }

    public String toString() {
        switch (down.value) {
            case 1:
                return "" + up;
            case -1:
                if (up.value != 0) {
                    return "-" + up;
                } else {
                    return "" + up;
                }
            default:
                return "" + up + "/" + down;
        }
    }

}
