
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    // Rint stands for Radical Integer, allows for exact form

    // used for UI
    // INPUT METHODS
    // ==========================================================================================
    static ArrayList<vector> inputMatrix(Scanner sf) {

        ArrayList<vector> matrix;
        int vectorSize;
        int vectorNumber;
        System.out.println("input dimensions(only use integers)");
        System.out.println("rows: ");
        vectorSize = sf.nextInt();
        System.out.println("columns: ");
        vectorNumber = sf.nextInt();

        matrix = new ArrayList<vector>(vectorNumber);
        // instantiates matrix
        for (int i = 0; i < vectorNumber; i++) {
            matrix.add(new vector(new ArrayList<>(vectorSize)));
        }
        System.out.println("input matrix:");
        // assigns matrix
        for (int i = 0; i < vectorSize; i++) {
            System.out.println("input row [" + i + "]:");
            for (int j = 0; j < vectorNumber; j++) {
                Rint temp = new Rint(false, sf.nextInt());
                matrix.get(j).add(temp);
            }
        }
        return matrix;

    }

    static Rint inputRint(Scanner sf) {
        Rint output;
        String input = sf.nextLine();
        int inputNum;
        int coeffNum;
        input.trim();
        /*
         * 3 possibilities
         * x
         * Ra
         * aRb
         */
        if (input.contains("R")) {
            int tempIndex = input.indexOf("R");
            if (tempIndex == 0) {
                inputNum = Integer.parseInt(input.substring(1));
                output = new Rint(true, inputNum);
            } else {
                coeffNum = Integer.parseInt(input.substring(0, tempIndex));
                inputNum = Integer.parseInt(input.substring(tempIndex + 1));
                output = new Rint(true, coeffNum, inputNum);
            }
        } else {
            inputNum = Integer.parseInt(input);
            output = new Rint(false, inputNum);
        }
        return output;
    }

    // inputMatrix with dimensions
    static ArrayList<vector> inputMatrix(int x, int y, Scanner sf) {
        System.out.println("input dimensions(only use integers): ");
        ArrayList<vector> matrix;
        int vectorSize;
        int vectorNumber;
        vectorSize = x;// row
        vectorNumber = y; // column

        matrix = new ArrayList<vector>(vectorNumber);
        // instantiates matrix
        for (int i = 0; i < vectorNumber; i++) {
            matrix.add(new vector(new ArrayList<>(vectorSize)));
        }
        System.out.println("input matrix:");
        // assigns matrix
        for (int i = 0; i < vectorSize; i++) {
            System.out.println("input row [" + i + "]:");
            for (int j = 0; j < vectorNumber; j++) {
                Rint temp = new Rint(false, sf.nextInt());
                matrix.get(j).add(temp);
            }
        }
        return matrix;

    }

    // asks whether output should be in vector form
    static boolean askVector(Scanner sf) {
        String vectorMode = "";
        boolean output;
        System.out.println("In Vector Form?: y/n?");
        while (!(vectorMode.equals("y") || vectorMode.equals("n") ||
                vectorMode.equals("yes") || vectorMode.equals("no") ||
                vectorMode.equals("Y") || vectorMode.equals("N"))) {

            vectorMode = sf.nextLine();
        }
        if (vectorMode.equals("y") || vectorMode.equals("Y") || vectorMode.equals("yes")) {
            output = true;
        } else {
            output = false;
        }

        return output;
    }

    // input Vector
    static vector inputVector(Scanner sf) {
        System.out.println("Vector Size:");
        vector output = new vector();
        int size = sf.nextInt();

        for (int i = 0; i < size; i++) {
            Rint temp = new Rint(false, sf.nextInt());
            output.add(temp);
        }
        return output;

    }

    static boolean continueCalc(Scanner sf) {
        System.out.println("Continue Calculations? y/n");
        while (true) {
            String input = sf.nextLine();
            switch (input) {
                case "y":
                case "Yes":
                case "Y":
                case "yes":
                    return true;
                case "n":
                case "No":
                case "N":
                case "no":
                    return false;
                default:
                    continue;

            }
        }

    }

    // OUTPUT
    // METHODS=============================================================================================
    // an if statement that decides whether to print a method in vector form or not
    static void matrixDecision(boolean v, ArrayList<vector> matrix) {
        if (v) {
            printMatrixVectorMode(matrix);

        } else {
            printMatrix(matrix);
        }
    }

    // print matrix in output
    static void printMatrix(ArrayList<vector> matrix) {
        System.out.println("Matrix: ");

        for (int i = 0; i < matrix.get(0).getSize(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                System.out.print("[" + (matrix.get(j).coeff.multip(matrix.get(j).getIndex(i))) + "]");
            }
            System.out.println("");
        }

    }

    // print matrix in vector format
    static void printMatrixVectorMode(ArrayList<vector> matrix) {

        System.out.println("Matrix: ");
        // go through matrix as normal
        for (int i = 0; i < matrix.get(0).getSize(); i++) {

            for (int j = 0; j < matrix.size(); j++) {
                // simplify matrix
                // simplify matrix
                matrix.get(j).simplify();
                // if not in the middle add spaces to compensate for coeff
                // otherwise write coeff
                if (i != (matrix.get(i).getSize() / 2)) {
                    String tempCoeffString = matrix.get(j).coeff.toString();
                    for (int k = 0; k < tempCoeffString.length() + 3; k++) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("" + matrix.get(j).coeff.toString() + " * ");
                }
                System.out.print("[" + matrix.get(j).getIndex(i) + "] ");
            }
            System.out.println("");
        }
    }

    // printMatrix for Fraction
    static void printMatrixF(ArrayList<ArrayList<Fraction>> matrix) {
        System.out.println("Matrix: ");
        for (int i = 0; i < matrix.get(0).size(); i++) {
            System.out.print("[");
            for (int j = 0; j < matrix.size(); j++) {
                System.out.print(matrix.get(j).get(i).toString() + ", ");
            }
            System.out.println("]");
        }
    }

    // main
    // method!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static void main(String args[]) {

        Scanner sf;
        ArrayList<vector> m1;
        ArrayList<vector> m2;
        Rint r;
        ArrayList<vector> matrixOut;
        boolean cont = false;
        ArrayList<vector> contMatrix = new ArrayList<vector>();
        String operations[] = { "help", "matrixSum", "matrixProduct", "dotProduct", "orthogonal", "orthonormal",
                "continue",
                "quit" };
        System.out.println("type your operation, type \"help\" for a list of operations");
        boolean inputLoop = true;
        boolean vectorMode;
        int tempX;
        int tempY;

        if (args.length == 1) {
            try {
                File inputFile = new File(args[0]);
                sf = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                sf = new Scanner(System.in);
            }

        } else {
            sf = new Scanner(System.in);
        }

        while (inputLoop) {
            String input = sf.nextLine();
            input.trim();
            switch (input) {
                case "help":
                    for (String s : operations) {
                        System.out.print("-");
                        System.out.println(s);
                    }
                    break;
                case "scalar":
                    System.out.println("input matrix");

                    // if continue
                    if (cont) {
                        m1 = contMatrix;
                    } else {
                        m1 = inputMatrix(sf);
                    }
                    System.out.println("input number (if radical see radical format in help");
                    r = inputRint(sf);
                    Matrix.scalar(m1, r);
                    vectorMode = askVector(sf);
                    matrixDecision(vectorMode, m1);

                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = m1;
                    } else {
                        contMatrix = null;
                    }
                    break;

                case "printMatrix":
                    System.out.println("input matrix");

                    m1 = inputMatrix(sf);
                    vectorMode = askVector(sf);
                    matrixDecision(vectorMode, m1);
                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = m1;
                    } else {
                        contMatrix = null;
                    }
                    break;
                case "matrixSum":
                    System.out.println("input 2 matrices");

                    m1 = inputMatrix(sf);
                    m2 = inputMatrix(sf);
                    matrixOut = Matrix.add(m1, m2);
                    vectorMode = askVector(sf);
                    System.out.println("Sum:");
                    matrixDecision(vectorMode, matrixOut);

                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = matrixOut;
                    } else {
                        contMatrix = null;
                    }
                    break;

                case "matrixProduct":
                    System.out.println("input 2 matrices");

                    System.out.println("Matrix 1:");
                    System.out.println("input dimensions(only use integers)");
                    System.out.println("rows: ");
                    tempX = sf.nextInt();
                    System.out.println("columns: ");
                    tempY = sf.nextInt();
                    m1 = inputMatrix(tempX, tempY, sf);
                    System.out.println("Matrix 2:");
                    System.out.println("input dimensions(only use integers)");
                    System.out.println("columns: ");
                    tempY = sf.nextInt();
                    m2 = inputMatrix(tempX, tempY, sf);
                    matrixOut = Matrix.multip(m1, m2);

                    vectorMode = askVector(sf);
                    System.out.println("Product:");
                    matrixDecision(vectorMode, matrixOut);
                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = matrixOut;
                    } else {
                        contMatrix = null;
                    }
                    break;

                case "orthogonal":
                    m1 = inputMatrix(sf);
                    printMatrix(m1);
                    matrixOut = orthogonal.orthogonalize(m1);
                    vectorMode = askVector(sf);
                    System.out.println("Orthogonal :");
                    matrixDecision(vectorMode, matrixOut);

                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = matrixOut;
                    } else {
                        contMatrix = null;
                    }
                    break;
                case "orthonormal":
                    m1 = inputMatrix(sf);
                    printMatrix(m1);
                    matrixOut = orthogonal.orthonormalize(m1);
                    vectorMode = askVector(sf);
                    System.out.println("Orthonormal Set:");
                    matrixDecision(vectorMode, matrixOut);
                    // continue calculatrions
                    cont = continueCalc(sf);
                    if (cont) {
                        contMatrix = matrixOut;
                    } else {
                        contMatrix = null;
                    }
                    break;

                case "dotProduct":
                    vector v1 = inputVector(sf);
                    vector v2 = inputVector(sf);
                    Fraction out = orthogonal.dotProduct(v1, v2);
                    System.out.println("Result: " + out + "");

                    break;
                case "RREF":
                    m1 = inputMatrix(sf);
                    ArrayList<ArrayList<Fraction>> RREF = Matrix.RREF(m1);
                    printMatrixF(RREF);
                    // continue rref
                    break;

                case "diagonlize":
                    break;
                case "quit":
                    inputLoop = false;
                    break;
            }

        }

    }

}
