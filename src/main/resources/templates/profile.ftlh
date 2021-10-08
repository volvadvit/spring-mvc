<#import "/parts/common.ftlh" as c>

<@c.page>
${message!""}

<h5>${username}</h5>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-10">
            <input type="password" name="password" class="form-control" placeholder="new password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email:</label>
        <div class="col-sm-10">
            <input type="email" name="email" class="form-control" placeholder="yournew@email.com" value="${email!""}"/>
        </div>
    </div>
    <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
    <button class="btn btn-success" type="submit"> Save </button>
</form>
</@c.page>