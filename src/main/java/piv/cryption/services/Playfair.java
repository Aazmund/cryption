package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Playfair {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    private String keyValidator(String key){
        //чистим ключ от повторяющихся символов
        for (int i = 0; i < key.length(); i++){
            for (int j = i+1; j < key.length(); j++){
                if (i != j && key.charAt(i) == key.charAt(j)){
                    key = key.substring(0, j) + key.substring(j + 1);
                }
            }
        }
        return key;
    }

    public void encrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String bufferKey = cryptoDto.getContext().toLowerCase().replaceAll("\\s" , "");
        String alphabet;
        String bufferAlpha;
        StringBuilder result = new StringBuilder();
        char[][] matrix;
        int col;
        int row;
        if (alphabetRus.contains(String.valueOf(str.charAt(0)))){
            alphabet = alphabetRus;
            col = 8;
            row = 4;
            str = str.replaceAll("ъ", "ь");
            if (str.length() % 2 != 0)
                str += 'ь';
        }
        else{
            alphabet = alphabetEng;
            col = 5;
            row = 5;
            str = str.replaceAll("j", "i");
            if (str.length() % 2 != 0)
                str += 'x';
        }
        matrix = new char[col][row];
        bufferAlpha = alphabet;
        bufferKey = keyValidator(bufferKey);

        //записываем ключ в матрицу и удаляем из алфавита этот символ

        for (int i = 0; i < col; i++){
            for (int j = 0; j < row; j++){
                matrix[i][j] = '0';
                if(bufferKey.length() != 0){
                    matrix[i][j] = bufferKey.charAt(0);
                    bufferAlpha = bufferAlpha.replaceAll(String.valueOf(bufferKey.charAt(0)), "");
                    bufferKey = bufferKey.substring(1);
                }
            }
        }

        //дополянем матрицу алфавитом

        for (int i = 0; i < col; i++){
            for (int j = 0; j < row; j++){
                if(matrix[i][j] == '0'){
                    matrix[i][j] = bufferAlpha.charAt(0);
                    bufferAlpha = bufferAlpha.substring(1);
                }
            }
        }

        //разбиваем строку на биграмы и шифруем каждую
        for (int bigram = 0; bigram < str.length() - 1; bigram += 2){
            int bufFirstI = -1, bufFirstJ = -1, bufSecondI = -1, bufSecondJ = -1;

            for (int i = 0; i < col; i++){
                for(int j = 0; j < row; j++){
                    if(matrix[i][j] == str.charAt(bigram)){
                        bufFirstI = i;
                        bufFirstJ = j;
                    }
                    if(matrix[i][j] == str.charAt(bigram + 1)){
                        bufSecondI = i;
                        bufSecondJ = j;
                    }
                }
            }

            if (bufFirstJ == bufSecondJ){ // если символы в одном столбце
                if (bufFirstI < col - 1){
                    bufFirstI++;
                }else{
                    bufFirstI = 0;
                }

                if (bufSecondI < col - 1){
                    bufSecondI++;
                }else{
                    bufSecondI = 0;
                }
            } else if (bufFirstI == bufSecondI){ //если символы в отдной строке
                if (bufFirstJ < row - 1){
                    bufFirstJ++;
                }else{
                    bufFirstJ = 0;
                }

                if (bufSecondJ < row - 1){
                    bufSecondJ++;
                }else{
                    bufSecondJ = 0;
                }

            } else { //если символы в разных строках столбцах

                int buffer = bufFirstJ;
                bufFirstJ = bufSecondJ;
                bufSecondJ = buffer;

            }
            result.append(matrix[bufFirstI][bufFirstJ]);
            result.append(matrix[bufSecondI][bufSecondJ]);
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String bufferKey = cryptoDto.getContext().toLowerCase().replaceAll("\\s" , "");
        String alphabet;
        String bufferAlpha;
        StringBuilder result = new StringBuilder();
        char[][] matrix;
        int col;
        int row;
        if (alphabetRus.contains(String.valueOf(str.charAt(0)))){
            alphabet = alphabetRus;
            col = 8;
            row = 4;
        }
        else{
            alphabet = alphabetEng;
            col = 5;
            row = 5;
        }
        matrix = new char[col][row];
        bufferAlpha = alphabet;
        bufferKey = keyValidator(bufferKey);

        //записываем ключ в матрицу и удаляем из алфавита этот символ

        for (int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                matrix[i][j] = '0';
                if(bufferKey.length() != 0){
                    matrix[i][j] = bufferKey.charAt(0);
                    bufferAlpha = bufferAlpha.replaceAll(String.valueOf(bufferKey.charAt(0)), "");
                    bufferKey = bufferKey.substring(1);
                }
            }
        }

        //дополянем матрицу алфавитом
        for (int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                if(matrix[i][j] == '0'){
                    matrix[i][j] = bufferAlpha.charAt(0);
                    bufferAlpha = bufferAlpha.substring(1);
                }
            }
        }

        //разбиваем строку на биграмы и шифруем каждую
        for (int bigram = 0; bigram < str.length() - 1; bigram += 2){
            int bufFirstI = -1, bufFirstJ = -1, bufSecondI = -1, bufSecondJ = -1;

            for (int i = 0; i < col; i++){
                for(int j = 0; j < row; j++){
                    if(matrix[i][j] == str.charAt(bigram)){
                        bufFirstI = i;
                        bufFirstJ = j;
                    }
                    if(matrix[i][j] == str.charAt(bigram + 1)){
                        bufSecondI = i;
                        bufSecondJ = j;
                    }
                }
            }

            if (bufFirstJ == bufSecondJ){ // если символы в одном столбце
                if (bufFirstI - 1 >= 0){
                    bufFirstI--;
                }else{
                    bufFirstI = col - 1;
                }

                if (bufSecondI -1 >= 0){
                    bufSecondI--;
                }else{
                    bufSecondI = col - 1;
                }

            } else if (bufFirstI == bufSecondI){ //если символы в одной строке
                if (bufFirstJ - 1 >= 0){
                    bufFirstJ--;
                }else{
                    bufFirstJ = row - 1;
                }

                if (bufSecondJ - 1 >= 0){
                    bufSecondJ--;
                }else{
                    bufSecondJ = row - 1;
                }
            } else { //если символы в разных строках столбцах

                int buffer = bufFirstJ;
                bufFirstJ = bufSecondJ;
                bufSecondJ = buffer;
            }

            result.append(matrix[bufFirstI][bufFirstJ]);
            result.append(matrix[bufSecondI][bufSecondJ]);
        }

        cryptoDto.setResult(result.toString());
    }
}
