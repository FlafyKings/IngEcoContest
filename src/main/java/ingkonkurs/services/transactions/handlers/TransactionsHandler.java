package ingkonkurs.services.transactions.handlers;

import ingkonkurs.services.transactions.Account;
import ingkonkurs.services.transactions.Transaction;
import ingkonkurs.services.transactions.codecs.TransactionsEncoder;
import ingkonkurs.utils.decoder.JSONDecoder;
import ingkonkurs.utils.response.ResponseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.TreeMap;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

@ChannelHandler.Sharable
public class TransactionsHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf request) throws Exception {

        //Zamiana formatu JSON na tablice klasy Transaction
        Transaction[] transactions = JSONDecoder.decode(request, Transaction[].class);

        //Obliczenie salda dla każdego konta istniejącego w spisie transakcji
        TreeMap<String, Account> accounts = new TreeMap<>();
        calculateTransactions(transactions, accounts);

        //Zamiana drzewa klasy Account na format JSON
        ByteBuf out = Unpooled.buffer();
        TransactionsEncoder.encode(accounts, out);

        //Wysłanie odpowiedzi do klienta
        ResponseHandler.sendResponse(ctx, OK, out);

        //Usunięcie serwisu z szyny Netty
        ctx.pipeline().remove(this);
    }

    private void calculateTransactions(Transaction[] transactions, TreeMap<String, Account> accounts){
        for (Transaction transaction : transactions) {
            String debitAccount = transaction.getDebitAccount();
            String creditAccount = transaction.getCreditAccount();
            float amount = transaction.getAmount();

            accounts.putIfAbsent(debitAccount, new Account(debitAccount));
            accounts.putIfAbsent(creditAccount, new Account(creditAccount));
            accounts.put(debitAccount, accounts.get(debitAccount).addDebit(amount));
            accounts.put(creditAccount, accounts.get(creditAccount).addCredit(amount));
        }
    }
}
