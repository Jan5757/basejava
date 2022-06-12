package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }

    protected void checkStorageOverflow(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    @Override
    protected void doSave(Object key, Resume r) {
        checkStorageOverflow(r);
        insertElement(key, r);
        size++;
    }

    @Override
    protected void doDelete(Object key) {
        int index = (int) key;
        doDeleteSpecial(index);
        size--;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage[(int) key] = r;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(int) key];
    }

    protected abstract void insertElement(Object key, Resume r);

    protected abstract void doDeleteSpecial(int index);
}
