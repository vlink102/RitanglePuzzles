package me.vlink102;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Genius3 {

    public Genius3() throws IOException {
        List<int[][]> pieces = List.of(
                new int[][]{{4, 4, 4, 4}},             // 1x4
                new int[][]{{1}},                      // 1x1
                new int[][]{{2, 2}},                   // 1x2
                new int[][]{{3, 3, 3}},                // 1x3
                new int[][]{{5, 5}, {5, 5}},           // 2x2
                new int[][]{{6, 6}, {6, 0}},           // Irregular 1
                new int[][]{{7, 7, 0}, {0, 7, 7}},     // Irregular 2
                new int[][]{{8, 0}, {8, 0}, {8, 8}},   // Irregular 3
                new int[][]{{0, 9, 0}, {9, 9, 9}}      // Irregular 4
        );

        List<int[][]> testSeeIfMyCodeActuallyWorks = new ArrayList<>() {{
            this.add(new int[][] {{1,1}, {1, 0}}); //
        }};

        Shape.total = 0;
        Shape.solveAll(new int[6][6], pieces, 0, new int[][] {
                {1, 0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 0, 0}
        });

        HashMap<Integer, Integer> test = new HashMap<>() {{
            this.put(1,4);
            this.put(2,6);
            this.put(0,7);
        }};
        //Shape.solveAll(new int[2][2], testSeeIfMyCodeActuallyWorks, 0, new int[][] {{0,0},{1,0}});
        System.out.println(Shape.total);
        System.out.println("Complete");
    }

    public static boolean areContentsEqual(List<Integer> list, Collection<Integer> collection) {
        if (list.size() != collection.size()) {
            return false;
        }
        Map<Integer, Long> listFrequencyMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Long> collectionFrequencyMap = collection.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return listFrequencyMap.equals(collectionFrequencyMap);
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
    public static boolean updateCounts(int[] counts, int... values) {
        for (int value : values) {
            int[] digits = getDigits(value);
            for (int digit : digits) {
                counts[digit] ++;
                if (counts[digit] > 4) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return true if digits are not separated
     */
    public static boolean validateDigitSeparation(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<Integer> checkedDigits = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int digit = grid[i][j];
                if (digit != 0 && !visited[i][j] && !checkedDigits.contains(digit)) {
                    if (!isConnected(grid, visited, i, j, digit)) return false;
                    checkedDigits.add(digit);
                }
            }
        }
        return true;
    }

    private static boolean isConnected(int[][] grid, boolean[][] visited, int row, int col, int target) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] localVisited = new boolean[rows][cols];
        dfs(grid, localVisited, row, col, target);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == target && !localVisited[i][j]) {
                    return false;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (localVisited[i][j]) {
                    visited[i][j] = true;
                }
            }
        }

        return true;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int row, int col, int target) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (visited[row][col] || grid[row][col] != target) return;
        visited[row][col] = true;
        dfs(grid, visited, row - 1, col, target);
        dfs(grid, visited, row + 1, col, target);
        dfs(grid, visited, row, col - 1, target);
        dfs(grid, visited, row, col + 1, target);
    }

    public enum ValidationState {
        PASSED,
        FAILED,
        UNKNOWN
    }

    @Getter
    @Setter
    public static class Genius3Validator {
        ValidationState validDigitSeparation = null;
        ValidationState countsValid = null;
        ValidationState containsCorrectShapeSizes = null;
        ValidationState gridIsClueValid = null;
        ValidationState gridSize = null;
        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";

        public Genius3Validator() {
        }

        @Override
        public String toString() {
            return (validDigitSeparation == null ? "validDigitSeparation=UNKNOWN" : (validDigitSeparation == ValidationState.PASSED ? "validDigitSeparation=" + TRUE : "validDigitSeparation=" + FALSE)) + ", " +
                    (countsValid == null ? "countsValid=UNKNOWN" : (countsValid == ValidationState.PASSED ? "countsValid=" + TRUE : "countsValid=" + FALSE)) + ", " +
                    (containsCorrectShapeSizes == null ? "containsCorrectShapeSizes=UNKNOWN" : (containsCorrectShapeSizes == ValidationState.PASSED ? "containsCorrectShapeSizes=" + TRUE : "containsCorrectShapeSizes=" + FALSE)) + ", " +
                    (gridIsClueValid == null ? "gridIsClueValid=UNKNOWN" : (gridIsClueValid == ValidationState.PASSED ? "gridIsClueValid=" + TRUE : "gridIsClueValid=" + FALSE)) + ", " +
                    (gridSize == null ? "gridSize=UNKNOWN" : (gridSize == ValidationState.PASSED ? "gridSize=" + TRUE : "gridSize=" + FALSE))
                    ;
        }
    }

    public static Genius3Validator validateGenius3(int[][] grid) {
        Genius3Validator validator = new Genius3Validator();
        validator.gridSize = grid.length == 6 && grid[0].length == 6 ? ValidationState.PASSED : ValidationState.FAILED;
        validator.validDigitSeparation = validateDigitSeparation(grid) ? ValidationState.PASSED : ValidationState.FAILED;

        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int[] ints : grid) {
            for (int anInt : ints) {
                counts.put(anInt, counts.getOrDefault(anInt, 0) + 1);
            }
        }
        validator.countsValid = counts.get(0) == 7  ? ValidationState.PASSED : ValidationState.FAILED;;
        validator.containsCorrectShapeSizes = (areContentsEqual(List.of(1, 2, 3, 3, 4, 4, 4, 4, 4, 7), counts.values())) ? ValidationState.PASSED : ValidationState.FAILED;

        List<List<Integer>> ac2 = generatePermutations(List.of(0,1,2,3,4,5,6,7));
        List<List<Integer>> ac3 = generatePermutations(List.of(0,1));
        List<List<Integer>> ac4 = generatePermutations(List.of(0,1));
        List<List<Integer>> dn2 = generatePermutations(List.of(0,1,2,3,4,5));
        List<List<Integer>> dn3 = generatePermutations(List.of(0,1,2,3));
        List<List<Integer>> dn4 = generatePermutations(List.of(0,1));

        System.out.println(ac2.size() + "*" + ac3.size() + "*" + ac4.size() + "*" + dn2.size() + "*" + dn3.size() + "*" + dn4.size());
        int permutations = 0;

        int[] counter = new int[10];

        for (List<Integer> across2Permutations : ac2) {
            List<GridVector> ac2Vectors = fromPermutation(across2Permutations, ACROSS_2_VECTORS);
            int oneAcross = extractVector(ac2Vectors.get(0).from, ac2Vectors.get(0).to, grid);
            int twoAcross = extractVector(ac2Vectors.get(1).from, ac2Vectors.get(1).to, grid);
            int threeAcross = extractVector(ac2Vectors.get(2).from, ac2Vectors.get(2).to, grid);
            int fourAcross = extractVector(ac2Vectors.get(3).from, ac2Vectors.get(3).to, grid);
            int fiveAcross = extractVector(ac2Vectors.get(4).from, ac2Vectors.get(4).to, grid);
            int sixAcross = extractVector(ac2Vectors.get(5).from, ac2Vectors.get(5).to, grid);
            int sevenAcross = extractVector(ac2Vectors.get(6).from, ac2Vectors.get(6).to, grid);
            int eightAcross = extractVector(ac2Vectors.get(7).from, ac2Vectors.get(7).to, grid);
            if (!updateCounts(counter, oneAcross, twoAcross, threeAcross, fourAcross, fiveAcross, sixAcross, sevenAcross, eightAcross)) {
                continue;
            }

            for (List<Integer> across3Permutations : ac3) {
                List<GridVector> ac3Vectors = fromPermutation(across3Permutations, ACROSS_3_VECTORS);
                int nineAcross = extractVector(ac3Vectors.get(0).from, ac3Vectors.get(0).to, grid);
                int tenAcross = extractVector(ac3Vectors.get(1).from, ac3Vectors.get(1).to, grid);
                if (!updateCounts(counter, nineAcross, tenAcross)) continue;

                for (List<Integer> across4Permutations : ac4) {
                    List<GridVector> ac4Vectors = fromPermutation(across4Permutations, ACROSS_4_VECTORS);
                    int elevenAcross = extractVector(ac4Vectors.get(0).from, ac4Vectors.get(0).to, grid);
                    int twelveAcross = extractVector(ac4Vectors.get(1).from, ac4Vectors.get(1).to, grid);
                    if (!updateCounts(counter, elevenAcross, twelveAcross)) continue;

                    for (List<Integer> down2Permutations : dn2) {
                        List<GridVector> dn2Vectors = fromPermutation(down2Permutations, DOWN_2_VECTORS);

                        int thirteenDown = extractVector(dn2Vectors.get(0).from, dn2Vectors.get(0).to, grid);
                        int fourteenDown = extractVector(dn2Vectors.get(1).from, dn2Vectors.get(1).to, grid);
                        int fifteenDown = extractVector(dn2Vectors.get(2).from, dn2Vectors.get(2).to, grid);
                        int sixteenDown = extractVector(dn2Vectors.get(3).from, dn2Vectors.get(3).to, grid);
                        int seventeenDown = extractVector(dn2Vectors.get(4).from, dn2Vectors.get(4).to, grid);
                        int eighteenDown = extractVector(dn2Vectors.get(5).from, dn2Vectors.get(5).to, grid);
                        if (!updateCounts(counter, thirteenDown, fourteenDown, fifteenDown, sixteenDown, seventeenDown, eighteenDown)) continue;

                        for (List<Integer> down3Permutations : dn3) {
                            List<GridVector> dn3Vectors = fromPermutation(down3Permutations, DOWN_3_VECTORS);
                            int nineteenDown = extractVector(dn3Vectors.get(0).from, dn3Vectors.get(0).to, grid);
                            int twentyDown = extractVector(dn3Vectors.get(1).from, dn3Vectors.get(1).to, grid);
                            int twentyOneDown = extractVector(dn3Vectors.get(2).from, dn3Vectors.get(2).to, grid);
                            int twentyTwoDown = extractVector(dn3Vectors.get(3).from, dn3Vectors.get(3).to, grid);

                            if (!updateCounts(counter, nineteenDown, twentyDown, twentyOneDown, twentyTwoDown)) continue;

                            for (List<Integer> down4Permutations : dn4) {
                                List<GridVector> dn4Vectors = fromPermutation(down4Permutations, DOWN_4_VECTORS);
                                int twentyThreeDown = extractVector(dn4Vectors.get(0).from, dn4Vectors.get(0).to, grid);
                                int twentyFourDown = extractVector(dn4Vectors.get(1).from, dn4Vectors.get(1).to, grid);
                                if (!updateCounts(counter, twentyThreeDown, twentyFourDown)) continue;

                                permutations ++;
                                validator.gridIsClueValid = validateGrid(oneAcross, twoAcross, threeAcross, fourAcross, fiveAcross, sixAcross, sevenAcross, eightAcross, nineAcross, tenAcross, elevenAcross, twelveAcross, thirteenDown, fourteenDown, fifteenDown, sixteenDown, seventeenDown, eighteenDown, nineteenDown, twentyDown, twentyOneDown, twentyTwoDown, twentyThreeDown, twentyFourDown) ? ValidationState.PASSED : ValidationState.FAILED;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(permutations);
        return validator;
    }

    public static int fromValues(int clueIndex, int[] values) {
        return values[clueIndex - 1];
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateGrid(int... values) {
        if (values[8] + values[9] >= values[20]) return false;
        if (values[6] - values[0] != values[17]) return false;
        if (values[7] + values[14] + values[15] != values[18]) return false;

        if (values[2] * values[12] != values[21]) return false;
        if (values[0] * values[15] - values[17] != values[9]) return false;
        if (values[1] * values[18] != values[10]) return false;
        if (5 * values[0] != values[14]) return false;

        if (values[13] / 2 != values[1]) return false;
        if (values[19] / 7 == values[6]) return false;
        if (!isPerfectSquare(values[23])) return false;
        if ((int) Math.sqrt(values[23]) != values[5]) return false;
        if (!isCube(values[12])) return false;

        if (!isFibonacciNumber(values[15])) return false;

        if (!isTriangularNumber(values[0])) return false;
        if (!isTriangularNumber(values[19])) return false;

        if (!(values[8] % values[2] == 0 && isPrime(values[2]))) return false;
        if (values[12] % values[3] != 0) return false;

        if (!isPrime(values[16])) return false;
        int[] clue8PrimeFactors = getPrimeFactors(values[7]);
        int[] clue5PrimeFactors = getPrimeFactors(values[4]);
        if (!hasCommonElement(clue8PrimeFactors, clue5PrimeFactors)) return false;

        if (!isPalindromic(values[8])) return false;
        if (!isPalindromic(values[11])) return false;
        if (!isPalindromic(values[13])) return false;
        if (!isPalindromic(values[22] / 35)) return false;
        return true;

    }

    public static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static boolean isFibonacciNumber(int number) {
        if (number < 0) return false;
        return isPerfectSquare(5 * number * number + 4) || isPerfectSquare(5 * number * number - 4);
    }

    public static boolean isCube(int number) {
        if (number < 0) number = -number;

        int cubeRoot = (int) Math.round(Math.cbrt(number));
        return cubeRoot * cubeRoot * cubeRoot == number;
    }

    public static boolean isPalindromic(int number) {
        if (number < 0) return false;
        String numStr = Integer.toString(number);

        String reversedStr = new StringBuilder(numStr).reverse().toString();
        return numStr.equals(reversedStr);
    }


    public static boolean hasCommonElement(int[] array1, int[] array2) {
        return Arrays.stream(array1)
                .anyMatch(x -> Arrays.stream(array2).anyMatch(y -> y == x));
    }

    public static int[] getPrimeFactors(int number) {
        number = Math.abs(number);
        List<Integer> primeFactors = new ArrayList<>();
        while (number % 2 == 0) {
            primeFactors.add(2);
            number /= 2;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                primeFactors.add(i);
                number /= i;
            }
        }
        if (number > 2) {
            primeFactors.add(number);
        }
        return primeFactors.stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<GridVector> fromPermutation(List<Integer> permutation, List<GridVector> staticVectors) {
        List<GridVector> vectors = new ArrayList<>();
        for (Integer i : permutation) {
            vectors.add(staticVectors.get(i));
        }
        return vectors;
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


    public static void generatePermutations(List<Integer> nums, List<Integer> current, boolean[] used, Runnable whenDone) {
        // Base case: if the current permutation has the same size as the input list
        if (current.size() == nums.size()) {
            // Print the current permutation
            whenDone.run();
            return;
        }

        // Iterate through all numbers
        for (int i = 0; i < nums.size(); i++) {
            if (!used[i]) { // Only use unused numbers
                // Mark the current number as used and add it to the current permutation
                used[i] = true;
                current.add(nums.get(i));

                // Recursively generate permutations for the remaining numbers
                generatePermutations(nums, current, used, whenDone);

                // Backtrack: remove the last number and mark it as unused
                current.removeLast();
                used[i] = false;
            }
        }
    }

    public static <T> List<List<T>> generatePermutations(List<T> list) {
        List<List<T>> result = new ArrayList<>();
        backtrack(list, new ArrayList<>(), result, new boolean[list.size()]);
        return result;
    }

    private static <T> void backtrack(List<T> list, List<T> temp, List<List<T>> result, boolean[] used) {
        if (temp.size() == list.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (used[i]) continue;
            used[i] = true;
            temp.add(list.get(i));
            backtrack(list, temp, result, used);
            temp.removeLast();
            used[i] = false;
        }
    }

    public static <T> void generatePermutations(List<T> nums, List<T> current, boolean[] used, Consumer<List<T>> whenDone) {
        // Base case: if the current permutation has the same size as the input list
        if (current.size() == nums.size()) {
            // Print the current permutation
            whenDone.accept(current);
            return;
        }

        // Iterate through all numbers
        for (int i = 0; i < nums.size(); i++) {
            if (!used[i]) { // Only use unused numbers
                // Mark the current number as used and add it to the current permutation
                used[i] = true;
                current.add(nums.get(i));

                // Recursively generate permutations for the remaining numbers
                generatePermutations(nums, current, used, whenDone);

                // Backtrack: remove the last number and mark it as unused
                current.removeLast();
                used[i] = false;
            }
        }
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

        public int apply(int[][] grid) {
            return extractVector(from, to, grid);
        }

        @Override
        public String toString() {
            return "GridVector{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    public static int extractVector(GridPoint from, GridPoint to, int[][] grid) {
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

    private static final List<GridVector> DOWN_2_VECTORS = new ArrayList<>(6) {{
        this.add(new GridVector(3,2,4,2)); // 2
        this.add(new GridVector(5,2,6,2)); // 2
        this.add(new GridVector(5,3,6,3)); // 2
        this.add(new GridVector(1,4,2,4)); // 2
        this.add(new GridVector(1,5,2,5)); // 2
        this.add(new GridVector(3,5,4,5)); // 2
    }};

    private static final List<GridVector> DOWN_3_VECTORS = new ArrayList<>(4) {{
        this.add(new GridVector(1,6,3,6)); // 3
        this.add(new GridVector(4,6,6,6)); // 3
        this.add(new GridVector(1,1,3,1)); // 3
        this.add(new GridVector(4,1,6,1)); // 3
    }};

    private static final List<GridVector> DOWN_4_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(1,3,4,3)); // 4
        this.add(new GridVector(3,4,6,4)); // 4
    }};

    private static final List<GridVector> ACROSS_2_VECTORS = new ArrayList<>(8) {{
        this.add(new GridVector(1,5,1,6)); // 2
        this.add(new GridVector(2,4,2,5)); // 2
        this.add(new GridVector(3,3,3,4)); // 2
        this.add(new GridVector(3,5,3,6)); // 2
        this.add(new GridVector(4,1,4,2)); // 2
        this.add(new GridVector(4,3,4,4)); // 2
        this.add(new GridVector(5,2,5,3)); // 2
        this.add(new GridVector(6,1,6,2)); // 2
    }};

    private static final List<GridVector> ACROSS_3_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(5,4,5,6)); // 3
        this.add(new GridVector(2,1,2,3)); // 3
    }};
    private static final List<GridVector> ACROSS_4_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(1,1,1,4)); // 4
        this.add(new GridVector(6,3,6,6)); // 4
    }};

    private ValidationResult getResult(Integer[][] grid) {
        if (grid.length != 6 || grid[0].length != 6) return null;

        return null;
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
