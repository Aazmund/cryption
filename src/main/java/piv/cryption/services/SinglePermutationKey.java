package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.util.Arrays;

@Service
public class SinglePermutationKey {
    public void encrypt(CryptoDto cryptoDto) {
        /** расчет размерности матрицы **/
        String key = cryptoDto.getContext();
        String str = cryptoDto.getString();
        int row = (int) Math.ceil((double) str.length() / key.length());
        int column = key.length();
        char[][] array = new char[row][column];
        StringBuilder result = new StringBuilder();

        /** перевод строки в матрицу **/
        for (int j = 0; j < column; j++) {
            for (int i = 0; i < row; i++) {
                if (str.length() != 0) {
                    array[i][j] = str.charAt(0);
                    str = str.substring(1);
                } else {
                    array[i][j] = ' ';
                }
            }
        }

        /** перестановка столбцов **/
        char[] keyArray = key.toCharArray();
        Arrays.sort(keyArray);
        String sortedKey = String.copyValueOf(keyArray);
        for (int j = 0; j < column; j++) {
            int index = sortedKey.indexOf(key.charAt(j));
            for (int i = 0; i < row; i++) {
                result.append(array[i][index]);
            }
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto) {
        /** расчет размерности матрицы **/
        String key = cryptoDto.getContext();
        String str = cryptoDto.getString();
        int row = (int) Math.ceil((double) str.length() / key.length());
        int column = key.length();
        char[][] array = new char[row][column];
        StringBuilder result = new StringBuilder();

        /** перевод строки в матрицу **/
        for (int j = 0; j < column; j++) {
            for (int i = 0; i < row; i++) {
                if (str.length() != 0) {
                    array[i][j] = str.charAt(0);
                    str = str.substring(1);
                } else {
                    array[i][j] = ' ';
                }
            }
        }

        /** перестановка столбцов **/
        char[] keyArray = key.toCharArray();
        Arrays.sort(keyArray);
        String sortedKey = String.copyValueOf(keyArray);
        for (int j = 0; j < column; j++){
            int index = key.indexOf(sortedKey.charAt(j));
            for (int i = 0; i < row; i++){
                result.append(array[i][index]);
            }
        }
        cryptoDto.setResult(result.toString());
    }
}
