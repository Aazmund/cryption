package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Rout {

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s", "");
        StringBuilder result = new StringBuilder();
        int length = (int) Math.sqrt(text.length());
        if ( length * length % text.length() != 0)
            length++;
        char[][] charText = new char[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (text.length() > 0) {
                    if (i % 2 == 1){
                        charText[i][j] = text.charAt(0);
                    }else{
                        charText[i][length - j - 1] = text.charAt(0);
                    }
                    text = text.substring(1);
                }
                else {
                    if (i % 2 == 1) {
                        charText[i][j] = '.';
                    } else {
                        charText[i][length - j - 1] = '.';
                    }
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result.append(charText[j][length - i - 1]);
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s", "");
        StringBuilder result = new StringBuilder();
        int length = (int) Math.sqrt(text.length());
        char[][] charText = new char[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                charText[j][length - i - 1] = text.charAt(0);
                text = text.substring(1);
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i % 2 == 1)
                    result.append(charText[i][j]);
                else
                    result.append(charText[i][length - j - 1]);
            }
        }
        cryptoDto.setResult(result.toString().replaceAll("[.]", ""));
    }
}
