<#import "/parts/common.ftlh" as c>
<@c.page>
    List of users
    <table class="table table-bordered">
        <thead class="thread-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
                <th scope="col">Commands</th>
            </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td><a class="btn btn-outline-danger" href="/user/${user.id}">edit</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
</@c.page>