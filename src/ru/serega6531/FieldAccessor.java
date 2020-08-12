package ru.serega6531;

import java.util.HashSet;
import java.util.Set;

import static ru.serega6531.Field.ROW_SIZE;

public abstract class FieldAccessor {

    public abstract int get(int num);

    public abstract void set(int num, int value);

    public int getSize() {
        return ROW_SIZE;
    }

    public int[] getAll() {
        int[] result = new int[getSize()];
        for (int i = 0; i < getSize(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    public boolean isUnique() {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < getSize(); i++) {
            int val = get(i);
            if (val != Field.UNSET && !set.add(val)) {
                return false;
            }
        }

        return true;
    }

}
