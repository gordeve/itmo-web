<#import "./commons.ftlh" as c>

<@c.page>
    <#if pageUser??>
        <div class="user-box">
            <img src="https://userpic.codeforces.org/no-title.jpg" alt="Avatar" style="float: right">
            <h2>${pageUser.login}</h2>
            <ul>
                <#assign params=[
                {"label": "id", "value": "${pageUser.id}" },
                {"label": "handle", "value": "${pageUser.login}" },
                {"label": "joined", "value": "${pageUser.creationTime}" }
                ]/>
                <#list params as param>
                    <li>${param.label}: ${param.value}</li>
                </#list>
            </ul>
        </div>
    <#else>
        No such user
    </#if>

</@c.page>
