package ru.serega6531;

public class ColumnAccessor extends FieldAccessor {

    private final Field field;
    private final int column;

    public ColumnAccessor(Field field, int column) {
        this.field = field;
        this.column = column;
    }

    public int get(int row) {
        return field.get(row, column);
    }

    public void set(int row, int value) {
        field.set(row, column, value);
    }

}
