<#import "/parts/common.ftlh" as c>
<@c.page>
User editor

<div class="card card-body my-3">
    <form class="d-grid gap-3" action="/user" method="post">
        Username:<div><input class="form-control" type="text" value="${user.username}" name="username"></div>
        User roles:
        <#list roles as role>
            <div>
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                </label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-success" type="submit">Save</button>
    </form>
</div>
</@c.page>