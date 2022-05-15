package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Caesar {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        for(int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) == -1)
                result.append(text.charAt(i));
            else {
                indexOf = alphabet.indexOf(text.charAt(i));
                result.append(alphabet.charAt((indexOf + key) % alphabet.length()));
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        for (int i = 0; i < text.length(); i++) {
            if (alphabet.indexOf(text.charAt(i)) == -1)
                result.append(text.charAt(i));
            else {
                indexOf = alphabet.indexOf(text.charAt(i));
                result.append(alphabet.charAt((indexOf - key + alphabet.length()) % alphabet.length()));
            }
        }
        cryptoDto.setResult(result.toString());
    }

}
