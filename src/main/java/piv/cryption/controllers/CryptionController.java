package piv.cryption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import piv.cryption.models.CryptoDto;
import piv.cryption.services.CryptoManager;

@RestController
public class CryptionController {

    @Autowired
    private CryptoManager cryptoManager;

    //http://localhost:8080/crypto?action=empty&string=empty&cryptName=0&context=empty
    @GetMapping("/crypto")

    @ResponseBody
    public CryptoDto crypto(CryptoDto cryptoDto){
        cryptoManager.action(cryptoDto);
        return cryptoDto;
    }

}
