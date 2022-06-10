package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    protected void insertElement(Resume r, Object key) {
        storage.add(r);
    }

    protected void deleteElement(Object key) {
        storage.remove((int) key);
    }

    protected void updateElement(Object key, Resume r) {
        storage.add((Integer) key, r);
    }

    protected Resume getElement(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected boolean isStorageLimit() {
        return storage.size() == Integer.MAX_VALUE;
    }

    @Override
    protected boolean isKeyExist(Object key) {
        return (int) key >= 0;
    }

    @Override
    protected boolean isKeyNotExist(Object key) {
        return (int) key < 0;
    }
}
