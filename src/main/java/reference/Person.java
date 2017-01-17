package reference;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/17
 */

import java.time.LocalDate;

class Person {

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    private String name;
    LocalDate birthday;

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

