<p>
    <button class="btn btn-dark" type="button"
            style="background-color: #cc1f53"
            data-bs-toggle="collapse" data-bs-target="#addNewMessage"
            aria-expanded="false" aria-controls="addNewMessage">
        Add new message
    </button>
</p>

<div class="collapse <#if message??>show</#if>" id="addNewMessage">
    <div class="card card-body my-3">
        <form class="d-grid gap-3" method="post" enctype="multipart/form-data">
            <div><input type="text" class="form-control ${(tagError??)?string('is-invalid','')}"
                        name="tag" placeholder="Type tag" value="<#if message??>${message.tag}</#if>">
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div><input type="text" class="form-control ${(textError??)?string('is-invalid','')}"
                        name="text" value="<#if message??>${message.text}</#if>" placeholder="Type message"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <label for="file">1Mb max!</label>
            <div id="file"><input type="file" name="file"></div>
            <div><input type="hidden" name="_csrf" value="${_csrf.token}"/></div>
            <div><input type="hidden" name="id" value="<#if message??>${message.id!null}</#if>"/></div>
            <div><button class="btn btn-success" type="submit">Post</button></div>
        </form>
    </div>
</div>