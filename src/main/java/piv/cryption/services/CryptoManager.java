package piv.cryption.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class CryptoManager {

    @Autowired
    Caesar caesar;

    public void Encryption(CryptoDto cryptoDto){
        cryptoDto.setEncryptString("hihi");
    }

}
