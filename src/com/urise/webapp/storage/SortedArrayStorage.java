package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        int index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("В базе закончилось свободное место!");
        } else if (index > 0) {
            System.out.println("Резюме " + r.getUuid() + " уже есть в базе!");
        } else {
            int indexForInsert = -index - 1;
            for (int i = size; i > indexForInsert; i--) {
                storage[i] = storage[i - 1];
            }
            storage[indexForInsert] = r;
            size++;
        }
    }

    //must use Arrays.binarySearch for save(Resume r) working correctly
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
