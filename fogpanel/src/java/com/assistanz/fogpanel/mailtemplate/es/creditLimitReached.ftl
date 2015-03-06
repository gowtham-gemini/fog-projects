<#import "header.ftl" as header>
<#import "footer.ftl" as footer>
<@header.headerContent/> 
Límite de crédito alcanzado

Halo ${user.account.firstName} ,

Tu cuenta ha alcanzado ${creditLimit} en ${date}

Saludos,
${signature} 
<@footer.footerContent/>
