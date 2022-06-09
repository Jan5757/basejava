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

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    protected void insertElement(Resume r, int index) {
        storage.add(r);
    }

    protected void deleteElement(int index) {
        storage.remove(index);
    }

    protected void updateElement(int index, Resume r) {
        storage.add(index, r);
    }

    protected Resume getElement(int index) {
        return storage.get(index);
    }

    @Override
    protected boolean isStorageLimit() {
        return storage.size() == Integer.MAX_VALUE;
    }
}
