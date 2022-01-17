# Приложение Cars
[![Build Status](https://app.travis-ci.com/kva-devops/job4j_cars.svg?branch=master)](https://app.travis-ci.com/kva-devops/job4j_cars)
[![codecov](https://codecov.io/gh/kva-devops/job4j_cars/branch/master/graph/badge.svg?token=YBZ9UHKVSB)](https://codecov.io/gh/kva-devops/job4j_cars)

## О проекте.
#### Описание
Веб приложение для размещения объявлений о продаже автомобилей.
Позволяет просматривать и добавлять объявления.

#### Технологии
>JDK14, Maven, PostgreSQL, Java Servlet, JSP, JSTL, Hibernate, Junit

## Сборка.
0. Скачать файлы репозитория
1. Создать базу данных в соответствии с настройками в файле *src/main/resources/hibernate.cfg.xml*
1. Произвести сборку проекта: `mvn clean install`
2. Скопировать полученный файл "job4j_cars-1.0.war" из папки target в папку вашего сервера
3. Приложение будет доступно по адресу: http://localhost:8080

## Как пользоваться.
На главной странице приложения доступен список всех объявлений.
![main](images/Selection_115.png)

Чтобы иметь возможность размещать объявления, необходимо авторизоваться
в системе, либо зарегистрироваться.
![auth](images/Selection_099.png) 
![reg](images/Selection_100.png)

После авторизации происходит перенаправление на главную страницу, из которой
становится доступна ссылка на личный кабинет.
![mainAuth](images/Selection_110.png)
![cabinet](images/Selection_101.png)

Для добавления объявления нужно перейти по ссылке "Добавить объявление" и
заполнить все необходимые поля.
![addAds](images/Selection_111.png)

Фото загружается отдельно после создания объявления.
![upPhoto1](images/Selection_112.png)
![upPhoto2](images/Selection_105.png)

После добавления объявления оно становится доступным для редактирования и удаления в личном кабинете 
и на главной странице. 
![afterAll1](images/Selection_113.png)
![afterAll2](images/Selection_114.png)

## Контакты.
Кутявин Владимир

skype: tribuna87

email: tribuna87@mail.ru