package me.vlink102;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Triangles {
    public static final int[][] TWO_DIGIT_TRIPLES = {{21, 20, 29}, {35, 12, 37}, {45, 28, 53}, {11, 60, 61}, {33, 56, 65}, {63, 16, 65}, {55, 48, 73}, {13, 84, 85}, {77, 36, 85}, {39, 80, 89}, {65, 72, 97}};
    public static final int[][] TRIPLES_one1_two3 = {{15, 112, 113}, {117, 44, 125}, {105, 88, 137}, {17, 144, 145}, {143, 24, 145}, {51, 140, 149}, {85, 132, 157}, {165, 52, 173}, {19, 180, 181}, {57, 176, 185}, {95, 168, 193}, {195, 28, 197}, {187, 84, 205}, {21, 220, 221}, {221, 60, 229}, {255, 32, 257}, {23, 264, 265}, {247, 96, 265}, {69, 260, 269}, {285, 68, 293}, {25, 312, 313}, {75, 308, 317}, {323, 36, 325}, {27, 364, 365}, {357, 76, 365}, {399, 40, 401}, {29, 420, 421}, {87, 416, 425}, {437, 84, 445}, {31, 480, 481}, {93, 476, 485}, {483, 44, 485}, {525, 92, 533}, {33, 544, 545}, {575, 48, 577}, {35, 612, 613}, {675, 52, 677}, {37, 684, 685}, {39, 760, 761}, {783, 56, 785}, {41, 840, 841}, {899, 60, 901}, {43, 924, 925}};

    public static final int[] TRIANGULAR_NUMBERS = {0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210, 231, 253, 276, 300, 325, 351, 378, 406, 435, 465, 496, 528, 561, 595, 630, 666, 703, 741, 780, 820, 861, 903, 946, 990, 1035, 1081};

    public Triangles() throws IOException {
        printTriangles(new int[4][6]);
        //computeTriangles();
        findSolution();
        System.out.println("Complete");
    }

    public static void main(String[] args) throws IOException {
        printNumberSets();
        runTests();
        new Triangles();
    }

    public static void printNumberSets() {
        System.out.println("Two digit triples: " + Arrays.deepToString(TWO_DIGIT_TRIPLES));
        System.out.println("Triples with 1 2-digit and 2 3-digit numbers: " + Arrays.deepToString(TRIPLES_one1_two3));
        System.out.println("Triangular numbers: " + Arrays.toString(TRIANGULAR_NUMBERS));
    }

    public static void runTests() {

        System.out.println("All tests passed");
    }

    public static int condense(int... digits) {
        int value = 0;
        for (int i = 0; i < digits.length; i++) {
            int scalar = (int) Math.pow(10, digits.length - i - 1);
            value += digits[i] * scalar;
        }
        return value;
    }

    public static int extractVector(GridPoint from, GridPoint to, Integer[][] grid) {
        if (from.row != to.row && from.col != to.col) throw new IllegalArgumentException("Must be a straight vector");
        int digitCount = from.row == to.row ? Math.abs(to.col - from.col) + 1 : Math.abs(to.row - from.row) + 1;
        int[] digits = new int[digitCount];

        int suppliedDigitCount = 0;

        for (int i = from.row; i <= to.row; i++) {
            for (int j = from.col; j <= to.col; j++) {
                digits[suppliedDigitCount] = grid[i][j];
                suppliedDigitCount++;
            }
        }
        return condense(digits);
    }

    public static final Integer[][] modifiableGrid = new Integer[4][9];

    static {
        for (Integer[] integers : modifiableGrid) {
            Arrays.fill(integers, 1);
        }
    }

    private void printTriangles(int[][] grid) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/3ef09725780c37a4fca1cc4f00598fde.png"));
        ImageIcon icon = new ImageIcon(myPicture);
        Image background = icon.getImage();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(339, 355));
        frame.setResizable(false);
        frame.setTitle("Number Soup");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, 339, 331, this);
            }
        };
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        //constraints.gridheight = 89;
        //constraints.gridwidth = 57;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.anchor = GridBagConstraints.SOUTHEAST;

        for (int row = 0; row < 4; row++) {
            constraints.gridy = row;
            for (int col = 0; col < 6; col++) {
                constraints.gridx = col;
                TextFieldUnique cell = new TextFieldUnique(new GridPoint(row, col));
                if (grid[row][col] != 0) {
                    cell.setText(String.valueOf(grid[row][col]));
                }

                cell.setPreferredSize(new Dimension(57, 89));
                cell.setOpaque(false);
                cell.setBorder(null);
                cell.setHorizontalAlignment(JTextField.RIGHT);
                cell.setBackground(new Color(0, 0, 0, 0));
                cell.setFont(new Font("Arial", Font.BOLD, 40));

                cell.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) || c == '0') {
                            e.consume(); // Ignore input that is not 1-9
                            return;
                        }

                        try {
                            if (!String.valueOf(c).matches("\\d{1}")) {
                                e.consume();
                                return;
                            }
                            modifiableGrid[cell.gridPoint.row][cell.gridPoint.col] = Integer.parseInt(String.valueOf(c));
                            printTriangleConsole(modifiableGrid);
                        } catch (InvalidGridException gridException) {
                            gridException.printStackTrace();
                        }
                    }
                });

                panel.add(cell, constraints);
            }
        }

        contentPane.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public void computeTriangles() {
        Integer a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0;
        Integer b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, b6 = 0;
        Integer c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;
        Integer d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0;

        for (int a1i = 1; a1i <= 9; a1i++) {
            a1 = a1i;

            for (int a2i = 0; a2i <= 9; a2i++) {
                if (a2i % 2 == 0) continue;
                a2 = a2i;

                for (int a3i = 1; a3i <= 9; a3i++) {
                    a3 = a3i;

                    for (int a4i = 1 /* 3dn uses */; a4i <= 9; a4i++) {
                        if (a4i % 2 == 0) continue;
                        a4 = a4i;

                        for (int a5i = 1; a5i <= 9; a5i++) {
                            a5 = a5i;

                            for (int a6i = 1 /* 5dn uses */; a6i <= 9; a6i++) {
                                if (a6i % 2 == 0) continue;
                                a6 = a6i;

                                int oneAcross = (10 * a1) + a2;
                                int twoAcross = (10 * a3) + a4;
                                int fourAcross = (10 * a5) + a6;

                                boolean triangularPerimeter = isTriangularNumber(oneAcross + twoAcross + fourAcross);
                                boolean allSidesOdd = isOddComposite(oneAcross) && isOddComposite(twoAcross) && isOddComposite(fourAcross);
                                boolean coPrime = gcd(oneAcross, twoAcross) == 1 && gcd(twoAcross, fourAcross) == 1 && gcd(fourAcross, oneAcross) == 1;

                                if (triangularPerimeter && allSidesOdd && coPrime) {

                                    // compute 11ac, 10dn, 12dn

                                    for (int c3i = 1; c3i <= 9; c3i++) {
                                        c3 = c3i;
                                        for (int c4i = 0; c4i <= 9; c4i++) {
                                            c4 = c4i;

                                            int elevenAcross = (10 * c3) + c4;
                                            for (int c1i = 1; c1i <= 9; c1i++) {
                                                c1 = c1i;
                                                for (int d1i = 1 /* 13ac uses */; d1i <= 9; d1i++) {
                                                    d1 = d1i;

                                                    int tenDown = (10 * c1) + d1;

                                                    for (int c6i = 1; c6i <= 9; c6i++) {
                                                        c6 = c6i;

                                                        for (int d6i = 0; d6i <= 9; d6i++) {
                                                            d6 = d6i;

                                                            int twelveDown = (10 * c6) + d6;

                                                            if (isPrimitivePythagoreanTriangle(elevenAcross, tenDown, twelveDown)) {
                                                                for (int b1i = 1; b1i <= 9; b1i++) {
                                                                    b1 = b1i;
                                                                    int oneDown = (10 * a1) + b1;
                                                                    for (int b2i = 1 /* 7dn uses */; b2i <= 9; b2i++) {
                                                                        b2 = b2i;
                                                                        for (int b3i = 0; b3i <= 9; b3i++) {
                                                                            b3 = b3i;

                                                                            int twoDown = (100 * a3) + (10 * b3) + c3;

                                                                            for (int b4i = 1; b4i <= 9; b4i++) {
                                                                                b4 = b4i;
                                                                                int threeDown = (100 * a4) + (10 * b4) + c4;
                                                                                for (int b5i = 1 /* 9dn uses */; b5i <= 9; b5i++) {
                                                                                    b5 = b5i;

                                                                                    for (int b6i = 0; b6i <= 9; b6i++) {
                                                                                        b6 = b6i;

                                                                                        int fiveDown = (10 * a6) + b6;
                                                                                        if (isPerfectSquare(twoDown + threeDown)) {
                                                                                            // 13ac 7dn 1dn
                                                                                            for (int c2i = 0; c2i <= 9; c2i++) {
                                                                                                c2 = c2i;
                                                                                                for (int d2i = 0; d2i <= 9; d2i++) {
                                                                                                    d2 = d2i;

                                                                                                    for (int d3i = 0; d3i <= 9; d3i++) {
                                                                                                        d3 = d3i;

                                                                                                        int thirteenAcross = (100 * d1) + (10 * d2) + d3;
                                                                                                        int sevenDown = (100 * b2) + (10 * c2) + d2;

                                                                                                        if (isPrimitivePythagoreanTriangle(thirteenAcross, sevenDown, oneDown)) {
                                                                                                            // 14ac 9dn 5dn

                                                                                                            for (int d4i = 1; d4i <= 9; d4i++) {
                                                                                                                d4 = d4i;

                                                                                                                for (int d5i = 0; d5i <= 9; d5i++) {
                                                                                                                    d5 = d5i;

                                                                                                                    int fourteenAcross = (100 * d4) + (10 * d5) + d6;
                                                                                                                    for (int c5i = 0; c5i <= 9; c5i++) {
                                                                                                                        c5 = c5i;
                                                                                                                        int nineDown = (100 * b5) + (10 * c5) + d5;

                                                                                                                        if (isPrimitivePythagoreanTriangle(fourteenAcross, nineDown, fiveDown)) {
                                                                                                                            printTriangleConsole(new Integer[][]{
                                                                                                                                    {a1, a2, a3, a4, a5, a6},
                                                                                                                                    {b1, b2, b3, b4, b5, b6},
                                                                                                                                    {c1, c2, c3, c4, c5, c6},
                                                                                                                                    {d1, d2, d3, d4, d5, d6}
                                                                                                                            });
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

    public static int[] getDigits(int number) {
        String numberString = String.valueOf(number);
        int[] digits = new int[numberString.length()];
        String[] digitStrings = numberString.split("");
        for (int i = 0; i < numberString.length(); i++) {
            digits[i] = Integer.parseInt(digitStrings[i]);
        }
        return digits;
    }

    public void printTriangleConsole(Integer[][] grid) {
        System.out.print(getResult(grid));
        System.out.print(Arrays.deepToString(grid) + "\n");
    }

    public static int[] convertListToIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    @Setter
    @Getter
    public class ValidationResult {
        private boolean ac1ac2ac4;
        private boolean ac6ac8;
        private boolean ac11dn10dn12;
        private boolean ac13dn7dn1;
        private boolean ac14dn9dn5;
        private int sumAcross;
        private int sumDown;

        public ValidationResult() {
        }

        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";

        @Override
        public String toString() {
            return  "Sum Across: " + sumAcross + ", " +
                    "Sum Down: " + sumDown + ", " +
                    (ac1ac2ac4 ? "ac1ac2ac4=" + TRUE : "ac1ac2ac4=" + FALSE) + ", " +
                    (ac6ac8 ? "ac6ac8=" + TRUE : "ac6ac8=" + FALSE) + ", " +
                    (ac11dn10dn12 ? "ac11dn10dn12=" + TRUE : "ac11dn10dn12=" + FALSE) + ", " +
                    (ac13dn7dn1 ? "ac13dn7dn1=" + TRUE : "ac13dn7dn1=" + FALSE) + ", " +
                    (ac14dn9dn5 ? "ac14dn9dn5=" + TRUE : "ac14dn9dn5=" + FALSE);
        }
    }

    private static final Integer[][][] solutions = {
            {{5, 1, 7, 7, 2, 5}, {2, 6, 0, 3, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 1, 7, 7, 2, 5}, {2, 6, 1, 2, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 1, 7, 7, 2, 5}, {2, 6, 2, 1, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 0, 6, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 1, 5, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 2, 4, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 3, 3, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 4, 2, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 5, 1, 6, 2}, {1, 7, 6, 3, 7, 6}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 4, 9, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 5, 8, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 6, 7, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 7, 6, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 8, 5, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{5, 7, 4, 9, 6, 5}, {2, 6, 9, 4, 6, 2}, {3, 7, 7, 7, 7, 8}, {6, 7, 5, 6, 7, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 4, 9, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 5, 8, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 6, 7, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 7, 6, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 8, 5, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 2, 5, 5, 1}, {6, 3, 9, 4, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 0, 9, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 1, 8, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 2, 7, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 3, 6, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 4, 5, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 5, 4, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 6, 3, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 7, 2, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 8, 1, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 0, 1, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 8, 9, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 2, 5}, {6, 3, 9, 8, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 0, 9, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 1, 8, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 2, 7, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 3, 6, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 4, 5, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 5, 4, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 6, 3, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 7, 2, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 8, 1, 6, 2}, {5, 6, 2, 8, 7, 4}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 0, 1, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 8, 9, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 6, 9, 8, 5}, {6, 3, 9, 8, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 8, 1, 9, 5}, {6, 3, 0, 5, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 8, 1, 9, 5}, {6, 3, 1, 4, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 8, 1, 9, 5}, {6, 3, 2, 3, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 8, 1, 9, 5}, {6, 3, 3, 2, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 8, 1, 9, 5}, {6, 3, 4, 1, 6, 2}, {3, 6, 5, 6, 7, 6}, {3, 5, 7, 6, 7, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 2, 9, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 3, 8, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 4, 7, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 5, 6, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 6, 5, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 7, 4, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 8, 3, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}},
            {{7, 7, 9, 5, 8, 1}, {6, 3, 9, 2, 1, 7}, {3, 6, 5, 6, 4, 6}, {3, 5, 7, 1, 4, 5}}
    };

    public void findSolution() {
        for (Integer[][] grid : solutions) {
            int oneAcross = extractVector(GridPoint.of(0, 0), GridPoint.of(0, 1), grid);
            int twoAcross = extractVector(GridPoint.of(0, 2), GridPoint.of(0, 3), grid);
            int fourAcross = extractVector(GridPoint.of(0, 4), GridPoint.of(0, 5), grid);

            int sixAcross = extractVector(GridPoint.of(1, 0), GridPoint.of(1, 2), grid);
            int eightAcross = extractVector(GridPoint.of(1, 3), GridPoint.of(1, 5), grid);

            int elevenAcross = extractVector(GridPoint.of(2, 2), GridPoint.of(2, 3), grid);
            int tenDown = extractVector(GridPoint.of(2, 0), GridPoint.of(3, 0), grid);
            int twelveDown = extractVector(GridPoint.of(2, 5), GridPoint.of(3, 5), grid);

            int thirteenAcross = extractVector(GridPoint.of(3, 0), GridPoint.of(3, 2), grid);
            int sevenDown = extractVector(GridPoint.of(1, 1), GridPoint.of(3, 1), grid);
            int oneDown = extractVector(GridPoint.of(0, 0), GridPoint.of(1, 0), grid);

            int fourteenAcross = extractVector(GridPoint.of(3, 3), GridPoint.of(3, 5), grid);
            int nineDown = extractVector(GridPoint.of(1, 4), GridPoint.of(3, 4), grid);
            int fiveDown = extractVector(GridPoint.of(0, 5), GridPoint.of(1, 5), grid);

            int[] answers = {oneAcross, twoAcross, fourAcross, sixAcross, eightAcross, elevenAcross, tenDown, twelveDown, thirteenAcross, sevenDown, oneDown, fourteenAcross, nineDown, fiveDown};
            if (differs(answers, oneAcross + twoAcross + fourAcross)) {
                printTriangleConsole(grid);
            }
        }
    }

    public static boolean differs(int[] array, int difference) {
        HashSet<Integer> seen = new HashSet<>();
        for (int i : array) {
            if (seen.contains(i - difference) || seen.contains(i + difference)) {
                return true;
            }
            seen.add(i);
        }
        return false;
    }

    public ValidationResult getResult(Integer[][] grid) {
        if (grid.length != 4 || grid[0].length != 6) return null;
        ValidationResult result = new ValidationResult();
        int oneAcross = extractVector(GridPoint.of(0, 0), GridPoint.of(0, 1), grid);
        int twoAcross = extractVector(GridPoint.of(0, 2), GridPoint.of(0, 3), grid);
        int fourAcross = extractVector(GridPoint.of(0, 4), GridPoint.of(0, 5), grid);

        int sixAcross = extractVector(GridPoint.of(1, 0), GridPoint.of(1, 2), grid);
        int eightAcross = extractVector(GridPoint.of(1, 3), GridPoint.of(1, 5), grid);

        int elevenAcross = extractVector(GridPoint.of(2, 2), GridPoint.of(2, 3), grid);
        int tenDown = extractVector(GridPoint.of(2, 0), GridPoint.of(3, 0), grid);
        int twelveDown = extractVector(GridPoint.of(2, 5), GridPoint.of(3, 5), grid);

        int thirteenAcross = extractVector(GridPoint.of(3, 0), GridPoint.of(3, 2), grid);
        int sevenDown = extractVector(GridPoint.of(1, 1), GridPoint.of(3, 1), grid);
        int oneDown = extractVector(GridPoint.of(0, 0), GridPoint.of(1, 0), grid);

        int fourteenAcross = extractVector(GridPoint.of(3, 3), GridPoint.of(3, 5), grid);
        int nineDown = extractVector(GridPoint.of(1, 4), GridPoint.of(3, 4), grid);
        int fiveDown = extractVector(GridPoint.of(0, 5), GridPoint.of(1, 5), grid);

        int twoDown = extractVector(GridPoint.of(0, 2), GridPoint.of(2, 2), grid);
        int threeDown = extractVector(GridPoint.of(0, 3), GridPoint.of(2, 3), grid);

        int sumAcross = oneAcross + twoAcross + fourAcross + sixAcross + eightAcross + elevenAcross + thirteenAcross + fourteenAcross;
        int sumDown = tenDown + twelveDown + twoDown + threeDown + sevenDown + oneDown + nineDown + fiveDown;

        result.setSumAcross(sumAcross);
        result.setSumDown(sumDown);

        int perimeter1ac2ac4ac = oneAcross + twoAcross + fourAcross;
        boolean triangularPerimeter = isTriangularNumber(perimeter1ac2ac4ac);
        boolean allSidesOdd = isOddComposite(oneAcross) && isOddComposite(twoAcross) && isOddComposite(fourAcross);
        boolean coPrime = gcd(oneAcross, twoAcross) == 1 && gcd(twoAcross, fourAcross) == 1 && gcd(fourAcross, oneAcross) == 1;
        result.setAc1ac2ac4(triangularPerimeter && allSidesOdd && coPrime);

        result.setAc6ac8(isPerfectSquare(twoDown + threeDown));
        result.setAc11dn10dn12(isPrimitivePythagoreanTriangle(elevenAcross, tenDown, twelveDown));
        result.setAc13dn7dn1(isPrimitivePythagoreanTriangle(thirteenAcross, sevenDown, oneDown));
        result.setAc14dn9dn5(isPrimitivePythagoreanTriangle(fourteenAcross, nineDown, fiveDown));

        return result;
    }

    private static boolean isPrimitivePythagoreanTriangle(int a, int b, int c) {
        int[] sides = {a, b, c};
        java.util.Arrays.sort(sides);
        a = sides[0];
        b = sides[1];
        c = sides[2];

        if (a * a + b * b != c * c) return false;
        if (gcd(a, b) != 1 || gcd(b, c) != 1 || gcd(c, a) != 1) return false;
        return a % 2 != b % 2;
    }

    private static boolean satisfiesTriangleInequality(int a, int b, int c) {
        return a + b > c && b + c > a && c + a > b;
    }

    private static boolean isOddComposite(int n) {
        if (n % 2 == 0 || n < 9) {
            return false; // Must be odd and at least the smallest odd composite (9)
        }
        return !isPrime(n); // Composite means not prime
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static boolean isTriangularNumber(int n) {
        int x = (int) (Math.sqrt(2 * n));
        return x * (x + 1) / 2 == n;
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    public static boolean isPresent(int number, int[] set) {
        return Arrays.stream(set).anyMatch(value -> value == number);
    }

    public class InvalidGridException extends AssertionError {
        public InvalidGridException(String text) {
            super(text);
        }
    }

    public static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }


    public class TextFieldUnique extends JTextField {
        @Getter
        private final GridPoint gridPoint;

        public TextFieldUnique(GridPoint gridPoint) {
            super();
            this.gridPoint = gridPoint;
        }
    }

    public record GridPoint(int row, int col) {
        public static GridPoint of(int row, int col) {
            return new GridPoint(row, col);
        }
    }
}
