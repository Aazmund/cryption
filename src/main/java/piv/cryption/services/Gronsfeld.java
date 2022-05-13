package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Gronsfeld {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String generateKey(String text, String keyWord)
    {
        StringBuilder key = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < text.length(); i++){
            if (alphabetRus.indexOf(text.charAt(i)) != -1 && alphabetEng.indexOf(text.charAt(i)) != -1){
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
        int indexOf;
        int step;
        if (alphabetRus.indexOf(text.charAt(0)) != -1) {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetRus.indexOf(text.charAt(i)) != -1) {
                    indexOf = alphabetRus.indexOf(text.charAt(i));
                    step = Integer.parseInt(String.valueOf(key.charAt(i)));
                    result.append(alphabetRus.charAt((indexOf + step) % alphabetRus.length()));
                } else {
                    result.append(text.charAt(i));
                }
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetEng.indexOf(text.charAt(i)) != -1) {
                    indexOf = alphabetEng.indexOf(text.charAt(i));
                    step = Integer.parseInt(String.valueOf(key.charAt(i)));
                    result.append(alphabetEng.charAt((indexOf + step) % alphabetEng.length()));
                } else {
                    result.append(text.charAt(i));
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String keyWord = cryptoDto.getContext();
        String key = generateKey(text, keyWord);
        StringBuilder result = new StringBuilder();
        int indexOf;
        int step;
        if (alphabetRus.indexOf(text.charAt(0)) != -1) {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetRus.indexOf(text.charAt(i)) != -1) {
                    indexOf = alphabetRus.indexOf(text.charAt(i));
                    step = Integer.parseInt(String.valueOf(key.charAt(i)));
                    result.append(alphabetRus.charAt((indexOf - step + alphabetRus.length()) % alphabetRus.length()));
                } else {
                    result.append(text.charAt(i));
                }
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetEng.indexOf(text.charAt(i)) != -1) {
                    indexOf = alphabetEng.indexOf(text.charAt(i));
                    step = Integer.parseInt(String.valueOf(key.charAt(i)));
                    result.append(alphabetEng.charAt((indexOf - step + alphabetEng.length()) % alphabetEng.length()));
                } else {
                    result.append(text.charAt(i));
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }
}
