<#import "login.ftlh" as l>
<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #2a282e">
    <div class="container-fluid">
        <a class="navbar-brand" style="color: #cc1f53" href="/">Talkie!</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/main">Messages</a>
                </li>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/user/profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/messages/${name}">My messages</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/user">User List</a>
                    </li>
                </#if>
            </ul>
        </div>
        <#if user??>
            <div class="navbar-text me-3">${name}</div>
        <#else>
            <div class="navbar-text me-3">To start talkie -> </div>
        </#if>
        <@l.logout/>
    </div>
</nav>