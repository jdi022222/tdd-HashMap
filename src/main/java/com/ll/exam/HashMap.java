package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {
    Object[] keys;
    Object[] values;
    int lastIndex;

    HashMap() {
        lastIndex = -1;
        keys = new Object[1];
        values = new Object[1];
    }

    private boolean isArrayFull() {
        return lastIndex >= keys.length - 1;
    }

    private void extendArraySize() {
        Object[] newKeys = new Object[keys.length * 2];
        Object[] newValues = new Object[values.length * 2];

        for (int i = 0; i < keys.length; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }

    private void extendArraySizeIfFull() {
        if (isArrayFull()) {
            extendArraySize();
        }
    }

    private int getIndexOfKey(K key) {
        for (int i = 0; i <= lastIndex; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void put(K key, V value) {

        int index = getIndexOfKey(key);
        if (index != -1) {
            values[index] = value;
        } else {
            if (isArrayFull() == true) {
                extendArraySizeIfFull();
            }
            lastIndex++;
            keys[lastIndex] = key;
            values[lastIndex] = value;
        }
    }

    public V get(K key) {
        int index = getIndexOfKey(key);
        if (index >= -1) {
            return (V) values[index];
        }
        return null;
    }

    public int size() {
        return this.lastIndex + 1;
    }

    public void remove(K key) {
        int index = getIndexOfKey(key);
        for (int i = index; i < lastIndex; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        lastIndex--;
    }

    public List<K> keySet() {
        List<K> keySet = new ArrayList<>();

        for ( int i = 0; i < lastIndex+1; i++ ) {
            keySet.add((K)keys[i]);
        }

        return keySet;
    }
}
