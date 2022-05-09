package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Trithemius {
    private static final String alphabet =
            "абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            ", ";

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] key = cryptoDto.getContext().split("@",3);
        int keyA = Integer.parseInt(key[0]);
        int keyB = Integer.parseInt(key[1]);
        int keyC = Integer.parseInt(key[2]);
        StringBuilder result = new StringBuilder();
        int indexOf;
        int k;
        for (int i = 0; i < text.length(); i++) {
            indexOf = alphabet.indexOf(text.charAt(i));
            k = keyA*(i*i) + keyB*i + keyC;
            result.append(alphabet.charAt((indexOf + k) % alphabet.length()));
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] key = cryptoDto.getContext().split("@",3);
        int keyA = Integer.parseInt(key[0]);
        int keyB = Integer.parseInt(key[1]);
        int keyC = Integer.parseInt(key[2]);
        StringBuilder result = new StringBuilder();
        int indexOf;
        int k;
        int l;
        for (int i = 0; i < text.length(); i++) {
            indexOf = alphabet.indexOf(text.charAt(i));
            k = keyA*(i*i) + keyB*i + keyC;
            l = indexOf - k;
            while (l<0)
                l += alphabet.length();
            l = l % alphabet.length();
            result.append(alphabet.charAt(l));
        }
        cryptoDto.setResult(result.toString());
    }
}
