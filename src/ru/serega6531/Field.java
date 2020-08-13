package ru.serega6531;

import ru.serega6531.accessor.BoxAccessor;
import ru.serega6531.accessor.ColumnAccessor;
import ru.serega6531.accessor.FlatFieldAccessor;
import ru.serega6531.accessor.RowAccessor;

import java.util.HashSet;
import java.util.Set;

public class Field {

    private final FieldParameters parameters;

    public static final int UNSET = -1;

    private final int[][] data;
    private final FlatFieldAccessor flatFieldAccessor = new FlatFieldAccessor(this);

    public Field(int[][] data, FieldParameters parameters) {
        this.data = data;
        this.parameters = parameters;
    }

    public Field(FieldParameters parameters) {
        this.data = new int[parameters.getRowSize()][parameters.getRowSize()];
        this.parameters = parameters;

        for (int row = 0; row < parameters.getRowSize(); row++) {
            for (int column = 0; column < parameters.getRowSize(); column++) {
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
                System.out.print(((char) ('a' + i - 1)) + " ");
//                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public boolean validate() {
        for (int row = 0; row < parameters.getRowSize(); row++) {
            if (!getRow(row).isUnique()) {
                return false;
            }
        }

        for (int column = 0; column < parameters.getRowSize(); column++) {
            if (!getColumn(column).isUnique()) {
                return false;
            }
        }

        // validate numbers in boxes (3x3)
        for (int xBoxNum = 0; xBoxNum < parameters.getRowSize() / parameters.getBoxSize(); xBoxNum++) {
            for (int yBoxNum = 0; yBoxNum < parameters.getRowSize() / parameters.getBoxSize(); yBoxNum++) {
                int x = xBoxNum * parameters.getBoxSize();
                int y = yBoxNum * parameters.getBoxSize();

                Set<Integer> set = new HashSet<>();

                for (int row = 0; row < parameters.getBoxSize(); row++) {
                    for (int column = 0; column < parameters.getBoxSize(); column++) {
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

    public Set<Integer> getImpossibleNumbers(int row, int column) {
        Set<Integer> set = new HashSet<>();

        set.addAll(getRow(row).getAllSet());
        set.addAll(getColumn(column).getAllSet());
        set.addAll(getBox(row, column).getAllSet());

        return set;
    }

    public RowAccessor getRow(int row) {
        return new RowAccessor(this, row);
    }

    public ColumnAccessor getColumn(int column) {
        return new ColumnAccessor(this, column);
    }

    public BoxAccessor getBox(int row, int column) {
        return new BoxAccessor(this, parameters, row, column);
    }

    public FlatFieldAccessor getFlat() {
        return flatFieldAccessor;
    }

    public FieldParameters getParameters() {
        return parameters;
    }

    public Field copy() {
        return new Field(data, parameters);
    }

}
