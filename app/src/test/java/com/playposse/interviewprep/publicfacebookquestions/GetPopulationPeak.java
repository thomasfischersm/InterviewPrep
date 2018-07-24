package com.playposse.interviewprep.publicfacebookquestions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given an array of people's birth and death years, determine which year, the population peaked.
 */
public class GetPopulationPeak   {

    @Test
    public void test0() {
        Person[] persons = new Person[] {
                new Person(1800, 1870),
                new Person(1801, 1871),
                new Person(1802, 1872)};
        assertEquals(1802, findYearOfPeakPopulation(persons));
    }

    @Test
    public void test1() {
        Person[] persons = new Person[] {
                new Person(1800, 1801),
                new Person(1801, 1871),
                new Person(1802, 1872),
                new Person(1803, 1872)};
        assertEquals(1803, findYearOfPeakPopulation(persons));
    }

    @Test
    public void test2() {
        Person[] persons = new Person[] {
                new Person(1800, 1801),
                new Person(1801, 1801),
                new Person(1802, 1802),
                new Person(1803, 1803)};
        assertEquals(1801, findYearOfPeakPopulation(persons));
    }

    private static int findYearOfPeakPopulation(Person[] persons) {
        // Note: Discuss what to do if there is no matching year, e.g. return 0 or -1.

        // Check for good input.
        if ((persons == null) || (persons.length == 0)) {
            return 0;
        }

        // Find min and max year.
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        for (Person person : persons) {
            minYear = Math.min(minYear, person.getBirthYear());
            maxYear = Math.max(maxYear, person.getBirthYear());
            // Note: Could check for valid data, e.g. birth before death, realistic age.
        }

        // Increment max year to get the upper boundary exclusive.
        maxYear++;

        // Create array of deltas.
        int[] delta = new int[maxYear - minYear];
        for (Person person : persons) {
            delta[person.getBirthYear() - minYear]++;

            int deathIndex = person.getDeathYear() - minYear + 1;
            if (deathIndex < delta.length) {
                delta[deathIndex]--;
            }
        }

        // Iterate through population by year.
        int pop = 0;
        int maxPop = 0;
        int maxPopIndex = 0;
        for (int i = 0; i < delta.length; i++) {
            pop += delta[i];
            if (pop > maxPop) {
                maxPop = pop;
                maxPopIndex = i;
            }
        }

        return maxPopIndex + minYear;
    }

    private static class Person {

        private final int birthYear;
        private final int deathYear;

        public Person(int birthYear, int deathYear) {
            this.birthYear = birthYear;
            this.deathYear = deathYear;
        }

        private int getBirthYear() {
            return birthYear;
        }

        private int getDeathYear() {
            return deathYear;
        }
    }
}
