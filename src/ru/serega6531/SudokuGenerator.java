package ru.serega6531;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.serega6531.Field.ROW_SIZE;

public class SudokuGenerator {

    public static void main(String[] args) {
        Field field = new SudokuGenerator().generate();
        field.print();
    }

    public Field generate() {
        Field field = new Field();
        return step(0, field);
    }

    public Field step(int num, Field field) {
        FlatFieldAccessor flat = field.getFlat();

        List<Integer> numbers = IntStream.iterate(1, i -> i <= ROW_SIZE, i -> i + 1)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        // if all numbers are wrong, we must go one iteration back
        for (Integer number : numbers) {
            flat.set(num, number);

            if (field.validate()) {
                // if field is valid, try one iteration deeper, unless we hit the end
                if (num == Field.FIELD_SIZE - 1) {
                    return field;
                } else {
                    Field newField = step(num + 1, field);
                    if (newField != null) {
                        return newField;
                    }

                    // clear the field after current position
                    for (int i = num + 1; i < Field.ROW_SIZE * Field.ROW_SIZE; i++) {
                        flat.set(i, Field.UNSET);
                    }
                }
            }
        }

        return null;
    }

}
