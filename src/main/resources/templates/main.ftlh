<#import "/parts/common.ftlh" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main">
            <input type="text" name="filter" placeholder="Search..." value="${filter!""}">
            <button class="btn btn-primary" type="submit">Search</button>
        </form>
    </div>
</div>

<p>
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#addNewMessage" aria-expanded="false" aria-controls="addNewMessage">
        Add new message
    </button>
</p>

<div class="collapse" id="addNewMessage">
    <div class="card card-body my-3">
        <form class="d-grid gap-3" method="post" enctype="multipart/form-data">
            <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
            <div><input type="text" class="form-control" name="text" placeholder="Type message"></div>
            <div><input type="text" class="form-control" name="tag" placeholder="Type tag"></div>
            <div><input type="file" name="file"></div>
            <div><button class="btn btn-primary" type="submit">Post</button></div>
        </form>
    </div>
</div>

<div class="row row-cols-1 row-cols-md-3 g-4">
<#list messages as message>
    <div class="col">
        <div class="card my-3">
            <div class="card-header">
                tag: <i>${message.tag}</i>
            </div>
            <div>
                <#if message.filename??>
                    <img class="card-img-top" src="/img/${message.filename}">
                    <img class="card-img-top" src="/img/${message.filename}">
                </#if>>
            </div>
            <div class="m-2">
                <span>${message.text}</span>
            </div>
            <div class="card-footer text-muted">
                ${message.authorName}
            </div>
        </div>
    </div>
<#else>
No messages
</#list>
</div>
</@c.page>