package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Object key, Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    protected void deleteElement(Object key) {
        storage[(int) key] = storage[size - 1];
        storage[size - 1] = null;
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