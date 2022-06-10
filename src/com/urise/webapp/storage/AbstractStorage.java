package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        Object key = getSearchKey(r.getUuid());
        if (isKeyNotExist(key)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateElement(key, r);
        }
    }

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        Object key = getSearchKey(r.getUuid());
        if (isStorageLimit()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (isKeyExist(key)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, key);
        }
    }

    public Resume get(String uuid) {
        Object key = getSearchKey(uuid);
        if (isKeyNotExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(key);
    }

    public void delete(String uuid) {
        Object key = getSearchKey(uuid);
        if (isKeyNotExist(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(key);
        }
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void insertElement(Resume r, Object key);

    protected abstract void deleteElement(Object key);

    protected abstract void updateElement(Object key, Resume r);

    protected abstract Resume getElement(Object key);

    protected abstract boolean isStorageLimit();

    protected abstract boolean isKeyExist(Object key);

    protected abstract boolean isKeyNotExist(Object key);
}
