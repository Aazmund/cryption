package piv.cryption.models;

public class CryptoDto
{
    private String action;
    private String string;
    private String cryptName;
    private String context;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
