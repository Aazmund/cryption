package piv.cryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import piv.cryption.models.CryptoDto;

@SpringBootApplication
public class CryptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptionApplication.class, args);
    }

}
