package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    //must use Arrays.binarySearch for insertElement(Resume r, int index) working correctly
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int indexForInsert = -index - 1;
        for (int i = size; i > indexForInsert; i--) {
            storage[i] = storage[i - 1];
        }
        storage[indexForInsert] = r;
    }

    @Override
    protected void deleteElement(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }
}