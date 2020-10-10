package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04;

import com.ssw.demo.javaConcurrentProgrammingPractice.Person;

import java.util.HashSet;
import java.util.Set;

/** 通过封装机制来确保线程安全(Person类的线程安全性未知，若其不安全，则需要额外的同步操作)
 * @author wss
 * @created 2020/9/14 15:53
 * @since 1.0
 */
public class code4_2 {

    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}
