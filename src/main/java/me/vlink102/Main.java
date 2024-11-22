package me.vlink102;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int[] threeDigitSquares = {100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961};
    private static final int[] twoDigitPrimes = { 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private static final int[] palindromicPrimes = { 101, 131, 151, 181, 191, 313, 353, 373, 383, 727, 757, 787, 797, 919, 929 };
    private static final int[] twoDigitCubes = { 27, 64};
    private static final int[] twoDigitTriangular = { 10, 15, 21, 28, 36, 45, 55, 66, 78, 91 };
    private static final int[] twoDigitSquares = {4, 9, 16, 25, 36, 49, 64, 81};



    public static void artem() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        for (int threeDigitSquare : threeDigitSquares) {
            a1 = getDigitAtPosition(threeDigitSquare, 0);
            a2 = getDigitAtPosition(threeDigitSquare, 1);
            a3 = getDigitAtPosition(threeDigitSquare, 2);

            for (int i = 0; i <= 9; i++) {
                b2 = i;

                if (isSquareNumber(a2 + b2)) {
                    //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});

                    for (int j = 0; j <= 9; j++) {
                        b3 = j;

                        int threeAcross = (b2 * 10) + b3;
                        for (int twoDigitPrime : twoDigitPrimes) {
                            int a = getDigitAtPosition(twoDigitPrime, 0);
                            int b = getDigitAtPosition(twoDigitPrime, 1);
                            int eoinieisot = (b * 10) + a;
                            if (eoinieisot == threeAcross) {
                                //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                for (int k = 0; k <= 9; k++) {
                                    c3 = k;
                                    int fourdown = (b3 * 10) + c3;
                                    for (int i1 : twoDigitTriangular) {
                                        if (fourdown == i1) {
                                            //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                            for (int l = 0; l <= 9; l++) {
                                                b1 = l;

                                                for (int m = 0; m <= 9; m++) {
                                                    c2 = m;

                                                    int oneDown = (100 * a1) + (10 * b1) + c1;

                                                    for (int n = 0; n <= 9; n++) {
                                                        c1 = n;
                                                        int fiveAcross = (100 * c1) + (10 * c2) + c3;

                                                        if (oneDown % fourdown == 0) {
                                                            int test = oneDown / fourdown;
                                                            if (test % 2 != 0) {
                                                                int sumall = a1 + a2 + a3 + b1 + b2 + b3 + c1 + c2 + c3;
                                                                if (fiveAcross % sumall == 0) {
                                                                    printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                                                    System.out.println(test );
                                                                }
                                                            }


                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void wtf() {
        int a1 = 3, a2 = 6, a3 = 1,
                b1 = 0, b2 = 3, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;

        for (int i = 0; i < 9; i++) {
            b3 = i;
            int threeAcross = (b2 * 10) + b3;
            for (int twoDigitPrime : twoDigitPrimes) {
                int digit1 = getDigitAtPosition(twoDigitPrime, 0);
                int digit2 = getDigitAtPosition(twoDigitPrime, 1);
                int num = (digit2 * 10) + digit1;
                if (threeAcross == num) {
                    //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                    for (int j = 0; j < 9; j++) {
                        c3 = j;
                        for (int k = 0; k < 9; k++) {
                            b1 = k;
                            int oneDown = (100 * a1) + (10 * b1) + c1;
                            int fourDown = (10 * b3) + c3;

                            if (oneDown % fourDown == 0) {
                                int divisor = oneDown / fourDown;
                                System.out.println(divisor);
                                for (int l = 0; l < 9; l++) {
                                    c2 = l;
                                    for (int m = 1; m <= 6; m++) {
                                        c1 = m;
                                        int fiveAcross = (100 * c1) + (10 * c2) + c3;
                                        int sumAll = a1 + a2 + a3 + b1 + b2 + b3 + c1 + c2 + c3;
                                        if (fiveAcross % sumAll == 0) {
                                            //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                            for (int i1 : twoDigitTriangular) {
                                                if (fourDown == i1) {
                                                    printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                                }
                                            }
                                        }
                                    }


                                }
                            }
                        }


                    }
                }
            }
        }


    }



    public static void q28() {

        for (int d = 0; d < 2024; d++) {
            int n = 0;
            int total = 0;
            for (int i = d; i < 1000 && total <= 2024; i++) {
                total += i;
                n++;
                if (total == 2024) {
                    System.out.println("Found 2024: " + n);
                }
            }
        }

    }

    public static void puzzlePee() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        // 1 Across
        for (int threeDigitSquare : threeDigitSquares) {
            a1 = getDigitAtPosition(threeDigitSquare, 0);
            a2 = getDigitAtPosition(threeDigitSquare, 1);
            a3 = getDigitAtPosition(threeDigitSquare, 2);
            System.out.println(threeDigitSquare);

            //System.out.println("b1" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));
            // 2 Down
            for (int i = 0; i < 9; i++) {
                b2 = i;
                int digitSum = a2 + b2;
                if (isSquareNumber(digitSum)) {
                    //System.out.println("Square: " + digitSum + "[" + a2 + "+" + b2 + "] (b2=" + b2 + ")");
                    //System.out.println("b2" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));

                    for (int j = 0; j < 9; j++) {
                        b3 = j;
                        int threeAcross = (b2 * 10) + b3;
                        for (int twoDigitPrime : twoDigitPrimes) {
                            int digit1 = getDigitAtPosition(twoDigitPrime, 0);
                            int digit2 = getDigitAtPosition(twoDigitPrime, 1);
                            int reversed = (digit2 * 10) + digit1;
                            if (reversed == threeAcross) {
                                //System.out.println("Reversed Prime: " + threeAcross);
                                printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});

                            }
                        }
                    }
                }
            }
        }
    }

    private static void puzzleP() {
        int a1 = 3, a2 = 6, a3 = 1,
                b1 = 0, b2 = 3, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        for (int i = 0; i <= 9; i++) {
                b3 = i;

                int reversed3Across = (b3 * 10) + b2;

                for (int twoDigitPrime : twoDigitPrimes) {
                    if (reversed3Across == twoDigitPrime) {
                        for (int j = 0; j <= 9; j++) {
                            c3 = j;

                            int fourDown = (b3 * 10) + c3;
                            for (int i1 : twoDigitTriangular) {
                                if (fourDown == i1) {
                                    //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                    for (int k = 0; k <= 9; k++) {
                                        b1 = k;
                                        for (int l = 0; l <= 9; l++) {
                                            c1 = l;
                                            for (int m = 0; m <= 9; m++) {
                                                c2 = m;

                                                int fiveAcross = (c1 * 100) + (c2 * 10) + c3;
                                                int sumall = a1 + a2 + a3 + b1 + b2 + b3 + c1 + c2 + c3;
                                                if (fiveAcross % sumall == 0) {
                                                    //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                                    int oneDown = (100 * a1) + (10 * b1) + c1;
                                                    if (oneDown % fourDown == 0) {
                                                        int divided = oneDown / fourDown;
                                                        if (isPrime(divided)) {
                                                            printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private static void printPossibleSolution(int[][] grid) {
        if (!validateS(grid)) return;
        if (validateGrid(grid)) {
            System.out.println(Arrays.deepToString(grid) + " " + convertToLocation(grid));
        }
    }

    public static String formatDM(int latDegrees, int latMinutes,
                                  int lonDegrees, int lonMinutes) {
        return latDegrees + "°" + latMinutes + "'00.0\"N " + lonDegrees + "°" + lonMinutes + "'00.0W";
    }

    private static String convertToLocation(int[][] grid) {
        int degNorth = (10 * grid[0][0]) + grid[0][1];
        int minsNorth = (10 * grid[0][2]) + grid[1][2];

        int degWest = (10 * grid[2][2]) + grid[2][1];
        int minsWest = (10 * grid[2][0]) + grid[1][0];
        return formatDM(degNorth, minsNorth, degWest, minsWest);
    }

    private static boolean validateGrid(int[][] grid) {
        int AB = (10 * grid[0][0]) + grid[0][1];
        if (AB > 71) return false;
        int CD = (10 * grid[0][2]) + grid[1][2];
        if (CD > 60) return false;
        int EF = (10 * grid[2][2]) + grid[2][1];
        if (EF > 172) return false;
        int GH = (10 * grid[2][0]) + grid[1][0];
        if (GH > 60) return false;
        if (grid[2][2] < 6) return false;
        return true;
    }

    public static int getDigitAtPosition(int number, int n) {
        String numberString = String.valueOf(number);
        char[] characters = numberString.toCharArray();
        return Integer.parseInt(String.valueOf(characters[n]));
    }

    public static int[] reverseDigits(int number, int digits) {
        String numberString = String.valueOf(number);
        if (numberString.length() < digits) {
            numberString = "0" + numberString;
        }
        char[] numberStringArray = numberString.toCharArray();
        StringBuilder newString = new StringBuilder();
        for (int i = numberStringArray.length - 1; i >= 0; i--) {
            newString.append(numberStringArray[i]);
        }
        String bro = newString.toString();
        char[] broArray = bro.toCharArray();
        int[] yea = new int[digits];
        for (int i = 0; i < broArray.length - 1; i++) {
            yea[i] = Integer.parseInt(String.valueOf(broArray[i]));
        }
        return yea;
    }

    private static int[][] puzzleQ() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;

        for (int palindromicPrime : palindromicPrimes) {
            a1 = getDigitAtPosition(palindromicPrime, 0);
            a2 = getDigitAtPosition(palindromicPrime, 1);
            a3 = getDigitAtPosition(palindromicPrime, 2);
            //System.out.println(Arrays.deepToString(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}}));

            for (int i = 0; i < 9; i++) {
                b1 = i;
                for (int j = 0; j < 9; j++) {
                    c1 = j;

                    int digitSum1Down = a1 + b1 + c1;
                    int oneDown = (a1 * 100) + (b1 * 10) + c1;
                    if (palindromicPrime - digitSum1Down == oneDown) {
                        //System.out.println("Digit sum 1 down: " + digitSum1Down);
                        //System.out.println(Arrays.deepToString(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}}));
                        // 2 Down
                        for (int k = 0; k < 9; k++) {
                            b2 = k;
                            int twoDown = (10 * a2) + b2;
                            if (is2dPrime(twoDown)) {
                                //System.out.println("Prime: " + twoDown);
                                //System.out.println(Arrays.deepToString(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}}));
                                // 3 Across
                                for (int l = 0; l < 9; l++) {
                                    b3 = l; // TODO
                                    // 4 Down
                                    for (int m = 0; m < 9; m++) {
                                        c3 = m;
                                        int fourDown = (b3 * 10) + c3;
                                        if (is2dCubeNumber(fourDown)) {
                                            //System.out.println("Cube: " + fourDown);
                                            //System.out.println(Arrays.deepToString(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}}));
                                            // 5 Across
                                            int reverse3Across = (b3*10) + b2;
                                            for (int n = 0; n < 9; n++) {
                                                c2 = n;
                                                //System.out.println(Arrays.deepToString(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}}));

                                                int digitSum5Across = (int) Math.pow(c1 + c2 + c3, 2);
                                                //System.out.println("Digit sum 5 across squared: " + digitSum5Across);
                                                int fiveAcross = (100 * c1) + (10 * c2) + c3;
                                                //System.out.println("Five Across: " + fiveAcross);

                                                //System.out.println(digitSum5Across + reverse3Across);
                                                if (fiveAcross == digitSum5Across + reverse3Across) {
                                                    //System.out.println("Yippee!");
                                                    printPossibleSolution(new int[][] {{a1,a2,a3},{b1,b2,b3}, {c1,c2,c3}});
                                                    //return new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}};
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return new int[][] {};
    }

    private static int[][] p() {
        int a1 = 0, a2 = 0, a3 = 0,
            b1 = 0, b2 = 0, b3 = 0,
            c1 = 0, c2 = 0, c3 = 0;
        for (int threeDigitSquare : threeDigitSquares) {
            // 1 Across
            a1 = getDigitAtPosition(threeDigitSquare, 0);
            a2 = getDigitAtPosition(threeDigitSquare, 1);
            a3 = getDigitAtPosition(threeDigitSquare, 2);
            // 2 Down
            for (int i = 0; i < 9; i++) {
                b2 = i;
                if (isSquareNumber(a2 + b2)) {
                    // 3 Across
                    for (int j = 0; j < 9; j++) {
                        b3 = j;
                        if (is2dPrime((10 * b3) + b2)) {
                            // 4 Down TODO

                            // 1 Down TODO

                            // 5 Across TODO
                        }
                    }
                }
            }
        }
        return new int[][] {};
    }

    private static int[][] q() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;

        // 1 Across
        for (int palindromicPrime : palindromicPrimes) {
            System.out.println("Trying palindromic: " + palindromicPrime);
            a1 = getDigitAtPosition(palindromicPrime, 0);
            a2 = getDigitAtPosition(palindromicPrime, 1);
            a3 = getDigitAtPosition(palindromicPrime, 2);
            System.out.println("" + a1 + ", " + a2 + ", " + a3);
            // 1 Down
            for (int i = 0; i < 9; i++) {
                b1 = i;
                System.out.println("b1" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));
                for (int j = 0; j < 9; j++) {
                    c1 = j;
                    System.out.println("c1" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));

                    int digitSum1Down = a1 + b1 + c1;
                    int oneDown = (100 * a1) + (10 * b1) + c1;
                    if (palindromicPrime - digitSum1Down == oneDown) {
                        // 2 Down
                        for (int k = 0; k < 9; k++) {
                            b2 = k;
                            System.out.println("b2" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));

                            if (is2dPrime((a2 * 10) + b2)) {
                                // 3 Across TODO
                                for (int d = 0; d < 9; d++) {
                                    b3 = d;
                                    System.out.println("b3"+ Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));

                                    // 5 Across
                                    for (int l = 0; l < 9; l++) {
                                        c2 = l;
                                        System.out.println("c2" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));

                                        for (int m = 0; m < 9; m++) {
                                            c3 = m;
                                            System.out.println("c3" + Arrays.deepToString(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}}));


                                            int digitSum5Across = c1 + c2 + c3;
                                            int reverse3Across = (b3 * 10) + b2;
                                            int fiveAcross = (100 * c1) + (10 * c2) + c3;
                                            if (digitSum5Across + reverse3Across == fiveAcross) {
                                                // 4 Down
                                                int fourDown = (10 * b3) + c3;
                                                if (is2dCubeNumber(fourDown)) {
                                                    // Solved
                                                    return new int[][] {{a1,a2,a3},{b1,b2,b3},{c1,c2,c3}};
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        return new int[][] {};
    }


    private static void r2() {

        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;

        //1ac
        for (int i = 0; i <= 9; i++) {
            a1 = i;
            for (int j = 0; j <= 9; j++) {
                a2 = j;
                for (int k = 0; k <= 9; k++) {
                    a3 = k;
                    for (int l = 0; l <= 9; l++) {
                       b1 = l;
                        for (int m = 0; m <= 9; m++) {
                            c1 = m;
                            for (int n = 0; n <= 9; n++) {
                                b2 = n;

                                if (a1+ a2 + a3 == a1 + b1 + c1 && a1 + b1 + c1 == a2 + b2) {
                                    int digitprod1across = a1 * a2 * a3;
                                    int digitprod1down = a1 * b1 * c1;
                                    int twoDown = (a2 * 10) + b2;
                                    if (twoDown == digitprod1down + digitprod1across) {
                                        for (int o = 0; o <= 9; o++) {
                                            b3 = o;
                                            for (int p = 0; p <= 9; p++) {
                                                c3 = p;

                                                int threeAcross = (b2 * 10) + b3;
                                                int fourDown = (b3 * 10) + c3;
                                                int digitSum4down = b3 + c3;
                                                if (threeAcross == fourDown - digitSum4down) {
                                                    // 5ac
                                                    for (int q = 0; q <= 9; q++) {
                                                        c2 = q;
                                                        int digitsum5ac = c1 + c2 + c3;
                                                        if (isPrime(digitsum5ac)) {

                                                            printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void r() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 0, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        for (int i = 1; i <= 7; i++) {
            a1 = i;
            for (int j = 0; j <= 6; j++) {
                a3 = j;
                for (int k = 1; k <= 6; k++) {
                    c1 = k;

                    for (int l = 2; l <= 4; l++) {
                        b2 = l;

                        for (int m = 0; m <= 9; m++) {
                            a2 = m;

                            for (int n = 0; n <= 9; n++) {
                                b1 = n;

                                // 1ac
                                if (a1 + a2 + a3 == a1 + b1 + c1) {
                                    // 1dn
                                    if (a2 + b2 == a1 + b1 + c1) {
                                        // 2dn
                                        if ((a1 * a2 * a3) + (a1 * b1 * c1) == (10 * a2) + b2) {
                                            // 3ac
                                            //printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                            for (int o = 0; o <= 9; o++) {
                                                b3 = o;
                                                for (int p = 0; p <= 9; p++) {
                                                    c3 = p;


                                                    int fourDown = (b3 * 10) + c3;
                                                    int digitSum4Down = b3 + c3;
                                                    if (fourDown - digitSum4Down == (b2 * 10) + b3) {
                                                        for (int q = 0; q <= 9; q++) {
                                                            c2 = q;
                                                            int digitSum5ac = c1 + c2 + c3;
                                                            if (isPrime(digitSum5ac)) {
                                                                printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*
 static boolean validateQ() {
        int[][] grid = new int[3][3];
        for (int palindromicPrime : palindromicPrimes) {
            grid[0][0] = getDigitAtPosition(palindromicPrime, 0);
            grid[0][1] = getDigitAtPosition(palindromicPrime, 1);
            grid[0][2] = getDigitAtPosition(palindromicPrime, 2);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int digitSum = grid[0][0] + i + j;
                    int oneDown = (100 * grid[0][0]) + (10 * i) + j;
                    int difference = palindromicPrime - digitSum;

                    if (oneDown == difference) {
                        grid[1][0] = i;
                        grid[2][0] = j;

                        for (int u = 0; u < 9; u++) {
                            int number = (grid[0][1] * 10) + u;
                            for (int twoDigitPrime : twoDigitPrimes) {
                                if (number == twoDigitPrime) {
                                    grid[1][1] = u;


                                    // 3 across TODO

                                    int reverse3Across = (grid[1][2] * 10) + grid[1][1];
                                    int digitSum5Across = grid[2][0] + k + l;
                                    for (int k = 0; k < 9; k++) {
                                        for (int l = 0; l < 9; l++) {
                                            int fiveAcross = (100 * grid[2][0]) + (10 * k) + l;
                                            if (digitSum5Across + reverse3Across == fiveAcross) {
                                                if (is2dCubeNumber((10 * grid[1][2]) + grid[2][2])) {
                                                    // solved


                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }

        }
    }
    */

    private static boolean is2dTriangularNumber(int number) {
        for (int i : twoDigitTriangular) {
            if (number == i) return true;
        }
        return false;
    }

    private static boolean is2dCubeNumber(int number) {
        for (int twoDigitCube : twoDigitCubes) {
            if (number == twoDigitCube) return true;
        }
        return false;
    }

    private static boolean isSquareNumber(int number) {
        for (int twoDigitSquare : twoDigitSquares) {
            if (twoDigitSquare == number) return true;
        }
        return false;
    }

    private static boolean is2dPrime(int number) {
        for (int twoDigitPrime : twoDigitPrimes) {
            if (number == twoDigitPrime) return true;
        }
        return false;
    }

    private static boolean isPalindromicPrime(int number) {
        for (int palindromicPrime : palindromicPrimes) {
            if (palindromicPrime == number) return true;
        }
        return false;
    }

    public static void s2() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 2, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        for (int i = 1; i <= 7; i++) {
            a1 = i;
            for (int j = 0; j <= 9; j++) {
                if (j == 8) continue;
                a2 = j;

                for (int k = 0; k <= 6; k++) {
                    a3 = k;

                    int oneAcross = (100 * a1) + (10 * a2) + a3;
                    if (!isPrime(oneAcross)) {
                    }
                }
            }
        }
    }

    public static boolean validateS(int[][] grid) {
        int oneAcross = (100 * grid[0][0]) + (10 * grid[0][1]) + grid[0][2];
        int threeAcross = (10 * grid[1][1]) + grid[1][2];
        int fiveAcross = (100 * grid[2][0]) + (10 * grid[2][1]) + grid[2][2];
        int oneDown = (100 * grid[0][0]) + (10 * grid[1][0]) + grid[2][0];
        int twoDown = (10 * grid[0][1]) + grid[1][1];
        int fourDown = (10 * grid[1][2]) + grid[2][2];

        if (isPrime(oneAcross)) return false;
        int digitSum1Across = grid[0][0] + grid[0][1] + grid[0][2];
        int reverse3Across = (grid[1][2] * 10) + grid[1][1];
        if (twoDown != reverse3Across) return false;
        if (!is2dTriangularNumber(fourDown)) return false;
        if (oneDown != threeAcross + Math.pow(digitSum1Across, 2)) return false;
        if (fiveAcross != oneDown + threeAcross + fourDown) return false;
        return true;
    }

    public static void s() {
        int a1 = 0, a2 = 0, a3 = 0,
                b1 = 0, b2 = 2, b3 = 0,
                c1 = 0, c2 = 0, c3 = 0;
        for (int i = 0; i <= 9; i++) {
            a1 = i;
            for (int j = 0; j <= 9; j++) {
                a2 = j;
                for (int k = 0; k <= 9; k++) {
                    a3 = k;

                    // 1ac
                    int oneAcross = (100 * a1) + (10 * a2) + a3;
                    if (!isPrime(oneAcross)) {
                        // 1dn
                        b3 = a2;
                        int threeAcross = (b2 * 10) + b3;
                        int squaresum1ac = (int) Math.pow(a1 + a2 + a3, 2);
                        int oneDown = squaresum1ac + threeAcross;
                        if (String.valueOf(oneDown).length() == 3) {
                            int digit2 = getDigitAtPosition(oneDown, 1);
                            int digit3 = getDigitAtPosition(oneDown, 2);
                            b1 = digit2;
                            c1 = digit3;
                            for (int l = 0; l <= 9; l++) {
                                c3 = l;
                                for (int i1 : twoDigitTriangular) {
                                    int fourDown = (b3 * 10) + c3;
                                    if (fourDown == i1) {
                                        int fiveac = oneDown + threeAcross + fourDown;
                                        int middleDigit = getDigitAtPosition(fiveac, 1);
                                        c2 = middleDigit;

                                        printPossibleSolution(new int[][] {{a1,a2,a3}, {b1,b2,b3}, {c1,c2,c3}});
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        /*wtf();
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        r();
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        System.out.println(" dsoindfijgnsdfgnkfjdngd");
        artem();;*/
        //s();
        System.out.println("q: " + convertToLocation(new int[][] {{3,7,3},{6,1,2},{2,7,7}}));
        System.out.println("s: " + convertToLocation(new int[][] {{2,7,5},{2,2,7},{3,2,8}}));
        System.out.println("p: " + convertToLocation(new int[][] {{3,6,1},{6,3,2},{4,6,8}}));
        System.out.println("r: " + convertToLocation(new int[][] {{3,8,1},{5,4,5},{4,5,8}}));

    }
}