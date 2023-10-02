package br.com.brigadadoslobos.gerenciador;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API do Sistema Gerenciador Brigada dos Lobos",
        version = "1.0", description = "Cadastros"))
public class GerenciadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorApplication.class, args);
    }
    //link do Swagger http://localhost:8080/swagger-ui/index.html

    //link do banco de dados teste http://localhost:8080/h2-console
    //username: sa
    //password:
}
