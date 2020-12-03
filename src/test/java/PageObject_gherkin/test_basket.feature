Feature: String operation

  Scenario Outline: :
    When Добавим <number_of_product> единицы товара в корзину
    And Удалим все товары из коризины
    Then Перeйдем на главную страницу магазина
    And Проверим является ли последняя отрытая страница главной


    Examples:
    | number_of_product |
    | 1                 |
    | 3                 |

