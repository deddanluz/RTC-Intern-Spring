# RTC-Intern-Spring
Spring Boot приложение, онлайн-дневник для хранения информации о группах, учебных планах, учениках и успеваемости в PostgreSQl и предоставляющее REST API интерфейс для получения средней оценки ученика по номеру группы и изменение оценки у конкретного ученика по фамилии, имени, группе и предмету

## Требования
Данный проект создан с помощью Apache NetBeans IDE 18 и Maven. Для запуска данного консольного приложения требуется JVM версии не ниже 20, а также PostgreSQL версии 16. Для соединения с БД необходимо указать адрес, логин и пароль в файле `src/main/resources/application.properties` (при использовании через командную строку необходимо пересобрать jar-файл). Приложение работает с уже заполненными таблицами БД согласно сруктуре, приведенной в разделе [Структура БД](https://github.com/deddanluz/RTC-Intern-Spring/tree/main#структура-бд). В папке с проектом также должна находиться папка `lib` - в ней хранятся зависимости. Это нужно для того, чтобы запускать приложение из командной строки.

## Структура БД
Для корректной работы необходимо перед первым запуском создать таблицы в соответсвии с данной структурой:

```
CREATE TABLE IF NOT EXISTS intern.groups (
    id SERIAL PRIMARY KEY,
    number INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS intern.students (
    id SERIAL PRIMARY KEY,
    family VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	age INTEGER NOT NULL,
    group_id INTEGER NOT NULL REFERENCES intern.groups (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS intern.subjects (
    id SERIAL PRIMARY KEY,
	subject VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS intern.groups_subjects (
	group_id INTEGER NOT NULL,
    subject_id INTEGER NOT NULL,
    PRIMARY KEY (group_id, subject_id),
    FOREIGN KEY (group_id) REFERENCES intern.groups (id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES intern.subjects (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS intern.performance (
    id SERIAL PRIMARY KEY,
	student_id INTEGER NOT NULL,
    subject_id INTEGER NOT NULL,
    grade INTEGER NOT NULL,
    FOREIGN KEY (student_id) REFERENCES intern.students (id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES intern.subjects (id) ON DELETE CASCADE

);
```
**Допустимым вариантом также является установка параметра `spring.jpa.hibernate.ddl-auto` в файле `src/main/resources/application.properties` в `create` или `update`.**

## REST API
Приложение предоставляет доступ к REST API интерфейсу по адресу: `http://localhost:8080/students`. Реализованы методы:
- GET (получения средних оценок учеников по номеру группы).
  Обрабатываются запросы вида `http://localhost:8080/group/{groupNumber}/average_grades`.
  где `groupNumber` должно быть целым числом (в противном случае будет получена ошибка 400).
  В ответ будет возвращена следующая структура (например), содержащая информацию об ученике и средней оценки:

  ```
  [
    {
        "student": {
            "id": 6,
            "family": "Абрамов",
            "name": "Александр",
            "age": 16,
            "group": {
                "id": 4,
                "number": 10
            }
        },
        "averageGrade": 2.1666666666666665
    },
    ...
  ]
  ```
  **Время первого запроса составляет около 1 секунды (на БД, содержащей 100 тыс. записей учеников), при повторных запросах время сокращается до нескольких сотен миллисекунд.**
  
- PUT (изменения оценки у ученика по фамилии, имени, номеру группы и предмету).
  Обрабатываются запросы вида `http://localhost:8080/student/{studentId}/subject/{subjectId}`.
  В теле запроса должен быть объект оценки (например), значение оценки из которого будет записано (заменено) в БД:

  ```
  {
    "grade":1
  }
  ```

  В ответ будет возвращена следующая структура (например), соответствующая измененным объектам учеников:

  ```
  {
    "id": 71719,
    "family": "Попов",
    "name": "Илья",
    "age": 16,
    "group": {
        "id": 4,
        "number": 10
    }
  }
  ```
  **Время выполнения запроса сотавляет несколько сотен миллисекунд.**

_При отправке некорректного запроса будет возвращен код ошибки 400._

## Версии

### 1.1
Исправлено:
- папки target и lib исключены,
- @Repository удалена,
- name-generated методы заменены на query-methods,
- raw-type коллекции больше не используются,
- dto классы не зависят от entity; entity не используются как тело запроса или ответа (применен Mapstruct);
- получение средней оценки перенесено в sql-запрос,
- BeanUtils больше не используется,
- изменены запросы.

### 1.0
Реализовано:
- взаимодействие с PostgreSQL на основе Spring Data JPA,
- REST API интерфейс (GET и PUT), для получения редних оценок по номеру группы и изменения оценки по ученику, группе и предмету.
