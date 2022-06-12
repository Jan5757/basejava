package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    protected void doSave(Object key, Resume r) {
        storage.put((String) key, r);
    }

    protected void doDelete(Object key) {
        storage.remove((String) key);
    }

    protected void doUpdate(Object key, Resume r) {
        doSave(key, r);
    }

    protected Resume doGet(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }
}
