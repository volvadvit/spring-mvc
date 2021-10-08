<#import "/parts/common.ftlh" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main">
            <input type="text" name="filter" placeholder="Search by tag" value="${filter!""}">
            <button class="btn btn-primary" type="submit">Search</button>
        </form>
    </div>
</div>

<#include "parts/messageAdd.ftlh" />
<#include "parts/messageList.ftlh" />

</@c.page>