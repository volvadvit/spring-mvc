<#import "/parts/common.ftlh" as c>
<@c.page>

<h5>Messages of ${username}</h5>

<#if isCurrentUser>
    <#include "parts/messageAdd.ftlh" />
</#if>

<#include "parts/messageList.ftlh" />
</@c.page>