<#import "/parts/common.ftlh" as c>
<@c.page>
<div align="center">
    <h5>
        Howdy ho!
    </h5><br>
    <div>
        Here you can post your thoughts, photos and ideas...
    </div><br>
    <form action="/logout" method="post">
        <button class="btn btn-dark" style="background-color: #cc1f53" type="submit">
            Log In
        </button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</div>
</@c.page>