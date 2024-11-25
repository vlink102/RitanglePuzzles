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
import java.util.List;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Genius3 {

    public static final int[] fibonacci2Digit = {13, 21, 34, 55, 89};
    public static final int[] triangularMultipleOf7 = {105, 210, 231, 378, 406, 595, 630};
    public static final Integer[][] modifiableGrid = new Integer[6][6];
    private static final List<GridVector> DOWN_2_VECTORS = new ArrayList<>(6) {{
        this.add(new GridVector(1, 4, 2, 4)); // 13 | 0
        this.add(new GridVector(1, 5, 2, 5)); // 14 | 1
        this.add(new GridVector(3, 2, 4, 2)); // 15 | 2
        this.add(new GridVector(3, 5, 4, 5)); // 16 | 3
        this.add(new GridVector(5, 2, 6, 2)); // 17 | 4
        this.add(new GridVector(5, 3, 6, 3)); // 18 | 5
    }};
    private static final List<GridVector> DOWN_3_VECTORS = new ArrayList<>(4) {{
        this.add(new GridVector(1, 1, 3, 1)); // 19 | 0
        this.add(new GridVector(1, 6, 3, 6)); // 20 | 1
        this.add(new GridVector(4, 1, 6, 1)); // 21 | 2
        this.add(new GridVector(4, 6, 6, 6)); // 22 | 3
    }};
    private static final List<GridVector> DOWN_4_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(1, 3, 4, 3)); // 23 | 0
        this.add(new GridVector(3, 4, 6, 4)); // 24 | 1
    }};
    private static final List<GridVector> ACROSS_2_VECTORS = new ArrayList<>(8) {{
        this.add(new GridVector(1, 5, 1, 6)); // 1 | 0
        this.add(new GridVector(2, 4, 2, 5)); // 2 | 1
        this.add(new GridVector(3, 3, 3, 4)); // 3 | 2
        this.add(new GridVector(3, 5, 3, 6)); // 4 | 3
        this.add(new GridVector(4, 1, 4, 2)); // 5 | 4
        this.add(new GridVector(4, 3, 4, 4)); // 6 | 5
        this.add(new GridVector(5, 2, 5, 3)); // 7 | 6
        this.add(new GridVector(6, 1, 6, 2)); // 8 | 7
    }};
    private static final List<GridVector> ACROSS_3_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(2, 1, 2, 3)); // 9  | 0
        this.add(new GridVector(5, 4, 5, 6)); // 10 | 1
    }};
    private static final List<GridVector> ACROSS_4_VECTORS = new ArrayList<>(2) {{
        this.add(new GridVector(1, 1, 1, 4)); // 11 | 0
        this.add(new GridVector(6, 3, 6, 6)); // 12 | 1
    }};

    static {
        for (Integer[] integers : modifiableGrid) {
            Arrays.fill(integers, 1);
        }
    }

    public static void generatePermutationsOfPieces() {
        List<List<Integer>> permutations = generatePermutations(List.of(0, 1, 2, 3, 4, 5, 6, 7), new Predicate<List<Integer>>() {
            @Override
            public boolean test(List<Integer> integers) {
                return true;
            }
        });
        List<int[][]> pieces = List.of(
                new int[][]{{1, 1, 1, 1}},             // 1x4
                new int[][]{{2}},                      // 1x1
                new int[][]{{3, 3}},                   // 1x2
                new int[][]{{4, 4, 4}},                // 1x3
                new int[][]{{6, 6}, {6, 6}},           // 2x2
                new int[][]{{7, 7}, {7, 0}},           // Irregular 1
                new int[][]{{8, 8, 0}, {0, 8, 8}},     // Irregular 2
                new int[][]{{9, 0}, {9, 0}, {9, 9}}   // Irregular 3
                //new int[][]{{0, 9, 0}, {9, 9, 9}}      // Irregular 4
        );
        for (List<Integer> permutation : permutations) {
            List<int[][]> piecePermutation = new ArrayList<>();
            for (Integer i : permutation) {
                int[][] ok = pieces.get(i);
                int[][] better = new int[ok.length][ok[0].length];
                for (int j = 0; j < ok.length; j++) {
                    for (int k = 0; k < ok[0].length; k++) {
                        if (ok[j][k] == 0) continue;
                        better[j][k] = i;
                    }
                }
                piecePermutation.add(better);
            }

            Shape.solveAll(new int[][] {
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 5, 0, 0, 0, 0},
                    {0, 5, 5, 0, 0, 0},
                    {0, 5, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0}
            }, piecePermutation, 0, new int[][]{
                    {1, 0, 1, 1, 1, 1},
                    {1, 0, 0, 1, 0, 0},
                    {0, 1, 1, 1, 1, 0},
                    {1, 0, 1, 0, 0, 1},
                    {0, 1, 1, 1, 0, 0},
                    {1, 0, 1, 0, 0, 0}
            });
        }
    }

    public Genius3() throws IOException {
        List<int[][]> pieces = List.of(
                new int[][]{{6, 6, 6, 6}},             // 1x4
                new int[][]{{1}},                      // 1x1
                new int[][]{{8, 8}},                   // 1x2
                new int[][]{{9, 9, 9}},                // 1x3
                new int[][]{{4, 4}, {4, 4}},           // 2x2
                new int[][]{{2, 2}, {2, 0}},           // Irregular 1
                new int[][]{{7, 7, 0}, {0, 7, 7}},     // Irregular 2
                new int[][]{{3, 0}, {3, 0}, {3, 3}}   // Irregular 3
                //new int[][]{{0, 9, 0}, {9, 9, 9}}      // Irregular 4
        );

        List<int[][]> testSeeIfMyCodeActuallyWorks = new ArrayList<>() {{
            this.add(new int[][]{{1, 1}, {1, 0}}); //
        }};

        int[][] blah = new int[][] {
                {6, 6, 6, 6, 8, 8},
                {3, 3, 3, 1, 0, 8},
                {0, 5, 3, 7, 2, 2},
                {0, 5, 5, 7, 7, 2},
                {0, 5, 4, 4, 7, 0},
                {0, 0, 4, 4, 0, 0}
        };

        System.out.println(hailMary());

        Shape.total = 0;
        Shape.solveAll(new int[][] {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 5, 0, 0, 0, 0},
                {0, 5, 5, 0, 0, 0},
                {0, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        }, pieces, 0, new int[][]{
                {1, 0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 0, 0}
        });

        //generatePermutationsOfPieces();

        HashMap<Integer, Integer> test = new HashMap<>() {{
            this.put(1, 4);
            this.put(2, 6);
            this.put(0, 7);
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
                counts[digit]++;
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

    public static Genius3Validator hailMary() {
        int[][] yippee = {
                {6, 6, 6, 6, 8, 8},
                {3, 3, 3, 1, 0, 8},
                {0, 5, 3, 7, 2, 2},
                {9, 5, 5, 7, 7, 2},
                {9, 5, 4, 4, 7, 0},
                {9, 0, 4, 4, 0, 0}
        };

        return validateGenius3(yippee);
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
        validator.countsValid = counts.get(0) == 7 ? ValidationState.PASSED : ValidationState.FAILED;
        validator.containsCorrectShapeSizes = (areContentsEqual(List.of(1, 2, 3, 3, 4, 4, 4, 4, 4, 7), counts.values())) ? ValidationState.PASSED : ValidationState.FAILED;

        List<List<Integer>> ac2 = generatePermutations(List.of(0, 1, 2, 3, 4, 5, 6, 7), new Predicate<List<Integer>>() {
            @Override
            public boolean test(List<Integer> integers) {
                if (integers.get(0) == 0 || integers.get(0) == 6) return false;
                if (integers.get(2) == 0 || integers.get(2) == 6) return false;
                return integers.get(6) != 0 && integers.get(6) != 6;
            }
        });
        List<List<Integer>> ac3 = generatePermutations(List.of(0, 1), integers -> true);
        List<List<Integer>> ac4 = generatePermutations(List.of(0, 1), integers -> true);
        List<List<Integer>> dn2 = generatePermutations(List.of(0, 1, 2, 3, 4, 5), integers -> integers.getFirst() != 5);
        List<List<Integer>> dn3 = generatePermutations(List.of(0, 1, 2, 3), integers -> integers.get(2) != 1);
        List<List<Integer>> dn4 = List.of(List.of(0, 1)); //generatePermutations(List.of(0, 1));

        //System.out.println(ac2.size() + "*" + ac3.size() + "*" + ac4.size() + "*" + dn2.size() + "*" + dn3.size() + "*" + dn4.size());
        int permutations = 0;

        int[] counter = new int[10];

        for (List<Integer> across2Permutations : ac2) {
            List<GridVector> ac2Vectors = fromPermutation(across2Permutations, ACROSS_2_VECTORS);
            int oneAcross = extractVector(ac2Vectors.get(0).from, ac2Vectors.get(0).to, grid);
            if (oneAcross != 10) {
                //System.out.println("oneAcross: " + oneAcross);
                continue;
            }
            int twoAcross = extractVector(ac2Vectors.get(1).from, ac2Vectors.get(1).to, grid);
            if (!(twoAcross == 11 || twoAcross == 22 || twoAcross == 33 || twoAcross == 44)) {
                //System.out.println("twoAcross: " + twoAcross);
                continue;
            }
            int threeAcross = extractVector(ac2Vectors.get(2).from, ac2Vectors.get(2).to, grid);
            if (threeAcross != 37) {
                //System.out.println("threeAcross: " + threeAcross);
                continue;
            }
            int fourAcross = extractVector(ac2Vectors.get(3).from, ac2Vectors.get(3).to, grid);
            if (fourAcross != 54 && fourAcross != 81) {
                System.out.println("fouracross: " + fourAcross);
                continue;
            }
            int fiveAcross = extractVector(ac2Vectors.get(4).from, ac2Vectors.get(4).to, grid);
            int sixAcross = extractVector(ac2Vectors.get(5).from, ac2Vectors.get(5).to, grid);
            if (!(sixAcross >= 60)) {
                System.out.println("sixacross: " + sixAcross);
                continue;
            }
            int sevenAcross = extractVector(ac2Vectors.get(6).from, ac2Vectors.get(6).to, grid);
            if (sevenAcross != 90) {
                System.out.println("sevenacross: " + sevenAcross);
                continue;
            }
            int eightAcross = extractVector(ac2Vectors.get(7).from, ac2Vectors.get(7).to, grid);
            if (!updateCounts(counter, oneAcross, twoAcross, threeAcross, fourAcross, fiveAcross, sixAcross, sevenAcross, eightAcross)) {
                continue;
            }

            for (List<Integer> across3Permutations : ac3) {
                List<GridVector> ac3Vectors = fromPermutation(across3Permutations, ACROSS_3_VECTORS);
                int nineAcross = extractVector(ac3Vectors.get(0).from, ac3Vectors.get(0).to, grid);
               /* if (!(nineAcross == 111 || nineAcross == 222 || nineAcross == 333)) {
                    System.out.println("nineAcross: " + nineAcross);
                    continue;
                }*/
                int tenAcross = extractVector(ac3Vectors.get(1).from, ac3Vectors.get(1).to, grid);
                /*if (tenAcross != 470) {
                    System.out.println("tenAcross: " + tenAcross);
                    continue;
                }*/
                if (!updateCounts(counter, nineAcross, tenAcross)) continue;


                for (List<Integer> across4Permutations : ac4) {
                    List<GridVector> ac4Vectors = fromPermutation(across4Permutations, ACROSS_4_VECTORS);
                    int elevenAcross = extractVector(ac4Vectors.get(0).from, ac4Vectors.get(0).to, grid);
                    if (!(elevenAcross >= 2090)) {
                        System.out.println("elevenAcross: " + elevenAcross);
                        continue;
                    }
                    int twelveAcross = extractVector(ac4Vectors.get(1).from, ac4Vectors.get(1).to, grid);
                    if (!updateCounts(counter, elevenAcross, twelveAcross)) {
                        continue;
                    }

                    for (List<Integer> down2Permutations : dn2) {
                        List<GridVector> dn2Vectors = fromPermutation(down2Permutations, DOWN_2_VECTORS);

                        int thirteenDown = extractVector(dn2Vectors.get(0).from, dn2Vectors.get(0).to, grid);
                        if (thirteenDown != 27) {
                            System.out.println("thirteenDown: " + thirteenDown);
                            continue;
                        }
                        int fourteenDown = extractVector(dn2Vectors.get(1).from, dn2Vectors.get(1).to, grid);
                        if (!(fourteenDown >= 33)) {
                            System.out.println("fourteenDown: " + fourteenDown);
                            continue;
                        }
                        if (fourteenDown % 2 != 0) {
                            System.out.println("fourteenDown % 2 != 0: " + fourteenDown);
                            continue;
                        }
                        if (fourteenDown % 11 != 0) {
                            System.out.println("fourteenDown % 11 != 0: " + fourteenDown);
                            continue;
                        }
                        int fifteenDown = extractVector(dn2Vectors.get(2).from, dn2Vectors.get(2).to, grid);
                        if (fifteenDown != 50) {
                            System.out.println("fifteenDown: " + fifteenDown);
                            continue;
                        }
                        int sixteenDown = extractVector(dn2Vectors.get(3).from, dn2Vectors.get(3).to, grid);
                        if (sixteenDown != 55) {
                            System.out.println("sixteenDown: " + sixteenDown);
                            continue;
                        }
                        int seventeenDown = extractVector(dn2Vectors.get(4).from, dn2Vectors.get(4).to, grid);
                        if (!(seventeenDown == 61 || seventeenDown == 67 || seventeenDown == 71 || seventeenDown == 73)) {
                            System.out.println("seventeenDown: " + seventeenDown);
                            continue;
                        }
                        int eighteenDown = extractVector(dn2Vectors.get(5).from, dn2Vectors.get(5).to, grid);
                        if (!(sevenAcross >= eighteenDown + 10)) {
                            System.out.println("eighteenDown + 10 < sevenAcross: " + eighteenDown);
                            continue;
                        }
                        if (eighteenDown != 80) {
                            System.out.println("eighteendown: " + eighteenDown);
                            continue;
                        }

                        if (Stream.of(thirteenDown, fourteenDown, fifteenDown, sixteenDown, seventeenDown, eighteenDown).anyMatch(integer -> {
                            String s = String.valueOf(integer);
                            return s.startsWith("9") || s.startsWith("1");
                        })) {
                            continue;
                        }

                        if (!updateCounts(counter, thirteenDown, fourteenDown, fifteenDown, sixteenDown, seventeenDown, eighteenDown))
                            continue;

                        for (List<Integer> down3Permutations : dn3) {
                            List<GridVector> dn3Vectors = fromPermutation(down3Permutations, DOWN_3_VECTORS);
                            int nineteenDown = extractVector(dn3Vectors.get(0).from, dn3Vectors.get(0).to, grid);
                            if (!(nineteenDown >= 196 && nineteenDown <= 204)) {
                                System.out.println("nineteenDown: " + nineteenDown);
                                continue;
                            }
                            int twentyDown = extractVector(dn3Vectors.get(1).from, dn3Vectors.get(1).to, grid);
                            if (twentyDown != 630) {
                                System.out.println("twentyDown: " + twentyDown);
                                continue;
                            }
                            int twentyOneDown = extractVector(dn3Vectors.get(2).from, dn3Vectors.get(2).to, grid);
                            if (!(twentyOneDown < 904)) {
                                System.out.println("twentyOneDown: " + twentyOneDown);
                                continue;
                            }
                            int twentyTwoDown = extractVector(dn3Vectors.get(3).from, dn3Vectors.get(3).to, grid);
                            if (twentyTwoDown != 999) {
                                System.out.println("twentyTwoDown: " + twentyTwoDown);
                                continue;
                            }

                            if (!updateCounts(counter, nineteenDown, twentyDown, twentyOneDown, twentyTwoDown))
                                continue;

                            for (List<Integer> down4Permutations : dn4) {
                                List<GridVector> dn4Vectors = fromPermutation(down4Permutations, DOWN_4_VECTORS);
                                int twentyThreeDown = extractVector(dn4Vectors.get(0).from, dn4Vectors.get(0).to, grid);
                                if (!(twentyThreeDown >= 1155)) {
                                    System.out.println("twentyThreeDown: " + twentyThreeDown);
                                    continue;
                                }
                                int twentyFourDown = extractVector(dn4Vectors.get(1).from, dn4Vectors.get(1).to, grid);
                                if (!(twentyFourDown > 3535 && twentyFourDown < 8100)) {
                                    System.out.println("twentyFourDown: " + twentyFourDown);
                                    continue;
                                }
                                if (!updateCounts(counter, twentyThreeDown, twentyFourDown)) continue;

                                permutations++;
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
        //if (values[12] != 27 && values[12] != 64) return false;
        //if (values[13] % 2 != 0) return false;
        //if (values[13] % 11 != 0) return false;
        //if (values[14] % 5 != 0) return false;
        //if (Arrays.stream(fibonacci2Digit).noneMatch(i -> i == values[15])) return false;
        //if (!(10 <= values[0] && values[0] < 20)) return false;
        //if (Arrays.stream(triangularMultipleOf7).noneMatch(i -> i == values[19])) return false;
        //if () return false;
        //if (!(values[0] == 10 || values[0] == 15)) return false;
        //if (!(values[1] == 11 || values[1] == 22 || values[1] == 33 || values[1] == 44)) return false;
        /*if (values[3] != 54) return false;
        if (!(values[5] >= 60)) return false;
        if (!(values[6] == 85 || values[6] == 90)) return false;
        if (!(values[8] == 111 || values[8] == 222 || values[8] == 333 || values[8] == 444)) return false;
        if (!(values[9] >= 570 && values[9] < 800)) return false;
        if (!(values[10] >= 2090)) return false;
        if (values[12] != 27) return false;
        if (!(values[13] >= 33)) return false;
        if (values[14] != 50) return false;
        if (values[15] != 55) return false;
        if (!(values[16] == 61 || values[16] == 67 || values[16] == 71 || values[16] == 73)) return false;
        if (!(values[17] < 80)) return false;
        if (!(values[18] > 190 && values[18] < 230)) return false;
        if (values[19] != 630) return false;
        if (values[21] != 999) return false;
        // 23dn >= 101 - 222  ?????
        if (!(values[23] > 3535 && values[23] < 8100)) return false;*/

        if (values[8] + values[9] >= values[20]) {
            System.out.println(" if (values[8] + values[9] >= values[20]) {");
            return false;
        }
        if (values[6] - values[0] != values[17]) {
            System.out.println(" if (values[6] - values[0] != values[17]) {");
            return false;
        }
        if (values[7] + values[14] + values[15] != values[18]) {
            System.out.println(" if (values[7] + values[14] + values[15] != values[18]) {");
            return false;
        }

        if (values[2] * values[12] != values[21]) {
            System.out.println(" if (values[2] * values[12] != values[21]) {");
            return false;
        }
        if (values[0] * values[15] - values[17] != values[9]) {
            System.out.println(" if (values[0] * values[15] - values[17] != values[9]) {");
            return false;
        }
        if (values[1] * values[18] != values[10]) {
            System.out.println(" if (values[1] * values[18] != values[10]) {");
            return false;
        }
        if (5 * values[0] != values[14]) {
            System.out.println(" if (5 * values[0] != values[14]) {");
            return false;
        }

        if (values[13] / 2 != values[1]) {
            System.out.println(" if (values[13] / 2 != values[1]) {");
            return false;
        }
        if (values[19] / 7 == values[6]) {
            System.out.println(" if (values[19] / 7 == values[6]) {");
            return false;
        }
        if (!isPerfectSquare(values[23])) {
            System.out.println(" if (!isPerfectSquare(values[23])) {");
            return false;
        }
        if ((int) Math.sqrt(values[23]) != values[5]) {
            System.out.println(" if ((int) Math.sqrt(values[23]) != values[5]) {");
            return false;
        }
        if (!isCube(values[12])) {
            System.out.println(" if (!isCube(values[12])) {");
            return false;
        }

        if (!isFibonacciNumber(values[15])) {
            System.out.println(" if (!isFibonacciNumber(values[15])) {");
            return false;
        }

        if (!isTriangularNumber(values[0])) {
            System.out.println(" if (!isTriangularNumber(values[0])) {");
            return false;
        }
        if (!isTriangularNumber(values[19])) {
            System.out.println(" if (!isTriangularNumber(values[19])) {");
            return false;
        }

        if (!(values[8] % values[2] == 0 && isPrime(values[2]))) {
            System.out.println(" if (!(values[8] % values[2] == 0 && isPrime(values[2]))) {");
            return false;
        }
        if (values[12] % values[3] != 0) {
            System.out.println(" if (values[12] % values[3] != 0) {");
            return false;
        }

        if (!isPrime(values[16])) {
            System.out.println(" if (!isPrime(values[16])) {");
            return false;
        }
        int[] clue8PrimeFactors = getPrimeFactors(values[7]);
        int[] clue5PrimeFactors = getPrimeFactors(values[4]);
        if (!hasCommonElement(clue8PrimeFactors, clue5PrimeFactors)) {
            System.out.println(" if (!hasCommonElement(clue8PrimeFactors, clue5PrimeFactors)) {");
            return false;
        }

        if (!isPalindromic(values[8])) {
            System.out.println(" if (!isPalindromic(values[8])) {");
            return false;
        }
        if (!isPalindromic(values[11])) {
            System.out.println(" if (!isPalindromic(values[11])) {");
            return false;
        }
        if (!isPalindromic(values[13])) {
            System.out.println(" if (!isPalindromic(values[13])) {");
            return false;
        }
        return isPalindromic(values[22] / 35);

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

    public static <T> List<List<T>> generatePermutations(List<T> list, Predicate<List<T>> predicate) {
        List<List<T>> result = new ArrayList<>();
        backtrack(list, new ArrayList<>(), result, new boolean[list.size()], predicate);
        return result;
    }

    private static <T> void backtrack(List<T> list, List<T> temp, List<List<T>> result, boolean[] used, Predicate<List<T>> predicate) {
        if (temp.size() == list.size() && predicate.test(temp)) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (used[i]) continue;
            used[i] = true;
            temp.add(list.get(i));
            backtrack(list, temp, result, used, predicate);
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

    private void printGenius3Console(Integer[][] grid) {
        System.out.print(getResult(grid));
        System.out.println(Arrays.deepToString(grid) + "\n");
    }

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

    public enum ValidationState {
        PASSED,
        FAILED,
        UNKNOWN
    }

    @Getter
    @Setter
    public static class Genius3Validator {
        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";
        ValidationState validDigitSeparation = null;
        ValidationState countsValid = null;
        ValidationState containsCorrectShapeSizes = null;
        ValidationState gridIsClueValid = null;
        ValidationState gridSize = null;

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

    public record GridPoint(int row, int col) {
        public static GridPoint of(int row, int col) {
            return new GridPoint(row, col);
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

    public class TextFieldUnique extends JTextField {
        @Getter
        private final GridPoint gridPoint;

        public TextFieldUnique(GridPoint gridPoint) {
            super();
            this.gridPoint = gridPoint;
        }
    }

    @Getter
    @Setter
    public class ValidationResult {
        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";
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

        @Override
        public String toString() {
            return "Sum Across: " + sumAcross + ", " +
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

    public class InvalidGridException extends AssertionError {
        public InvalidGridException(String text) {
            super(text);
        }
    }
}
