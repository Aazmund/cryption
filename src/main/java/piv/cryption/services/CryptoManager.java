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
    private MagicSquare magicSquare;
    @Autowired
    private Trithemius trithemius;
    @Autowired
    private Vigenere vigenere;
    @Autowired
    private Gronsfeld gronsfeld;
    @Autowired
    private CaesarKeyWord caesarKeyWord;
    @Autowired
    private DoublePermutation doublePermutation;
    @Autowired
    private Hill hill;

    public void action(CryptoDto cryptoDto){
        switch (cryptoDto.getAction()){
            case "decrypt": decrypt(cryptoDto); break;
            case "encrypt": encrypt(cryptoDto); break;
            case "magicSquare": magicSquare.action(cryptoDto); break;
        }
    }

    private void decrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "caesar": caesar.decrypt(cryptoDto); break;
            case "affine": affine.decrypt(cryptoDto); break;
            case "singlePermutationKey": singlePermutationKey.decrypt(cryptoDto); break;
            case "trithemius": trithemius.decrypt(cryptoDto); break;
            case "vigenere": vigenere.decrypt(cryptoDto); break;
            case "gronsfeld": gronsfeld.decrypt(cryptoDto); break;
            case "caesarKeyWord": caesarKeyWord.decrypt(cryptoDto); break;
            case "hill": hill.decrypt(cryptoDto); break;
        }
    }

    private void encrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "caesar": caesar.encrypt(cryptoDto); break;
            case "affine": affine.encrypt(cryptoDto); break;
            case "singlePermutationKey": singlePermutationKey.encrypt(cryptoDto); break;
            case "trithemius": trithemius.encrypt(cryptoDto); break;
            case "vigenere": vigenere.encrypt(cryptoDto); break;
            case "gronsfeld": gronsfeld.encrypt(cryptoDto); break;
            case "caesarKeyWord": caesarKeyWord.encrypt(cryptoDto); break;
            case "hill": hill.encrypt(cryptoDto); break;
        }
    }

}
