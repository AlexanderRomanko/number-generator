package com.example.numbergenerator.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class NumberModelImpl implements NumberModel {

    public static final char[] SYMBOL_LIST = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};
    public static final int MIN_DIGIT = 1;
    public static final int MAX_DIGIT = 999;
    public static final String REGION = "116 RUS";

    private char[] chars;
    private int numbers;
    private String region;

    public NumberModelImpl() {
        createRandomNumber();
    }

    public NumberModelImpl(String number) {
        this.chars = getCharactersFromNumber(number);
        this.numbers = getDigitsFromNumber(number);
        this.region = " " + REGION;
    }

    public NumberModelImpl getNext() {
        if (numbers == MAX_DIGIT) {
            return createNumberWithIncrementedChar(chars);
        } else {
            return createNumberWithIncrementedDigit();
        }
    }

    private char[] getCharactersFromNumber(String number) {
        return new char[]{number.charAt(0), number.charAt(4), number.charAt(5)};
    }

    private int getDigitsFromNumber(String number) {
        return Integer.parseInt(number.substring(1, 4));
    }

    private void createRandomNumber() {
        this.chars = new char[3];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = SYMBOL_LIST[new Random().nextInt(SYMBOL_LIST.length)];
        }
        this.numbers = new Random().nextInt(MAX_DIGIT - 1) + 1;
        this.region = " " + REGION;
    }

    private NumberModelImpl createNumberWithIncrementedDigit() {
        return new NumberModelImpl(String.format("%s%03d%s%s%s", this.chars[0],
                ++this.numbers, this.chars[1], this.chars[2], this.region));
    }

    private static NumberModelImpl createNumberWithIncrementedChar(char[] chars) {
        for (int i = chars.length - 1; i >= 0; i--) {
            int charIndex = new String(SYMBOL_LIST).indexOf(chars[i]);
            if (charIndex == SYMBOL_LIST.length - 1) {
                chars[i] = SYMBOL_LIST[0];
            } else {
                chars[i] = SYMBOL_LIST[++charIndex];
                break;
            }
        }
        return new NumberModelImpl(String.format("%s%03d%s%s%s", chars[0], MIN_DIGIT, chars[1], chars[2], REGION));
    }

    @Override
    public String toString() {
        return String.format("%s%03d%s%s%s", this.chars[0], this.numbers, this.chars[1], this.chars[2], this.region);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberModelImpl that = (NumberModelImpl) o;
        return numbers == that.numbers && Arrays.equals(chars, that.chars) && region.equals(that.region);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numbers, region);
        result = 31 * result + Arrays.hashCode(chars);
        return result;
    }
}
