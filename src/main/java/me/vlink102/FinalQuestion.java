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
import java.util.function.Function;

public class FinalQuestion {
    private static final int[] THREE_DIGIT_HAPPY_NUMBERS = removeContainedZeroes(new int[]{100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998});
    private static final int[] THREE_DIGIT_LUCKY_NUMBERS = removeContainedZeroes(threeDigitsOnly(generateLuckyNumbers(1000)));
    private static final int[] THREE_DIGIT_CUBES = removeContainedZeroes(new int[]{125, 216, 343, 512, 729});
    private static final int[] THREE_DIGIT_LUCAS_NUMBERS = removeContainedZeroes(new int[]{123, 199, 322, 521, 843});
    private static final int[] THREE_DIGIT_CULLEN_NUMBERS = removeContainedZeroes(new int[]{141, 161, 385, 897});
    private static final int[] THREE_DIGIT_SQUARES = removeContainedZeroes(new int[]{100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961});
    private static final int[] THREE_DIGIT_TRIANGULAR_NUMBERS = removeContainedZeroes(new int[]{105, 120, 136, 153, 171, 190, 210, 231, 253, 276, 300, 325, 351, 378, 406, 435, 465, 496, 528, 561, 595, 630, 666, 703, 741, 780, 820, 861, 903, 946, 990});
    private static final int[] THREE_DIGIT_FIBONACCI_NUMBERS = removeContainedZeroes(new int[]{144, 233, 377, 610, 987});
    private static final int[] HARSHAD_NUMBERS = {100, 102, 108, 110, 111, 112, 114, 117, 120, 126, 132, 133, 135, 140, 144, 150, 152, 153, 156, 162, 171, 180, 190, 192, 195, 198, 200, 201, 204, 207, 209, 210, 216, 220, 222, 224, 225, 228, 230, 234, 240, 243, 247, 252, 261, 264, 266, 270, 280, 285, 288, 300, 306, 308, 312, 315, 320, 322, 324, 330, 333, 336, 342, 351, 360, 364, 370, 372, 375, 378, 392, 396, 399, 400, 402, 405, 407, 408, 410, 414, 420, 423, 432, 440, 441, 444, 448, 450, 460, 465, 468, 476, 480, 481, 486, 500, 504, 506, 510, 511, 512, 513, 516, 518, 522, 531, 540, 550, 552, 555, 558, 576, 588, 592, 594, 600, 603, 605, 612, 621, 624, 629, 630, 640, 644, 645, 648, 660, 666, 684, 690, 700, 702, 704, 711, 715, 720, 730, 732, 735, 736, 738, 756, 770, 774, 777, 780, 782, 792, 800, 801, 803, 804, 810, 820, 825, 828, 832, 840, 846, 864, 870, 874, 880, 882, 888, 900, 902, 910, 912, 915, 918, 935, 936, 954, 960, 966, 972, 990, 999};//generateHarshadNumbers();

    private static final int[] FOUR_DIGIT_PRIMES = removeContainedZeroes(new int[] {1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287, 2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423, 2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083, 3089, 3109, 3119, 3121, 3137, 3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257, 3259, 3271, 3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331, 3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413, 3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571, 3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643, 3659, 3671, 3673, 3677, 3691, 3697, 3701, 3709, 3719, 3727, 3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803, 3821, 3823, 3833, 3847, 3851, 3853, 3863, 3877, 3881, 3889, 3907, 3911, 3917, 3919, 3923, 3929, 3931, 3943, 3947, 3967, 3989, 4001, 4003, 4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057, 4073, 4079, 4091, 4093, 4099, 4111, 4127, 4129, 4133, 4139, 4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231, 4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283, 4289, 4297, 4327, 4337, 4339, 4349, 4357, 4363, 4373, 4391, 4397, 4409, 4421, 4423, 4441, 4447, 4451, 4457, 4463, 4481, 4483, 4493, 4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123, 8147, 8161, 8167, 8171, 8179, 8191, 8209, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287, 8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443, 8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623, 8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741, 8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887, 8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043, 9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203, 9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349, 9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479, 9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649, 9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803, 9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973});

    private static final int[] DIVISIBLE_BY_DIVISORS = {128, 132, 136, 152, 156, 184, 225, 228, 232, 248, 252, 276, 288, 296, 328, 344, 348, 372, 376, 384, 396, 424, 441, 444, 448, 468, 472, 488, 492, 516, 536, 564, 568, 584, 612, 625, 632, 636, 664, 672, 684, 712, 732, 776, 792, 824, 828, 852, 856, 864, 872, 876, 882, 896, 936, 948, 972, 996};//threeDigitsOnly(removeContainedZeroes(divisibleByDivisors()));

    public FinalQuestion() throws IOException {
        printNumberSoup(new int[4][9]);
        //numberSoup2();
    }

    public static void main(String[] args) throws IOException {
        printNumberSets();
        runTests();
        new FinalQuestion();
    }

