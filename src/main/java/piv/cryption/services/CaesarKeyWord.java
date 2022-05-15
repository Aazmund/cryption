package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class CaesarKeyWord {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String generateAlphabet(int k, String keyWord, String alphabet)
    {
        char[] keyAlphabet;
        keyAlphabet = new char[alphabet.length()];
        for (int i = 0; i < keyWord.length(); i++) {
            keyAlphabet[k+i] = keyWord.charAt(i);
        }
        int count = k + keyWord.length();
        for (int j = 0; j < alphabet.length(); j++) {
            int indexOf = Arrays.toString(keyAlphabet).indexOf(alphabet.charAt(j));
            if (indexOf == -1) {
                keyAlphabet[count % alphabet.length()] = alphabet.charAt(j);
                count++;
            }
        }
        return String.valueOf(keyAlphabet);
    }

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase();
        String[] keyContext = cryptoDto.getContext().toLowerCase().split("@",2);
        StringBuilder result = new StringBuilder();
        String keyAlphabet;
        int indexOf;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], alphabet);
        for(int i = 0; i < text.length(); i++) {
            indexOf = alphabet.indexOf(text.charAt(i));
            result.append(keyAlphabet.charAt(indexOf));
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase();
        String[] keyContext = cryptoDto.getContext().toLowerCase().split("@",2);
        StringBuilder result = new StringBuilder();
        String keyAlphabet;
        int indexOf;
        String alphabet;
        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], alphabet);
        for(int i = 0; i < text.length(); i++) {
            indexOf = keyAlphabet.indexOf(text.charAt(i));
            result.append(alphabet.charAt(indexOf));
        }
        cryptoDto.setResult(result.toString());
    }
}
