package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.util.Arrays;

@Service
public class Richelieu {

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String[] stringsKey = cryptoDto.getContext().toLowerCase().split("\\s");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < stringsKey.length; i++) {
            char[] keyArray = stringsKey[i].toCharArray();
            Arrays.sort(keyArray);
            String keySorted = String.copyValueOf(keyArray);
            for (int j = 0; j < keySorted.length(); j++) {
                int indexOf = keySorted.indexOf(stringsKey[i].charAt(j));
                if (i * keySorted.length() + indexOf < text.length())
                    result.append(text.charAt(i * keySorted.length() + indexOf));
            }
            result.append(" ");
        }
        cryptoDto.setResult(result.toString());
    }
    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String[] stringsKey = cryptoDto.getContext().toLowerCase().split("\\s");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stringsKey.length; i++) {
            char[] keyArray = stringsKey[i].toCharArray();
            Arrays.sort(keyArray);
            String keySorted = String.copyValueOf(keyArray);
            for (int j = 0; j < keySorted.length(); j++) {
                int indexOf = stringsKey[i].indexOf(keySorted.charAt(j));
                if (i * keySorted.length() + indexOf < text.length())
                    result.append(text.charAt(i * keySorted.length() + indexOf));
            }
        }
        cryptoDto.setResult(result.toString());
    }
}
