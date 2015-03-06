if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

var product = {
  
    calculate : function(data, id)  {
        document.getElementById("total-"+id).innerHTML = 0;
        document.getElementById("total-"+id).innerHTML  = data.value * Number(document.getElementById("price-"+id).innerHTML);
    },
    enableEdit: function() {
        document.getElementById("customName").style.display = "inline-block";
        document.getElementById("customNameLabel").style.display = "none";
        document.getElementById("saveButton").style.display = "block";
    },
    resetLastUpdatedDate: function() {
        document.getElementById("lastUpdatedOn").value = "";
        document.getElementById("updatedDateValue").innerHTML = "";
    }
};
var signup = {
    checkPassword: function() {
        
        var agreement = document.getElementById('acceptTermsConditions');
        if (!agreement.checked){
            alert("You have to agree for the terms and conditions to proceed.") ;
            return false;
        } 
        
        
        var pass1 = document.getElementById("password").value;
        var pass2 = document.getElementById("confirmPassword").value;
        if (pass1 !== pass2) {
            //alert("Passwords Do not match");
            document.getElementById("password").validity.valid = 'false';
            document.getElementById("confirmPassword").validity.valid = 'false';
            document.getElementById("confirmPassword").setCustomValidity("Please enter the same Password as above.");
            document.getElementById("confirmPassword").focus();
        } else {
            document.getElementById("password").validity.valid = 'true';
            document.getElementById("confirmPassword").validity.valid = 'true';
            document.getElementById("confirmPassword").setCustomValidity("");
        }
    }
};


$('.dropdown-toggle').dropdown();