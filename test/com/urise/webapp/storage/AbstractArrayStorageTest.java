package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @After
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        for (Resume r : storage.getAll()) {
            Assert.assertNull(r);
        }
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_3));
        Assert.assertNotSame(storage.get(UUID_3), resume3);
    }

    @Test
    public void save() {
        storage.save(new Resume());
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void get() {
        Resume result = storage.get(UUID_1);
        Assert.assertSame(result, resume1);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] result = storage.getAll();
        for (int i = 0; i < storage.size(); i++) {
            Assert.assertSame(result[i], storage.get(result[i].getUuid()));
        }
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("OMyDummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Overflow is too early");
        }
        storage.save(new Resume());
    }
}