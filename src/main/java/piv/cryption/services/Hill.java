package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Hill {
    private static final String alphabetRus = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    public void encrypt(CryptoDto cryptoDto) {
        String msg = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String key = cryptoDto.getContext().toLowerCase().replaceAll("\\s" , "");
        StringBuilder result = new StringBuilder();
        String alphabet;
        if (alphabetRus.contains(String.valueOf(msg.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }

        // if irregular length, then perform padding
        if (msg.length() % 2 != 0){
            msg = msg.substring(0,msg.length()-1);
        }

        // message to 2x(N/2) matrix
        int[][] msg2D = new int[2][msg.length()/2];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < msg.length(); i++){
            if (i % 2 == 0){
                msg2D[0][itr1] = alphabet.indexOf(msg.charAt(i));
                itr1++;
            } else {
                msg2D[1][itr2] = alphabet.indexOf(msg.charAt(i));
                itr2++;
            }
        }

        // key to 2x2 matrix
        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = alphabet.indexOf(key.charAt(itr3));
                itr3++;
            }
        }

        // validating the key
        // finding determinant of key matrix
        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, alphabet.length());

        // multiplicative inverse of key matrix
        int mulInverse = -1;
        for (int i = 0; i < alphabet.length(); i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, alphabet.length()) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            }
        }

        if (mulInverse == -1){
            cryptoDto.setResult("invalid key");
            return;
        }

        int itrCount = msg.length() / 2;
        int temp;
        for (int i = 0; i < itrCount; i++) {
            temp = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
            result.append(alphabet.charAt(temp % alphabet.length()));
            temp = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
            result.append(alphabet.charAt(temp % alphabet.length()));
        }
        cryptoDto.setResult(result.toString());
    }

    public void decrypt(CryptoDto cryptoDto) {
        String msg = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        String key = cryptoDto.getContext().toLowerCase().replaceAll("\\s" , "");
        StringBuilder result = new StringBuilder();
        String alphabet;
        if (alphabetRus.contains(String.valueOf(msg.charAt(0)))){
            alphabet = alphabetRus;
        }
        else{
            alphabet = alphabetEng;
        }

        // if irregular length, then perform padding
        if (msg.length() % 2 != 0){
            msg = msg.substring(0,msg.length()-1);
        }

        // message to 2x(N/2) matrix
        int[][] msg2D = new int[2][msg.length()/2];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < msg.length(); i++){
            if (i % 2 == 0){
                msg2D[0][itr1] = alphabet.indexOf(msg.charAt(i));
                itr1++;
            } else {
                msg2D[1][itr2] = alphabet.indexOf(msg.charAt(i));
                itr2++;
            }
        }

        // key to 2x2 matrix
        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = alphabet.indexOf(key.charAt(itr3));
                itr3++;
            }
        }

        // validating the key
        // finding determinant of key matrix
        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, alphabet.length());

        // multiplicative inverse of key matrix
        int mulInverse = -1;
        for (int i = 0; i < alphabet.length(); i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, alphabet.length()) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            }
        }

        if (mulInverse == -1){
            cryptoDto.setResult("invalid key");
            return;
        }

        // adjugate matrix
        // swapping
        int swapTemp = key2D[0][0];
        key2D[0][0] = key2D[1][1];
        key2D[1][1] = swapTemp;

        // changing signs
        key2D[0][1] *= -1;
        key2D[1][0] *= -1;

        key2D[0][1] = moduloFunc(key2D[0][1], 26);
        key2D[1][0] = moduloFunc(key2D[1][0], 26);

        // multiplying multiplicative inverse with adjugate matrix
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] *= mulInverse;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = moduloFunc(key2D[i][j], alphabet.length());
            }
        }
        int itrCount = msg.length() / 2;
        int temp;
        for (int i = 0; i < itrCount; i++) {
            temp = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
            result.append(alphabet.charAt(temp % alphabet.length()));
            temp = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
            result.append(alphabet.charAt(temp % alphabet.length()));
        }

        cryptoDto.setResult(result.toString());
    }

    private static int moduloFunc(int a, int b){
        int result = a % b;
        if (result < 0){
            result += b;
        }
        return result;
    }

}
