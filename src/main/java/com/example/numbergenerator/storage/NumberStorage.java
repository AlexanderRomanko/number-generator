package com.example.numbergenerator.storage;

import com.example.numbergenerator.model.NumberModelImpl;

public interface NumberStorage {

    void add(NumberModelImpl number) throws Exception;

    boolean isExist(NumberModelImpl number);

    NumberModelImpl getLast();

}
