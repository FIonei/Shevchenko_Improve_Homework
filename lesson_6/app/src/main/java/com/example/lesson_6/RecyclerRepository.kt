package com.example.lesson_6

class RecyclerRepository {
    val items = listOf(
        RecyclerItem(
            itemName = "Холодная вода",
            itemNumber = 37956324,
            isRedAlert = true,
            commentary = "Отправьте данные до 18.03.2021"
        ),
        RecyclerItem(
            itemName = "Горячая вода",
            itemNumber = 3452345,
            isRedAlert = true,
            commentary = "Отправьте данные до 18.03.2021"
        ),
        RecyclerItem(
            itemName = "Электричество",
            itemNumber = 34213412,
            isRedAlert = false,
            commentary = "Оплачено, дата следующей оплаты 02.05.2021"
        ),
        RecyclerItem(
            itemName = "Газ",
            itemNumber = 23452452,
            isRedAlert = false,
            commentary = "Данные приняты, следующая подача 30.06.2021"
        ),
        RecyclerItem(
            itemName = "Интернет и ТВ",
            itemNumber = 24524556,
            isRedAlert = true,
            commentary = "Отключено за неуплату"
        ),
        RecyclerItem(
            itemName = "Отопление",
            itemNumber = 653546,
            isRedAlert = false,
            commentary = "Отправьте данные до 18.03.2021"
        )
    )
}