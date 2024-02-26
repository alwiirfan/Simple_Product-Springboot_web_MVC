package com.alwi.irfani.utils;

import org.springframework.stereotype.Component;

@Component
public class RandomNumber {

        public long getRandom(long min, long max) {
            return min + (long) (Math.random() * (max-min));
        }
}
