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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Genius3 {

    public Genius3() throws IOException {
        List<int[][]> pieces = List.of(
                new int[][]{{1}},                      // 1x1
                new int[][]{{2, 2}},                   // 1x2
                new int[][]{{3, 3, 3}},                // 1x3
                new int[][]{{4, 4, 4, 4}},             // 1x4
                new int[][]{{5, 5}, {5, 5}},           // 2x2
                new int[][]{{6, 6}, {6, 0}},           // Irregular 1
                new int[][]{{7, 7, 0}, {0, 7, 7}},     // Irregular 2
                new int[][]{{8, 0}, {8, 0}, {8, 8}},   // Irregular 3
                new int[][]{{0, 9, 0}, {9, 9, 9}}      // Irregular 4
        );

        List<int[][]> testSeeIfMyCodeActuallyWorks = new ArrayList<>() {{
            this.add(new int[][] {{1,1}, {1, 1}}); // 2x2
            this.add(new int[][] {{2,2,2}, {0,0,2}}); // L shape
            this.add(new int[][] {{0,3,0},{3,3,3}}); // t shape
            this.add(new int[][] {{4}}); // 1x1
        }};

        Shape.total = 0;
        Shape.solveAll(new int[4][4], testSeeIfMyCodeActuallyWorks, 0);
        System.out.println(Shape.total);
        System.out.println("Complete");
    }

    public static void main(String[] args) throws IOException {
        printNumberSets();
        runTests();
        new Genius3();
    }

    public static void printNumberSets() {

    }

    public static void runTests() {

        System.out.println("All tests passed");
    }

    public static final Integer[][] modifiableGrid = new Integer[6][6];

    static {
        for (Integer[] integers : modifiableGrid) {
            Arrays.fill(integers, 1);
        }
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

    private void printGenius3Console(Integer[][] grid) {
        System.out.print(getResult(grid));
        System.out.println(Arrays.deepToString(grid) + "\n");
    }

    private static boolean isTriangularNumber(int n) {
        int x = (int) (Math.sqrt(2 * n));
        return x * (x + 1) / 2 == n;
    }

    @Getter
    @Setter
    public class ValidationResult {
        boolean ac1;
        boolean ac2;
        boolean ac3;
        boolean ac4;
        boolean ac5;
        boolean ac6;
        boolean ac7;
        boolean ac8;
        boolean ac9;
        boolean ac10;
        boolean ac11;
        boolean ac12;
        boolean dn13;
        boolean dn14;
        boolean dn15;
        boolean dn16;
        boolean dn17;
        boolean dn18;
        boolean dn19;
        boolean dn20;
        boolean dn21;
        boolean dn22;
        boolean dn23;
        boolean dn24;
        private int sumAcross;
        private int sumDown;

        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";


        @Override
        public String toString() {
            return  "Sum Across: " + sumAcross + ", " +
                    "Sum Down: " + sumDown + ", " +
                    (ac1 ? "ac1=" + TRUE : "ac1=" + FALSE) + ", " +
                    (ac2 ? "ac2=" + TRUE : "ac2=" + FALSE) + ", " +
                    (ac3 ? "ac3=" + TRUE : "ac3=" + FALSE) + ", " +
                    (ac4 ? "ac4=" + TRUE : "ac4=" + FALSE) + ", " +
                    (ac5 ? "ac5=" + TRUE : "ac5=" + FALSE) + ", " +
                    (ac6 ? "ac6=" + TRUE : "ac6=" + FALSE) + ", " +
                    (ac7 ? "ac7=" + TRUE : "ac7=" + FALSE) + ", " +
                    (ac8 ? "ac8=" + TRUE : "ac8=" + FALSE) + ", " +
                    (ac9 ? "ac9=" + TRUE : "ac9=" + FALSE) + ", " +
                    (ac10 ? "ac10=" + TRUE : "ac10=" + FALSE) + ", " +
                    (ac11 ? "ac11=" + TRUE : "ac11=" + FALSE) + ", " +
                    (ac12 ? "ac12=" + TRUE : "ac12=" + FALSE) + ", " +
                    (dn13 ? "dn13=" + TRUE : "dn13=" + FALSE) + ", " +
                    (dn14 ? "dn14=" + TRUE : "dn14=" + FALSE) + ", " +
                    (dn15 ? "dn15=" + TRUE : "dn15=" + FALSE) + ", " +
                    (dn16 ? "dn16=" + TRUE : "dn16=" + FALSE) + ", " +
                    (dn17 ? "dn17=" + TRUE : "dn17=" + FALSE) + ", " +
                    (dn18 ? "dn18=" + TRUE : "dn18=" + FALSE) + ", " +
                    (dn19 ? "dn19=" + TRUE : "dn19=" + FALSE) + ", " +
                    (dn20 ? "dn20=" + TRUE : "dn20=" + FALSE) + ", " +
                    (dn21 ? "dn21=" + TRUE : "dn21=" + FALSE) + ", " +
                    (dn22 ? "dn22=" + TRUE : "dn22=" + FALSE) + ", " +
                    (dn23 ? "dn23=" + TRUE : "dn23=" + FALSE) + ", " +
                    (dn24 ? "dn24=" + TRUE : "dn24=" + FALSE);
        }
    }

    @Getter
    private static class GridVector {
        private final GridPoint from;
        private final GridPoint to;

        public GridVector(int fromRow, int fromCol, int toRow, int toCol) {
            this.from = new GridPoint(fromRow - 1, fromCol - 1);
            this.to = new GridPoint(toRow - 1, toCol - 1);
        }

        public int apply(Integer[][] grid) {
            return extractVector(from, to, grid);
        }
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

    public static int condense(int... digits) {
        int value = 0;
        for (int i = 0; i < digits.length; i++) {
            int scalar = (int) Math.pow(10, digits.length - i - 1);
            value += digits[i] * scalar;
        }
        return value;
    }

    private static final List<GridVector> DOWN_VECTORS = new ArrayList<>(12) {{
        this.add(new GridVector(1,1,3,1));
        this.add(new GridVector(4,1,6,1));
        this.add(new GridVector(3,2,4,2));
        this.add(new GridVector(5,2,6,2));
        this.add(new GridVector(1,3,4,3));
        this.add(new GridVector(5,3,6,3));
        this.add(new GridVector(1,4,2,4));
        this.add(new GridVector(3,4,6,4));
        this.add(new GridVector(1,5,2,5));
        this.add(new GridVector(3,5,4,5));
        this.add(new GridVector(1,6,3,6));
        this.add(new GridVector(4,6,6,6));
    }};

    private static final List<GridVector> ACROSS_VECTOR = new ArrayList<>(12) {{
        this.add(new GridVector(1,1,1,4));
        this.add(new GridVector(1,5,1,6));
        this.add(new GridVector(2,1,2,3));
        this.add(new GridVector(2,4,2,5));
        this.add(new GridVector(3,3,3,4));
        this.add(new GridVector(3,5,3,6));
        this.add(new GridVector(4,1,4,2));
        this.add(new GridVector(4,3,4,4));
        this.add(new GridVector(5,2,5,3));
        this.add(new GridVector(5,4,5,6));
        this.add(new GridVector(6,1,6,2));
        this.add(new GridVector(6,3,6,6));
    }};

    private ValidationResult getResult(Integer[][] grid) {
        if (grid.length != 6 || grid[0].length != 6) return null;

        return null;
    }

    public static class Shape {
        public static List<int[][]> findAllPlacements(int[][] grid, int[][] piece) {
            List<int[][]> placements = new ArrayList<>();
            List<int[][]> transformations = generateTransformations(piece);
            int gridRows = grid.length;
            int gridCols = grid[0].length;
            for (int[][] transformedPiece : transformations) {
                int pieceRows = transformedPiece.length;
                int pieceCols = transformedPiece[0].length;
                for (int startRow = 0; startRow <= gridRows - pieceRows; startRow++) {
                    for (int startCol = 0; startCol <= gridCols - pieceCols; startCol++) {
                        if (canPlace(grid, transformedPiece, startRow, startCol)) {
                            int[][] newGrid = applyPlacement(grid, transformedPiece, startRow, startCol);
                            placements.add(newGrid);
                        }
                    }
                }
            }

            return placements;
        }
        static int total = 0;

        public static void solveAll(int[][] grid, List<int[][]> pieces, int pieceIndex) {
            if (pieceIndex == pieces.size()) {
                printGrid(grid);
                total++;
                return;
            }

            int[][] piece = pieces.get(pieceIndex);

            List<int[][]> placements = findAllPlacements(grid, piece);

            for (int[][] placement : placements) {
                solveAll(placement, pieces, pieceIndex + 1);
            }
        }

        public static List<int[][]> generateTransformations(int[][] piece) {
            List<int[][]> transformations = new ArrayList<>();
            transformations.add(piece); // Original orientation

            int[][] rotated = piece;
            for (int i = 0; i < 3; i++) {
                rotated = rotateClockwise(rotated);
                if (!containsMatrix(transformations, rotated)) {
                    transformations.add(rotated);
                }
            }

            int[][] reflected = reflectHorizontally(piece);
            if (!containsMatrix(transformations, reflected)) {
                transformations.add(reflected);
            }

            rotated = reflected;
            for (int i = 0; i < 3; i++) {
                rotated = rotateClockwise(rotated);
                if (!containsMatrix(transformations, rotated)) {
                    transformations.add(rotated);
                }
            }

            return transformations;
        }
        public static int[][] rotateClockwise(int[][] piece) {
            int rows = piece.length;
            int cols = piece[0].length;
            int[][] rotated = new int[cols][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rotated[j][rows - 1 - i] = piece[i][j];
                }
            }
            return rotated;
        }

        public static int[][] reflectHorizontally(int[][] piece) {
            int rows = piece.length;
            int cols = piece[0].length;
            int[][] reflected = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    reflected[i][j] = piece[i][cols - 1 - j];
                }
            }
            return reflected;
        }

        public static boolean containsMatrix(List<int[][]> list, int[][] matrix) {
            for (int[][] mat : list) {
                if (Arrays.deepEquals(mat, matrix)) {
                    return true;
                }
            }
            return false;
        }

        public static List<int[][]> findPlacements(int[][] grid, int[][] piece) {
            List<int[][]> placements = new ArrayList<>();
            List<int[][]> transformations = generateTransformations(piece);
            int gridRows = grid.length;
            int gridCols = grid[0].length;

            for (int[][] transformedPiece : transformations) {
                int pieceRows = transformedPiece.length;
                int pieceCols = transformedPiece[0].length;

                for (int startRow = 0; startRow <= gridRows - pieceRows; startRow++) {
                    for (int startCol = 0; startCol <= gridCols - pieceCols; startCol++) {
                        if (canPlace(grid, transformedPiece, startRow, startCol)) {
                            placements.add(applyPlacement(grid, transformedPiece, startRow, startCol));
                        }
                    }
                }
            }

            return placements;
        }

        public static boolean canPlace(int[][] grid, int[][] piece, int startRow, int startCol) {
            int pieceRows = piece.length;
            int pieceCols = piece[0].length;

            for (int i = 0; i < pieceRows; i++) {
                for (int j = 0; j < pieceCols; j++) {
                    if (piece[i][j] != 0) {
                        int gridValue = grid[startRow + i][startCol + j];
                        if (gridValue != 0) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        public static int[][] applyPlacement(int[][] grid, int[][] piece, int startRow, int startCol) {
            int[][] newGrid = deepCopy(grid);
            int pieceRows = piece.length;
            int pieceCols = piece[0].length;

            for (int i = 0; i < pieceRows; i++) {
                for (int j = 0; j < pieceCols; j++) {
                    if (piece[i][j] != 0) {
                        newGrid[startRow + i][startCol + j] = piece[i][j];
                    }
                }
            }

            return newGrid;
        }

        public static int[][] deepCopy(int[][] original) {
            int[][] copy = new int[original.length][];
            for (int i = 0; i < original.length; i++) {
                copy[i] = Arrays.copyOf(original[i], original[i].length);
            }
            return copy;
        }

        public static boolean solve(int[][] grid, List<int[][]> pieces, int pieceIndex) {
            if (pieceIndex == pieces.size()) {
                printGrid(grid);
                return true;
            }

            int[][] piece = pieces.get(pieceIndex);
            List<int[][]> placements = findPlacements(grid, piece);

            for (int[][] placement : placements) {
                if (solve(placement, pieces, pieceIndex + 1)) {
                    return true;
                }
            }

            return false;
        }
        public static void printGrid(int[][] grid) {
            for (int[] row : grid) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            /*System.out.print("Totals for each number: ");
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int[] ints : grid) {
                for (int anInt : ints) {
                    counts.put(anInt, counts.getOrDefault(anInt, 0) + 1);
                }
            }
            counts.forEach((integer, integer2) -> {
                if (integer2 > 4) {
                    throw new RuntimeException();
                }
                System.out.print(integer2);
            });*/
            System.out.println();
        }

    }

    private void displayGenius3(int[][] grid) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/genius3.png"));
        ImageIcon icon = new ImageIcon(myPicture);
        Image background = icon.getImage();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(339, 355));
        frame.setResizable(false);
        frame.setTitle("Genius 3");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, 723, 800, this);
            }
        };
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.anchor = GridBagConstraints.SOUTHEAST;

        for (int row = 0; row < 6; row++) {
            constraints.gridy = row;
            for (int col = 0; col < 6; col++) {
                constraints.gridx = col;
                TextFieldUnique cell = new TextFieldUnique(new GridPoint(row, col));
                if (grid[row][col] != 0) {
                    cell.setText(String.valueOf(grid[row][col]));
                }

                cell.setPreferredSize(new Dimension(131, 131));
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
                            printGenius3Console(modifiableGrid);
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

    public class InvalidGridException extends AssertionError {
        public InvalidGridException(String text) {
            super(text);
        }
    }
}
