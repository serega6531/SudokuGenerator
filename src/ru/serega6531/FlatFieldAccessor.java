package ru.serega6531;

public class FlatFieldAccessor extends FieldAccessor {

    private final Field field;

    public FlatFieldAccessor(Field field) {
        this.field = field;
    }

    @Override
    public int getSize() {
        return Field.ROW_SIZE * Field.ROW_SIZE;
    }

    public int get(int num) {
        int row = num / Field.ROW_SIZE;
        int column = num % Field.ROW_SIZE;

        return field.get(row, column);
    }

    @Override
    public void set(int num, int value) {
        int row = num / Field.ROW_SIZE;
        int column = num % Field.ROW_SIZE;

        field.set(row, column, value);
    }

}
