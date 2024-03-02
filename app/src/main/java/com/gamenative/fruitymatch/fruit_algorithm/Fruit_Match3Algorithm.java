package com.gamenative.fruitymatch.fruit_algorithm;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Match3Algorithm {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    private Fruit_Match3Algorithm() {
    }
    //========================================================

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static void findMatchTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        // Find match 3 in row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 2; j++) {
                // Check is tile type match the next two column
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()) {
                    // Update match
                    for (int n = 0; n <= 2; n++) {
                        Fruit_Match3Tile t = tiles[i][j + n];
                        // We make sure not match multiple times
                        if (t.isMatchable() && t.getTileState() == Fruit_TileState.IDLE) {
                            t.matchTile();
                        }
                    }
                }
            }
        }

        // Find match 3 in column
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 2; i++) {
                // Check is tile type match the next two row
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i + 1][j].getTileType()
                        && type == tiles[i + 2][j].getTileType()) {
                    // Update match
                    for (int n = 0; n <= 2; n++) {
                        Fruit_Match3Tile t = tiles[i + n][j];
                        // We make sure not match multiple times
                        if (t.isMatchable() && t.getTileState() == Fruit_TileState.IDLE) {
                            t.matchTile();
                        }
                    }
                }
            }
        }
    }

    public static void playTileEffect(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                if (t.getTileState() == Fruit_TileState.MATCH) {
                    t.playTileEffect();
                }
            }
        }
    }

    public static void resetMatchTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        // Reset tile row and column
        for (int j = 0; j < col; j++) {
            for (int i = row - 1; i >= 0; i--) {
                Fruit_Match3Tile currentTile = tiles[i][j];
                // Find the match tile
                if (currentTile.getTileState() != Fruit_TileState.MATCH) {
                    continue;
                }
                // Search the column bottom up
                for (int n = i - 1; n >= 0; n--) {
                    Fruit_Match3Tile upperTile = tiles[n][j];
                    // We skip the negligible tile
                    if (upperTile.isNegligible()) {
                        continue;
                    }
                    // We first check if upper tile is swappable
                    if (!upperTile.isSwappable()) {
                        // Put it to waiting state and hide it before reset
                        currentTile.setTileState(Fruit_TileState.WAITING);
                        currentTile.hideTile();
                        break;
                    }
                    // Otherwise, swap with the idle upper tile
                    if (upperTile.getTileState() == Fruit_TileState.IDLE) {
                        swapTile(tiles, currentTile, upperTile);
                        break;
                    }
                }
            }
        }

        // Reset tile position and type
        for (int j = 0; j < col; j++) {
            for (int i = row - 1, n = 1; i >= 0; i--) {
                Fruit_Match3Tile t = tiles[i][j];
                // Find the match tile
                if (t.getTileState() == Fruit_TileState.MATCH) {
                    t.resetXByColumn(t.getColumn());
                    t.resetYByRow(-n++);
                    t.resetTile();
                }
            }
        }
    }

    public static void findUnreachableTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        // Important to search from top down to left right
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile currentTile = tiles[i][j];
                // Find the waiting tile
                if (currentTile.getTileState() != Fruit_TileState.WAITING) {
                    continue;
                }
                // Search the column bottom up
                for (int n = i - 1; n >= 0; n--) {
                    Fruit_Match3Tile upperTile = tiles[n][j];
                    // We skip the negligible tile
                    if (upperTile.isNegligible()) {
                        continue;
                    }
                    // Check is the 3 upper tile swappable
                    //  X X X
                    //    O
                    //    O
                    if ((j == 0 || !tiles[n][j - 1].isSwappable())
                            && !tiles[n][j].isSwappable()
                            && (j == col - 1 || !tiles[n][j + 1].isSwappable())) {
                        // The tile is not reachable, we put it to unreachable state
                        currentTile.setTileState(Fruit_TileState.UNREACHABLE);
                    }

                    // No need to check the next one if it is reachable
                    break;
                }
            }
        }
    }

    public static void checkUnreachableTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        // Important to search from top down to left right
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile currentTile = tiles[i][j];
                // Find the unreachable tile
                if (currentTile.getTileState() != Fruit_TileState.UNREACHABLE) {
                    continue;
                }
                // Search the column bottom up
                for (int n = i - 1; n >= 0; n--) {
                    Fruit_Match3Tile upperTile = tiles[n][j];
                    // We skip the negligible tile
                    if (upperTile.isNegligible()) {
                        continue;
                    }
                    // Check is the 3 upper tile swappable
                    //  X X X
                    //    O
                    //    O
                    if ((j > 0 && tiles[n][j - 1].isSwappable())
                            || tiles[n][j].isSwappable()
                            || (j < col - 1 && tiles[n][j + 1].isSwappable())) {
                        // The tile is now reachable, we put it to match state
                        currentTile.setTileState(Fruit_TileState.MATCH);
                    }

                    // No need to check the next one if it is unreachable
                    break;
                }
            }
        }
    }

    public static void checkWaitingTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int j = 0; j < col; j++) {
            for (int i = row - 1; i > 0; i--) {
                Fruit_Match3Tile currentTile = tiles[i][j];
                // Find the waiting tile
                if (currentTile.getTileState() != Fruit_TileState.WAITING) {
                    continue;
                }
                // Search the column bottom up
                for (int n = i - 1; n >= 0; n--) {
                    Fruit_Match3Tile upperTile = tiles[n][j];
                    // We skip the negligible tile
                    if (upperTile.isNegligible()) {
                        continue;
                    }
                    // Find the unswappable tile
                    if (!upperTile.isSwappable()) {
                        // Search the right one next to it
                        //    X O
                        //    O
                        //    O
                        if (j < col - 1) {
                            Fruit_Match3Tile targetTile = tiles[n][j + 1];
                            // We make sure the tile is not unswappable or still moving
                            if (targetTile.isSwappable()
                                    && !targetTile.isMoving()
                                    && targetTile.getTileState() != Fruit_TileState.WAITING) {
                                // Put the tile to match state and swap
                                currentTile.setTileState(Fruit_TileState.MATCH);
                                swapTile(tiles, targetTile, currentTile);
                                // We find available tile from right, so we do not search left
                                break;
                            }
                        }

                        // Search the left one next to it
                        //  O X
                        //    O
                        //    O
                        if (j > 0) {
                            Fruit_Match3Tile targetTile = tiles[n][j - 1];
                            // We make sure the tile is not unswappable or still moving
                            if (targetTile.isSwappable()
                                    && !targetTile.isMoving()
                                    && targetTile.getTileState() != Fruit_TileState.WAITING) {
                                // Put the tile to match state and swap
                                currentTile.setTileState(Fruit_TileState.MATCH);
                                swapTile(tiles, targetTile, currentTile);
                            }
                        }

                        // No need to find the next unswappable tile
                        break;
                    }
                }
            }
        }
    }

    public static void swapTile(Fruit_Match3Tile[][] tiles, Fruit_Match3Tile tileA, Fruit_Match3Tile tileB) {
        // Swap row
        int rowA = tileA.getRow();
        int rowB = tileB.getRow();
        tileA.setRow(rowB);
        tileB.setRow(rowA);

        // Swap column
        int colA = tileA.getColumn();
        int colB = tileB.getColumn();
        tileA.setColumn(colB);
        tileB.setColumn(colA);

        // Swap tile
        tiles[rowA][colA] = tileB;
        tiles[rowB][colB] = tileA;
    }

    public static void initTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                t.initTile();
            }
        }
    }

    public static void shuffleTile(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                if (t.isShufflable()) {
                    t.shuffleTile();
                }
            }
        }
    }

    public static void moveTile(Fruit_Match3Tile[][] tiles, int row, int col, long elapsedMillis) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                t.moveTile(elapsedMillis);
            }
        }
    }

    public static boolean isMatch(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                if (t.getTileState() == Fruit_TileState.MATCH) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isWaiting(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                if (t.getTileState() == Fruit_TileState.WAITING) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isMoving(Fruit_Match3Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Match3Tile t = tiles[i][j];
                if (t.isMoving()) {
                    return true;
                }
            }
        }

        return false;
    }
    //========================================================

}
