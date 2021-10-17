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
        <br/>
        <div class="col-sm-10">
            <div class="g-recaptcha" data-sitekey="6Ld_ldUcAAAAAIhzpJ7oULqBNBKuoiX5zxd_gXvX"></div>
            <#if captchaError??>
                <p class="text-danger">${captchaError}</p>
            </#if>
        </div>
    </#if>
    <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
    <br/>
    <#if isRegisterForm>
        <button class="btn btn-dark" style="background-color: #cc1f53" type="submit">Create</button>
        <a class="btn btn-outline-dark" href="/login">Sign In</a>
    <#else>
        <button class="btn btn-dark" style="background-color: #cc1f53" type="submit">Login</button>
        <a class="btn btn-outline-dark" href="/registration">Create account</a>
    </#if>
</form>
</#macro>


<#macro logout>
<#include "security.ftlh">
<form action="/logout" method="post">
    <button class="btn" style="color: #cc1f53" type="submit">
        <#if user??>
            Sign Out
        <#else>
            Log In
        </#if>
    </button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</#macro>