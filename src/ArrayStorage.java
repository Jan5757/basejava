import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int firstIndexOfNullResume = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            } else {
                firstIndexOfNullResume = 0;
                return;
            }
        }
    }

    //if the same "Unique identifier", the resume is replaced
    void save(Resume r) {
        Objects.requireNonNull(r, "Резюме не должно быть пустым (null)");
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(r.uuid)) {
                    storage[i] = r;
                    return;
                } else if (i == storage.length - 1) {
                    resize();
                }
            } else {
                storage[i] = r;
                firstIndexOfNullResume++;
                return;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < firstIndexOfNullResume; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < firstIndexOfNullResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                swapBeforeLastNull(i);
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[firstIndexOfNullResume];
        System.arraycopy(storage, 0, result, 0, firstIndexOfNullResume);
        return result;
    }

    int size() {
        return firstIndexOfNullResume;
    }

    private void resize() {
        Resume[] newStorage = new Resume[storage.length * 2];
        System.arraycopy(storage, 0, newStorage, 0, storage.length);
        storage = newStorage;
    }

    private void swapBeforeLastNull(int index) {
        storage[index] = storage[firstIndexOfNullResume - 1];
        storage[firstIndexOfNullResume - 1] = null;
    }
}
