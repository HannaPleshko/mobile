package client.entityClass;

import java.util.Objects;

public class Accounting {
    private int id_accounting;
    private String vendercode;
    private String date;
    private int amount;
    private String login;


    public Accounting() {
    }

    public Accounting(int id_accounting, String vendercode, String date, int amount, String login) {
        this.id_accounting = id_accounting;
        this.vendercode = vendercode;
        this.date = date;
        this.amount = amount;
        this.login = login;
    }

    public int getId_accounting() {
        return id_accounting;
    }

    public void setId_accounting(int id_accounting) {
        this.id_accounting = id_accounting;
    }

    public String getVendercode() {
        return vendercode;
    }

    public void setVendercode(String vendercode) {
        this.vendercode = vendercode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounting that = (Accounting) o;
        return id_accounting == that.id_accounting &&
                amount == that.amount &&
                Objects.equals(vendercode, that.vendercode) &&
                Objects.equals(date, that.date) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_accounting, vendercode, date, amount, login);
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "id_accounting=" + id_accounting +
                ", vendercode='" + vendercode + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", login='" + login + '\'' +
                '}';
    }
}
