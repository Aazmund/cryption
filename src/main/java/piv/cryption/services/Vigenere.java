package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Vigenere {
    private static final String alphabet =
            "абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String generateKey(String text, String keyWord)
    {
        StringBuilder key = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < text.length(); i++){
            if (alphabet.indexOf(text.charAt(i)) != -1){
                key.append(keyWord.charAt(counter));
                counter++;
                if (counter == keyWord.length()){
                    counter = 0;
                }
            }else{
                key.append(text.charAt(i));
            }
        }
        return key.toString();
    }

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String keyWord = cryptoDto.getContext();
        String key = generateKey(text, keyWord);
        StringBuilder result = new StringBuilder();
        int index;
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) == -1)
                result.append(text.charAt(i));
            else {
                index = (alphabet.indexOf(text.charAt(i)) +
                        alphabet.indexOf(key.charAt(i))) % alphabet.length();
                result.append(alphabet.charAt(index));
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String keyWord = cryptoDto.getContext();
        String key = generateKey(text, keyWord);
        StringBuilder result = new StringBuilder();
        int index;
        for (int i = 0; i < text.length() && i < key.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) == -1)
                result.append(text.charAt(i));
            else {
                index = (alphabet.indexOf(text.charAt(i)) -
                        alphabet.indexOf(key.charAt(i)) + alphabet.length()) % alphabet.length();
                result.append(alphabet.charAt(index));
            }
        }
        cryptoDto.setResult(result.toString());
    }

}
