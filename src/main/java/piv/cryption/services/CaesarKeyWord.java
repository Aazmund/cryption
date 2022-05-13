package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class CaesarKeyWord {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String generateAlphabet(int k, String keyWord, String language)
    {
        char[] keyAlphabet;
        if (language.equals("Rus")){
            keyAlphabet = new char[alphabetRus.length()];
            for (int i = 0; i < keyWord.length(); i++) {
                keyAlphabet[k+i] = keyWord.charAt(i);
            }
            int count = k + keyWord.length();
            for (int j = 0; j < alphabetRus.length(); j++) {
                int indexOf = Arrays.toString(keyAlphabet).indexOf(alphabetRus.charAt(j));
                if (indexOf == -1) {
                    keyAlphabet[count % alphabetRus.length()] = alphabetRus.charAt(j);
                    count++;
                }
            }
        } else{
            keyAlphabet = new char[alphabetEng.length()];
            for (int i = 0; i < keyWord.length(); i++) {
                keyAlphabet[k+i] = keyWord.charAt(i);
            }
            int count = k + keyWord.length();
            for (int j = 0; j < alphabetEng.length(); j++) {
                int indexOf = Arrays.toString(keyAlphabet).indexOf(alphabetEng.charAt(j));
                if (indexOf == -1) {
                    keyAlphabet[count % alphabetEng.length()] = alphabetEng.charAt(j);
                    count++;
                }
            }
        }
        return String.valueOf(keyAlphabet);
    }

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] keyContext = cryptoDto.getContext().split("@",2);
        StringBuilder result = new StringBuilder();
        String keyAlphabet;
        int indexOf;
        if (alphabetRus.indexOf(text.charAt(0)) != -1){
            keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], "Rus");
            for(int i = 0; i < text.length(); i++) {
                indexOf = alphabetRus.indexOf(text.charAt(i));
                result.append(keyAlphabet.charAt(indexOf));
            }
        } else{
            keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], "Eng");
            for(int i = 0; i < text.length(); i++) {
                indexOf = alphabetEng.indexOf(text.charAt(i));
                result.append(keyAlphabet.charAt(indexOf));
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] keyContext = cryptoDto.getContext().split("@",2);
        StringBuilder result = new StringBuilder();
        String keyAlphabet;
        int indexOf;
        if (alphabetRus.indexOf(text.charAt(0)) != -1){
            keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], "Rus");
            for(int i = 0; i < text.length(); i++) {
                indexOf = keyAlphabet.indexOf(text.charAt(i));
                result.append(alphabetRus.charAt(indexOf));
            }
        } else{
            keyAlphabet = generateAlphabet(Integer.parseInt(keyContext[0]), keyContext[1], "Eng");
            for(int i = 0; i < text.length(); i++) {
                indexOf = keyAlphabet.indexOf(text.charAt(i));
                result.append(alphabetEng.charAt(indexOf));
            }
        }
        cryptoDto.setResult(result.toString());
    }
}
