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
        if (size - indexForInsert >= 0)
            System.arraycopy(storage, indexForInsert, storage, indexForInsert + 1, size - indexForInsert);
        storage[indexForInsert] = r;
        size++;
    }

    @Override
    protected void deleteElement(Object key) {
        int index = (int) key;
        if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }

    @Override
    protected void updateElement(Object key, Resume r) {
        storage[(int) key] = r;
    }

    @Override
    protected Resume getElement(Object key) {
        return storage[(int) key];
    }

    @Override
    protected boolean isStorageLimit() {
        return size == STORAGE_LIMIT;
    }
}