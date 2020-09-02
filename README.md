# Weather1
Погодное приложение, которое выводит погоду на ближайшие 14 дней по наименованию города (на английсокм) или по текущим координатам пользователя.
Данные берёт с сайта openweathermap.org

- В качестве презентационного патера использую MVVM c LiveData.
- Retrofit2 и RxJava2 используются для получения данных о погоде от api сайта.
- RxJava2 так-же используется для передачи и индикации данный о местоположении пользователя
- Dagger2 используется для внедрения зависимостей Context, WeatherRepository, Retrofit, Api в глобальном scope приложения,
 зависимостей WeatherAdapter и WeatherInteractor в scope WeatherDetailsActivity и зависимости Geolocation в scope MainActivity
- Picasso используется для отрисовки пиктограмм cоответствующей погоды дня в RecyclerView, пиктограммы так-же с сайта openweathermap.org

- При написании этой программы максимально старался придерживаться подхода Clean Architecture и принципов SOLID

