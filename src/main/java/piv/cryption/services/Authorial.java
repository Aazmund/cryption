package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Authorial {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String generateKey(String text, String keyWord)
    {
        StringBuilder key = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < text.length(); i++){
            key.append(keyWord.charAt(counter));
            counter++;
            if (counter == keyWord.length()){
                counter = 0;
            }
        }
        return key.toString();
    }

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s", "");
        String keyWord = cryptoDto.getContext().toLowerCase();
        StringBuilder result = new StringBuilder();
        int length = (int) Math.sqrt(text.length());
        if ( length * length % text.length() != 0)
            length++;
        char[][] textArray = new char[length][length];
        int indexOf;
        int step;
        String alphabet;

        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        String key = generateKey(text, keyWord);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i * length + j < text.length()) {
                    if (alphabet.contains(String.valueOf(text.charAt(i * length + j)))) {
                        indexOf = alphabet.indexOf(text.charAt(i * length + j));
                        step = Integer.parseInt(String.valueOf(key.charAt(i * length + j)));
                        textArray[i][j] = alphabet.charAt((indexOf + step) % alphabet.length());
                    } else {
                        textArray[i][j] = text.charAt(i * length + j);
                    }
                } else {
                    textArray[i][j] = '.';
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result.append(textArray[j][length - i - 1]);
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s", "");
        String keyWord = cryptoDto.getContext().toLowerCase();
        StringBuilder result = new StringBuilder();
        int length = (int) Math.sqrt(text.length());
        if ( length * length % text.length() != 0)
            length++;
        char[][] textArray = new char[length][length];
        int indexOf;
        int step;
        String alphabet;

        if (alphabetRus.contains(String.valueOf(text.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }
        String key = generateKey(text, keyWord);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i * length + j < text.length()) {
                    textArray[j][length - i - 1] = text.charAt(i * length + j);
                } else
                    textArray[i][j] = '.';
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (alphabet.contains(String.valueOf(textArray[i][j]))) {
                    indexOf = alphabet.indexOf(textArray[i][j]);
                    step = Integer.parseInt(String.valueOf(key.charAt(i * length  + j)));
                    result.append(alphabet.charAt((indexOf - step + alphabet.length()) % alphabet.length()));
                } else {
                    result.append(text.charAt(i));
                }
            }
        }
        cryptoDto.setResult(result.toString().replaceAll("[.]", ""));
    }

}
