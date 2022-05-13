import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countResume = 0;

    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    //if the same "Unique identifier", the resume is replaced
    void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
        }
        storage[countResume] = r;
        countResume++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
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
    Resume[] getAll() {
        Resume[] resumes = new Resume[countResume];
        System.arraycopy(storage, 0, resumes, 0, countResume);
        return resumes;
    }

    int size() {
        return countResume;
    }

}
