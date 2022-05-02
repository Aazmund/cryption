package piv.cryption.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class CryptoManager {

    @Autowired
    private Caesar caesar;
    @Autowired
    private SinglePermutationKey singlePermutationKey;
    @Autowired
    private Affine affine;
    @Autowired
    private CaesarKeyWord caesarKeyWord;
    @Autowired
    private DoublePermutation doublePermutation;
    @Autowired
    private Trithemius trithemius;
    @Autowired
    private Vigenere vigenere;

    public void Action(CryptoDto cryptoDto){
        switch (cryptoDto.getAction()){
            case "Decrypt": Decrypt(cryptoDto); break;
            case "Encrypt": Encrypt(cryptoDto); break;
        }
    }
    private void Decrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "Caesar": caesar.decrypt(cryptoDto); break;
        }
    }

    private void Encrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "Caesar": caesar.encrypt(cryptoDto); break;
        }
    }

}
