package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Vigenere {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String generateKey(String text, String keyWord, String alphabet)
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
        String text = cryptoDto.getString().toLowerCase();
        String keyWord = cryptoDto.getContext().toLowerCase();
        StringBuilder result = new StringBuilder();
        int index;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        String key = generateKey(text, keyWord, alphabet);
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
        String text = cryptoDto.getString().toLowerCase();
        String keyWord = cryptoDto.getContext().toLowerCase();
        StringBuilder result = new StringBuilder();
        int index;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        String key = generateKey(text, keyWord, alphabet);
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
