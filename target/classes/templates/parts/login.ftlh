<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Username: </label>
        <div class="col-sm-10">
            <input type="text" name="username" class="form-control" placeholder="Username"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-10">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" placeholder="your@email.com"/>
            </div>
        </div>
    </#if>
    <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
    <button class="btn btn-primary" type="submit">
        <#if isRegisterForm> Create <#else> Sign In </#if>
    </button>
    <#if !isRegisterForm><div><a href="/registration">Sign Up</a></div></#if>>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <button class="btn btn-primary" type="submit">Sign Out</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</#macro>