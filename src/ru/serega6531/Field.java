package ru.serega6531;

import java.util.HashSet;
import java.util.Set;

public class Field {

    public static final int ROW_SIZE = 9;
    public static final int FIELD_SIZE = ROW_SIZE * ROW_SIZE;
    public static final int BOX_SIZE = 3;

    public static final int UNSET = -1;

    private final int[][] data;
    private final FlatFieldAccessor flatFieldAccessor = new FlatFieldAccessor(this);

    public Field(int[][] data) {
        this.data = data;
    }

    public Field() {
        this.data = new int[ROW_SIZE][ROW_SIZE];

        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < ROW_SIZE; column++) {
                data[row][column] = UNSET;
            }
        }
    }

    public void set(int row, int column, int value) {
        data[row][column] = value;
    }

    public int get(int row, int column) {
        return data[row][column];
    }

    public void print() {
        for (int[] row : data) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public boolean validate() {
        for (int row = 0; row < data.length; row++) {
            if (!getRow(row).isUnique()) {
                return false;
            }
        }

        for (int column = 0; column < data.length; column++) {
            if (!getColumn(column).isUnique()) {
                return false;
            }
        }

        // validate numbers in boxes (3x3)
        for (int xBoxNum = 0; xBoxNum < ROW_SIZE / BOX_SIZE; xBoxNum++) {
            for (int yBoxNum = 0; yBoxNum < ROW_SIZE / BOX_SIZE; yBoxNum++) {
                int x = xBoxNum * BOX_SIZE;
                int y = yBoxNum * BOX_SIZE;

                Set<Integer> set = new HashSet<>();

                for (int row = 0; row < BOX_SIZE; row++) {
                    for (int column = 0; column < BOX_SIZE; column++) {
                        int val = get(x + row, y + column);
                        if (val != UNSET && !set.add(val)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public RowAccessor getRow(int row) {
        return new RowAccessor(this, row);
    }

    public ColumnAccessor getColumn(int column) {
        return new ColumnAccessor(this, column);
    }

    public FlatFieldAccessor getFlat() {
        return flatFieldAccessor;
    }

    public Field copy() {
        return new Field(data);
    }

}
