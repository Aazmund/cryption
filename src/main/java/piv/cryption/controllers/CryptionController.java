package piv.cryption.controllers;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import piv.cryption.models.CryptoDto;
import piv.cryption.services.CryptoManager;

@RestController
public class CryptionController {

    @Autowired
    private CryptoManager cryptoManager;

    @PostMapping("/crypto")
    public ResponseEntity<JSONObject> crypto(@RequestBody CryptoDto cryptoDto){
        try{
            cryptoManager.action(cryptoDto);
            JSONObject response = new JSONObject();
            response.put("result",cryptoDto.getResult());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
