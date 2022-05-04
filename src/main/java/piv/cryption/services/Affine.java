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
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (text.charAt(i) == ' ') {
                    result.append(" ");
                    break;
                }
                else if (text.charAt(i) == alphabet.charAt(j)){
                    int index = (keyB+keyA*(j))%alphabet.length();
                result.append(alphabet.charAt(index));
                break;
                }
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
        for (int i = 0; i < alphabet.length(); i++) {
            if ((keyA*i)%alphabet.length()==1) {
                multi = i;
            }
        }
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (text.charAt(i) == ' ') {
                    result.append(" ");
                    break;
                }
                else if (text.charAt(i) == alphabet.charAt(j)){
                    int index = ((j-keyB)*multi)%alphabet.length();
                    if (index < 0)
                        index+=alphabet.length();
                    result.append(alphabet.charAt(index));
                    break;
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }

}
