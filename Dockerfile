# 1. 자바 실행 환경(JDK) 가져오기
FROM amazoncorretto:17-alpine

# 2. 빌드된 Jar 파일을 컨테이너 내부로 복사
# build/libs/ 폴더 안에 생성된 .jar 파일을 app.jar라는 이름으로 복사함
COPY build/libs/*.jar app.jar

# 3. 서버 실행 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]