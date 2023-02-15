package by.sologub;

import by.sologub.model.Animal;
import by.sologub.model.Car;
import by.sologub.model.Flower;
import by.sologub.model.House;
import by.sologub.model.Person;
import by.sologub.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
//        task14();
//        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int zooCapacity = 7;
        int zooNumber = 3;
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(zooCapacity * (zooNumber - 1))
                .limit(zooCapacity)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String origin = "Japanese";
        String gender = "Female";
        animals.stream()
                .filter(animal -> origin.equals(animal.getOrigin()))
                .map(animal -> {
                    animal.setBread(animal.getBread().toUpperCase());
                    return animal;
                })
                .filter(animal -> gender.equals(animal.getGender()))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int minAge = 30;
        String firstLetter = "A";
        animals.stream()
                .filter(animal -> animal.getAge() > minAge)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith(firstLetter))
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String gender = "Female";
        long femaleAnimals = animals.stream()
                .filter(animal -> gender.equals(animal.getGender()))
                .count();
        System.out.println("Female animals: " + femaleAnimals);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int minAge = 20;
        int maxAge = 30;
        String origin = "Hungarian";
        boolean containHungarianAnimal = animals.stream()
                .filter(animal -> animal.getAge() >= minAge && animal.getAge() <= maxAge)
                .anyMatch(animal -> origin.equals(animal.getOrigin()));
        System.out.println("Contain animal from Hungarian: " + containHungarianAnimal);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String genderMale = "Male";
        String genderFemale = "Female";
        boolean allMaleOrFemale = animals.stream()
                .allMatch(animal -> genderMale.equals(animal.getGender()) || genderFemale.equals(animal.getGender()));
        System.out.println("All animals are male or female: " + allMaleOrFemale);
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String origin = "Oceania";
        boolean noAnimalsFromOceania = animals.stream().noneMatch(animal -> origin.equals(animal.getOrigin()));
        System.out.println("No animals from Oceania: " + noAnimalsFromOceania);
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int animalsLimit = 100;
        OptionalInt maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(animalsLimit)
                .mapToInt(Animal::getAge)
                .max();
        System.out.println("Max age: " + (maxAge.isPresent() ? maxAge.getAsInt() : "no animals"));
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        OptionalInt minLength = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(bread -> bread.length)
                .min();
        System.out.println("Min length: " + (minLength.isPresent() ? minLength.getAsInt() : "no animals"));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        int ageSum = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println("Age sum: " + ageSum);
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        String origin = "Indonesian";
        OptionalDouble indonesianAnimalsAverageAge = animals.stream()
                .filter(animal -> origin.equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average();
        System.out.println("Average age animals from Indonesian: " +
                (indonesianAnimalsAverageAge.isPresent() ? indonesianAnimalsAverageAge.getAsDouble() : "no animals"));
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        int minAge = 18;
        int maxAge = 27;
        String gender = "Male";
        int academyCapacity = 200;
        Predicate<Person> ageInLimits = person -> {
            int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
            return age >= minAge && maxAge < maxAge;
        };
        people.stream()
                .filter(person -> gender.equals(person.getGender()))
                .filter(ageInLimits)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(academyCapacity)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        String hospital = "Hospital";
        int transportCapacity = 500;
        int maxAgeForChildren = 18;
        int oldMaleMinAge = 63;
        int oldFemaleMinAge = 58;
        String maleGender = "Male";
        String femaleGender = "Female";
        Predicate<Person> isInSecondStage = person -> {
            int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
            return age < maxAgeForChildren ||
                    (maleGender.equals(person.getGender()) && age >= oldMaleMinAge) ||
                    (femaleGender.equals(person.getGender()) && age >= oldFemaleMinAge) ||
                    age >= oldMaleMinAge;
        };
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry(hospital.equals(house.getBuildingType()) ? 1 :
                                isInSecondStage.test(person) ? 2 : 3, person)))
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .limit(transportCapacity)
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        int kgToTonCoefficient = 1000;
        double pricePerTon = 7.14;
        int decimalPlaces = 2;
        String keyNotDelivered = "Not delivered";
        Predicate<Car> carForTurkmenistan = car -> "Jaguar".equals(car.getCarMake()) ||
                "White".equals(car.getColor());
        Predicate<Car> carForUzbekistan = car -> car.getMass() < 1500 ||
                List.of("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake());
        Predicate<Car> carForKazakhstan = car -> ("Black".equals(car.getColor()) && car.getMass() > 4000) ||
                List.of("GMC", "Dodge").contains(car.getCarMake());
        Predicate<Car> carForKyrgyzstan = car -> car.getReleaseYear() < 1982 ||
                List.of("Civic", "Cherokee").contains(car.getCarModel());
        Predicate<Car> carForRussia = car -> !List.of("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
                car.getPrice() > 40000;
        Predicate<Car> carForMongolia = car -> car.getVin().contains("59");
        Map<String, List<Car>> carsForDelivery = cars.stream()
                .collect(Collectors.groupingBy(car ->
                        carForTurkmenistan.test(car) ? "1. Turkmenistan" :
                                carForUzbekistan.test(car) ? "2. Uzbekistan" :
                                        carForKazakhstan.test(car) ? "3. Kazakhstan" :
                                                carForKyrgyzstan.test(car) ? "4. Kyrgyzstan" :
                                                        carForRussia.test(car) ? "5. Russia" :
                                                                carForMongolia.test(car) ? "6. Mongolia" : keyNotDelivered));
        carsForDelivery.remove(keyNotDelivered);
        carsForDelivery.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .peek(carsForCountry -> System.out.print(carsForCountry.getKey() + " has cars for "))
                .mapToInt(e -> e.getValue().stream().mapToInt(Car::getMass).sum())
                .mapToDouble(mass -> mass / kgToTonCoefficient)
                .reduce(0, (left, right) -> {
                    double result = left + right;
                    System.out.println(result + " ton");
                    return result;
                });
        BigDecimal totalSum = carsForDelivery.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().stream()
                        .mapToDouble(car -> car.getMass() * pricePerTon / kgToTonCoefficient)
                        .mapToObj(BigDecimal::new)
                        .map(price -> price.setScale(decimalPlaces, RoundingMode.HALF_UP))
                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .peek(carsForCountry ->
                        System.out.println(carsForCountry.getKey() + " has cars for " + carsForCountry.getValue() + "$"))
                .map(Map.Entry::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total sum vor delivery service: " + totalSum + "$");
        ;
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        List<String> flowerVaseMaterial = List.of("Glass", "Aluminum", "Steel");
        String namePattern = "[C-S].+";
        int yearForCount = 5;
        int avgDays = 365;
        double waterCubicMeterCost = 1.39;
        int literToCubicMeterCoefficient = 1000;
        int decimalPlaces = 2;
        double sum = flowers.stream()
                .filter(flower -> flower.getCommonName().matches(namePattern))
                .filter(Flower::isShadePreferred)
                .filter(flower -> flowerVaseMaterial.stream()
                        .anyMatch(material -> flower.getFlowerVaseMaterial().contains(material)))
                .sorted(Comparator.comparing(Flower::getOrigin, Comparator.reverseOrder())
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay, Comparator.reverseOrder())
                        .thenComparing(Flower::getCommonName, Comparator.reverseOrder()))
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() *
                        yearForCount * avgDays * waterCubicMeterCost / literToCubicMeterCoefficient)
                .mapToObj(BigDecimal::new)
                .map(totalPrice -> totalPrice.setScale(decimalPlaces, RoundingMode.HALF_UP))
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        System.out.println("Sum for 5 years is " + sum + "$");
    }

    private static void task16() throws IOException {
    }
}