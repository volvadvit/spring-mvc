<#include "security.ftlh">
<!-- Masonry -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/4.2.2/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>

    <div class="row row-cols-md-4" data-masonry='{"percentPosition": true }'>
    <#list messages as message>
        <div class="col-md-3">
            <div class="card border-dark my-3">
                <div class="card-header" style="color: #cc1f53">
                     #<i>${message.tag}</i>
                </div>
                <div>
                    <#if message.filename??>
                        <img class="card-img-top" src="/img/${message.filename}">
                    </#if>
                </div>
                <div class="m-2">
                    <p class="card-text">${message.text}</p>
                </div>
                <div class="card-footer text-muted">
                    <a class="btn btn-outline-info" href="/messages/${message.author.username}">${message.authorName}</a>
                    <#if message.author.id == currentUserId>
                        <a class="btn btn-outline-danger ms-2" href="/messages?message=${message.id}">
                            Edit
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        <div>No messages</div>
    </#list>
</div>