package piv.cryption.services;

import org.springframework.stereotype.Service;
import piv.cryption.models.CryptoDto;

@Service
public class Richelieu {
    private static final int SIZE = 6;
    private static final int[][] KEY =
            {{0, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0}};

    public void encrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase().replaceAll("\\s" , "");
        StringBuilder result = new StringBuilder();
        char[][] charResult = new char[SIZE][SIZE];
        int count = text.length() / (SIZE*SIZE);
        if (text.length() % (SIZE*SIZE) != 0)
            count++;
        for (int c = 0; c < count; c++) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        if (text.length() > 0) {
                        charResult[i][j] = text.charAt(0);
                        text = text.substring(1);
                        }
                        else
                            charResult[i][j] = '.';
                    }
                }
            }

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        if (text.length() > 0) {
                        charResult[j][SIZE - i - 1] = text.charAt(0);
                        text = text.substring(1);
                        }
                        else
                            charResult[j][SIZE - i - 1] = '.';
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        if (text.length() > 0) {
                        charResult[SIZE - i - 1][SIZE - j -1] = text.charAt(0);
                        text = text.substring(1);
                        }
                        else
                            charResult[SIZE - i - 1][SIZE - j -1] = '.';
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        if (text.length() > 0) {
                            charResult[SIZE - j - 1][i] = text.charAt(0);
                            text = text.substring(1);
                        }
                        else
                            charResult[SIZE - j - 1][i] = '.';
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    result.append(charResult[i][j]);
                }
            }
        }
        cryptoDto.setResult(result.toString());
    }
    public void decrypt(CryptoDto cryptoDto){
        String text = cryptoDto.getString().toLowerCase();
        StringBuilder result = new StringBuilder();
        int count = text.length() / (SIZE*SIZE);
        if (text.length() % (SIZE*SIZE) != 0)
            count++;
        for (int c = 0; c < count; c++) {
            char[][] charText = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    charText[i][j] = text.charAt(0);
                    text = text.substring(1);
                }
            }

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        result.append(charText[i][j]);
                    }
                }
            }

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        result.append(charText[j][SIZE - i - 1]);
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        result.append(charText[SIZE - i - 1][SIZE - j - 1]);
                    }
                }
            }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (KEY[i][j] == 1) {
                        result.append(charText[SIZE - j - 1][i]);
                    }
                }
            }
        }
        result = new StringBuilder(result.toString().replaceAll("[.]", ""));
        cryptoDto.setResult(result.toString());
    }
}
