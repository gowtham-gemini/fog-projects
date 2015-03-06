/*
 * The common payment gateway interface
 * Abstract idea of any payment gateway implementations for FogPanel
 * 
 */
package com.assistanz.fogpanel.paymentgateway;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCardToken;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.core.rest.PayPalResource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author sujai
 */
public class Paypal implements PaymentGateway {

    private String accessToken;

    public Paypal(Properties config, HashMap<String, String> map) throws Exception {
//        config.setProperty("clientID", "AfLyyxAp6U4Yjig23ahvvBdelSS8sxr7D8xZleVW1IPNuOc6-lodwXgdC0Uj");
//        config.setProperty("clientSecret", "EC1qrBD-dgkJ-i9tAqOoE2JS6S7CjdPDfpG83xyCk6SmWWSi6kXuVWv4G273");
//        config.setProperty("service.EndPoint", "https://api.sandbox.paypal.com");
        PayPalResource.initConfig(config);

        // ###AccessToken
        // Retrieve the access token from
        // OAuthTokenCredential by passing in
        // ClientID and ClientSecret
        String clientID = config.getProperty("clientID");
        String clientSecret = config.getProperty("clientSecret");
        String serviceEndPoint = config.getProperty("service.EndPoint");
        
        map.put("service.EndPoint", serviceEndPoint);
                       
        OAuthTokenCredential tokenCredential = (new OAuthTokenCredential(clientID, clientSecret, map));
        this.accessToken = tokenCredential.getAccessToken();
    }

    @Override
    public CreditCard addCreditCard(CreditCard card) throws Exception {
        try {
            com.paypal.api.payments.CreditCard paypalCard = new com.paypal.api.payments.CreditCard();
            paypalCard.setExpireMonth(card.getExpiryMonth());
            paypalCard.setExpireYear(card.getExpiryYear());
            paypalCard.setNumber(card.getNumber());
            paypalCard.setType(card.getType());

            APIContext apiContext = new APIContext(accessToken);
            com.paypal.api.payments.CreditCard createdPaypalCard = paypalCard.create(apiContext);

            CreditCard createdCard = new CreditCard();
            createdCard.setUUID(createdPaypalCard.getId());
            createdCard.setExpiryMonth(createdPaypalCard.getExpireMonth());
            createdCard.setExpiryYear(createdPaypalCard.getExpireYear());
            createdCard.setNumber(createdPaypalCard.getNumber());
            createdCard.setType(createdPaypalCard.getType());
            return createdCard;
        } catch (PayPalRESTException e) {
            throw new Exception(e);
        }
    }

    @Override
    public Properties updateCreditCard(CreditCard card) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Properties removeCreditCard(String uniqueIdentifier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Transaction processPayment(CreditCard card, Transaction transaction) throws Exception {
        transaction.setCreditCard(card);
        return this.processPayment(transaction);
    }

    @Override
    public Transaction processPayment(Transaction transaction) throws Exception {
        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        Boolean useSavedCard = false;
        com.paypal.api.payments.CreditCard creditCard = new com.paypal.api.payments.CreditCard();
        CreditCardToken creditCardToken = new CreditCardToken();
        if (transaction.getCreditCard().getUUID() != null) {
            useSavedCard = true;
            creditCardToken.setCreditCardId(transaction.getCreditCard().getUUID());
        } else {
            creditCard.setCvv2(transaction.getCreditCard().getCvv());
            creditCard.setExpireMonth(transaction.getCreditCard().getExpiryMonth());
            creditCard.setExpireYear(transaction.getCreditCard().getExpiryYear());
            creditCard.setFirstName(transaction.getCreditCard().getFirstName());
            creditCard.setLastName(transaction.getCreditCard().getLastName());
            creditCard.setNumber(transaction.getCreditCard().getNumber());
            creditCard.setType(transaction.getCreditCard().getType());
        }
        

        // ###Details
        // Let's you specify details of a payment amount.
//        Details details = new Details();
//        details.setShipping("0");
//        //details.setSubtotal(transaction.getAmount().toPlainString());
//        details.setTax("0");

        // ###Amount
        // Let's you specify a payment amount.
        Amount amount = new Amount();
        amount.setCurrency(transaction.getCurrency());
        // Total must be equal to the sum of shipping, tax and subtotal.
        amount.setTotal(transaction.getAmount().toPlainString());
//        amount.setDetails(details);

        // ###Transaction
        // A transaction defines the contract of a
        // payment - what is the payment for and who
        // is fulfilling it. Transaction is created with
        // a `Payee` and `Amount` types
        com.paypal.api.payments.Transaction paypalTransaction =
                new com.paypal.api.payments.Transaction();
        paypalTransaction.setAmount(amount);
        paypalTransaction
                .setDescription(transaction.getDescription());

        // The Payment creation API requires a list of
        // Transaction; add the created `Transaction`
        // to a List
        List<com.paypal.api.payments.Transaction> transactions =
                new ArrayList<com.paypal.api.payments.Transaction>();
        transactions.add(paypalTransaction);

        // ###FundingInstrument
        // A resource representing a Payeer's funding instrument.
        // In this case, a Saved Credit Card can be passed to
        // charge the payment.
        FundingInstrument fundingInstrument = new FundingInstrument();
        if (useSavedCard == true) {
            fundingInstrument.setCreditCardToken(creditCardToken);
        } else {
            fundingInstrument.setCreditCard(creditCard);
        }

        // The Payment creation API requires a list of
        // FundingInstrument; add the created `FundingInstrument`
        // to a List
        List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
        fundingInstrumentList.add(fundingInstrument);

        // ###Payer
        // A resource representing a Payer that funds a payment
        // Use the List of `FundingInstrument` and the Payment Method
        // as 'credit_card'
        Payer payer = new Payer();
        payer.setFundingInstruments(fundingInstrumentList);
        payer.setPaymentMethod("credit_card");

        // ###Payment
        // A Payment Resource; create one using
        // the above types and intent as 'sale'
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        try {

            // ### APIContext
            // APIContext which takes 'Access Token'
            // argument
            APIContext apiContext = new APIContext(accessToken);
            // Use this variant if you want to pass in a request id  
            // that is meaningful in your application, ideally 
            // a order id.
			/* 
             * String requestId = Long.toString(System.nanoTime();
             * APIContext apiContext = new APIContext(accessToken, requestId ));
             */

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            Payment createdPayment = payment.create(apiContext);
            //transaction.setU
            //req.setAttribute("response", Payment.getLastResponse());
            transaction.setUUID(createdPayment.getId());
            transaction.setTransactionDateTime(new Date());
            transaction.setCompleted(Boolean.TRUE);
            
        } catch (PayPalRESTException e) {
            throw new Exception(e);
        }

        return transaction;
    }
}
