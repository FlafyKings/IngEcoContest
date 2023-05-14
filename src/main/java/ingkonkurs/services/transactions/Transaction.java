package ingkonkurs.services.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    private final String debitAccount;
    private final String creditAccount;
    private final float amount;
    public Transaction(@JsonProperty("debitAccount") String debitAccount,
                       @JsonProperty("creditAccount") String creditAccount,
                       @JsonProperty("amount") float amount) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction: debitAccount: " + debitAccount + ", creditAccount: " + creditAccount + ", amount: " + amount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public float getAmount() {
        return amount;
    }
}
