package ru.serega6531.accessor;

import ru.serega6531.Field;

public class FlatFieldAccessor extends FieldAccessor {

    private final Field field;

    public FlatFieldAccessor(Field field) {
        this.field = field;
    }

    @Override
    public int getSize() {
        return field.getParameters().getRowSize() * field.getParameters().getRowSize();
    }

    public int get(int num) {
        int row = transformToRow(num);
        int column = transformToColumn(num);

        return field.get(row, column);
    }

    @Override
    public void set(int num, int value) {
        int row = num / field.getParameters().getRowSize();
        int column = num % field.getParameters().getRowSize();

        field.set(row, column, value);
    }

    public int transformToRow(int num) {
        return num / field.getParameters().getRowSize();
    }

    public int transformToColumn(int num) {
        return num % field.getParameters().getRowSize();
    }

}
