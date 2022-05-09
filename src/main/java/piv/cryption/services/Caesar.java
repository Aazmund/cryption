package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Caesar {
    private static final String alphabet =
            "абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    ", ";

    public void encrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        for(int i = 0; i < str.length(); i++) {
            indexOf = alphabet.indexOf(str.charAt(i));
            result.append(alphabet.charAt(indexOf + key));
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        for(int i = 0; i < str.length(); i++){
            indexOf = alphabet.indexOf(str.charAt(i));
            if(indexOf - key >= 0){
                result.append(alphabet.charAt(indexOf - key));
            }else{
                result.append(alphabet.charAt(indexOf - key) + alphabet.length());
            }
        }
        cryptoDto.setResult(result.toString());
    }

}
