<#import "/parts/common.ftlh" as c>
<#import "/parts/login.ftlh" as l>
<@c.page>
<h2>Login page</h2>
<@l.login "/login" />
<div><a href="/registration">Sign Up</a></div>
</@c.page>