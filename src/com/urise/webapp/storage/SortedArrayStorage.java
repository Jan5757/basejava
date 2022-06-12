package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    //must use Arrays.binarySearch for insertElement(Resume r, int index) working correctly
    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Object key, Resume r) {
        int indexForInsert = -(int) key - 1;
        if (size - indexForInsert >= 0) { //нужно ли это условие тут? ключ мы подаём известный
            System.arraycopy(storage, indexForInsert, storage, indexForInsert + 1, size - indexForInsert);
        }
        storage[indexForInsert] = r;
    }

    @Override
    protected void doDeleteSpecial(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}