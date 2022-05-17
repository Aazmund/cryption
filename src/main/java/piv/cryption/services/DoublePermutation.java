package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

import java.util.Arrays;

@Service
public class DoublePermutation {
    public void encrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString().toLowerCase();
        String[] key = cryptoDto.getContext().split("@",2);
        String key1 = key[0];
        String key2 = key[1];
        StringBuilder result = new StringBuilder();
        int col = key1.length();
        int row = key2.length();
        char[][] array = new char[row][col];

        //заполняем матрицу
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                array[i][j] = str.charAt(0);
                str = str.substring(1);
            }
        }

        char[] key1Array = key1.toCharArray();
        Arrays.sort(key1Array);
        String sortedKey1 = String.copyValueOf(key1Array);

        char[] key2Array = key2.toCharArray();
        Arrays.sort(key2Array);
        String sortedKey2 = String.copyValueOf(key2Array);

        //переставляем стобцы
        for (int j = 0; j < col; j++) {
            int index = sortedKey1.indexOf(key1.charAt(j));
            for (int i = 0; i < row; i++) {
                result.append(array[i][index]);
            }
        }

        //переставляем строки
        for (int j = 0; j < col; j++){
            for (int i = 0; i < row; i++){
                array[i][j] = result.charAt(0);
                result = new StringBuilder(result.substring(1));
            }
        }

        for (int i = 0; i < row; i++){
            int index = sortedKey2.indexOf(key2.charAt(i));
            for (int j = 0; j < col; j++){
                result.append(array[index][j]);
            }
        }

//        System.out.println(result);
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto){
        String str = cryptoDto.getString().toLowerCase();
        String[] key = cryptoDto.getContext().split("@",2);
        String key1 = key[0];
        String key2 = key[1];
        StringBuilder result = new StringBuilder();
        int col = key1.length();
        int row = key2.length();
        char[][] array = new char[row][col];

        //заполняем матрицу
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                array[i][j] = str.charAt(0);
                str = str.substring(1);
            }
        }

        char[] key1Array = key1.toCharArray();
        Arrays.sort(key1Array);
        String sortedKey1 = String.copyValueOf(key1Array);

        char[] key2Array = key2.toCharArray();
        Arrays.sort(key2Array);
        String sortedKey2 = String.copyValueOf(key2Array);

        //переставляем строки
        for (int i = 0; i < row; i++){
            int index = key2.indexOf(sortedKey2.charAt(i));
            for (int j = 0; j < col; j++){
                result.append(array[index][j]);
            }
        }

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                array[i][j] = result.charAt(0);
                result = new StringBuilder(result.substring(1));
            }
        }

        //перемещаем столбцы
        for (int j = 0; j < col; j++){
            int index = key1.indexOf(sortedKey1.charAt(j));
            for (int i = 0; i < row; i++){
                result.append(array[i][index]);
            }
        }

        for (int j = 0; j < col; j++){
            for (int i = 0; i < row; i++){
                array[i][j] = result.charAt(0);
                result = new StringBuilder(result.substring(1));
            }
        }

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                result.append(array[i][j]);
            }
        }

        cryptoDto.setResult(result.toString());
    }
}
