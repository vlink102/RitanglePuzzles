package me.vlink102;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shape {
    public static List<int[][]> findAllPlacements(int[][] grid, int[][] piece, int[][] constraintGrid) {
        List<int[][]> placements = new ArrayList<>();
        List<int[][]> transformations = generateTransformations(piece);
        int gridRows = grid.length;
        int gridCols = grid[0].length;

        for (int[][] transformedPiece : transformations) {
            int pieceRows = transformedPiece.length;
            int pieceCols = transformedPiece[0].length;
            if (pieceRows == 1 && pieceCols == 4) {
                if (canPlace(grid, transformedPiece, 0, 0, constraintGrid)) {
                    int[][] newGrid = applyPlacement(grid, transformedPiece, 0, 0);
                    placements.add(newGrid);
                }
                if (canPlace(grid, transformedPiece, 5, 2, constraintGrid)) {
                    int[][] newGrid = applyPlacement(grid, transformedPiece, 5, 2);
                    placements.add(newGrid);
                }
                continue;
            }
            for (int startRow = 0; startRow <= gridRows - pieceRows; startRow++) {
                for (int startCol = 0; startCol <= gridCols - pieceCols; startCol++) {
                    if (canPlace(grid, transformedPiece, startRow, startCol, constraintGrid)) {
                        int[][] newGrid = applyPlacement(grid, transformedPiece, startRow, startCol);
                        placements.add(newGrid);
                    }
                }
            }
        }

        return placements;
    }

    static int total = 0;

    public static void solveAll(int[][] grid, List<int[][]> pieces, int pieceIndex, int[][] constraintGrid) {
        if (pieceIndex == pieces.size()) {
            // Final check: ensure all 1's in the constraint grid are covered
            for (int i = 0; i < constraintGrid.length; i++) {
                for (int j = 0; j < constraintGrid[0].length; j++) {
                    if (constraintGrid[i][j] == 1 && grid[i][j] == 0) {
                        return; // Invalid solution if a required cell is not covered
                    }
                }
            }

            // Print the valid solution
            printGrid(grid);
            total++; // Count the total solutions
            return;
        }

        int[][] piece = pieces.get(pieceIndex);
        List<int[][]> placements = findAllPlacements(grid, piece, constraintGrid);

        for (int[][] placement : placements) {
            solveAll(placement, pieces, pieceIndex + 1, constraintGrid);
        }
    }

    public static List<int[][]> generateTransformations(int[][] piece) {
        List<int[][]> transformations = new ArrayList<>();
        transformations.add(piece); // Original orientation

        if (piece.length == 1 && piece[0].length == 4) {
            return transformations;
        }
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

    public static List<int[][]> findPlacements(int[][] grid, int[][] piece, int[][] constraintGrid) {
        List<int[][]> placements = new ArrayList<>();
        List<int[][]> transformations = generateTransformations(piece);
        int gridRows = grid.length;
        int gridCols = grid[0].length;

        for (int[][] transformedPiece : transformations) {
            int pieceRows = transformedPiece.length;
            int pieceCols = transformedPiece[0].length;

            for (int startRow = 0; startRow <= gridRows - pieceRows; startRow++) {
                for (int startCol = 0; startCol <= gridCols - pieceCols; startCol++) {
                    if (canPlace(grid, transformedPiece, startRow, startCol, constraintGrid)) {
                        placements.add(applyPlacement(grid, transformedPiece, startRow, startCol));
                    }
                }
            }
        }

        return placements;
    }

    public static boolean canPlace(int[][] grid, int[][] piece, int startRow, int startCol, int[][] constraintGrid) {
        int pieceRows = piece.length;
        int pieceCols = piece[0].length;

        for (int i = 0; i < pieceRows; i++) {
            for (int j = 0; j < pieceCols; j++) {
                if (piece[i][j] != 0) {
                    int gridRow = startRow + i;
                    int gridCol = startCol + j;

                    // Check if the grid cell is already occupied
                    if (grid[gridRow][gridCol] != 0) {
                        return false;
                    }

                    // Check if the piece overlaps the required constraint
                    if (constraintGrid[gridRow][gridCol] == 1 && piece[i][j] == 0) {
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

    public static boolean solve(int[][] grid, List<int[][]> pieces, int pieceIndex, int[][] constraintGrid) {
        if (pieceIndex == pieces.size()) {
            printGrid(grid);
            return true;
        }

        int[][] piece = pieces.get(pieceIndex);
        List<int[][]> placements = findPlacements(grid, piece, constraintGrid);

        for (int[][] placement : placements) {
            if (solve(placement, pieces, pieceIndex + 1, constraintGrid)) {
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
