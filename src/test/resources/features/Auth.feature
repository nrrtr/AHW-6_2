#language:ru
Функциональность: перевод средств

  Структура сценария:
    Пусть пользователь залогинен с именем <login> и паролем <password>
    Когда пользователь переводит <amount> рублей с карты с номером <cardNumber> на свою <cardId> карту с главной страницы
    Тогда баланс его <cardId> карты из списка на главной странице должен стать <expectedBalance> рублей
    Примеры:
      | login   | password    | amount  | cardNumber            | cardId | expectedBalance |
      | "vasya" | "qwerty123" | "5000"  | "5559 0000 0000 0002" | "1"    | "15000"         |
      | "vasya" | "qwerty123" | "10000" | "5559 0000 0000 0002" | "1"    | "20000"         |
      | "vasya" | "qwerty123" | "5000"  | "5559 0000 0000 0001" | "2"    | "15000"         |
      | "vasya" | "qwerty123" | "10000" | "5559 0000 0000 0001" | "2"    | "20000"         |