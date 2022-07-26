
public class Rint {
    public boolean radical;
    public int value;
    public int coeff = 1;

    public Rint(boolean t, int i) {
        radical = t;
        value = i;
        coeff = 1;
    }

    public Rint(boolean t, int c, int i) {
        radical = t;
        value = i;
        coeff = c;
    }

    public void check() {
        if (value < 0 && radical) {
            System.out.println("imaginary number error");
        }

        // if perfect square, rewrite as radical as... not radical
        if (radical) {
            if (Math.sqrt(value) == (int) Math.sqrt(value)) {
                value = (int) (Math.sqrt(value)) * coeff;
                coeff = 1;
                radical = false;
            }
        } else if (coeff != 1) {
            value *= coeff;
            coeff = 1;
        }
    }

    public Rint multip(Rint r) {
        Rint output;
        if (radical) {
            // this is radical
            if (r.radical) {
                output = new Rint(true, coeff * r.coeff, value * r.value);
            } else {
                output = new Rint(true, coeff * r.value, value);
            }
        } else {
            // this isn't radical
            if (r.radical) {
                output = new Rint(true, value * r.coeff, r.value);
            } else {
                output = new Rint(false, value * r.value);
            }
        }
        output.check();
        return output;
    }

    public Rint add(Rint r) {
        Rint output;
        if (radical) {
            if (r.radical && r.value == value) {
                output = new Rint(true, coeff + r.coeff, value);
            }
            System.out.println("radical-nonradical error");
            return null;
        } else {
            output = new Rint(false, value + r.value);
        }
        return output;

    }

    public Rint sub(Rint r) {
        Rint output;
        if (radical) {

            if (r.radical && r.value == value) {
                output = new Rint(true, coeff - r.coeff, value);
            } else {
                System.out.println(this + " and " + r);
                System.out.println("radical-nonradical error");
            }
            return null;
        } else {
            output = new Rint(false, value - r.value);
        }
        return output;
    }

    public Fraction divide(Rint r) {
        Fraction output = new Fraction(this, r);
        output.simplify();
        return output;
    }

    public Fraction divide(Fraction f) {
        Fraction output = new Fraction(f.down.multip(this), f.up);
        output.simplify();
        return output;
    }

    public String toString() {
        if (radical) {
            if (coeff == 1) {
                return "R(" + value + ")";
            } else {
                return "" + coeff + "R(" + value + ")";
            }
        } else {
            return "" + value;
        }
    }
}
