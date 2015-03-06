package cs.api.tester

class AccountController {

    def createAccount() { render(view: "createAccount", model: [menuSignup: "highlight",  apiUrl: "/api/admin/account/createAccount", method: "GET"]);}
    
    def listAccount() {
        render(view: "listAccount", model: [menuListAccount: "highlight",  apiUrl: "/api/admin/account/listAccount", method: "GET"]);
    }
}
