/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




var showPage = {
    displayFailureReason: function () {
        var hideMsg = "Failure reason hide";
        var showMsg = "Failure reason show";
        
        var failureContainer = document.getElementById("failure-container-div");
        var linkFailure = document.getElementById("linkFailure");
        
        if(failureContainer.style.display === "none") {
            failureContainer.style.display='';
            linkFailure.innerHTML   = hideMsg;
            
        } else {
            failureContainer.style.display='none';
            linkFailure.innerHTML   = showMsg;
        }
        
    }
};
window.showPage = showPage;