    public static void runTests() {
        Assert.assertEquals(condense(1, 2, 3), 123);
        Assert.assertEquals(condense(1, 2), 12);
        Assert.assertEquals(condense(1), 1);
        Assert.assertEquals(condense(9, 9, 9, 9, 9, 9), 999999);

        Integer[][] testMatrix = new Integer[][]{
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

        Assert.assertArrayEquals(getFactors(10), new int[]{1, 2, 5, 10});
        Assert.assertArrayEquals(getFactors(20), new int[]{1, 2, 4, 5, 10, 20});

        Assert.assertTrue(isSquareNumber(36));
        Assert.assertFalse(isSquareNumber(2));
        Assert.assertTrue(isCubeNumber(125));
        Assert.assertFalse(isCubeNumber(2));

        Assert.assertArrayEquals(getPrimeFactors(10), new int[] {2, 5});
        Assert.assertArrayEquals(getPrimeFactors(17), new int[] {17});
        Assert.assertArrayEquals(getPrimeFactors(24), new int[] {2, 2, 2, 3});

        Assert.assertEquals(getDigitAtPosition(100, 1), 0);
        Assert.assertEquals(getDigitAtPosition(123, 2), 3);
        Assert.assertEquals(getDigitAtPosition(123, 0), 1);
        Assert.assertEquals(getDigitAtPosition(9, 0), 9);

        Assert.assertTrue(areUnique(new int[] {1,2,3}, new int[]{4,5,6}, new int[]{7,8,9}));
        Assert.assertFalse(areUnique(new int[] {1,2,3}, new int[]{4,5,6}, new int[]{1,2,3}));


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

    private static String convertToLocation(int[][] grid) {
        return ((10 * grid[0][0]) + grid[0][1]) + "°" + ((10 * grid[0][2]) + grid[1][2]) + "'00.0\"N " + ((10 * grid[2][2]) + grid[2][1]) + "°" + ((10 * grid[2][0]) + grid[1][0]) + "'00.0W";
    }

    public class TextFieldUnique extends JTextField {
        @Getter
        private final GridPoint gridPoint;

        public TextFieldUnique(GridPoint gridPoint) {
            super();
            this.gridPoint = gridPoint;
        }
    }

    public static final Integer[][] modifiableGrid = new Integer[4][9];

    static {
        for (Integer[] integers : modifiableGrid) {
            Arrays.fill(integers, 1);
        }
    }

    private void printNumberSoup(int[][] grid) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/0b3d6f96cc20ea9fa5ac36b10b9934af.png"));
        ImageIcon icon = new ImageIcon(myPicture);
        Image background = icon.getImage();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(511, 355));
        frame.setResizable(false);
        frame.setTitle("Number Soup");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0,0, 511, 334, this);
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
            for (int col = 0; col < 9; col++) {
                constraints.gridx = col;
                TextFieldUnique cell = new TextFieldUnique(new GridPoint(row, col));
                if (grid[row][col] != 0) {
                    cell.setText(String.valueOf(grid[row][col]));
                }

                cell.setPreferredSize(new Dimension(57, 89));
                cell.setOpaque(false);
                cell.setBorder(null);
                cell.setHorizontalAlignment(JTextField.RIGHT);
                cell.setBackground(new Color(0,0,0,0));
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
                            printNumberSoupConsole(modifiableGrid);
                        } catch (InvalidSoupException soupException) {
                            soupException.printStackTrace();
                        } finally {

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

    public static boolean areUnique(int... values) {
        return Arrays.stream(values).distinct().count() == values.length;
    }

    public static boolean areUnique(int[]... arrays) {
        List<Integer> finalArray = new ArrayList<>();
        for (int[] array : arrays) {
            for (int i : array) {
                finalArray.add(i);
            }
        }
        return Arrays.stream(finalArray.toArray()).distinct().count() == finalArray.size();
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

    public static List<int[]> topRow = generateTopRow();
    public static List<int[]> bottomRow = generateBottomRow();

    public static final int[] SUM_CUBE_DIGITS_IS_SQUARE = generate4Down();

    public static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static int[] generate4Down() {
        java.util.List<Integer> validNumbers = new java.util.ArrayList<>();

        // Iterate over all 4-digit numbers
        for (int num = 1000; num <= 9999; num++) {
            // Extract the digits of the number
            int digit1 = num / 1000;      // First digit
            int digit2 = (num / 100) % 10; // Second digit
            int digit3 = (num / 10) % 10;  // Third digit
            int digit4 = num % 10;         // Fourth digit

            // Calculate the sum of the cubes of the digits
            int sumOfCubes = (int) (Math.pow(digit1, 3) + Math.pow(digit2, 3) +
                    Math.pow(digit3, 3) + Math.pow(digit4, 3));

            // Check if the sum of cubes is a perfect square
            if (isPerfectSquare(sumOfCubes)) {
                validNumbers.add(num); // If true, add the number to the list
            }
        }

        // Convert the ArrayList to an int[] array
        return convertListToIntArray(validNumbers);
    }

    public static List<int[]> generateTopRow() {
        List<int[]> valid = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generatePermutations(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), current, new boolean[9], new Runnable() {
            @Override
            public void run() {
                int[] digits = new int[] {current.get(0), current.get(1), current.get(2), current.get(3), current.get(4), current.get(5), current.get(6), current.get(7), current.get(8)};
                if (!areUnique(digits)) return;
                int num1 = condense(digits[0], digits[1], digits[2]);
                int num2 = condense(digits[3], digits[4], digits[5]);
                int num3 = condense(digits[6], digits[7], digits[8]);

                boolean c1 = isPresent(num1, THREE_DIGIT_CUBES);
                boolean c2 = isPresent(num2, THREE_DIGIT_CUBES);
                boolean c3 = isPresent(num3, THREE_DIGIT_CUBES);
                boolean l1 = isPresent(num1, THREE_DIGIT_LUCKY_NUMBERS);
                boolean l2 = isPresent(num2, THREE_DIGIT_LUCKY_NUMBERS);
                boolean l3 = isPresent(num3, THREE_DIGIT_LUCKY_NUMBERS);
                boolean h1 = isPresent(num1, THREE_DIGIT_HAPPY_NUMBERS);
                boolean h2 = isPresent(num2, THREE_DIGIT_HAPPY_NUMBERS);
                boolean h3 = isPresent(num3, THREE_DIGIT_HAPPY_NUMBERS);

                if ((c1 || c2 || c3) && (l1 || l2 || l3) && (h1 || h2 || h3)) {
                    valid.add(convertListToIntArray(current));
                }
            }
        });
        return valid;
    }

    public static List<int[]> generateBottomRow() {
        List<int[]> valid = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generatePermutations(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), current, new boolean[9], new Runnable() {
            @Override
            public void run() {
                int[] digits = new int[] {current.get(0), current.get(1), current.get(2), current.get(3), current.get(4), current.get(5), current.get(6), current.get(7), current.get(8)};
                if (!areUnique(digits)) return;
                int num1 = condense(digits[0], digits[1], digits[2]);
                int num2 = condense(digits[3], digits[4], digits[5]);
                int num3 = condense(digits[6], digits[7], digits[8]);

                boolean c1 = isPresent(num1, THREE_DIGIT_SQUARES);
                boolean c2 = isPresent(num2, THREE_DIGIT_SQUARES);
                boolean c3 = isPresent(num3, THREE_DIGIT_SQUARES);
                boolean l1 = isPresent(num1, THREE_DIGIT_TRIANGULAR_NUMBERS);
                boolean l2 = isPresent(num2, THREE_DIGIT_TRIANGULAR_NUMBERS);
                boolean l3 = isPresent(num3, THREE_DIGIT_TRIANGULAR_NUMBERS);
                boolean h1 = isPresent(num1, THREE_DIGIT_FIBONACCI_NUMBERS);
                boolean h2 = isPresent(num2, THREE_DIGIT_FIBONACCI_NUMBERS);
                boolean h3 = isPresent(num3, THREE_DIGIT_FIBONACCI_NUMBERS);

                if ((c1 || c2 || c3) && (l1 || l2 || l3) && (h1 || h2 || h3)) {
                    valid.add(convertListToIntArray(current));
                }
            }
        });
        return valid;
    }

