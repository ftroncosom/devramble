/*
 * The MIT License
 *
 * Copyright 2016 Vladimir Djurovic <vdjurovic@vektorsoft.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vektorsoft.ids;

import java.util.Arrays;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class Base62 {

    static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String base62(int value) {
        final StringBuilder sb = new StringBuilder(1);
        do {
            sb.insert(0, BASE62[value % 62]);
            value /= 62;
        } while (value > 0);
        return sb.toString();
    }
    
    public static char[] encode(int value, int length){
        char[] data = new char[length];
        Arrays.fill(data, '0');
        for(int i = length - 1;i >= 0;i--){
            data[i] = BASE62[value % 62];
            value /= 62;
        }
        if(value > 0){
            throw new IllegalArgumentException("Invalid encoding length: " + length);
        }
        return data;
    }
}
