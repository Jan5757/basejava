package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "DummyBlank";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @AfterEach
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        Resume expected = new Resume(UUID_3);
        storage.update(expected);
        Resume actual = storage.get(UUID_3);
        Assertions.assertSame(expected, actual);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assertions.assertArrayEquals(expected, storage.getAll());
        assertSize(storage.getAll().length);

    }

    @Test
    public void size() {
        assertSize(3);
    }

    public void assertSize(int expected) {
        Assertions.assertEquals(expected, storage.size());
    }

    public void assertGet(Resume expected) {
        Assertions.assertEquals(expected, storage.get(expected.getUuid()));
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, ()-> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, ()-> storage.update(new Resume(UUID_NOT_EXIST)), "Exception not throw");
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, ()-> storage.save(RESUME_1));
    }

}