    public static int[] convertListToIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static final int[][][] topRowWB = {{{125}, {367}, {489}}, {{125}, {367}, {498}}, {{125}, {367}, {849}}, {{125}, {367}, {894}}, {{125}, {367}, {948}}, {{125}, {367}, {984}}, {{125}, {469}, {873}}, {{125}, {478}, {639}}, {{125}, {478}, {693}}, {{125}, {489}, {367}}, {{125}, {498}, {367}}, {{125}, {639}, {478}}, {{125}, {693}, {478}}, {{125}, {849}, {367}}, {{125}, {873}, {469}}, {{125}, {894}, {367}}, {{125}, {948}, {367}}, {{125}, {984}, {367}}, {{145}, {368}, {729}}, {{145}, {729}, {368}}, {{148}, {356}, {729}}, {{148}, {729}, {356}}, {{154}, {368}, {729}}, {{154}, {729}, {368}}, {{184}, {356}, {729}}, {{184}, {729}, {356}}, {{356}, {148}, {729}}, {{356}, {184}, {729}}, {{356}, {418}, {729}}, {{356}, {481}, {729}}, {{356}, {729}, {148}}, {{356}, {729}, {184}}, {{356}, {729}, {418}}, {{356}, {729}, {481}}, {{356}, {729}, {814}}, {{356}, {729}, {841}}, {{356}, {814}, {729}}, {{356}, {841}, {729}}, {{367}, {125}, {489}}, {{367}, {125}, {498}}, {{367}, {125}, {849}}, {{367}, {125}, {894}}, {{367}, {125}, {948}}, {{367}, {125}, {984}}, {{367}, {489}, {125}}, {{367}, {489}, {512}}, {{367}, {498}, {125}}, {{367}, {498}, {512}}, {{367}, {512}, {489}}, {{367}, {512}, {498}}, {{367}, {512}, {849}}, {{367}, {512}, {894}}, {{367}, {512}, {948}}, {{367}, {512}, {984}}, {{367}, {849}, {125}}, {{367}, {849}, {512}}, {{367}, {894}, {125}}, {{367}, {894}, {512}}, {{367}, {948}, {125}}, {{367}, {948}, {512}}, {{367}, {984}, {125}}, {{367}, {984}, {512}}, {{368}, {145}, {729}}, {{368}, {154}, {729}}, {{368}, {415}, {729}}, {{368}, {451}, {729}}, {{368}, {514}, {729}}, {{368}, {541}, {729}}, {{368}, {729}, {145}}, {{368}, {729}, {154}}, {{368}, {729}, {415}}, {{368}, {729}, {451}}, {{368}, {729}, {514}}, {{368}, {729}, {541}}, {{415}, {368}, {729}}, {{415}, {729}, {368}}, {{418}, {356}, {729}}, {{418}, {729}, {356}}, {{451}, {368}, {729}}, {{451}, {729}, {368}}, {{469}, {125}, {873}}, {{469}, {512}, {873}}, {{469}, {873}, {125}}, {{469}, {873}, {512}}, {{478}, {125}, {639}}, {{478}, {125}, {693}}, {{478}, {512}, {639}}, {{478}, {512}, {693}}, {{478}, {639}, {125}}, {{478}, {639}, {512}}, {{478}, {693}, {125}}, {{478}, {693}, {512}}, {{481}, {356}, {729}}, {{481}, {729}, {356}}, {{489}, {125}, {367}}, {{489}, {367}, {125}}, {{489}, {367}, {512}}, {{489}, {512}, {367}}, {{498}, {125}, {367}}, {{498}, {367}, {125}}, {{498}, {367}, {512}}, {{498}, {512}, {367}}, {{512}, {367}, {489}}, {{512}, {367}, {498}}, {{512}, {367}, {849}}, {{512}, {367}, {894}}, {{512}, {367}, {948}}, {{512}, {367}, {984}}, {{512}, {469}, {873}}, {{512}, {478}, {639}}, {{512}, {478}, {693}}, {{512}, {489}, {367}}, {{512}, {498}, {367}}, {{512}, {639}, {478}}, {{512}, {693}, {478}}, {{512}, {849}, {367}}, {{512}, {873}, {469}}, {{512}, {894}, {367}}, {{512}, {948}, {367}}, {{512}, {984}, {367}}, {{514}, {368}, {729}}, {{514}, {729}, {368}}, {{541}, {368}, {729}}, {{541}, {729}, {368}}, {{639}, {125}, {478}}, {{639}, {478}, {125}}, {{639}, {478}, {512}}, {{639}, {512}, {478}}, {{693}, {125}, {478}}, {{693}, {478}, {125}}, {{693}, {478}, {512}}, {{693}, {512}, {478}}, {{729}, {145}, {368}}, {{729}, {148}, {356}}, {{729}, {154}, {368}}, {{729}, {184}, {356}}, {{729}, {356}, {148}}, {{729}, {356}, {184}}, {{729}, {356}, {418}}, {{729}, {356}, {481}}, {{729}, {356}, {814}}, {{729}, {356}, {841}}, {{729}, {368}, {145}}, {{729}, {368}, {154}}, {{729}, {368}, {415}}, {{729}, {368}, {451}}, {{729}, {368}, {514}}, {{729}, {368}, {541}}, {{729}, {415}, {368}}, {{729}, {418}, {356}}, {{729}, {451}, {368}}, {{729}, {481}, {356}}, {{729}, {514}, {368}}, {{729}, {541}, {368}}, {{729}, {814}, {356}}, {{729}, {841}, {356}}, {{814}, {356}, {729}}, {{814}, {729}, {356}}, {{841}, {356}, {729}}, {{841}, {729}, {356}}, {{849}, {125}, {367}}, {{849}, {367}, {125}}, {{849}, {367}, {512}}, {{849}, {512}, {367}}, {{873}, {125}, {469}}, {{873}, {469}, {125}}, {{873}, {469}, {512}}, {{873}, {512}, {469}}, {{894}, {125}, {367}}, {{894}, {367}, {125}}, {{894}, {367}, {512}}, {{894}, {512}, {367}}, {{948}, {125}, {367}}, {{948}, {367}, {125}}, {{948}, {367}, {512}}, {{948}, {512}, {367}}, {{984}, {125}, {367}}, {{984}, {367}, {125}}, {{984}, {367}, {512}}, {{984}, {512}, {367}}};
    public static final int[][][] bottomRowWB = {{{324}, {561}, {987}}, {{324}, {987}, {561}}, {{561}, {324}, {987}}, {{561}, {987}, {324}}, {{987}, {324}, {561}}, {{987}, {561}, {324}}};


