<#import "/parts/common.ftlh" as c>
<@c.page>
<div align="center" class="form-row my-2">
    <div class="form-group col-md-4">
        <form class="form-inline" method="get" action="/main">
            <input type="text" name="filter" placeholder="Search by tag" value="${filter!""}">
            <button class="btn btn-dark" style="background-color:#2a282e" type="submit">Search</button>
        </form>
    </div>
</div>
<#include "parts/messageAdd.ftlh" />
<#include "parts/messageList.ftlh" />
</@c.page>