package piv.cryption.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DoublePermutation {
    private static final String smallLetterEng = "abcdefghijklmnopqrstuvwxyz";
    private static final String smallLetterRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void crypt(String str, String key1, String key2){
        String result = "";

        int col = key1.length();
        int row = key2.length();

        //заполняем матрицу

        char[][] array = new char[row][col];
        char[][] bufferArray = new char[row][col];

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
                result += array[i][index];
            }
        }

        //переставляем строки

        for (int j = 0; j < col; j++){
            for (int i = 0; i < row; i++){
                array[i][j] = result.charAt(0);
                result = result.substring(1);
            }
        }

        for (int i = 0; i < row; i++){
            int index = sortedKey2.indexOf(key2.charAt(i));
            for (int j = 0; j < col; j++){
                result += array[index][j];
            }
        }

//        System.out.println(result);
        decrypt(result, key1, key2);
    }

    public static void decrypt(String str, String key1, String key2){
        String result = "";

        int col = key1.length();
        int row = key2.length();

        //заполняем матрицу

        char[][] array = new char[row][col];

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
                result += array[index][j];
            }
        }

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                array[i][j] = result.charAt(0);
                result = result.substring(1);
            }
        }

        //перемещаем столбцы

        for (int j = 0; j < col; j++){
            int index = key1.indexOf(sortedKey1.charAt(j));
            for (int i = 0; i < row; i++){
                result += array[i][index];
            }
        }

        for (int j = 0; j < col; j++){
            for (int i = 0; i < row; i++){
                array[i][j] = result.charAt(0);
                result = result.substring(1);
            }
        }

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                result += array[i][j];
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {

        crypt("hide the gold in the tree stumps", "qwertyui", "azsx");
        crypt("punks never die punk never die", "kmjnf", "asdghj");

        crypt("боже храни криптографиюю", "крот", "продге");
        crypt("как же сложно придумать рандомные фразы для теста шифров", "памгитя", "опрмифвз");
    }
}
