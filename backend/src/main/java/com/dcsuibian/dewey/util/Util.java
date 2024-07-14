package com.dcsuibian.dewey.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Util {
    public static <T, R> List<R> batchConvert(Iterable<T> iterable, Function<T, R> converter) {
        List<R> list = new ArrayList<>();
        for (var item : iterable) {
            list.add(converter.apply(item));
        }
        return list;
    }

    public static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10); // 生成一个0到9的随机数
            sb.append(number);
        }
        return sb.toString();
    }
}
