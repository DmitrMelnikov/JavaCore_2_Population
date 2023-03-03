import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

//        Из коллеции объектов Person необходимо:
//
//        Найти количество несовершеннолетних (т.е. людей младше 18 лет).
//        Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
//        Получить отсортированный по фамилии список потенциально работоспособных
//        людей с высшим образованием в выборке
//        (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        // Несовершенолетние
        long stream18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(stream18);

        // призывники
        long streamArmy = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .count();
        System.out.println(streamArmy);

        // Работающие граждане c высшим образованием.

        List<Person> streamWorker = persons.stream()
                .filter(x -> (x.getAge() >= 18 && x.getAge() <= 65 && x.getSex() == Sex.MAN) ||
                       (x.getAge() >= 18 && x.getAge() <= 60 && x.getSex() == Sex.WOMAN))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        streamWorker.forEach(System.out::println);


    }
}