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

    public void action(CryptoDto cryptoDto){
        switch (cryptoDto.getAction()){
            case "Decrypt": decrypt(cryptoDto); break;
            case "Encrypt": encrypt(cryptoDto); break;
            case "MagicSquare": magicSquare.action(cryptoDto); break;
        }
    }
    private void decrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "Caesar": caesar.decrypt(cryptoDto); break;
            case "Affine": affine.decrypt(cryptoDto); break;
            case "SinglePermutationKey": singlePermutationKey.decrypt(cryptoDto); break;
            case "Trithemius": trithemius.decrypt(cryptoDto); break;
            case "Vigenere": vigenere.decrypt(cryptoDto); break;
            case "Gronsfeld": gronsfeld.decrypt(cryptoDto); break;
        }
    }

    private void encrypt(CryptoDto cryptoDto){
        switch (cryptoDto.getCryptName()){
            case "Caesar": caesar.encrypt(cryptoDto); break;
            case "Affine": affine.encrypt(cryptoDto); break;
            case "SinglePermutationKey": singlePermutationKey.encrypt(cryptoDto); break;
            case "Trithemius": trithemius.encrypt(cryptoDto); break;
            case "Vigenere": vigenere.encrypt(cryptoDto); break;
            case "Gronsfeld": gronsfeld.encrypt(cryptoDto); break;
        }
    }

}
