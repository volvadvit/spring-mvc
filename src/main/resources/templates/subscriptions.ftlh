<#import "/parts/common.ftlh" as c>
<@c.page>
    <h3 align="center">${username}</h3><br>
    <h4 align="center" style="color: #cc1f53">${type}</h4><br>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a class="btn-outline-dark" href="/messages/${user.username}">${user.username}</a>
            </li>
        </#list>
    </ul>
</@c.page>