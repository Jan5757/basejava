package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateElement(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected Resume getElement(int index) {
        return storage[index];
    }

    @Override
    protected boolean isStorageLimit() {
        return size == STORAGE_LIMIT;
    }
}