package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override//only new resume
    public void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        if (size == STORAGE_LIMIT) {
            System.out.println("В базе закончилось свободное место!");
        } else if (getIndex(r.getUuid()) > 0) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в базе!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
