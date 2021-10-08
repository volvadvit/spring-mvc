<#macro login path isRegisterForm>

<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Username: </label>
        <div class="col-sm-10">
            <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid','')}"
                   placeholder="Username" value="<#if user??>${user.username}</#if>" />
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-10">
            <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid','')}"
                   placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email:</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                       placeholder="your@email.com" value="<#if user??>${user.email}</#if>"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Confirm password:</label>
            <div class="col-sm-10">
                <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid','')}"
                       placeholder="Repeat Password"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        </div>
    </#if>
    <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
    <#if isRegisterForm>
        <button class="btn btn-primary" type="submit">Create</button>
        <div class="btn btn-outline-secondary">
            <a href="/login">Sign In</a>
        </div>
    <#else>
        <button class="btn btn-primary" type="submit">Login</button>
        <div class="btn btn-outline-secondary">
            <a href="/registration">Create new account</a>
        </div>
    </#if>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <button class="btn btn-outline-primary" type="submit">Change User</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</#macro>