package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage{

    protected List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public void update(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateElement(index, r);
        }
    }

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r, "Resume should not be empty (null)");
        int index = getIndex(r.getUuid());
        if (storage.size() == Integer.MAX_VALUE) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
        }
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
}
