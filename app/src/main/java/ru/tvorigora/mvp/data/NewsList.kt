package ru.tvorigora.mvp.data

import android.content.res.Resources

fun newsList():List<News>{
    return listOf(
        News(
            id = 1,
            caption = "Приглашаем на мастер-классы «Твори-Мастери»",
            url = "https://tvorigora.ru/приглашаем-на-мастер-классы-твори-мас/",
            imageUrl = "https://tvorigora.ru/wp-content/uploads/2024/11/YNFiZHyLMsA-768x768.jpg"
        ),
        News(
            id = 2,
            caption = "Новые встречи проекта «Арт-вояж: путешествие за вдохновением»",
            url = "https://tvorigora.ru/новые-встречи-проекта-арт-вояж-путеш/",
            imageUrl = "https://tvorigora.ru/wp-content/uploads/2024/11/EB_SpKIeGNs-768x768.jpg"
        ),
        News(
            id = 3,
            caption = "«Школа Хитрука для школьников» в гостях у «Ну и ну!»",
            url = "https://tvorigora.ru/школа-хитрука-для-школьников-в-гост/",
            imageUrl = "https://tvorigora.ru/wp-content/uploads/2024/11/EWkSfLwb3Oo-768x578.jpg"
        ),
        News(
            id = 4,
            caption = "5 дней мультканикул пролетели!",
            url = "https://tvorigora.ru/5-дней-мультканикул-пролетели/",
            imageUrl = "https://tvorigora.ru/wp-content/uploads/2024/11/LkAqnS2eGBI-768x578.jpg"
        ),
        News(
            id = 5,
            caption = "Акция «Вам открытка» к Международному дню анимации",
            url = "https://tvorigora.ru/%d0%b0%d0%ba%d1%86%d0%b8%d1%8f-%d0%b2%d0%b0%d0%bc-%d0%be%d1%82%d0%ba%d1%80%d1%8b%d1%82%d0%ba%d0%b0-%d0%ba-%d0%bc%d0%b5%d0%b6%d0%b4%d1%83%d0%bd%d0%b0%d1%80%d0%be%d0%b4%d0%bd%d0%be%d0%bc/",
            imageUrl = "https://tvorigora.ru/wp-content/uploads/2024/10/ln8K5chiksE-768x576.jpg"
        )
    )
}