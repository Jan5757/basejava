package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, countResume-1, null);
        countResume = 0;
    }

    public void update(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        if (!isPesumePresent(r.uuid)) {
            System.out.println("Резюме " + r.uuid + " отсутствует в базе!");
            return;
        }
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
        }
    }

    //only new resume
    public void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        if (isPesumePresent(r.uuid)) {
            System.out.println("Резюме " + r.uuid + " уже есть в базе!");
            return;
        }
        if (countResume == storage.length) {
            System.out.println("В базе закончилось свободное место!");
            return;
        }
        storage[countResume] = r;
        countResume++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        System.out.println("Резюме " + uuid + " отсутствует в базе!");
        return null;
    }

    public void delete(String uuid) {
        if (!isPesumePresent(uuid)) {
            System.out.println("Резюме " + uuid + " отсутствует в базе!");
            return;
        }
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[countResume - 1];
                storage[countResume - 1] = null;
                countResume--;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, countResume );
    }

    public int size() {
        return countResume;
    }

    boolean isPesumePresent(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

}
