import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int CountResume = 0;

    void clear() {
        for (int i = 0; i < CountResume; i++) {
            storage[i] = null;
        }
        CountResume = 0;
    }

    //if the same "Unique identifier", the resume is replaced
    void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        for (int i = 0; i < CountResume; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
        }
        storage[CountResume] = r;
        CountResume++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < CountResume; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < CountResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[CountResume - 1];
                storage[CountResume - 1] = null;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[CountResume];
        System.arraycopy(storage, 0, resumes, 0, CountResume);
        return resumes;
    }

    int size() {
        return CountResume;
    }

}
