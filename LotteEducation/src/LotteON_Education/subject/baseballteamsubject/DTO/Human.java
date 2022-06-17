package LotteON_Education.subject.baseballteamsubject.DTO;

import java.util.Objects;

public class Human {
    public int backNumber;
    public String name;
    public int age;
    public int key;
    public Long id;

    public int getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(int backNumber) {
        this.backNumber = backNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Human{" +
                "backNumber=" + backNumber +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", key=" + key +
                ", id=" + id +
                '}';
    }
}
