<#import "/parts/common.ftlh" as c>
<@c.page>
    <h5>Edit message</h5></br>
    <p>
        <button class="btn btn-info" type="button"
                data-bs-toggle="collapse" data-bs-target="#addNewMessage"
                aria-expanded="false" aria-controls="addNewMessage">
            Show edit form
        </button>
    </p>

    <div class="collapse <#if message??>show</#if>" id="addNewMessage">
        <div class="card card-body my-3">
            <form class="d-grid gap-3" method="post" enctype="multipart/form-data">
                <div>
                    <input type="text" class="form-control ${(textError??)?string('is-invalid','')}"
                           name="text" value="<#if message??>${message.text}</#if>" placeholder="Type message"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div>
                    <input type="text" class="form-control ${(tagError??)?string('is-invalid','')}"
                            name="tag" placeholder="Type tag" value="<#if message??>${message.tag}</#if>">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div><input type="file" name="file"></div>
                <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
                <div><input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/></div>
                <div><button class="btn btn-info" type="submit">Save</button></div>
            </form>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col">
            <div class="card my-3">
                <div class="card-header">
                    #<i>${message.tag}</i>
                </div>
                <div>
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}">
                    </#if>
                </div>
                <div class="m-2">
                    <span>${message.text}</span>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>
        </div>
    </div>
</@c.page>