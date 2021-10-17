<#include "security.ftlh">
<#import "pager.ftlh" as p>
<!-- Masonry -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/4.2.2/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>

<@p.pager url page />
<div class="row row-cols-md-4" data-masonry='{"percentPosition": true }'>
    <#list page.content as message>
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
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-start btn btn-outline-info" href="/messages/${message.author.username}">
                            ${message.authorName}
                        </a>
                        <a class="col align-self-center btn btn-outline-info" href="/messages/${message.id}/like">
                            <#if message.meLiked>
                                <i class="fas fa-heart"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>
                            ${message.likes}
                        </a>
                       <#if message.author.id == currentUserId>
                            <a class="col btn btn-outline-danger ms-2" href="/messages?message=${message.id}">
                                Edit
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    <#else>
        <div>No messages</div>
    </#list>
</div>