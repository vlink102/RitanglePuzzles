package me.vlink102;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FinalQuestion {
    private static final int[] THREE_DIGIT_HAPPY_NUMBERS = removeContainedZeroes(new int[]{129, 133, 139, 167, 188, 226, 236, 239, 338, 356, 367, 368, 379, 446, 469, 478, 556, 566, 888, 899});
    private static final int[] THREE_DIGIT_LUCKY_NUMBERS = removeContainedZeroes(threeDigitsOnly(generateLuckyNumbers(1000)));
    private static final int[] THREE_DIGIT_CUBES = removeContainedZeroes(new int[]{125, 216, 343, 512, 729});
    private static final int[] THREE_DIGIT_LUCAS_NUMBERS = removeContainedZeroes(new int[]{123, 199, 322, 521, 843});
    private static final int[] THREE_DIGIT_CULLEN_NUMBERS = removeContainedZeroes(new int[]{161, 385, 897});
    private static final int[] THREE_DIGIT_SQUARES = removeContainedZeroes(new int[]{100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961});
    private static final int[] THREE_DIGIT_TRIANGULAR_NUMBERS = removeContainedZeroes(new int[]{105, 120, 136, 153, 171, 190, 210, 231, 253, 276, 300, 325, 351, 378, 406, 435, 465, 496, 528, 561, 595, 630, 666, 703, 741, 780, 820, 861, 903, 946, 990});
    private static final int[] THREE_DIGIT_FIBONACCI_NUMBERS = removeContainedZeroes(new int[]{144, 233, 377, 610, 987});
    private static final int[] HARSHAD_NUMBERS = generateHarshadNumbers();

    private static final int[] DIVISIBLE_BY_DIVISORS = removeContainedZeroes(divisibleByDivisors());

    public static void main(String[] args) {
        printNumberSets();
        runTests();

    }

    public static void runTests() {
        Assert.assertEquals(condense(1, 2, 3), 123);
        Assert.assertEquals(condense(1, 2), 12);
        Assert.assertEquals(condense(1), 1);
        Assert.assertEquals(condense(9, 9, 9, 9, 9, 9), 999999);

        int[][] testMatrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 1, 2, 3},
                {4, 5, 6, 7}
        };

        Assert.assertEquals(extractVector(GridPoint.of(0, 0), GridPoint.of(0, 2), testMatrix), 123);
        Assert.assertEquals(extractVector(GridPoint.of(0, 1), GridPoint.of(0, 2), testMatrix), 23);
        Assert.assertEquals(extractVector(GridPoint.of(0, 0), GridPoint.of(0, 1), testMatrix), 12);
        Assert.assertEquals(extractVector(GridPoint.of(0, 0), GridPoint.of(3, 0), testMatrix), 1594);
        Assert.assertEquals(extractVector(GridPoint.of(1, 2), GridPoint.of(3, 2), testMatrix), 726);

        Assert.assertEquals(digitSum(123), 6);
        Assert.assertEquals(digitSum(1), 1);

        Assert.assertEquals(digitProduct(123), 6);
        Assert.assertEquals(digitProduct(1), 1);
        Assert.assertEquals(digitProduct(27), 14);
        Assert.assertEquals(digitProduct(427), 56);

        Assert.assertTrue(isPrime(13));
        Assert.assertFalse(isPrime(18));

        Assert.assertEquals(factorCount(10), 4); // 10, 1, 2, 5
        Assert.assertEquals(factorCount(5), 2);
        Assert.assertEquals(factorCount(13), 2);
        Assert.assertEquals(factorCount(20), 6); // 20, 1, 10, 2, 5, 4


        System.out.println("All tests passed");
    }

    public static int[] divisibleByDivisors() {
        List<Integer> divisible = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % factorCount(i) == 0) divisible.add(i);
        }
        return divisible.stream().mapToInt(i -> i).toArray();
    }

    public static int condense(int... digits) {
        int value = 0;
        for (int i = 0; i < digits.length; i++) {
            int scalar = (int) Math.pow(10, digits.length - i - 1);
            value += digits[i] * scalar;
        }
        return value;
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

    public static boolean validateNumberSoup(int[][] grid) {
        if (grid.length != 4 || grid[0].length != 9) return false;
        int oneAcross = extractVector(GridPoint.of(0, 0), GridPoint.of(0, 2), grid);
        int threeAcross = extractVector(GridPoint.of(0, 3), GridPoint.of(0, 5), grid);
        int sixAcross = extractVector(GridPoint.of(0, 6), GridPoint.of(0, 8), grid);
        int eightAcross = extractVector(GridPoint.of(1, 0), GridPoint.of(1, 1), grid);
        int tenAcross = extractVector(GridPoint.of(1, 2), GridPoint.of(1, 4), grid);
        int twelveAcross = extractVector(GridPoint.of(1, 5), GridPoint.of(1, 7), grid);
        int fifteenAcross = extractVector(GridPoint.of(2, 1), GridPoint.of(2, 3), grid);
        int sixteenAcross = extractVector(GridPoint.of(2, 4), GridPoint.of(2, 6), grid);
        int seventeenAcross = extractVector(GridPoint.of(2, 7), GridPoint.of(2, 8), grid);
        int nineteenAcross = extractVector(GridPoint.of(3, 0), GridPoint.of(3, 3), grid);
        int twentyAcross = extractVector(GridPoint.of(3, 3), GridPoint.of(3, 5), grid);
        int twentyOneAcross = extractVector(GridPoint.of(3, 6), GridPoint.of(3, 8), grid);

        int oneDown = extractVector(GridPoint.of(0, 0), GridPoint.of(1, 0), grid);
        int twoDown = extractVector(GridPoint.of(0, 2), GridPoint.of(3, 2), grid);
        int fourDown = extractVector(GridPoint.of(0, 4), GridPoint.of(3, 4), grid);
        int fiveDown = extractVector(GridPoint.of(0, 5), GridPoint.of(2, 5), grid);
        int sixDown = extractVector(GridPoint.of(0, 6), GridPoint.of(3, 6), grid);
        int sevenDown = extractVector(GridPoint.of(0, 8), GridPoint.of(1, 8), grid);
        int fourteenDown = extractVector(GridPoint.of(2, 0), GridPoint.of(3, 0), grid);

        int nineDown = extractVector(GridPoint.of(1, 1), GridPoint.of(2, 1), grid);
        int eighteenDown = extractVector(GridPoint.of(2, 8), GridPoint.of(3, 8), grid);
        int thirteenDown = extractVector(GridPoint.of(1, 7), GridPoint.of(2, 7), grid);
        int elevenDown = extractVector(GridPoint.of(1, 3), GridPoint.of(3, 3), grid);

        if (eightAcross != oneDown - nineDown) return false;
        if (!isPresent(tenAcross, DIVISIBLE_BY_DIVISORS)) return false;
        if (!isPresent(twelveAcross, THREE_DIGIT_LUCAS_NUMBERS)) return false;
        if (!isPresent(fifteenAcross, THREE_DIGIT_CULLEN_NUMBERS)) return false;
        if (!isPresent(sixteenAcross, DIVISIBLE_BY_DIVISORS)) return false;
        if (seventeenAcross != eighteenDown - thirteenDown) return false;
        if (twentyAcross % oneDown != 0) return false;
        if (!isPrime(twoDown)) return false;

        if (!isSquareNumber(sum(func(integer -> (int) Math.pow(integer, 3), getDigits(fourDown))))) return false;
        boolean elevenDownMinusHarshad = false;
        for (int harshadNumber : HARSHAD_NUMBERS) {
            if (fiveDown == elevenDown - harshadNumber) {
                elevenDownMinusHarshad = true;
                break;
            }
        }
        if (!elevenDownMinusHarshad) return false;
        if (!isPrime(sixDown)) return false;
        if (threeAcross % sevenDown == 0) {
            // is divisor
            int divisor = threeAcross / sevenDown;
            if (!isPrime(divisor)) {
                return false;
            }
        } else {
            return false;
        }
        int sum19AcrossDigits = digitSum(nineteenAcross);
        int sum20AcrossDigits = digitSum(twentyAcross);
        int sum21AcrossDigits = digitSum(twentyOneAcross);
        int totalSums = sum19AcrossDigits + sum20AcrossDigits + sum21AcrossDigits;
        if (totalSums % fourteenDown != 0) return false;

        int happyNumberCount = 0;
        int luckyNumberCount = 0;
        int cubeNumberCount = 0;
        if (isPresent(oneAcross, THREE_DIGIT_HAPPY_NUMBERS)) happyNumberCount++;
        if (isPresent(oneAcross, THREE_DIGIT_LUCKY_NUMBERS)) luckyNumberCount++;
        if (isPresent(oneAcross, THREE_DIGIT_CUBES)) cubeNumberCount++;
        if (isPresent(threeAcross, THREE_DIGIT_HAPPY_NUMBERS)) happyNumberCount++;
        if (isPresent(threeAcross, THREE_DIGIT_LUCKY_NUMBERS)) luckyNumberCount++;
        if (isPresent(threeAcross, THREE_DIGIT_CUBES)) cubeNumberCount++;
        if (isPresent(sixAcross, THREE_DIGIT_HAPPY_NUMBERS)) happyNumberCount++;
        if (isPresent(sixAcross, THREE_DIGIT_LUCKY_NUMBERS)) luckyNumberCount++;
        if (isPresent(sixAcross, THREE_DIGIT_CUBES)) cubeNumberCount++;

        if (!(happyNumberCount == 1 && luckyNumberCount == 1 && cubeNumberCount == 1)) return false;

        int squareNumberCount = 0;
        int fibonacciNumberCount = 0;
        int triangularNumberCount = 0;
        if (isSquareNumber(nineteenAcross)) squareNumberCount++;
        if (isPresent(nineteenAcross, THREE_DIGIT_FIBONACCI_NUMBERS)) fibonacciNumberCount++;
        if (isPresent(nineteenAcross, THREE_DIGIT_TRIANGULAR_NUMBERS)) triangularNumberCount++;
        if (isSquareNumber(twentyAcross)) squareNumberCount++;
        if (isPresent(twentyAcross, THREE_DIGIT_FIBONACCI_NUMBERS)) fibonacciNumberCount++;
        if (isPresent(twentyAcross, THREE_DIGIT_TRIANGULAR_NUMBERS)) triangularNumberCount++;
        if (isSquareNumber(twentyOneAcross)) squareNumberCount++;
        if (isPresent(twentyOneAcross, THREE_DIGIT_FIBONACCI_NUMBERS)) fibonacciNumberCount++;
        if (isPresent(twentyOneAcross, THREE_DIGIT_TRIANGULAR_NUMBERS)) triangularNumberCount++;

        return squareNumberCount == 1 && fibonacciNumberCount == 1 && triangularNumberCount == 1;
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

    public static boolean isSquareNumber(int number) {
        if (number < 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isCubeNumber(int number) {
        int absNumber = Math.abs(number);
        int cubeRoot = (int) Math.round(Math.cbrt(absNumber));
        return cubeRoot * cubeRoot * cubeRoot == absNumber;
    }

    public static int sum(int... values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    public static int prod(int... values) {
        int prod = 1;
        for (int value : values) {
            prod *= value;
        }
        return prod;
    }

    public static int[] func(Function<Integer, Integer> consumer, int... values) {
        int[] finalInts = new int[values.length];
        for (int i = 0; i < finalInts.length; i++) {
            finalInts[i] = consumer.apply(values[i]);
        }
        return finalInts;
    }

    public static boolean isPresent(int number, int[] set) {
        return Arrays.stream(set).anyMatch(value -> value == number);
    }

    public static void printNumberSets() {
        System.out.println("Happy Numbers: " + Arrays.toString(THREE_DIGIT_HAPPY_NUMBERS));
        System.out.println("Lucky Numbers: " + Arrays.toString(THREE_DIGIT_LUCKY_NUMBERS));
        System.out.println("Cube Numbers: " + Arrays.toString(THREE_DIGIT_CUBES));
        System.out.println("Lucas Numbers: " + Arrays.toString(THREE_DIGIT_LUCAS_NUMBERS));
        System.out.println("Cullen Numbers: " + Arrays.toString(THREE_DIGIT_CULLEN_NUMBERS));
        System.out.println("Squares Numbers: " + Arrays.toString(THREE_DIGIT_SQUARES));
        System.out.println("Triangular Numbers: " + Arrays.toString(THREE_DIGIT_TRIANGULAR_NUMBERS));
        System.out.println("Fibonacci Numbers: " + Arrays.toString(THREE_DIGIT_FIBONACCI_NUMBERS));
        System.out.println("Harshad Numbers: " + Arrays.toString(HARSHAD_NUMBERS));

        System.out.println("Numbers divisible by their factor count: " + Arrays.toString(DIVISIBLE_BY_DIVISORS));
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int factorCount(int number) {
        final int initialNumber = number;
        int factorCount = 1;
        for (int i = 2; i * i <= initialNumber; i++) {
            int power = 0;
            while (number % i == 0) {
                number /= i;
                power++;
            }
            factorCount *= (power + 1);
        }
        if (number > 1) {
            factorCount *= 2;
        }
        return factorCount;
    }

    public static int getDigitAtPosition(int number, int n) {
        String numberString = String.valueOf(number);
        char[] characters = numberString.toCharArray();
        return Integer.parseInt(String.valueOf(characters[n]));
    }

    public static int digitProduct(int number) {
        String numberString = String.valueOf(number);
        char[] characters = numberString.toCharArray();
        int prod = 1;
        for (char c : characters) {
            String character = String.valueOf(c);
            prod *= Integer.parseInt(character);
        }
        return prod;
    }

    public static int digitSum(int number) {
        String numberString = String.valueOf(number);
        char[] characters = numberString.toCharArray();
        int sum = 0;
        for (char c : characters) {
            String character = String.valueOf(c);
            sum += Integer.parseInt(character);
        }
        return sum;
    }

    public static int[] removeContainedZeroes(int[] initial) {
        List<Integer> zeroesRemoved = new ArrayList<>();
        for (int i : initial) {
            String s = String.valueOf(i);
            if (!s.contains("0")) {
                zeroesRemoved.add(i);
            }
        }
        return zeroesRemoved.stream().mapToInt(i -> i).toArray();
    }

    public static int[] generateHarshadNumbers() {
        List<Integer> harshadNumbers = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            if (i % digitSum(i) == 0) {
                harshadNumbers.add(i);
            }
        }
        return harshadNumbers.stream().mapToInt(i -> i).toArray();
    }

    public static int[] threeDigitsOnly(int[] initial) {
        List<Integer> threeDigits = new ArrayList<>();
        for (int number : initial) {
            if (number >= 100) {
                threeDigits.add(number);
            }
        }
        return threeDigits.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] generateLuckyNumbers(int limit) {
        if (limit < 1) {
            return new int[0];
        }

        // Initialize the list of numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= limit; i += 2) { // Start with odd numbers only
            numbers.add(i);
        }

        int currentIndex = 1; // Start from the second number in the list
        while (currentIndex < numbers.size() && numbers.get(currentIndex) < numbers.size()) {
            int step = numbers.get(currentIndex);

            // Generate a list of indices to remove
            List<Integer> indicesToRemove = new ArrayList<>();
            for (int i = step - 1; i < numbers.size(); i += step) {
                indicesToRemove.add(i);
            }

            // Remove elements in reverse order to avoid index shifting
            for (int i = indicesToRemove.size() - 1; i >= 0; i--) {
                numbers.remove((int) indicesToRemove.get(i));
            }

            currentIndex++;
        }

        // Convert to array
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    public record GridPoint(int row, int col) {
        public static GridPoint of(int row, int col) {
            return new GridPoint(row, col);
        }
    }
}
