package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Caesar {
    private static final String capitalLetterRus = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String smallLetterRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String capitalLetterEng = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String smallLetterEng = "abcdefghijklmnopqrstuvwxyz";

    public void encrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        if(smallLetterRus.indexOf(cryptoDto.getString().charAt(0)) != -1){
            for(int i = 0; i < str.length(); i++){
                if(smallLetterRus.indexOf(str.charAt(i)) == -1){
                    result.append(str.charAt(i));
                }else{
                    if(smallLetterRus.indexOf(str.charAt(i)) + key < smallLetterRus.length()){
                        result.append(smallLetterRus.charAt(smallLetterRus.indexOf(str.charAt(i)) + key));
                    }else{
                        result.append(smallLetterRus.charAt(smallLetterRus.indexOf(str.charAt(i)) + key - smallLetterRus.length()));
                    }
                }
            }

        }else{
            for(int i = 0; i < str.length(); i++){
                if(smallLetterEng.indexOf(str.charAt(i)) == -1){
                    result.append(str.charAt(i));
                }else{
                    if(smallLetterEng.indexOf(str.charAt(i)) + key < smallLetterEng.length()){
                        result.append(smallLetterEng.charAt(smallLetterEng.indexOf(str.charAt(i)) + key));
                    }else{
                        result.append(smallLetterEng.charAt(smallLetterEng.indexOf(str.charAt(i)) + key - smallLetterEng.length()));
                    }
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString();
        int key = Integer.parseInt(cryptoDto.getContext());
        StringBuilder result = new StringBuilder();
        if(smallLetterRus.indexOf(cryptoDto.getString().charAt(0)) != -1){
            for(int i = 0; i < str.length(); i++){
                if(smallLetterRus.indexOf(str.charAt(i)) == -1){
                    result.append(str.charAt(i));
                }else{
                    if(smallLetterRus.indexOf(str.charAt(i)) - key >= 0){
                        result.append(smallLetterRus.charAt(smallLetterRus.indexOf(str.charAt(i)) - key));
                    }else{
                        result.append(smallLetterRus.charAt( smallLetterRus.length() + (smallLetterRus.indexOf(str.charAt(i)) - key)));
                    }
                }
            }
        }else{
            for(int i = 0; i < str.length(); i++){
                if(smallLetterEng.indexOf(str.charAt(i)) == -1){
                    result.append(str.charAt(i));
                }else{
                    if(smallLetterEng.indexOf(str.charAt(i)) - key >= 0){
                        result.append(smallLetterEng.charAt(smallLetterEng.indexOf(str.charAt(i)) - key));
                    }else{
                        result.append(smallLetterEng.charAt( smallLetterEng.length() + (smallLetterEng.indexOf(str.charAt(i)) - key)));
                    }
                }
            }

        }
        cryptoDto.setResult(result.toString());
    }

}
