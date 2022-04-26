package piv.cryption.models;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class CryptoDto
{
    private String action;
    private String string;
    private String cryptName;
    private String context;
    private String encryptString;

    public CryptoDto(String action, String string, String cryptName, String context) {
        this.action = action;
        this.string = string;
        this.cryptName = cryptName;
        this.context = context;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getCryptName() {
        return cryptName;
    }

    public void setCryptName(String cryptName) {
        this.cryptName = cryptName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getEncryptString() {
        return encryptString;
    }

    public void setEncryptString(String encryptString) {
        this.encryptString = encryptString;
    }
}
