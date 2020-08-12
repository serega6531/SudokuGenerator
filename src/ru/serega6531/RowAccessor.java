package ru.serega6531;

public class RowAccessor extends FieldAccessor {

    private final Field field;
    private final int row;

    public RowAccessor(Field field, int row) {
        this.field = field;
        this.row = row;
    }

    public int get(int column) {
        return field.get(row, column);
    }

    public void set(int column, int value) {
        field.set(row, column, value);
    }

}
