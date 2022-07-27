
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    // Rint stands for Radical Integer, allows for exact form

    // used for UI
    // INPUT METHODS
    // ==========================================================================================
    static ArrayList<vector> inputMatrix(Scanner sf) {
        System.out.println("input dimensions(only use integers): ");
        ArrayList<vector> matrix;
        int vectorSize;
        int vectorNumber;
        System.out.println("row: ");
        vectorSize = sf.nextInt(); // row
        System.out.println("column: ");
        vectorNumber = sf.nextInt(); // column

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
            System.out.println("ouput found" + vectorMode);
        }
        if (vectorMode.equals("y") || vectorMode.equals("Y") || vectorMode.equals("yes")) {
            output = true;
        } else {
            output = false;
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
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).getSize(); j++) {
                System.out.print("[" + matrix.get(j).getIndex(i) + "]");
            }
            System.out.println("");
        }
    }

    // print matrix in vector format
    static void printMatrixVectorMode(ArrayList<vector> matrix) {
        System.out.println("Matrix: ");
        // go through matrix as normal
        for (int i = 0; i < matrix.get(0).getSize(); i++) {
            for (int j = 0; j < matrix.get(i).getSize(); j++) {

                // if not in the middle add spaces to compensate for coeff
                // otherwise write coeff
                if (i != matrix.get(0).getSize() / 2) {
                    String tempCoeffString = matrix.get(i).coeff.toString();
                    for (int k = 0; k < tempCoeffString.length() + 3; k++) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("" + matrix.get(i).coeff.toString() + " * ");
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

    // main method
    public static void main(String args[]) {
        Scanner sf;
        int tempX;
        int tempY;
        sf = new Scanner(System.in);

        ArrayList<vector> m1;
        ArrayList<vector> m2;
        ArrayList<vector> matrixOut;
        String operations[] = { "help", "matrixSum", "matrixProduct", "dotProduct", "orthogonal", "orthonormal" };
        System.out.println("type your operation, type \"help\" for a list of operations");
        boolean inputLoop = true;
        boolean vectorMode;
        while (inputLoop) {
            switch (sf.nextLine()) {
                case "help":
                    for (String s : operations) {
                        System.out.print("-");
                        System.out.println(s);
                    }
                    break;
                case "matrixSum":
                    System.out.println("input 2 matrices");
                    System.out.println("input dimensions(only use integers): ");
                    System.out.println("row: ");
                    tempX = sf.nextInt(); // row
                    System.out.println("column: ");
                    tempY = sf.nextInt(); // column

                    m1 = inputMatrix(tempX, tempY, sf);
                    m2 = inputMatrix(tempX, tempY, sf);
                    matrixOut = Matrix.add(m1, m2);
                    vectorMode = askVector(sf);
                    System.out.println("Sum:");
                    matrixDecision(vectorMode, matrixOut);
                    break;

                case "matrixProduct":
                    System.out.println("input 2 matrices");
                    System.out.println("input dimensions(only use integers): ");
                    System.out.println("row: ");
                    tempX = sf.nextInt(); // row
                    System.out.println("column: ");
                    tempY = sf.nextInt(); // column
                    System.out.println("input 2 matrices");

                    m1 = inputMatrix(tempX, tempY, sf);
                    m2 = inputMatrix(tempY, tempX, sf);
                    matrixOut = Matrix.multip(m1, m2);

                    vectorMode = askVector(sf);
                    System.out.println("Product:");
                    matrixDecision(vectorMode, matrixOut);

                    break;
                case "orthogonal":
                    m1 = inputMatrix(sf);
                    printMatrix(m1);
                    matrixOut = orthogonal.orthogonalize(m1);
                    vectorMode = askVector(sf);
                    System.out.println("Orthogonal :");
                    matrixDecision(vectorMode, matrixOut);
                    break;
                case "orthonormal":
                    m1 = inputMatrix(sf);
                    printMatrix(m1);
                    matrixOut = orthogonal.orthonormalize(m1);
                    vectorMode = askVector(sf);
                    System.out.println("Orthonormal Set:");
                    matrixDecision(vectorMode, matrixOut);
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
