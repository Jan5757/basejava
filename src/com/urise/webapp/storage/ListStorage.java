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

    protected void doSave(Object key, Resume r) {
        storage.add(r);
    }

    protected void doDelete(Object key) {
        storage.remove((int) key);
    }

    protected void doUpdate(Object key, Resume r) {
        storage.add((Integer) key, r);
    }

    protected Resume doGet(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }
}
