package ingkonkurs.services.transactions;

import java.util.Locale;

public class Account {
    private final String account;
    private int debitCount;
    private int creditCount;
    private float balance;

    public Account(String accountNumber) {
        this.account = accountNumber;
        this.balance = 0.0F;
    }

    @Override
    public String toString() {
        return "Account: accountNumber: " + account + ", balance: " + String.format("%.2f", balance) + ", debitCount: " + debitCount + ", creditCount: " + creditCount;
    }

    public String getAccountNumber(){
        return this.account;
    }

    public String getBalance() {
        return String.format(Locale.US, "%.2f", this.balance);
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public Account addDebit(float amount) {
        this.balance -= amount;
        this.debitCount += 1;
        return this;
    }

    public Account addCredit(float amount) {
        this.balance += amount;
        this.creditCount += 1;
        return this;
    }
}
