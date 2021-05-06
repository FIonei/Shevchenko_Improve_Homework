package com.example.lesson_4

class ItemsRepository {
    private val detailInfoItems = listOf(
        InfoItem("Квитанции", "- 40 080,55 Р", R.drawable.ic_bill),
        InfoItem("Счетчики", "Подайте показания", R.drawable.ic_counter),
        InfoItem("Рассрочка", "Сл. платеж 25.02.2018", R.drawable.ic_installment),
        InfoItem("Страхование", "Полис до 12.01.2019", R.drawable.ic_insurance),
        InfoItem("Интернет и ТВ", "Баланс 350 Р", R.drawable.ic_tv),
        InfoItem("Домофон", "Подключен", R.drawable.ic_homephone),
        InfoItem("Охрана", "Нет", R.drawable.ic_guard)
    )
    private val baseInfoItems = listOf(
        InfoItem("Контакты УК и служб", image = R.drawable.ic_uk_contacts),
        InfoItem("Мои заявки", image = R.drawable.ic_request),
        InfoItem("Памятка жителя А101", image = R.drawable.ic_about)
    )
    fun getDetail() = detailInfoItems
    fun getBase() = baseInfoItems
}