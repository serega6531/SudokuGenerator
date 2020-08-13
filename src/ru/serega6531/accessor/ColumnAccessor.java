package ru.serega6531.accessor;

import ru.serega6531.Field;

public class ColumnAccessor extends FieldAccessor {

    private final Field field;
    private final int column;

    public ColumnAccessor(Field field, int column) {
        this.field = field;
        this.column = column;
    }

    @Override
    public int getSize() {
        return field.getParameters().getRowSize();
    }

    public int get(int row) {
        return field.get(row, column);
    }

    public void set(int row, int value) {
        field.set(row, column, value);
    }

}
