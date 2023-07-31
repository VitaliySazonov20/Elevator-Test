Тестовое задание

Требования к реализации системы управления лифтом.
Система управления лифтом – комплекс программно-аппаратных средств для обеспечения управлением 2-х кабинным лифтом, который бы обеспечивал эффективный перевоз пассажиров в многоквартирном доме (20 этажей).
1 кабина – грузоподъемность 400 кг (5 человек)
2 кабина – грузоподъемность 800 кг (10 человек)
Каждый этаж оборудован:
•	одной кнопкой вызова: на первом этаже для поднятия вверх, на других этажах – спуска вниз.
•	2 дисплеями для каждой кабины с информацией, на каком этаже она сейчас находится.
Каждая кабина оборудована:
•	кнопками этажей (20 шт)
•	кнопкой закрытия дверей
•	кнопкой открытия дверей
•	кнопка вызова диспетчера
•	дисплеем с номером текущего этажа

Задание
1.	Необходимо разработать консольную программу (вывод в командную строку), которая реализует следующие классы:
Кабина лифта
Свойства:
a.	Этаж (где находится в данный момент времени кабина) для вывода на дисплее.
b.	Состояние (едет вверх/едет вниз/открывает двери/закрывает двери/стоит с открытыми дверьми)
Методы:
c.	Нажать кнопку этажа (1-20)
d.	Нажать кнопку закрытия дверей
e.	Нажать кнопку открытия дверей
f.	Нажать кнопку вызова диспетчера
g.	Датчик кабины фиксирует движение между дверьми
h.	Датчик кабины фиксирует отсутствие движения между дверьми
Этаж
Свойства:
i.	Текущий этаж кабины 1
j.	Текущий статус кабины 1
k.	Текущий этаж кабины 2
l.	Текущий статус кабины 2
m.	Статус кнопки вызова лифта (вызван/не вызван)
Методы:
n.	Нажать кнопку вызова лифта
2.	Создать объекты описанных классов для моделирования работы 2 лифтов на 20 этажах. Исходное положение лифтов – 1 этаж
3.	Написать метод, который имитирует действия пассажиров:
a.	Пассажир 1 вызывает лифт на 1м этаже и поднимается на 14 этаж
b.	Пассажир 2 вызывает лифт на 15 этаже и опускается на 1 этаж
4.	Все действия пассажиров и изменения статусов лифтов должны выводиться в консольное окно
5.	Если для решения будет не хватать каких-то параметров и требований, то можно использовать любые допущения. Например, в требованиях нет информации по скорости движения лифтов – можете ввести любую скорость и т.д.
6.	Стек – любой предпочтительный из C#, C/C++, Python, Delphi/Pascal, Java.
7.	Код необходимо залить в GitHub и прислать ссылку на него
8.	Ожидаемый срок решения – 5 дней
