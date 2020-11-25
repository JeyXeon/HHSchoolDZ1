package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  //Не хотим выдавать апи нашу фальшивую персону, поэтому конвертируем, начиная со второй
  public List<String> getNames(List<Person> persons) {
    // Заменил на упрощенный вызов метода.
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    // Включил шаг с пропуском элемента в ретутрн стейтмент (skip).
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //Множество со всеми уникальными именами
  public Set<String> getDifferentNames(List<Person> persons) {
    //Упростил составление сета
    return new HashSet<>(getNames(persons));
  }

  //Получение полного имени
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // Применил уже написанный ранее метод для получения имени.
    return persons.stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // Сократил проверку.
    boolean has = false;
    for (Person person : persons1) {
      if (persons2.contains(person)) {
        has = true;
        break;
      }
    }
    return has;
  }

  // Количество четных чисел
  public long countEven(Stream<Integer> numbers) {
    count = 0;
    numbers.filter(num -> num % 2 == 0).forEach(num -> count++);
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
