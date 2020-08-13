package ru.serega6531.accessor;

import ru.serega6531.Field;

public class RowAccessor extends FieldAccessor {

    private final Field field;
    private final int row;

    public RowAccessor(Field field, int row) {
        this.field = field;
        this.row = row;
    }

    @Override
    public int getSize() {
        return field.getParameters().getRowSize();
    }

    public int get(int column) {
        return field.get(row, column);
    }

    public void set(int column, int value) {
        field.set(row, column, value);
    }

}
