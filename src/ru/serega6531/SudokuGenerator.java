package ru.serega6531;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuGenerator {

    public static void main(String[] args) {
        Field field = new SudokuGenerator().generate(new FieldParameters(9));
        field.print();
    }

    public Field generate(FieldParameters parameters) {
        Field field = new Field(parameters);
        return step(0, field);
    }

    public Field step(int num, Field field) {
        FlatFieldAccessor flat = field.getFlat();

        List<Integer> numbers = IntStream.iterate(1, i -> i <= field.getParameters().getRowSize(), i -> i + 1)
                .boxed()
                .collect(Collectors.toList());
        numbers.removeAll(field.getRow(flat.transformToRow(num)).getAllSet());
        numbers.removeAll(field.getColumn(flat.transformToColumn(num)).getAllSet());
        Collections.shuffle(numbers);

        // if all numbers are wrong, we must go one iteration back
        for (Integer number : numbers) {
            flat.set(num, number);

            if (field.validate()) {
                // if field is valid, try one iteration deeper, unless we hit the end
                if (num == field.getParameters().getFieldSize() - 1) {
                    return field;
                } else {
                    Field newField = step(num + 1, field);
                    if (newField != null) {
                        return newField;
                    }

                    // clear the field after current position
                    for (int i = num + 1; i < field.getParameters().getFieldSize() && flat.get(i) != Field.UNSET; i++) {
                        flat.set(i, Field.UNSET);
                    }
                }
            }
        }

        return null;
    }

}
