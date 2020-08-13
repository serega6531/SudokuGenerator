package ru.serega6531;

public class FieldParameters {

    private final int rowSize;
    private final int boxSize;
    private final int fieldSize;

    public FieldParameters(int rowSize) {
        this.rowSize = rowSize;
        this.boxSize = (int) Math.sqrt(rowSize);
        this.fieldSize = rowSize * rowSize;

        if (boxSize * boxSize != rowSize) {
            throw new IllegalArgumentException("rowSize must be a square");
        }
    }

    /**
     * Normal sudoku rules (9x9 field, 3x3 boxes)
     *
     */
    public FieldParameters() {
        this(9);
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getBoxSize() {
        return boxSize;
    }

    public int getFieldSize() {
        return fieldSize;
    }
}
