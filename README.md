# Movie Review API

REST API для поиска фильмов, написания рецензий и управления избранным.

## Стек

- Java 21 + Spring Boot 3.5
- PostgreSQL + Spring Data JPA + Flyway
- Spring Security + JWT
- Redis (кэширование)
- Docker Compose
- Swagger/OpenAPI

## Запуск

### 1. Клонировать репозиторий
git clone https://github.com/sanzhidev/movie-review-api.git
cd movie-review-api

### 2. Запустить PostgreSQL и Redis
docker compose up -d

### 3. Запустить приложение
./mvnw spring-boot:run

### 4. Открыть Swagger
http://localhost:8080/swagger-ui/index.html

## Эндпоинты

### Auth
POST /api/auth/register — регистрация
POST /api/auth/login    — логин

### Movies
GET  /api/movies           — список фильмов
GET  /api/movies/{id}      — фильм по id
POST /api/movies/import    — импорт из TMDB

### Reviews
GET  /api/movies/{id}/reviews  — рецензии фильма
POST /api/movies/{id}/reviews  — добавить рецензию