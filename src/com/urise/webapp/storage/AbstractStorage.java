package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        doUpdate(getExistingSearchKey(r.getUuid()), r);
    }

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        doSave(getNotExistingSearchKey(r.getUuid()), r);
    }

    public Resume get(String uuid) {
        return doGet(getExistingSearchKey(uuid));
    }

    public void delete(String uuid) {
        doDelete(getExistingSearchKey(uuid));
    }

    protected Object getExistingSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Object key, Resume r);

    protected abstract void doDelete(Object key);

    protected abstract void doUpdate(Object key, Resume r);

    protected abstract Resume doGet(Object key);

    protected abstract boolean isExist(Object key);
}