    public void numberSoup2() {
        Integer a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0, a8 = 0, a9 = 0;
        Integer b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0, b6 = 0, b7 = 0, b8 = 0, b9 = 0;
        Integer c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0, c7 = 0, c8 = 0, c9 = 0;
        Integer d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0;

        for (int[] topInts : topRow) {
            int oneAcross = condense(topInts[0], topInts[1], topInts[2]);
            int threeAcross = condense(topInts[3], topInts[4], topInts[5]);
            int sixAcross = condense(topInts[6], topInts[7], topInts[8]);

            if (areUnique(topInts)) {
                a1 = topInts[0]; a2 = topInts[1]; a3 = topInts[2];
                a4 = topInts[3]; a5 = topInts[4]; a6 = topInts[5];
                a7 = topInts[6]; a8 = topInts[7]; a9 = topInts[8];

                for (int[] bottomInts : bottomRow) {
                    int nineteenAcross = condense(bottomInts[0], bottomInts[1], bottomInts[2]);
                    int twentyAcross = condense(bottomInts[3], bottomInts[4], bottomInts[5]);
                    int twentyOneAcross = condense(bottomInts[6], bottomInts[7], bottomInts[8]);

                    if (areUnique(bottomInts)) {
                        d1 = bottomInts[0]; d2 = bottomInts[1]; d3 = bottomInts[2];
                        d4 = bottomInts[3]; d5 = bottomInts[4]; d6 = bottomInts[5];
                        d7 = bottomInts[6]; d8 = bottomInts[7]; d9 = bottomInts[8];

                        int digitSum19ac = digitSum(nineteenAcross);
                        int digitSum20ac = digitSum(twentyAcross);
                        int digitSum21ac = digitSum(twentyOneAcross);
                        int bottomRowSum = digitSum19ac + digitSum20ac + digitSum21ac;

                        for (int c1i = 1; c1i <= 9; c1i++) {
                            c1 = c1i;
                            int fourteenDown = (c1 * 10) + d1;

                            // Check if divisible
                            if (bottomRowSum % fourteenDown == 0) {

                                // 7dn
                                for (int b9i = 1; b9i <= 9; b9i++) {
                                    b9 = b9i;
                                    int sevenDown = (a9 * 10) + b9;
                                    if (threeAcross % sevenDown == 0 && isPrime(sevenDown)) {
                                        // 4dn
                                        for (int b5i = 1; b5i <= 9; b5i++) {
                                            b5 = b5i;
                                            for (int c5i = 1; c5i <= 9; c5i++) {
                                                c5 = c5i;
                                                int a5Cubed = (int) Math.pow(a5, 3);
                                                int b5Cubed = (int) Math.pow(b5, 3);
                                                int c5Cubed = (int) Math.pow(c5, 3);
                                                int d5Cubed = (int) Math.pow(d5, 3);

                                                int sumOfDigits = a5Cubed + b5Cubed + c5Cubed + d5Cubed;
                                                if (isPerfectSquare(sumOfDigits)) {

                                                    for (int b3i = 1; b3i <= 9; b3i++) {
                                                        b3 = b3i;
                                                        for (int c3i = 1; c3i <= 9; c3i++) {
                                                            c3 = c3i;

                                                            if (isPrime(condense(a3, b3, c3, d3))) {
                                                                for (int b7i = 1; b7i <= 9; b7i++) {
                                                                    b7 = b7i;
                                                                    for (int c7i = 1; c7i <= 9; c7i++) {
                                                                        c7 = c7i;

                                                                        if (isPrime(condense(a7, b7, c7, d7))) {
                                                                            // 16ac and 10ac
                                                                            for (int b4i = 1; b4i <= 9; b4i++) {
                                                                                b4 = b4i;
                                                                                int tenAcross = (100 * b3) + (10 * b4) + b5;
                                                                                if (tenAcross % getFactors(tenAcross).length == 0) {
                                                                                    for (int c6i = 1; c6i <= 9; c6i++) {
                                                                                        c6 = c6i;
                                                                                        int sixteenAcross = (100 * c5) + (10 * c6) + c7;
                                                                                        if (sixteenAcross % getFactors(sixteenAcross).length == 0) {
                                                                                            // 5dn and 11dn

                                                                                            for (int c4i = 1; c4i <= 9; c4i++) {
                                                                                                c4 = c4i;

                                                                                                int elevenDown = (100 * b4) + (10 * c4) + d4;
                                                                                                for (int b6i = 1; b6i <= 9; b6i++) {
                                                                                                    b6 = b6i;
                                                                                                    int fiveDown = (100 * a6) + (10 * b6) + c6;
                                                                                                    for (int harshadNumber : HARSHAD_NUMBERS) {
                                                                                                        if (elevenDown - harshadNumber == fiveDown) {
                                                                                                            for (int c2i = 1; c2i <= 9; c2i++) {
                                                                                                                c2 = c2i;
                                                                                                                int fifteenAcross = (100 * c2) + (10 * c3) + c4;
                                                                                                                if (isPresent(fifteenAcross, THREE_DIGIT_CULLEN_NUMBERS)) {
                                                                                                                    for (int b8i = 1; b8i <= 9; b8i++) {
                                                                                                                        b8 = b8i;
                                                                                                                        int twelveAcross = (100 * b6) + (10 * b7) + b8;
                                                                                                                        if (isPresent(twelveAcross, THREE_DIGIT_LUCAS_NUMBERS)) {
                                                                                                                            // 17ac and 8ac

                                                                                                                            for (int b1i = 1; b1i <= 9; b1i++) {
                                                                                                                                b1 = b1i;
                                                                                                                                int oneDown = (a1 * 10) + b1;
                                                                                                                                if (twentyAcross % oneDown == 0) {
                                                                                                                                    for (int b2i = 1; b2i <= 9; b2i++) {
                                                                                                                                        b2 = b2i;

                                                                                                                                        int eightAcross = (b1 * 10) + b2;
                                                                                                                                        int nineDown = (b2 * 10) + c2;

                                                                                                                                        if (eightAcross == oneDown - nineDown) {
                                                                                                                                            for (int c8i = 1; c8i <= 9; c8i++) {
                                                                                                                                                c8 = c8i;
                                                                                                                                                int thirteenDown = (b8 * 10) + c8;
                                                                                                                                                for (int c9i = 1; c9i <= 9; c9i++) {
                                                                                                                                                    c9 = c9i;
                                                                                                                                                    int seventeenAcross = (c8 * 10) + c9;
                                                                                                                                                    int eighteenDown = (c9 * 10) + d9;

                                                                                                                                                    if (seventeenAcross == eighteenDown - thirteenDown) {
                                                                                                                                                        printNumberSoupConsole(new Integer[][] {
                                                                                                                                                                {a1, a2, a3, a4, a5, a6, a7, a8, a9},
                                                                                                                                                                {b1, b2, b3, b4, b5, b6, b7, b8, b9},
                                                                                                                                                                {c1, c2, c3, c4, c5, c6, c7, c8, c9},
                                                                                                                                                                {d1, d2, d3, d4, d5, d6, d7, d8, d9}
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


                            }
                        }
                    }
                }
            }
        }

        /*int possibleSolutions = 0;
        for (int[][] rowArray : topRowWB) {
            int oneAcross = rowArray[0][0];
            int threeAcross = rowArray[1][0];
            int sixAcross = rowArray[2][0];
            int[] digits1ac = getDigits(oneAcross);
            int[] digits3ac = getDigits(threeAcross);
            int[] digits6ac = getDigits(sixAcross);
            if (areUnique(digits1ac, digits3ac, digits6ac)) {
                a1 = digits1ac[0]; a2 = digits1ac[1]; a3 = digits1ac[2];
                a4 = digits3ac[0]; a5 = digits3ac[1]; a6 = digits3ac[2];
                a7 = digits6ac[0]; a8 = digits6ac[1]; a9 = digits6ac[2];

                for (int[][] ints : bottomRowWB) {
                    int nineteenAcross = ints[0][0];
                    int twentyAcross = ints[1][0];
                    int twentyOneAcross = ints[2][0];
                    int[] digits19ac = getDigits(nineteenAcross);
                    int[] digits20ac = getDigits(twentyAcross);
                    int[] digits21ac = getDigits(twentyOneAcross);
                    if (areUnique(digits19ac, digits20ac, digits21ac)) {
                        d1 = digits19ac[0]; d2 = digits19ac[1]; d3 = digits19ac[2];
                        d4 = digits20ac[0]; d5 = digits20ac[1]; d6 = digits20ac[2];
                        d7 = digits21ac[0]; d8 = digits21ac[1]; d9 = digits21ac[2];

                        // 14dn



                    }
                }
            }
        }*/
    }


    public void numberSoup() {
        Integer a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,
                b1=0,b2=0,b3=0,b4=0,b5=0,b6=0,b7=0,b8=0,b9=0,
                c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0,
                d1=0,d2=0,d3=0,d4=0,d5=0,d6=0,d7=0,d8=0,d9=0;
        System.out.println(Arrays.toString(SUM_CUBE_DIGITS_IS_SQUARE));
        Integer possibleSolutions = 0;
        for (int[] topRowDigits : topRow) {
            a1 = topRowDigits[0];
            a2 = topRowDigits[1];
            a3 = topRowDigits[2];
            a4 = topRowDigits[3];
            a5 = topRowDigits[4];
            a6 = topRowDigits[5];
            a7 = topRowDigits[6];
            a8 = topRowDigits[7];
            a9 = topRowDigits[8];

            int oneAcross = condense(a1,a2,a3);
            int threeAcross = condense(a4,a5,a6);
            int sixAcross = condense(a7,a8,a9);

            for (int[] bottomRowDigits : bottomRow) {
                d1 = bottomRowDigits[0];
                d2 = bottomRowDigits[1];
                d3 = bottomRowDigits[2];
                d4 = bottomRowDigits[3];
                d5 = bottomRowDigits[4];
                d6 = bottomRowDigits[5];
                d7 = bottomRowDigits[6];
                d8 = bottomRowDigits[7];
                d9 = bottomRowDigits[8];

                int nineteenAcross = condense(d1,d2,d3);
                int twentyAcross = condense(d4,d5,d6);
                int twentyOneAcross = condense(d7,d8,d9);

                for (int cellB1Iterator = 1; cellB1Iterator <= 9; cellB1Iterator++) {
                    b1 = cellB1Iterator;
                    int oneDown = (a1 * 10) + b1;
                    if (twentyAcross % oneDown == 0) {
                        for (int cellB2Iterator = 1; cellB2Iterator <= 9; cellB2Iterator++) {
                            b2 = cellB2Iterator;

                            int eightAcross = (b1 * 10) + b2;

                            for (int cellC2Iterator = 1; cellC2Iterator <= 9; cellC2Iterator++) {
                                c2 = cellC2Iterator;

                                int nineDown = (b2 * 10) + c2;

                                if (eightAcross == oneDown - nineDown) {
                                    // 2dn, 6dn
                                    for (int twoDownPrime : FOUR_DIGIT_PRIMES) {
                                        int[] twoDownDigits = getDigits(twoDownPrime);
                                        if (a3 != twoDownDigits[0]) continue;
                                        if (d3 != twoDownDigits[3]) continue;

                                        b3 = twoDownDigits[1];
                                        c3 = twoDownDigits[2];

                                        for (int sixDownPrime : FOUR_DIGIT_PRIMES) {
                                            int[] sixDownDigits = getDigits(sixDownPrime);
                                            if (a7 != sixDownDigits[0]) continue;
                                            if (d7 != sixDownDigits[3]) continue;

                                            b7 = sixDownDigits[1];
                                            c7 = sixDownDigits[2];

                                            // 15ac
                                            for (int cellC4Iterator = 1; cellC4Iterator <= 9; cellC4Iterator++) {
                                                c4 = cellC4Iterator;
                                                int fifteenAcross = (100 * c2) + (10 * c3) + c4;
                                                if (isPresent(fifteenAcross, THREE_DIGIT_CULLEN_NUMBERS)) {

                                                    // 14dn
                                                    int sum19ac20ac21ac = digitSum(nineteenAcross) + digitSum(twentyAcross) + digitSum(twentyOneAcross);
                                                    for (int cellC1Iterator = 1; cellC1Iterator <= 9; cellC1Iterator++) {
                                                        c1 = cellC1Iterator;
                                                        int fourteenDown = (10 * c1) + d1;
                                                        if (sum19ac20ac21ac % fourteenDown == 0) {

                                                            // 4dn

                                                            for (int sumCubeDigitsSquare : SUM_CUBE_DIGITS_IS_SQUARE) {
                                                                int[] sumCubeDigits = getDigits(sumCubeDigitsSquare);
                                                                b5 = sumCubeDigits[1];
                                                                c5 = sumCubeDigits[2];

                                                                // 10ac
                                                                for (int cellB4Iterator = 1; cellB4Iterator <= 9; cellB4Iterator++) {
                                                                    b4 = cellB4Iterator;
                                                                    int tenAcross = (100 * b3) + (10 * b4) + b5;
                                                                    if (isPresent(tenAcross, DIVISIBLE_BY_DIVISORS)) {
                                                                        for (int cellC6Iterator = 1; cellC6Iterator <= 9; cellC6Iterator++) {
                                                                            c6 = cellC6Iterator;
                                                                            int sixteenAcross = (100 * c5) + (10 * c6) + c7;
                                                                            if (isPresent(sixteenAcross, DIVISIBLE_BY_DIVISORS)) {
                                                                                // 5dn

                                                                                for (int cellB6Iterator = 1; cellB6Iterator <= 9; cellB6Iterator++) {
                                                                                    b6 = cellB6Iterator;
                                                                                    int fiveDown = (100 * a6) + (10 * b6) + c6;
                                                                                    int elevenDown = (100 * b4) + (10 * c4) + d4;
                                                                                    for (int harshadNumber : HARSHAD_NUMBERS) {
                                                                                        if (fiveDown == elevenDown - harshadNumber) {
                                                                                            for (int cellB8Iterator = 1; cellB8Iterator <= 9; cellB8Iterator++) {
                                                                                                b8 = cellB8Iterator;
                                                                                                int twelveAcross = (100 * b6) + (10 * b7) + b8;
                                                                                                if (isPresent(twelveAcross, THREE_DIGIT_LUCAS_NUMBERS)) {
                                                                                                    for (int cellC8Iterator = 1; cellC8Iterator <= 9; cellC8Iterator++) {
                                                                                                        c8 = cellC8Iterator;
                                                                                                        for (int cellC9Iterator = 1; cellC9Iterator <= 9; cellC9Iterator++) {
                                                                                                            c9 = cellC9Iterator;

                                                                                                            int seventeenAcross = (10 * c8) + c9;
                                                                                                            int thirteenDown = (b8 * 10) + c8;
                                                                                                            int eighteenDown = (c9 * 10) + d9;
                                                                                                            if (seventeenAcross == eighteenDown - thirteenDown) {
                                                                                                                // 7dn
                                                                                                                for (int cellB9Iterator = 1; cellB9Iterator <= 9; cellB9Iterator++) {
                                                                                                                    b9 = cellB9Iterator;
                                                                                                                    int sevenDown = (10 * a9) + b9;
                                                                                                                    if (threeAcross % sevenDown == 0) {

                                                                                                                        if (isPrime(sevenDown)) {
                                                                                                                            printNumberSoupConsole(a1,a2,a3,a4,a5,a6,a7,a8,a9,b1,b2,b3,b4,b5,b6,b7,b8,b9,c1,c2,c3,c4,c5,c6,c7,c8,c9,d1,d2,d3,d4,d5,d6,d7,d8,d9);
                                                                                                                            possibleSolutions++;
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
        System.out.println(possibleSolutions);

        /*List<Integer> current = new ArrayList<>();
        for (int i = 0; i < THREE_DIGIT_HAPPY_NUMBERS.length; i++) {
            for (int j = 0; j < THREE_DIGIT_LUCKY_NUMBERS.length; j++) {
                for (int k = 0; k < THREE_DIGIT_CUBES.length; k++) {
                    int[] array1Digits = getDigits(THREE_DIGIT_HAPPY_NUMBERS[i]);
                    int[] array2Digits = getDigits(THREE_DIGIT_LUCKY_NUMBERS[j]);
                    int[] array3Digits = getDigits(THREE_DIGIT_CUBES[k]);
                    if (!areUnique(array1Digits, array2Digits, array3Digits)) continue;
                    for (int l = 0; l < THREE_DIGIT_SQUARES.length; l++) {
                        for (int m = 0; m < THREE_DIGIT_TRIANGULAR_NUMBERS.length; m++) {
                            for (int n = 0; n < THREE_DIGIT_FIBONACCI_NUMBERS.length; n++) {
                                // Systematically assign numbers to the boxes
                                int[] array4Digits = getDigits(THREE_DIGIT_SQUARES[l]);
                                int[] array5Digits = getDigits(THREE_DIGIT_TRIANGULAR_NUMBERS[m]);
                                int[] array6Digits = getDigits(THREE_DIGIT_FIBONACCI_NUMBERS[n]);
                                if (!areUnique(array4Digits, array5Digits, array6Digits)) continue;

                                //System.out.println(THREE_DIGIT_HAPPY_NUMBERS[i] + " " + THREE_DIGIT_LUCKY_NUMBERS[j] + " " + THREE_DIGIT_CUBES[k] + " / " + THREE_DIGIT_SQUARES[l] + " " + THREE_DIGIT_TRIANGULAR_NUMBERS[m] + " " + THREE_DIGIT_FIBONACCI_NUMBERS[n]);
                                a1 = array1Digits[0];
                                a2 = array1Digits[1];
                                a3 = array1Digits[2];
                                a4 = array2Digits[0];
                                a5 = array2Digits[1];
                                a6 = array2Digits[2];
                                a7 = array3Digits[0];
                                a8 = array3Digits[1];
                                a9 = array3Digits[2];
                                d1 = array4Digits[0];
                                d2 = array4Digits[1];
                                d3 = array4Digits[2];
                                d4 = array5Digits[0];
                                d5 = array5Digits[1];
                                d6 = array5Digits[2];
                                d7 = array6Digits[0];
                                d8 = array6Digits[1];
                                d9 = array6Digits[2];

                                //printNumberSoupConsole(a1,a2,a3,a4,a5,a6,a7,a8,a9,b1,b2,b3,b4,b5,b6,b7,b8,b9,c1,c2,c3,c4,c5,c6,c7,c8,c9,d1,d2,d3,d4,d5,d6,d7,d8,d9);

                                for (int fourDigitPrime : FOUR_DIGIT_PRIMES) {
                                    int[] twoDownDigits = getDigits(fourDigitPrime);
                                    if (twoDownDigits[0] != a3 || twoDownDigits[3] != d3) continue;
                                    for (int fourDigitPrime2 : FOUR_DIGIT_PRIMES) {
                                        if (fourDigitPrime2 == fourDigitPrime) continue;
                                        int[] sixDownDigits = getDigits(fourDigitPrime2);
                                        if (sixDownDigits[0] != a6 || sixDownDigits[3] != d6) continue;

                                        b3 = twoDownDigits[1];
                                        c3 = twoDownDigits[2];

                                        b6 = sixDownDigits[1];
                                        c6 = sixDownDigits[2];

                                        printNumberSoupConsole(a1,a2,a3,a4,a5,a6,a7,a8,a9,b1,b2,b3,b4,b5,b6,b7,b8,b9,c1,c2,c3,c4,c5,c6,c7,c8,c9,d1,d2,d3,d4,d5,d6,d7,d8,d9);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }for (int threeDigitHappyNumber : THREE_DIGIT_HAPPY_NUMBERS) {
            for (int threeDigitLuckyNumber : THREE_DIGIT_LUCKY_NUMBERS) {
                for (int threeDigitCube : THREE_DIGIT_CUBES) {
                    // Systematically assign numbers to the boxes
                    int box1 = threeDigitHappyNumber;
                    int box2 = threeDigitLuckyNumber;
                    int box3 = threeDigitCube;

                    int[] box1Digits = getDigits(box1);
                    int[] box2Digits = getDigits(box2);
                    int[] box3Digits = getDigits(box3);


                    a1 = box1Digits[0];
                    a2 = box1Digits[1];
                    a3 = box1Digits[2];

                    a4 = box2Digits[0];
                    a5 = box2Digits[1];
                    a6 = box2Digits[2];

                    a7 = box3Digits[0];
                    a8 = box3Digits[1];
                    a9 = box3Digits[2];

                    for (int threeDigitSquare : THREE_DIGIT_SQUARES) {
                        for (int threeDigitTriangularNumber : THREE_DIGIT_TRIANGULAR_NUMBERS) {
                            for (int threeDigitFibonacciNumber : THREE_DIGIT_FIBONACCI_NUMBERS) {
                                int box4 = threeDigitSquare;
                                int box5 = threeDigitTriangularNumber;
                                int box6 = threeDigitFibonacciNumber;

                                int[] box4Digits = getDigits(box4);
                                int[] box5Digits = getDigits(box5);
                                int[] box6Digits = getDigits(box6);

                                if (!areUnique(box4Digits, box5Digits, box6Digits)) continue;

                                d1 = box4Digits[0];
                                d2 = box4Digits[1];
                                d3 = box4Digits[2];

                                d4 = box5Digits[0];
                                d5 = box5Digits[1];
                                d6 = box5Digits[2];

                                d7 = box6Digits[0];
                                d8 = box6Digits[1];
                                d9 = box6Digits[2];

                                // 1 Down
                                int[] factors20ac = getFactors(threeDigitTriangularNumber);
                                for (int cellB1Iterator = 1; cellB1Iterator <= 9; cellB1Iterator++) {
                                    b1 = cellB1Iterator;
                                    int oneDown = (10 * a1) + b1;

                                    if (isPresent(oneDown, factors20ac)) { // 1 down is factor of 20ac (therefore divisor)
                                        for (int cellB2Iterator = 1; cellB2Iterator <= 9; cellB2Iterator++) {
                                            b2 = cellB2Iterator;

                                            int eightAcross = (10 * b1) + b2;
                                            for (int cellC2Iterator = 1; cellC2Iterator <= 9; cellC2Iterator++) {
                                                c2 = cellC2Iterator;

                                                int nineDown = (10 * b2) + c2;
                                                if (eightAcross == oneDown - nineDown) {
                                                    possibleSolutions++;
                                                    printNumberSoupConsole(a1,a2,a3,a4,a5,a6,a7,a8,a9,b1,b2,b3,b4,b5,b6,b7,b8,b9,c1,c2,c3,c4,c5,c6,c7,c8,c9,d1,d2,d3,d4,d5,d6,d7,d8,d9);

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
        }*/

    }

    private void printNumberSoupConsole(int... v) {
        Integer[][] grid = new Integer[][] {
                {v[0], v[1], v[2], v[3], v[4], v[5], v[6], v[7], v[8]},
                {v[9], v[10],v[11],v[12],v[13],v[14],v[15],v[16],v[17]},
                {v[18],v[19],v[20],v[21],v[22],v[23],v[24],v[25],v[26]},
                {v[27],v[28],v[29],v[30],v[31],v[32],v[33],v[34],v[35]}
        };
        //checkEqualDigitCount(grid);
        //if (!checkEqualDigitCount(grid)) return;
        printNumberSoupConsole(grid);
    }

    public static boolean checkEqualDigitCount(Integer[][] grid) {
        // Map to store the frequency of each digit (0 to 9)
        Map<Integer, Integer> digitCount = new HashMap<>();

        // Iterate over the grid and count occurrences of each digit
        for (Integer[] row : grid) {
            for (int num : row) {
                digitCount.put(num, digitCount.getOrDefault(num, 0) + 1);
            }
        }

        // Get the first count value to compare with others
        Integer firstCount = null;
        for (int count : digitCount.values()) {
            if (firstCount == null) {
                firstCount = count;  // Set the first count
            } else if (!firstCount.equals(count)) {
                // If any count is different, return false
                return false;
            }
        }

        System.out.println(digitCount);
        // If all counts are equal, return true
        return true;
    }

    private String printCounts(HashMap<Integer,Integer> map) {
        StringJoiner joiner = new StringJoiner(", ");
        map.forEach((integer, integer2) -> {
            joiner.add(integer + "=" + integer2);
        });
        return ("{" + joiner + "}");
    }

    private void printNumberSoupConsole(Integer[][] grid) {
        //System.out.println("----------------------------------");
        //System.out.println("Testing Grid: " + Arrays.deepToString(grid));
        try {

            System.out.print(getResult(grid));
            System.out.print(Arrays.deepToString(grid));
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int i = 1; i <=9; i++) {
                counts.put(i, 0);
            }
            for (Integer[] integers : grid) {
                for (Integer integer : integers) {
                    counts.put(integer, counts.getOrDefault(integer, 0) + 1);
                }
            }
            int initial = 0;
            for (Integer value : counts.values()) {
                if (value == 0) continue;
                if (initial == 0) {
                    initial = value;
                } else {
                    if (!value.equals(initial)) {
                        System.out.print(", counts=" + ValidationResult.FALSE + " " + printCounts(counts) + "\n");
                        return;
                    }
                }

            }
            System.out.print(", counts=" + ValidationResult.TRUE + "\n");
        } catch (InvalidSoupException e) {
            //System.out.println("Invalid Grid: " + e.getMessage());
        }
        //System.out.println("----------------------------------");
    }

    public class InvalidSoupException extends AssertionError {
        public InvalidSoupException(String text) {
            super(text);
        }
    }

    @Setter
    @Getter
    public class ValidationResult {
        private boolean ac136;
        private boolean ac8;
        private boolean ac10;
        private boolean ac12;
        private boolean ac15;
        private boolean ac16;
        private boolean ac17;
        private boolean ac192021;
        private boolean dn1;
        private boolean dn2;
        private boolean dn4;
        private boolean dn5;
        private boolean dn6;
        private boolean dn7;
        private boolean dn14;

        public ValidationResult() {}
        public static final String FALSE = "\u001B[31mFALSE\u001B[0m";
        public static final String TRUE = "\u001B[32mTRUE\u001B[0m";

        @Override
        public String toString() {
            return (ac136 ? "ac136=" + TRUE : "ac136=" + FALSE) + ", " +
                    (ac8 ? "ac8=" + TRUE : "ac8=" + FALSE) + ", " +
                    (ac10 ? "ac10=" + TRUE : "ac10=" + FALSE) + ", " +
                    (ac12 ? "ac12=" + TRUE : "ac12=" + FALSE) + ", " +
                    (ac15 ? "ac15=" + TRUE : "ac15=" + FALSE) + ", " +
                    (ac16 ? "ac16=" + TRUE : "ac16=" + FALSE) + ", " +
                    (ac17 ? "ac17=" + TRUE : "ac17=" + FALSE) + ", " +
                    (ac192021 ? "ac192021=" + TRUE : "ac192021=" + FALSE) + ", " +
                    (dn1 ? "dn1=" + TRUE : "dn1=" + FALSE) + ", " +
                    (dn2 ? "dn2=" + TRUE : "dn2=" + FALSE) + ", " +
                    (dn4 ? "dn4=" + TRUE : "dn4=" + FALSE) + ", " +
                    (dn5 ? "dn5=" + TRUE : "dn5=" + FALSE) + ", " +
                    (dn6 ? "dn6=" + TRUE : "dn6=" + FALSE) + ", " +
                    (dn7 ? "dn7=" + TRUE : "dn7=" + FALSE) + ", " +
                    (dn14 ? "dn14=" + TRUE : "dn14=" + FALSE);
        }
    }

    public ValidationResult getResult(Integer[][] grid) {
        if (grid.length != 4 || grid[0].length != 9) return null;
        ValidationResult result = new ValidationResult();
        int oneAcross = extractVector(GridPoint.of(0, 0), GridPoint.of(0, 2), grid);
        System.out.print("[1ac: " + oneAcross + ", ");
        int threeAcross = extractVector(GridPoint.of(0, 3), GridPoint.of(0, 5), grid);
        System.out.print("3ac: " + threeAcross + ", ");
        int sixAcross = extractVector(GridPoint.of(0, 6), GridPoint.of(0, 8), grid);
        System.out.print("6ac: " + sixAcross + ", ");
        int eightAcross = extractVector(GridPoint.of(1, 0), GridPoint.of(1, 1), grid);
        System.out.print("8ac: " + eightAcross + ", ");
        int tenAcross = extractVector(GridPoint.of(1, 2), GridPoint.of(1, 4), grid);
        System.out.print("10ac: " + tenAcross + ", ");
        int twelveAcross = extractVector(GridPoint.of(1, 5), GridPoint.of(1, 7), grid);
        System.out.print("12ac: " + twelveAcross + ", ");
        int fifteenAcross = extractVector(GridPoint.of(2, 1), GridPoint.of(2, 3), grid);
        System.out.print("15ac: " + fifteenAcross + ", ");
        int sixteenAcross = extractVector(GridPoint.of(2, 4), GridPoint.of(2, 6), grid);
        System.out.print("16ac: " + sixteenAcross + ", ");
        int seventeenAcross = extractVector(GridPoint.of(2, 7), GridPoint.of(2, 8), grid);
        System.out.print("17ac: " + seventeenAcross + ", ");
        int nineteenAcross = extractVector(GridPoint.of(3, 0), GridPoint.of(3, 2), grid);
        System.out.print("19ac: " + nineteenAcross + ", ");
        int twentyAcross = extractVector(GridPoint.of(3, 3), GridPoint.of(3, 5), grid);
        System.out.print("20ac: " + twentyAcross + ", ");
        int twentyOneAcross = extractVector(GridPoint.of(3, 6), GridPoint.of(3, 8), grid);
        System.out.print("21ac: " + twentyOneAcross + ", ");

        int oneDown = extractVector(GridPoint.of(0, 0), GridPoint.of(1, 0), grid);
        System.out.print("1dn: " + oneDown + ", ");
        int twoDown = extractVector(GridPoint.of(0, 2), GridPoint.of(3, 2), grid);
        System.out.print("2dn: " + twoDown + ", ");
        int fourDown = extractVector(GridPoint.of(0, 4), GridPoint.of(3, 4), grid);
        System.out.print("4dn: " + fourDown + ", ");
        int fiveDown = extractVector(GridPoint.of(0, 5), GridPoint.of(2, 5), grid);
        System.out.print("5dn: " + fiveDown + ", ");
        int sixDown = extractVector(GridPoint.of(0, 6), GridPoint.of(3, 6), grid);
        System.out.print("6dn: " + sixDown + ", ");
        int sevenDown = extractVector(GridPoint.of(0, 8), GridPoint.of(1, 8), grid);
        System.out.print("7dn: " + sevenDown + ", ");
        int fourteenDown = extractVector(GridPoint.of(2, 0), GridPoint.of(3, 0), grid);
        System.out.print("14dn: " + fourteenDown + ", ");

        int nineDown = extractVector(GridPoint.of(1, 1), GridPoint.of(2, 1), grid);
        System.out.print("9dn: " + nineDown + ", ");
        int eighteenDown = extractVector(GridPoint.of(2, 8), GridPoint.of(3, 8), grid);
        System.out.print("18dn: " + eighteenDown + ", ");
        int thirteenDown = extractVector(GridPoint.of(1, 7), GridPoint.of(2, 7), grid);
        System.out.print("13dn: " + thirteenDown + ", ");
        int elevenDown = extractVector(GridPoint.of(1, 3), GridPoint.of(3, 3), grid);
        System.out.print("11dn: " + elevenDown + "] ");



        result.setAc8(eightAcross == oneDown - nineDown);
        result.setAc10(isPresent(tenAcross, DIVISIBLE_BY_DIVISORS));
        result.setAc12(isPresent(twelveAcross, THREE_DIGIT_LUCAS_NUMBERS));

        result.setAc15(isPresent(fifteenAcross, THREE_DIGIT_CULLEN_NUMBERS));
        result.setAc16(isPresent(sixteenAcross, DIVISIBLE_BY_DIVISORS));
        result.setAc17(seventeenAcross == eighteenDown - thirteenDown);
        result.setDn1(twentyAcross % oneDown == 0);
        result.setDn2(isPrime(twoDown));
        result.setDn6(isPrime(sixDown));
        result.setDn4(isSquareNumber(sum(func(integer -> (int) Math.pow(integer, 3), getDigits(fourDown)))));

        boolean elevenDownMinusHarshad = false;
        for (int harshadNumber : HARSHAD_NUMBERS) {
            if (fiveDown == elevenDown - harshadNumber) {
                elevenDownMinusHarshad = true;
                break;
            }
        }
        result.setDn5(elevenDownMinusHarshad);
        if (threeAcross % sevenDown == 0) {
            result.setDn7(isPrime(sevenDown));
        } else {
            result.setDn7(false);
        }
        int sum19AcrossDigits = digitSum(nineteenAcross);
        int sum20AcrossDigits = digitSum(twentyAcross);
        int sum21AcrossDigits = digitSum(twentyOneAcross);
        int totalSums = sum19AcrossDigits + sum20AcrossDigits + sum21AcrossDigits;
        result.setDn14(totalSums % fourteenDown == 0);

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

        result.setAc136(happyNumberCount >= 1 && luckyNumberCount >= 1 && cubeNumberCount >= 1);

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

        result.setAc192021(squareNumberCount >= 1 && fibonacciNumberCount >= 1 && triangularNumberCount >= 1);

        return result;
    }

    public boolean validateNumberSoup(Integer[][] grid) {
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
        int nineteenAcross = extractVector(GridPoint.of(3, 0), GridPoint.of(3, 2), grid);
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



        if (eightAcross != oneDown - nineDown) {
            throw new InvalidSoupException("8ac != 1dn - 9dn INFORMATION: {1dn=" + Arrays.toString(getDigits(oneDown)) + ", 9dn=" + Arrays.toString(getDigits(nineDown)) + "}");
        }
        if (!isPresent(tenAcross, DIVISIBLE_BY_DIVISORS)) {
            throw new InvalidSoupException("10ac is not divisible by factor count. INFORMATION: {10ac=" + Arrays.toString(getDigits(tenAcross)) + ", factors(" + factorCount(tenAcross) + ")=" + Arrays.toString(getFactors(tenAcross)) + "}");
        }
        if (!isPresent(twelveAcross, THREE_DIGIT_LUCAS_NUMBERS)) {
            throw new InvalidSoupException("12ac is not a Lucas number. INFORMATION: {12ac=" + Arrays.toString(getDigits(twelveAcross)) + "}");
        }
        if (!isPresent(fifteenAcross, THREE_DIGIT_CULLEN_NUMBERS)) {
            throw new InvalidSoupException("15ac is not a Cullen number. INFORMATION: {15ac=" + Arrays.toString(getDigits(fifteenAcross)) + "}");
        }
        if (!isPresent(sixteenAcross, DIVISIBLE_BY_DIVISORS)) {
            throw new InvalidSoupException("16ac is not divisible by factor count. INFORMATION: {16ac=" + Arrays.toString(getDigits(sixteenAcross)) + ", factors(" + factorCount(sixteenAcross) + ")=" + Arrays.toString(getFactors(sixteenAcross)) + "}");
        }
        if (seventeenAcross != eighteenDown - thirteenDown) {
            throw new InvalidSoupException("17ac != 18dn - 13dn INFORMATION: {18dn=" + Arrays.toString(getDigits(eighteenDown)) + ", 13dn=" + Arrays.toString(getDigits(thirteenDown)) + "}");
        }
        if (twentyAcross % oneDown != 0) {
            throw new InvalidSoupException("1dn is not a divisor of 20ac (20ac % 1dn != 0). INFORMATION: {1dn=" + Arrays.toString(getDigits(oneDown)) + ", 20ac=" + Arrays.toString(getDigits(twentyAcross)) + "}");
        }
        if (!isPrime(twoDown)) {
            throw new InvalidSoupException("2dn is not prime. INFORMATION: {2dn=" + Arrays.toString(getDigits(twoDown)) + ", factors(" + factorCount(twoDown) + ")=" + Arrays.toString(getFactors(twoDown)) + "}");
        }

        if (!isSquareNumber(sum(func(integer -> (int) Math.pow(integer, 3), getDigits(fourDown))))) {
            throw new InvalidSoupException("4dn != sum of cubes of it's digits. INFORMATION: {4dn=" + Arrays.toString(getDigits(fourDown)) + "}");
        }
        boolean elevenDownMinusHarshad = false;
        for (int harshadNumber : HARSHAD_NUMBERS) {
            if (fiveDown == elevenDown - harshadNumber) {
                elevenDownMinusHarshad = true;
                break;
            }
        }
        if (!elevenDownMinusHarshad) {
            throw new InvalidSoupException("5dn != 11dn - Harshad Number INFORMATION: {5dn=" + Arrays.toString(getDigits(fiveDown)) + ", 11dn=" + Arrays.toString(getDigits(elevenDown)) + "}");
        }
        if (!isPrime(sixDown)) {
            throw new InvalidSoupException("6dn is not prime. INFORMATION: {6dn=" + Arrays.toString(getDigits(sixDown)) + ", factors(" + factorCount(sixDown) + ")=" + Arrays.toString(getFactors(sixDown)) + "}");
        }
        if (threeAcross % sevenDown == 0) {
            // is divisor
            if (!isPrime(sevenDown)) {
                throw new InvalidSoupException("7dn is not a PRIME divisor of 3ac. INFORMATION: {7dn=" + Arrays.toString(getDigits(sevenDown)) + ", 3ac=" + Arrays.toString(getDigits(threeAcross)) + ", 7dn%3ac=" + (sevenDown % threeAcross) + "}");
            }
        } else {
            throw new InvalidSoupException("7dn is not a divisor of 3ac. INFORMATION: {7dn=" + Arrays.toString(getDigits(sevenDown)) + ", 3ac=" + Arrays.toString(getDigits(threeAcross)) + ", 7dn%3ac=" + (sevenDown % threeAcross) + "}");
        }
        int sum19AcrossDigits = digitSum(nineteenAcross);
        int sum20AcrossDigits = digitSum(twentyAcross);
        int sum21AcrossDigits = digitSum(twentyOneAcross);
        int totalSums = sum19AcrossDigits + sum20AcrossDigits + sum21AcrossDigits;
        if (totalSums % fourteenDown != 0) {
            throw new InvalidSoupException("14dn is not a divisor of the sums of 19ac + 20ac + 21ac INFORMATION: {19ac+20ac+21ac=" + totalSums + ", 14dn=" + Arrays.toString(getDigits(fourteenDown)) + "}");
        }

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

        if (!(happyNumberCount >= 1 && luckyNumberCount >= 1 && cubeNumberCount >= 1)) return false;

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

        return squareNumberCount >= 1 && fibonacciNumberCount >= 1 && triangularNumberCount >= 1;
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

    public static int[] getFactors(int number) {
        number = Math.abs(number);
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);
                if (i != number / i) {
                    factors.add(number / i);
                }
            }
        }
        return factors.stream().sorted().mapToInt(Integer::intValue).toArray();
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

    public static boolean containsZero(int number) {
        return String.valueOf(number).contains("0");
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
            if (number >= 100 && number < 1000) {
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
