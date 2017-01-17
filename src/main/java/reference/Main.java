package reference;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

/**
 * function
 * Author: yang.hao
 * Date: 2017/1/17
 */
public class Main {
    static class PersonAgeComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            return a.getBirthday().compareTo(b.getBirthday());
        }
    }

    public static void main(String[] args) {
        Person[] pArr = new Person[]{
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2016,3,1)),
                new Person("004", LocalDate.of(2016,12,1))};

        //Arrays.sort(pArr,new PersonAgeComparator());
        Arrays.sort(pArr,(Person a,Person b) -> a.getBirthday().compareTo(b.getBirthday()));
        Arrays.sort(pArr,(a,b) -> Person.compareByAge(a,b));
        Arrays.sort(pArr,Person::compareByAge);
        System.out.println(Arrays.asList(pArr));
    }
}

