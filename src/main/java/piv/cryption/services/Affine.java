package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Affine {
    //private static final String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] key = cryptoDto.getContext().split("@",2);
        int keyA = Integer.parseInt(key[0]);
        int keyB = Integer.parseInt(key[1]);
        StringBuilder result = new StringBuilder();
        int index, indexOf;
        for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ' ')
                    result.append(" ");
                else {
                    indexOf = alphabet.indexOf(text.charAt(i));
                    index = (keyB+keyA*(indexOf))%alphabet.length();
                result.append(alphabet.charAt(index));
                }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString();
        String[] key = cryptoDto.getContext().split("@",2);
        int keyA = Integer.parseInt(key[0]);
        int keyB = Integer.parseInt(key[1]);
        StringBuilder result = new StringBuilder();
        int multi = 0;
        int index, indexOf;
        for (int i = 0; i < alphabet.length(); i++) {
            if ((keyA*i)%alphabet.length()==1) {
                multi = i;
            }
        }
        for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ' ')
                    result.append(" ");
                else {
                    indexOf = alphabet.indexOf(text.charAt(i));
                    index = ((indexOf-keyB)*multi)%alphabet.length();
                    if (index < 0)
                        index+=alphabet.length();
                    result.append(alphabet.charAt(index));
                }
        }
        cryptoDto.setResult(result.toString());
    }

}
