$(document).ready(function() {
    //alert($('#selectedSearchMenu').val());
    $(".dropdown-menu li a").click(function(){
        $(".dropdown-menu li a").css('display','block');
        $(this).hide();
        $(".btn:first-child > span.drop").text($(this).text());
        $('#selectedSearchMenu').val($(this).text());
        //alert($('#selectedSearchMenu').val())
    });
});

