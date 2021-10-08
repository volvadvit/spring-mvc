<#include "security.ftlh">
<!-- Masonry -->
<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>

<div class="grid" data-masonry='{ "itemSelector": ".grid-item", "columnWidth": 200 }'>
    <#list messages as message>
    <div class="grid-item">
            <div class="card border-primary my-3">
                <div class="card-header">
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
                    <a href="/messages/${message.author.username}">${message.authorName}</a>
                    <#if message.author.id == currentUserId>
                        <a class="btn btn-outline-danger" href="/messages?message=${message.id}">
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