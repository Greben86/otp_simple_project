# сборка контейнера
docker build -t otp_simple_project .
# запуск контейнера
docker run -d -p 8080:8080 otp_simple_project