<#import "/parts/common.ftlh" as c>
<@c.page>

<h5>Messages of "${username}"</h5></br>

<#if !isCurrentUser>
    <#if isSubscriber>
        <a class="btn btn-outline-danger" href="/user/unsubscribe/${username}">Unsubscribe</a>
    <#else>
        <a class="btn btn-outline-success" href="/user/subscribe/${username}">Subscribe</a>
    </#if>
</#if>

<div class="container my-3">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Subscriptions</div>
                    <h3 class="card-text">
                        <a class="btn btn-outline-dark" href="/user/subscriptions/${username}/list">${subscriptionsCount}</a>
                    </h3>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">Subscribers</div>
                    <h3 class="card-text">
                        <a class="btn btn-outline-dark" href="/user/subscribers/${username}/list">${subscribersCount}</a>
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>

<#if isCurrentUser>
    <#include "parts/messageAdd.ftlh" />
</#if>

<#include "parts/messageList.ftlh" />
</@c.page>