package ru.serega6531.accessor;

import ru.serega6531.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FieldAccessor {

    public abstract int get(int num);

    public abstract void set(int num, int value);

    public abstract int getSize();

    public int[] getAll() {
        int[] result = new int[getSize()];
        for (int i = 0; i < getSize(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    public Set<Integer> getAllSet() {
        Set<Integer> list = new HashSet<>();

        for (int i = 0; i < getSize(); i++) {
            int val = get(i);
            if (val == Field.UNSET) {
                break;
            }
            list.add(val);
        }

        return list;
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
