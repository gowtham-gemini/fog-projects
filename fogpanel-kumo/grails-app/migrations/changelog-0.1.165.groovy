databaseChangeLog = {

    changeSet(author: "lakshmi (generated)", id: "1406027960413-2") {
        insert(tableName: "payment_gateways") {
            column(name: "id", valueNumeric: 1)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Paypal Payment Gateway")
            column(name: "gateway_name", value: "PayPal")
            column(name: "status", value: "ENABLE")
            column(name: "gateway_type", value: "ADVANCED")
        }
            
        insert(tableName: "payment_gateways") {
            column(name: "id", valueNumeric: 2)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "CCAvenue Payment Gateway")
            column(name: "gateway_name", value: "CCAvenue")
            column(name: "status", value: "ENABLE")
            column(name: "gateway_type", value: "SEAMLESS")
        }
    }

}
