import com.assistanz.fogpanel.LoginEventListener

// Place your Spring DSL code here
beans = {
//    loginEventListener(LoginEventListener)
    
    userDetailsService(com.assistanz.fogpanel.auth.CustomAuthService)

}
