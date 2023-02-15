package by.barkovsky;

import by.barkovsky.model.*;
import by.barkovsky.util.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        //task1();
        //task2();
        //task3();
        //task4();
        //task5();
        //task6();
        //task7();
        //task8();
        //task9();
        //task10();
        //task11();
        //task12();
        //task13();
        //task14();
        //task15();
        task16();
    }

    private static void task1() throws IOException {
        System.out.println("----------Task 01----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> (animal.getAge() >= 10 && animal.getAge() <= 20))
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        System.out.println("----------Task 02----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> Objects.equals(animal.getOrigin(), "Japanese"))
                .map(animal -> {
                    if (Objects.equals(animal.getGender(), "Female")) {
                        animal.setBread(animal.getBread().toUpperCase(Locale.ROOT));
                    }
                    return animal;
                })
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        System.out.println("----------Task 03----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(s -> s.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        System.out.println("----------Task 04----------");
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> Objects.equals(animal.getGender(), "Female"))
                .count();
        System.out.println(count);
    }

    private static void task5() throws IOException {
        System.out.println("----------Task 05----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> ((animal.getAge() >= 10 && animal.getAge() <= 20)
                        && Objects.equals(animal.getOrigin(), "Hungarian")))
                .findAny()
                .ifPresent(System.out::println);
    }

    private static void task6() throws IOException {
        System.out.println("----------Task 06----------");
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .allMatch(animal -> (Objects.equals(animal.getGender(), "Male")
                        || Objects.equals(animal.getGender(), "Female")));
        System.out.println(result);
    }

    private static void task7() throws IOException {
        System.out.println("----------Task 07----------");
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .noneMatch(animal -> Objects.equals(animal.getOrigin(), "Oceania"));
        System.out.println(result);
    }

    private static void task8() throws IOException {
        System.out.println("----------Task 08----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent(animal -> System.out.println(animal.getAge()));
    }

    private static void task9() throws IOException {
        System.out.println("----------Task 09----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparing(chars -> chars.length))
                .ifPresent(chars -> System.out.println(chars.length));
    }

    private static void task10() throws IOException {
        System.out.println("----------Task 10----------");
        List<Animal> animals = Util.getAnimals();
        long sum = animals.stream()
                .mapToLong(Animal::getAge)
                .sum();
        System.out.println(sum);
    }

    private static void task11() throws IOException {
        System.out.println("----------Task 11----------");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> Objects.equals(animal.getOrigin(), "Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(value -> System.out.println(value));
    }

    private static void task12() throws IOException {
        System.out.println("----------Task 12----------");
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> (Objects.equals(person.getGender(), "Male")))
                .filter(person -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) >= 18)
                .filter(person -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) < 27)
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        System.out.println("----------Task 13----------");
        List<House> houses = Util.getHouses();
        Predicate<Person> isChild = (person) -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) < 18;
        Predicate<Person> isOld = (person) -> ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now()) >= 65;
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> {
                            if (Objects.equals(house.getBuildingType(), "Hospital")) {
                                return Map.entry(1, person);
                            } else {
                                return Map.entry(isChild.or(isOld).test(person) ? 2 : 3, person);
                            }
                        })
                )
                .sorted(Map.Entry.comparingByKey())
                .limit(500)
                .map(Map.Entry::getValue)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        System.out.println("----------Task 14----------");
        List<Car> cars = Util.getCars();
        Predicate<Car> firstEchelonPredicate = car -> Objects.equals(car.getCarMake(), "Jaguar") || Objects.equals(car.getColor(), "White");
        Predicate<Car> secondEchelonPredicate = car -> car.getMass() < 1500 &&
                Objects.equals(car.getCarMake(), "BMW")
                || Objects.equals(car.getCarMake(), "Lexus")
                || Objects.equals(car.getCarMake(), "Chrysler")
                || Objects.equals(car.getCarMake(), "Toyota");
        Predicate<Car> thirdEchelonPredicate = car -> (Objects.equals(car.getColor(), "Black") && car.getMass() > 4000)
                || Objects.equals(car.getCarMake(), "GMC")
                || Objects.equals(car.getCarMake(), "Dodge");
        Predicate<Car> fourthEchelonPredicate = car -> car.getReleaseYear() < 1982
                || (Objects.equals(car.getCarModel(), "Civic") && Objects.equals(car.getCarModel(), "Cherokee"));
        Predicate<Car> fifthEchelonPredicate = car -> (!Objects.equals(car.getColor(), "Yellow")
                && !Objects.equals(car.getColor(), "Red")
                && !Objects.equals(car.getColor(), "Green")
                && !Objects.equals(car.getColor(), "Blue"))
                || car.getPrice() > 40000;
        Predicate<Car> sixthEchelonPredicate = car -> car.getVin().contains("59");

        List<Car> firstEchelon = cars.stream()
                .filter(firstEchelonPredicate)
                .toList();
        List<Car> secondEchelon = cars.stream()
                .filter(secondEchelonPredicate.and(firstEchelonPredicate.negate()))
                .toList();
        List<Car> thirdEchelon = cars.stream()
                .filter(thirdEchelonPredicate
                        .and(firstEchelonPredicate.negate())
                        .and(secondEchelonPredicate.negate()))
                .toList();
        List<Car> fourthEchelon = cars.stream()
                .filter(fourthEchelonPredicate
                        .and(firstEchelonPredicate.negate())
                        .and(secondEchelonPredicate.negate())
                        .and(thirdEchelonPredicate.negate()))
                .toList();
        List<Car> fifthEchelon = cars.stream()
                .filter(fifthEchelonPredicate
                        .and(firstEchelonPredicate.negate())
                        .and(secondEchelonPredicate.negate())
                        .and(thirdEchelonPredicate.negate())
                        .and(fourthEchelonPredicate.negate()))
                .toList();
        List<Car> sixthEchelon = cars.stream()
                .filter(sixthEchelonPredicate
                        .and(firstEchelonPredicate.negate())
                        .and(secondEchelonPredicate.negate())
                        .and(thirdEchelonPredicate.negate())
                        .and(fourthEchelonPredicate.negate())
                        .and(fifthEchelonPredicate.negate()))
                .toList();

        double firstEchelonExpenses = calculateExpenses(firstEchelon);
        double secondEchelonExpenses = calculateExpenses(secondEchelon);
        double thirdEchelonExpenses = calculateExpenses(thirdEchelon);
        double fourthEchelonExpenses = calculateExpenses(fourthEchelon);
        double fifthEchelonExpenses = calculateExpenses(fifthEchelon);
        double sixthEchelonExpenses = calculateExpenses(sixthEchelon);

        double transportCost = firstEchelonExpenses + secondEchelonExpenses + thirdEchelonExpenses +
                fourthEchelonExpenses + fifthEchelonExpenses + sixthEchelonExpenses;

        double firstEchelonPrice = calculateEchelonPrice(firstEchelon);
        double secondEchelonPrice = calculateEchelonPrice(secondEchelon);
        double thirdEchelonPrice = calculateEchelonPrice(thirdEchelon);
        double fourthEchelonPrice = calculateEchelonPrice(fourthEchelon);
        double fifthEchelonPrice = calculateEchelonPrice(fifthEchelon);
        double sixthEchelonPrice = calculateEchelonPrice(sixthEchelon);

        double profit = firstEchelonPrice + secondEchelonPrice + thirdEchelonPrice + fourthEchelonPrice +
                fifthEchelonPrice + sixthEchelonPrice - transportCost;

        System.out.printf("First echelon transport expenses: %.2f$ \nFirst echelon cars price: %.2f$\n---------------\n",
                firstEchelonExpenses, firstEchelonPrice);
        System.out.printf("Second echelon transport expenses: %.2f$ \nSecond echelon cars price: %.2f$\n---------------\n",
                secondEchelonExpenses, secondEchelonPrice);
        System.out.printf("Third echelon transport expenses: %.2f$ \nThird echelon cars price: %.2f$\n---------------\n",
                thirdEchelonExpenses, thirdEchelonPrice);
        System.out.printf("Fourth echelon transport expenses: %.2f$ \nFourth echelon cars price: %.2f$\n---------------\n",
                fourthEchelonExpenses, fourthEchelonPrice);
        System.out.printf("Fifth echelon transport expenses: %.2f$ \nFifth echelon cars price: %.2f$\n---------------\n",
                fifthEchelonExpenses, fifthEchelonPrice);
        System.out.printf("Sixth echelon transport expenses: %.2f$ \nSixth echelon cars price: %.2f$\n---------------\n",
                sixthEchelonExpenses, sixthEchelonPrice);
        System.out.printf("Transportation cost: %.2f$\n", transportCost);
        System.out.printf("All cars cost - transportation cost: %.2f$\n", profit);
    }

    private static void task15() throws IOException {
        System.out.println("----------Task 15----------");
        List<Flower> flowers = Util.getFlowers();
        Predicate<Flower> flowerNamePredicate = flower -> flower.getCommonName().charAt(0) >= 'C'
                && flower.getCommonName().charAt(0) <= 'S';
        Predicate<Flower> flowerPredicate = flower -> flower.isShadePreferred()
                && (new HashSet<>(flower.getFlowerVaseMaterial()).containsAll(List.of("Glass", "Aluminum", "Steel")));

        double sum = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed()
                )
                .filter(flowerNamePredicate)
                .sorted(Comparator.comparing(Flower::getCommonName).reversed())
                .filter(flowerPredicate)
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * 365 * 5 * 1.39 / 1000)
                .sum();
        System.out.printf("Total amount: %.2f$", sum);
    }

    private static void task16() throws IOException {
        System.out.println("----------Task 16----------");
        List<Book> books = Util.getBooks();
        int readSpeed = 1;
        int dayTimeInMinute = 45;

        Predicate<Book> genresPredicate = book -> Objects.equals(book.getGenre(), "Fantasy")
                || Objects.equals(book.getGenre(), "Science Fiction")
                || Objects.equals(book.getGenre(), "Classics");
        Predicate<Book> authorsPredicate = book -> Objects.equals(book.getAuthor(), "Leigh Bardugo")
                || Objects.equals(book.getAuthor(), "Toni Morrison");
        Predicate<Book> yearsPredicate = book -> (book.getYear() < 1940) || (book.getYear() > 1990);

        System.out.println("Books list:");
        long pagesCount = books.stream()
                .filter(genresPredicate)
                .filter(authorsPredicate.negate())
                .filter(yearsPredicate)
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.groupingBy(Book::getAuthor))
                .entrySet().stream()
                .peek(System.out::println)
                .mapToLong(value -> value.getValue().stream().mapToLong(Book::getPageCount).reduce(0, Long::sum))
                .sum();

        double pagesPerDay = readSpeed * dayTimeInMinute;
        double days = Math.ceil(pagesCount / pagesPerDay);
        System.out.println("Days count: " + days);
    }

    private static double calculateExpenses(List<Car> carList) {
        double pricePerTon = 7.14d;
        return carList.stream().mapToDouble(Car::getMass).reduce(0, Double::sum) / 1000 * pricePerTon;
    }

    private static double calculateEchelonPrice(List<Car> carList) {
        return carList.stream().mapToDouble(Car::getMass).reduce(0, Double::sum);
    }
}