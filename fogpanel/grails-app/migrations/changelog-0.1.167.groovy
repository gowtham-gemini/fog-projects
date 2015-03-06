databaseChangeLog = {

    changeSet(author: "lakshmi (generated)", id: "1406109700921-6") {
        insert(tableName: "payment_gateways") {
            column(name: "id", valueNumeric: 3)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "BrainTree Payment Gateway")
            column(name: "gateway_name", value: "BrainTree")
            column(name: "status", value: "DISABLE")
            column(name: "gateway_type", value: "ADVANCED")
            column(name: "include_exclude", value: "EXCLUDE")
            column(name: "is_default", value: "")
            column(name: "processing_fee_amount", valueNumeric: 10)
            column(name: "processing_fee_percent", valueNumeric: 2)
        }
            
        insert(tableName: "payment_gateways") {
            column(name: "id", valueNumeric: 4)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "MoneyBooker Payment Gateway")
            column(name: "gateway_name", value: "MoneyBooker")
            column(name: "status", value: "DISABLE")
            column(name: "gateway_type", value: "SEAMLESS")
            column(name: "include_exclude", value: "EXCLUDE")
            column(name: "is_default", value: "")
            column(name: "processing_fee_amount", valueNumeric: 10)
            column(name: "processing_fee_percent", valueNumeric: 2)
        }
        insert(tableName: "payment_gateways") {
            column(name: "id", valueNumeric: 5)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "NAB Payment Gateway")
            column(name: "gateway_name", value: "NAB")
            column(name: "status", value: "DISABLE")
            column(name: "gateway_type", value: "SEAMLESS")
            column(name: "include_exclude", value: "EXCLUDE")
            column(name: "is_default", value: "")
            column(name: "processing_fee_amount", valueNumeric: 10)
            column(name: "processing_fee_percent", valueNumeric: 2)
        }
    }
}
