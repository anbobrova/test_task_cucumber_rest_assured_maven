Feature: Поиск на сайте datausa.io

  Background: Сайт открыт
    Given Открываем сайт "https://datausa.io/search/"

  Scenario:Вводим текст в строку поиска и проверяем что результат найден
    When Вводим текст "Masters Colleges and Universities" в строку поиска
    And Кликаем на результат "Masters Colleges and Universities"
    Then Открыта страница отчета "Masters Colleges and Universities"

  Scenario:Вводим текст в строку поиска и проверяем что результат не найден
    When Вводим текст "Nothing to display" в строку поиска
    Then Поле с результатами отображает текст NO RESULTS FOUND

  Scenario Outline:Вводим текст в строку поиска и проверяем количество результатов
    When Вводим текст "<searchText>" в строку поиска
    Then Проверяем, что количество элементов равно "<reportsNumber>"

    Examples:
      | searchText                       | reportsNumber |
      | Computer and Information         | 2             |
      | Other Computer                   | 10            |
      | Support Service                  | 22            |
