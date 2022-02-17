package com.example.numbergenerator.storage;

import com.example.numbergenerator.exceptions.OversizeStorageException;
import com.example.numbergenerator.model.NumberModelImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class NumberStorageImpl implements NumberStorage{

    private final static String DEFAULT_NUMBER = "A000AA 116 RUS";
    private final static int MAX_CAPACITY = 1_724_544;
    private final Set<NumberModelImpl> numberSet;
    private NumberModelImpl last;

    public NumberStorageImpl() {
        this.numberSet = new LinkedHashSet<>();
    }

    public void add(NumberModelImpl number) throws OversizeStorageException {
        if (numberSet.size() == MAX_CAPACITY) {
            throw new OversizeStorageException();
        }
        numberSet.add(number);
        last = number;
    }

    public boolean isExist(NumberModelImpl number) {
        return numberSet.contains(number);
    }

    public NumberModelImpl getLast() {
        if (last == null) {
            last = new NumberModelImpl(DEFAULT_NUMBER);
        }
        return last;
    }
}
