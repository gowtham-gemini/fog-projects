function checkPasswordStrength(id) {
    var pwd =  dijit.byId(id).getValue();        
    var strength_text = dojo.byId('strength_text');
    var strength_id = dojo.byId('strength_id');
    var progress_bar = dojo.byId('progress_bar'); 

    var strong = new RegExp('^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$', 'g');
    var medium = new RegExp('^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$', 'g');
    var enough = new RegExp('(?=.{6,}).*', 'g');
	
    if (strength_text == null) {
        return;
    }	
    strength_id.value = 0;    
    var width = pwd.length * 10;
	
    if (pwd.length == 0) {
        strength_text.innerHTML = '&nbsp;';
        progress_bar.style.backgroundColor = '#FFFFFF';
    } else if (false == enough.test(pwd))	{
        strength_text.innerHTML = 'Too short';
        progress_bar.style.backgroundColor = '#DC143C';
    } else if (strong.test(pwd)) {
        strength_text.innerHTML = 'Strong';
        width = 100;
        progress_bar.style.backgroundColor = '#228B22';
        strength_id.value = 3;
    } else if (medium.test(pwd)) {
        strength_text.innerHTML = 'Medium';
        width = 70;
        progress_bar.style.backgroundColor = '#FF8C30';
        strength_id.value = 2;
    } else {
        width = 60;
        strength_text.innerHTML = 'Weak';
        progress_bar.style.backgroundColor = '#FFD700';
        strength_id.value = 1;
    }	
    progress_bar.style.width = width + '%'; 	
    if(pwd.length == 0) {
        dojo.byId('password_strength').style.display = "none";
    } else {
        dojo.byId('password_strength').style.display = "block";        
        if(pwd.length < 8) {  
//            dijit.byId("newPassword").focus();
//            dijit.byId("newPassword").set("invalidMessage", "Password length must be min 8 and max 15 characters");
            dijit.byId("confirmPassword").reset();           
            dijit.byId("confirmPassword").validator = function() {};             
         } else if(pwd.length > 15) {
//             dijit.byId("newPassword").focus();
//             dijit.byId("newPassword").set("invalidMessage", "Password length must be min 8 and max 15 characters");
             dijit.byId("confirmPassword").reset();           
             dijit.byId("confirmPassword").validator = function() {};       
         }
     }       
}
function blockSubmitButton (confirmPassword) {
    var pass =  dijit.byId("newPassword").getValue();
  
    var confirmPasswordValue = confirmPassword.getValue();
    confirmPassword.validator = function() {
        if(pass == confirmPasswordValue) { 
            dojo.byId("passwordSubmitButton").disabled = false;
            return true;      
        } else {
            dojo.byId("passwordSubmitButton").disabled = true;
//            confirmPassword.set("invalidMessage", "Password Do Not Match, Enter a Correct Password to Submit");
            return false;
        }        
    };  
}

function clearField (newPassword) {
    dijit.byId("confirmPassword").reset();
    dojo.byId("passwordSubmitButton").disabled = true;
    
}
function confirmPassword(confirmPassword) {
 
    var pass =  dijit.byId("newPassword").getValue();
    var status = true;   
    var confirmPasswordValue = confirmPassword.getValue();
    confirmPassword.validator = function() {
        if(pass == confirmPasswordValue) {           
            confirmPassword.set("invalidMessage", " ");
            status = true;
            return status;      
        } else if(pass != confirmPasswordValue) {
            confirmPassword.set("invalidMessage", "Password Do Not Match");
//            confirmPassword.focus();
            status = false;
            return status;
        } else {
            return false;
        }        
    };    
}

function confirmEmail(currentEmail) {
    var email =  dijit.byId("newEmail").value;    
    var status = true; 
   dijit.byId("confirmEmail").validator = function() {
       if(email == currentEmail.value) {
           currentEmail.set("invalidMessage", "");
           status = true;
           return status;
       } else {
           currentEmail.set("invalidMessage", "Email Do Not Match");
//           currentEmail.focus();
           status = false;
           return status;
       }
   };
    
}
//Random password generator
function generatePassword() {
        
        var keylist="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#%&'()*+,-./~:;<=>?@[\]^_`{|}";
    var temp='';

    for (var i=0;i<8;i++) {
        temp+=keylist.charAt(Math.floor(Math.random()*keylist.length));
    }

    dijit.byId("passwordGenerator").setValue(temp);

}
function showPasswordGenerator() {
    
    dijit.byId("passwordGeneratorDialog").show();  
    generatePassword();
    
}
function useGeneratedPassword() {
    
     var password = dijit.byId("passwordGenerator").value;
    
    dijit.byId("newPassword").setValue(password);
    dijit.byId("confirmPassword").setValue(password);
    dijit.byId("passwordGeneratorDialog").hide(); 
}	
	
 function copy() {
     var password = dijit.byId("passwordGenerator").value;
        alert(password);
    if(window.clipboardData) {
        alert(password);
        window.clipboardData.clearData();
        
        
        window.clipboardData.setData("Text", password);
                                              
     } else {
         alert(password);
         window.clipboardData.setData("Text", password);
     }
     
        
}		

