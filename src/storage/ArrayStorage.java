package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if(size >= storage.length){
            System.out.println("Storage overflow");
        }else {
            if(index == -1){
                storage[size] = r;
                size++;
            }else System.out.println("Index " + r.getUuid() + " already exist.");
        }



    }

    public Resume get(String uuid) {
        Resume r = new Resume();
        int index = getIndex(uuid);

        if(index == -1) System.out.println("Resume " + index + " not exist.");
        else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) r = storage[i];
            }
        }

        return r;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if(index == -1){
            System.out.println("Resume " + index + " not exist.");
        }else {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
        }
    }

    public void update(Resume resume){
        int index = getIndex(resume.getUuid());

        if(index > -1) storage[index] = resume;
        else System.out.println("Resume " + index + " not exist.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];

        if (size >= 0) System.arraycopy(storage, 0, resumes, 0, size);

        return resumes;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid){
        for(int i = 0; i < size; i++){
            if(storage[i].getUuid().equals(uuid)) return i;
        }

        return -1;
    }
}
