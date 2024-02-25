<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign uid = c.findBy(users, "id", user_id)!/>
    <#assign u = c.findBy(users, "handle", handle)!uid/>

    <#if u?has_content>
        <div class="user-box">
            <img src="https://userpic.codeforces.org/no-title.jpg" alt="Avatar" style="float: right">
            <h2><@c.userlink u false/></h2>
            <ul>
                <#assign params=[
                    {"label": "id", "value": "${u.id}" },
                    {"label": "handle", "value": "${u.handle}" },
                    {"label": "name", "value": "${u.name}" },
                    {"label": "number of posts", "value" : "${c.countPosts(u.id)}"}
                ]/>
                <#list params as param>
                    <li>${param.label}:
                    <#if param.label != "number of posts">
                         ${param.value}
                    <#else>
                        <a href="/posts?user_id=${u.id}">${param.value}</a>
                    </#if>
                    </li>
                </#list>
            </ul>
        </div>
        <div class="arrows">
            <#assign next=c.nextUserLink(users, u.id)!>
            <#assign prev=c.prevUserLink(users, u.id)!>
            <@c.conditionalAnchor text="←" link="${prev}"/>
            &nbsp;
            <@c.conditionalAnchor text="→" link="${next}"/>
        </div>
    <#else>
        No such user
    </#if>

</@c.page>
