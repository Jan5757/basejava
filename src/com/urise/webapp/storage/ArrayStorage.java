package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;//почему не final?
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void update(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        int index = findIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + r.getUuid() + " отсутствует в базе!");
        } else {
            storage[index] = r;
        }
    }

    //only new resume
    public void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        if (size == STORAGE_LIMIT) {
            System.out.println("В базе закончилось свободное место!");
        } else if (findIndex(r.getUuid()) > 0) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в базе!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " отсутствует в базе!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " отсутствует в базе!");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
