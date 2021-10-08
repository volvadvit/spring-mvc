<#import "/parts/common.ftlh" as c>
<#import "/parts/login.ftlh" as l>
<@c.page>
    <p class="text-warning">${message!""}</p>
    <@l.login "/login" false/>
</@c.page>