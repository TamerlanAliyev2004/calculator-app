# Используем официальный образ с JDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем все файлы проекта в контейнер
COPY . .

# Устанавливаем Gradle
RUN apt-get update && apt-get install -y gradle

# Собираем проект
RUN gradle build

# Открываем порт для приложения
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "build/libs/calculator-app-0.0.1-SNAPSHOT.jar"]
