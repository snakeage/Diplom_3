# Diplom_3: Автоматизация тестирования Stellar Burgers

## Описание проекта
Этот проект представляет собой набор автоматизированных тестов для веб-приложения **Stellar Burgers** [](https://stellarburgers.education-services.ru). Цель проекта — проверка функциональности UI и API, включая регистрацию, авторизацию, восстановление пароля и работу конструктора бургеров. Тесты выполняются в браузерах Google Chrome и Яндекс.Браузер с использованием Selenium WebDriver, а API-запросы отправляются через RestAssured. Отчёты о тестировании формируются с помощью Allure.

## Структура проекта
- **`src/main/java/ru/yandex/practicum/api`**: Классы для работы с API (например, `ApiClient.java` для запросов к эндпоинтам).
- **`src/main/java/ru/yandex/practicum/helpers`**: Вспомогательные утилиты (например, `DriverHelper.java` для настройки WebDriver).
- **`src/main/java/ru/yandex/practicum/pages`**: Классы Page Object для UI-тестирования (например, `MainPage.java`, `LoginPage.java`).
- **`src/test/java/ru/yandex/practicum/tests`**: Тестовые классы (например, `RegistrationTest.java`).
- **`src/test/resources`**: Конфигурационные файлы:
    - `browser.properties`: Настройка браузера (CHROME или YANDEX).
    - `yandexdriver`: Драйвер для Яндекс.Браузера.
- **`target/allure-results`**: Результаты тестов для Allure-отчёта.

## Используемые технологии
- **Java**: 11 (Amazon Corretto)
- **Maven**: 3.8.1 (управление зависимостями и сборка)
- **Selenium WebDriver**: 4.36.0 (UI-тестирование)
- **RestAssured**: 5.5.0 (API-тестирование)
- **JUnit**: 4.13.2 (фреймворк для тестирования)
- **Allure**: 2.24.0 (формирование отчётов)
- **Lombok**: 1.18.32 (упрощение кода)
- **Apache Commons Lang**: 3.12.0 (утилиты для работы со строками)
- **Hamcrest**: 2.2 (ассерты)

## Требования
- **JDK**: Amazon Corretto 11 или выше.
- **Maven**: Установлен и настроен.
- **Браузеры**:
    - Google Chrome (последняя версия).
    - Яндекс.Браузер (совместимый с ChromeDriver 4.36.0).
- **Драйверы**:
    - `chromedriver` для Chrome (автоматически загружается Selenium).
    - `yandexdriver` для Яндекс.Браузера (поместите в `src/test/resources/yandexdriver`).
- **Операционная система**: macOS, Windows или Linux.