package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Caesar {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        if (alphabetRus.indexOf(text.charAt(0)) != -1){
            for(int i = 0; i < text.length(); i++) {
                if (alphabetRus.indexOf(text.charAt(i)) == -1)
                    result.append(text.charAt(i));
                else {
                    indexOf = alphabetRus.indexOf(text.charAt(i));
                    result.append(alphabetRus.charAt((indexOf + key) % alphabetRus.length()));
                }
            }
        } else {
            for(int i = 0; i < text.length(); i++) {
                if (alphabetEng.indexOf(text.charAt(i)) == -1)
                    result.append(text.charAt(i));
                else {
                    indexOf = alphabetEng.indexOf(text.charAt(i));
                    result.append(alphabetEng.charAt((indexOf + key) % alphabetEng.length()));
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        int indexOf;
        if (alphabetRus.indexOf(text.charAt(0)) != -1) {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetRus.indexOf(text.charAt(i)) == -1)
                    result.append(text.charAt(i));
                else {
                    indexOf = alphabetRus.indexOf(text.charAt(i));
                    result.append(alphabetRus.charAt((indexOf - key + alphabetRus.length()) % alphabetRus.length()));
                }
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                if (alphabetEng.indexOf(text.charAt(i)) == -1)
                    result.append(text.charAt(i));
                else {
                    indexOf = alphabetEng.indexOf(text.charAt(i));
                    result.append(alphabetEng.charAt((indexOf - key + alphabetEng.length()) % alphabetEng.length()));
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }

}
