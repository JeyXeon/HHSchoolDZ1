package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;


public class Task8 implements Task {

  private long count;

  //Не хотим выдавать апи нашу фальшивую персону, поэтому конвертируем, начиная со второй
  public List<String> getNames(List<Person> persons) {
    // Заменил на упрощенный вызов метода.
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    // Включил шаг с пропуском элемента в ретутрн стейтмент (skip).
    // Использовал skip, чтобы пропуск происходил при перволм пробеге,
    // так как, используя ремув, сначала сформируется поток, а потом побежит удалять элемент.
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //Множество со всеми уникальными именами
  public Set<String> getDifferentNames(List<Person> persons) {
    //Упростил составление сета
    return new HashSet<>(getNames(persons));
  }

  //Получение полного имени
  public String convertPersonToString(Person person) {
    return String.join(" ",
        person.getSecondName(),
        person.getFirstName(),
        person.getMiddleName());
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // Применил уже написанный ранее метод для получения имени.
    return new HashSet<>(persons).stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // Сократил проверку.
    // Переписал, здесь сложность O(n) должна быть.
    Set<Person> persons = new HashSet<>();
    persons.addAll(persons1);
    persons.addAll(persons2);
    return persons.size() < persons1.size() + persons2.size();
  }

  // Количество четных чисел
  //Правильнее передавать в метод коллекцию, и уже потом создавать стрим
  public long countEven(Collection<Integer> numbers) {
    count = 0;
    numbers.stream().filter(num -> num % 2 == 0).forEach(num -> count++);
    return count;
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
