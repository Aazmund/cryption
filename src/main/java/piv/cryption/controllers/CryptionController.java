package piv.cryption.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import piv.cryption.models.CryptoDto;
import piv.cryption.services.CryptoManager;

@RestController
public class CryptionController {
    @Autowired
    CryptoManager cryptoManager;

    @GetMapping("/crypto")
    public CryptoDto crypto(@RequestParam(value = "action", required = false, defaultValue = "empty") String action,
                            @RequestParam(value = "string", required = false, defaultValue = "empty") String string,
                            @RequestParam(value = "cryptName", required = false, defaultValue = "0") String cryptName,
                            @RequestParam(value = "context", required = false, defaultValue = "empty") String context){
        CryptoDto cryptoDto = new CryptoDto(action, string, cryptName, context);
        cryptoManager.Encryption(cryptoDto);
        return cryptoDto;
    }

}
