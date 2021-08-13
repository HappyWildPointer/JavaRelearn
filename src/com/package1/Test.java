package com.package1;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId,Person::getName));
        System.out.println("idToName:"+idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: "+idToPerson.getClass().getName()+idToPerson);

        idToPerson = people().collect(
                Collectors.toMap(Person::getId,Function.identity(),(existingValue, newValue) -> {
                    throw new IllegalStateException();
                }, TreeMap::new)
        );
        //第三个引元阐述了如果原有的键值和新的键值产生冲突的解决方法（实际上默认的解决就是抛出异常），第四个引元使得本该为Map的本方法生产了TreeMap.
        System.out.println("idToPerson: "+idToPerson.getClass().getName()+idToPerson);
        Stream<Locale> locals = Stream.of(Locale.getAvailableLocales());
        Map<String,String> languageNames = locals.collect(
                Collectors.toMap(
                        Locale::getDisplayLanguage, l -> l.getDisplayLanguage(l),
                        (existingValue, newValue) -> {throw new IllegalStateException();},
                        TreeMap::new
                )
        );
    }

    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return getClass().getName() + "[id=" + id + "name=" + name + "]";
        }
    }

    public static Stream<Person> people() {
        return Stream.of(new Person(100, "Peter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
    }



}

