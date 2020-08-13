package ru.serega6531.accessor;

import ru.serega6531.Field;
import ru.serega6531.FieldParameters;

import java.util.HashSet;
import java.util.Set;

public class BoxAccessor extends FieldAccessor {

    private final Field field;
    private final FieldParameters parameters;
    private final int rowBase;
    private final int columnBase;

    public BoxAccessor(Field field, FieldParameters parameters, int row, int column) {
        this.field = field;
        this.parameters = parameters;

        this.rowBase = (row / parameters.getBoxSize()) * parameters.getBoxSize();
        this.columnBase = (column / parameters.getBoxSize()) * parameters.getBoxSize();
    }

    @Override
    public int get(int num) {
        int offsetY = transformToOffsetY(num);
        int offsetX = transformToOffsetX(num);

        return field.get(rowBase + offsetY, columnBase + offsetX);
    }

    @Override
    public void set(int num, int value) {
        int offsetY = transformToOffsetY(num);
        int offsetX = transformToOffsetX(num);

        field.set(rowBase + offsetY, columnBase + offsetX, value);
    }

    public Set<Integer> getAllSet() {
        Set<Integer> list = new HashSet<>();

        for (int i = 0; i < getSize(); i++) {
            int val = get(i);
            if (val != Field.UNSET) {
                list.add(val);
            }
        }

        return list;
    }

    @Override
    public int getSize() {
        return parameters.getRowSize();  // boxSize * boxSize
    }

    public int transformToOffsetY(int num) {
        return num / parameters.getBoxSize();
    }

    public int transformToOffsetX(int num) {
        return num % parameters.getBoxSize();
    }

}
