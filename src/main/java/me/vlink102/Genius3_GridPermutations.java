package me.vlink102;

import java.util.*;

public class Genius3_GridPermutations {
    static final int GRID_SIZE = 2;
    static final boolean[][] covered = {
            {true, false},
            {false, false}
    };

    public Genius3_GridPermutations() {}

    public int findPermutations(List<int[][]> pieces) {
        List<List<int[][]>> transformedPieces = new ArrayList<>();
        for (int[][] piece : pieces) {
            transformedPieces.add(generateTransformations(piece));
        }

        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        return placePieces(grid, transformedPieces, 0);
    }

    private int placePieces(int[][] grid, List<List<int[][]>> pieces, int index) {
        if (index == pieces.size()) {
            // All pieces placed successfully
            return 1;
        }

        int count = 0;
        for (int[][] piece : pieces.get(index)) {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (canPlace(grid, piece, row, col)) {
                        place(grid, piece, row, col, true);
                        count += placePieces(grid, pieces, index + 1);
                        place(grid, piece, row, col, false);
                    }
                }
            }
        }
        return count;
    }

    private List<int[][]> generateTransformations(int[][] piece) {
        Set<String> seen = new HashSet<>();
        List<int[][]> transformations = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            piece = rotate(piece);
            addIfUnique(piece, transformations, seen);
            piece = reflect(piece);
            addIfUnique(piece, transformations, seen);
            piece = reflect(piece); // Restore original orientation
        }

        return transformations;
    }

    private int[][] rotate(int[][] piece) {
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

    private int[][] reflect(int[][] piece) {
        int rows = piece.length;
        int cols = piece[0].length;
        int[][] reflected = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                reflected[i][cols - 1 - j] = piece[i][j];
            }
        }
        return reflected;
    }

    private void addIfUnique(int[][] piece, List<int[][]> transformations, Set<String> seen) {
        String serialized = Arrays.deepToString(piece);
        if (!seen.contains(serialized)) {
            transformations.add(piece);
            seen.add(serialized);
        }
    }

    private boolean canPlace(int[][] grid, int[][] piece, int row, int col) {
        int rows = piece.length;
        int cols = piece[0].length;
        if (row + rows > GRID_SIZE || col + cols > GRID_SIZE) return false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (piece[i][j] != 0) {
                    // Check if the piece would overlap another piece
                    if (grid[row + i][col + j] != 0) {
                        return false;
                    }
                    // Check if it covers a required "true" spot
                    if (covered[row + i][col + j] && piece[i][j] == 0) {
                        return false;  // Must cover this spot
                    }
                }
            }
        }
        return true;
    }

    private void place(int[][] grid, int[][] piece, int row, int col, boolean place) {
        int value = place ? 1 : 0;
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != 0) {
                    grid[row + i][col + j] = place ? piece[i][j] : 0;
                }
            }
        }
    }

}
