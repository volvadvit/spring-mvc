<#import "/parts/common.ftlh" as c>
<#import "/parts/login.ftlh" as l>
<@c.page>
    <div class="mb-1">
        <h2>Registration</h2>
    </div>
    <p class="text-warning">${message!""}</p>
    <@l.login "/registration" true/>
</@c.page>