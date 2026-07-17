# AVALIAÇÃO COMPASS

> Aplicação de gerenciamento de votos de uma assembleia de associados.

[![Java Version][java-image]][java-url]
[![Spring Version][spring-image]][spring-url]

## Importação dos Endpoints

Swagger:
http://localhost:8080/swagger-ui.html

OpenAPI:
http://localhost:8080/v3/api-docs


## Configuração de Desenvolvimento

### Build

Para fazer o build da aplicação deve ser executado o seguinte comando.

```sh
./gradlew clean build --refresh-dependencies
```

Para executar a aplicação:

```sh
./gradlew bootRun
```

Para rodar os testes da aplicação:
```sh
./gradlew test
```

[java-image]: https://img.shields.io/badge/Java-v21-yellow
[spring-image]: https://img.shields.io/badge/Spring--Boot-v4.1.0-green
[java-url]: https://www.oracle.com/java/technologies/javase/21u-relnotes.html
[spring-url]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/4.1.0