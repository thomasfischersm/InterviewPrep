package com.playposse.interviewprep.crackingthecodeinterview.stacksandqueues;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by thoma on 7/8/2018.
 */
public class AnimalShelter {

    @Test
    public void test() {
        SpecialQueue queue = new SpecialQueue();

        // Simple add/remove.
        queue.enqueue(new Dog(1));
        assertNull(queue.dequeueCat());
        assertEquals(1, queue.dequeueDog().getId());

        // Add/remove with a distraction.
        queue.enqueue(new Dog(2));
        queue.enqueue(new Cat(3));
        assertEquals(2, queue.dequeueDog().getId());

        // Add multiple.
        queue.enqueue(new Dog(4));
        queue.enqueue(new Dog(5));
        queue.enqueue(new Dog(6));
        assertEquals(4, queue.dequeueDog().getId());
        assertEquals(5, queue.dequeueDog().getId());
        assertEquals(6, queue.dequeueDog().getId());

        queue.enqueue(new Dog(7));
        assertEquals(3, queue.dequeueAny().getId());
        assertEquals(7, queue.dequeueAny().getId());

        assertNull(queue.dequeueAny());
        assertNull(queue.dequeueCat());
        assertNull(queue.dequeueDog());
    }

    private static abstract class Animal {

        private final int id;

        protected Animal(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private static class SpecialQueue {

        private final LinkedList<Wrapper<Dog>> dogs = new LinkedList<>();
        private final LinkedList<Wrapper<Cat>> cats = new LinkedList<>();

        private long currentTime = 0;

        public void enqueue(Dog dog) {
            dogs.add(new Wrapper<Dog>(dog, currentTime++));
        }

        public void enqueue(Cat cat) {
            cats.add(new Wrapper<Cat>(cat, currentTime++));

        }

        public Animal dequeueAny() {
            if (dogs.isEmpty() && cats.isEmpty()) {
                return null;
            } else if (cats.isEmpty()) {
                return dequeueDog();
            } else if (dogs.isEmpty()) {
                return dequeueCat();
            }

            Wrapper<Dog> dogWrapper = dogs.peek();
            Wrapper<Cat> catWrapper = cats.peek();

            if (dogWrapper.getTimeStamp() < catWrapper.getTimeStamp()) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }

        public Dog dequeueDog() {
            if (dogs.isEmpty()) {
                return null;
            } else {
                return dogs.remove(0).getAnimal();
            }
        }

        public Cat dequeueCat() {
            if (cats.isEmpty()) {
                return null;
            } else {
                return cats.remove(0).getAnimal();
            }
        }
    }

    private static class Dog extends Animal {
        protected Dog(int id) {
            super(id);
        }
    }

    private static class Cat extends Animal {
        protected Cat(int id) {
            super(id);
        }
    }

    private static class Wrapper<T extends Animal> {

        private final T animal;
        private final long timeStamp;

        private Wrapper(T animal, long timeStamp) {
            this.animal = animal;
            this.timeStamp = timeStamp;
        }

        public T getAnimal() {
            return animal;
        }

        public long getTimeStamp() {
            return timeStamp;
        }
    }
}
