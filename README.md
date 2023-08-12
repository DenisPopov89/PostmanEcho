# postmanEcho
[![Build status](https://ci.appveyor.com/api/projects/status/2ta3o9ocuwil3ndg?svg=true)](https://ci.appveyor.com/project/DenisGrigoryevichPopov/ci-appveyor)
## Задача №3 - Postman Echo

**Важно**: эту задачу нужно выполнять в отдельном репозитории

В этой задаче мы сэмулируем ситуацию, в которой SUT уже запущен, а мы из теста просто обращаемся к нему.

Есть специальный сервис, предназначенный для тестирования HTTP-запросов. Называется он [Postman Echo](https://docs.postman-echo.com) (никогда не тестируйте автоматизированными средствами веб-сервисы, если у вас нет на этого письменного разрешения либо веб-сервисы специально не предназначены для этого).

Мы можем отправлять туда запросы и получать ответы.

С GET-запросами мы немного потренировались, теперь нас будут интересовать POST-запросы, а именно отправка тела запроса:

```java
// Given - When - Then
// Предусловия
given()
  .baseUri("https://postman-echo.com")
  .body("some data") // отправляемые данные (заголовки и query можно выставлять аналогично)
// Выполняемые действия
.when()
  .post("/post")
// Проверки
.then()
  .statusCode(200)
  .body(/* --> ваша проверка здесь <-- */)
;
```

Что нужно сделать:
1. Создайте новый проект на базе Gradle
2. Добавьте необходимые зависимости (если вы не пишите схему, то только rest-assured)
3. Напишите тест, взяв сам запрос из кода выше
4. Изучите ответ и напишите JsonPath-выражение вместо строк `/* --> ваша проверка здесь <--*/`, которое проверит, что в нужном поле хранятся отправленные вами данные (обратите внимание, теперь у вас не массив, а объект).

Можете воспользоваться сервисом https://rapathevaluate.herokuapp.com для быстрой проверки своих JsonPath-выражений.

Удостоверьтесь, что если вы будете использовать неверное выражение, то тесты упадут (в том числе и в CI).

Обратите внимание: если вам приходит вот такой объект:
```json
{
    "data": "some value"
}
```

То обратиться к нему с помощью JsonPath можно вот так: `data`, например: `.body("data", equalTo("some value"))`. Т.е. обращение к полю верхнеуровневого объекта (он называется безымянный) идёт без точки (в примере на лекциях у нас был массив и мы сразу обращались `[0]` - то же самое).

Если соберётесь отправлять текст не на латинице, то вам нужно будет выставлять кодировку (например, UTF-8):
```java
given()
  .baseUri("https://postman-echo.com")
  .contentType("text/plain; charset=UTF-8")
  .body("some data")
.when()
  .post("/post")
.then()
  .statusCode(200)
  .body(/* --> ваша проверка здесь <-- */)
;
```

Пришлите на проверку ссылку на ваш репозиторий (удостоверьтесь, что в истории сборки были как Success, так и Fail, иначе будет не видно, как вы проверяли, что сборка падает в CI).
