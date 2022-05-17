package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class Playfair {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    public static String keyValidator(String key){

        //чистим ключ от пробелов и повторяющихся символов

        for (int i = 0; i < key.length(); i++){
            for (int j = i+1; j < key.length(); j++){
                if (i != j && key.charAt(i) == key.charAt(j)){
                    key = key.substring(0, j) + key.substring(j + 1);
                }
                if (key.charAt(i) == ' '){
                    key = key.substring(0, i) + key.substring(i + 1);
                }
            }
        }
        return key;
    }

    public static void crypt(String str, String key, String alphabet){
        String bufferKey = key;
        String bufferAlpha = alphabet;
        String result = "";
        char[][] matrix;
        int col = 0;
        int row = 0;

        if (alphabet.charAt(0) == 'a'){
            col = 5;
            row = 5;
            matrix = new char[col][row];
        }else{
            col = 8;
            row = 4;
            matrix = new char[col][row];
        }

        bufferKey = keyValidator(bufferKey);
        str = str.replaceAll("j", "i");
        str = str.replaceAll("ъ", "ь");

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

        str = str.replaceAll(" ", "");
        if (str.length() % 2 != 0){
            if (alphabet.charAt(0) == 'a'){
                str += 'x';
            }else{
                str += 'ь';
            }
        }

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

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

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

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

            } else { //если символы в разных строках столбцах

                int buffer = bufFirstJ;
                bufFirstJ = bufSecondJ;
                bufSecondJ = buffer;

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

            }
        }

//        for (int i = 0; i < 5; i++){
//            for (int j = 0; j < 5; j++){
//                System.out.print(matrix[i][j] + "  ");
//            }
//            System.out.println();
//        }

//        System.out.println(result);
        decrypt(result, key, alphabet);
    }

    public static void decrypt(String str, String key, String alphabet){
        String bufferKey = key;
        String bufferAlpha = alphabet;
        String result = "";
        char[][] matrix;
        int col = 0;
        int row = 0;

        if (alphabet.charAt(0) == 'a'){
            col = 5;
            row = 5;
            matrix = new char[col][row];
        }else{
            col = 8;
            row = 4;
            matrix = new char[col][row];
        }

        bufferKey = keyValidator(bufferKey);
        str = str.replaceAll("j", "i");

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

        str = str.replaceAll(" ", "");
        if (str.length() % 2 != 0){
            str += 'x';
        }

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

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

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

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

            } else { //если символы в разных строках столбцах

                int buffer = bufFirstJ;
                bufFirstJ = bufSecondJ;
                bufSecondJ = buffer;

                result += matrix[bufFirstI][bufFirstJ];
                result += matrix[bufSecondI][bufSecondJ];

            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        List<CryptoDto> dto = new ArrayList<>();
        dto.add(new CryptoDto("hide the gold in the tree stump", "playfair example"));
        dto.add(new CryptoDto("punks never die","commander"));
        dto.add(new CryptoDto("for example","table"));
        dto.add(new CryptoDto("idiocy often looks like intelligence","wheatson"));

        for (CryptoDto cryptoDto: dto) {
            crypt(cryptoDto);
        }

        List<CryptoDto> dtoRus = new ArrayList<>();
        dtoRus.add(new CryptoDto("боже храни криптографию", "ключ"));
        dtoRus.add(new CryptoDto("дарова ильюха как дела", "пример плейфера"));
        dtoRus.add(new CryptoDto("как же сложно придумать рандомные фразы для теста шифровки", "памагитя"));
        dtoRus.add(new CryptoDto("ночь улица фонарь аптека", "опрмифвзх"));

        for (CryptoDto cryptoDto: dtoRus) {
            crypt(cryptoDto);
        }
    }
}
