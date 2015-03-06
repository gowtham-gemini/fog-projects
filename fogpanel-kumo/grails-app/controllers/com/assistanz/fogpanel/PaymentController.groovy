package com.assistanz.fogpanel

import com.assistanz.fogpanel.paymentgateway.*;

class PaymentController {

    def paymentService;
    
    def index() {
        
        //def card = paymentService.saveCard("CARD-1234-AZ-UID2", "4417119669820331", 11, 2018, "");
        CreditCard card = new CreditCard();
//        4417119669820331
        card.number = "4111111111111111";
        card.cvv = "111";
        card.expiryMonth = 11;
        card.expiryYear = 2018;
        card.type = "visa";
        //paymentService.saveCard("CARD-1234-AZ-UID2", "4417119669820331", 11, 2018, ""
        paymentService.makePayment(card, 200, "Test Payment 2");
    }
    
    def paymentCCAvenueStatus() {
        
    }
}
