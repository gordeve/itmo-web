<#-- @ftlvariable name="uri" type="String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/users", "name": "Users"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <a href="${item.href}"
                        <#if item.href == uri>class="current"</#if>
                        >${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <@sidePost post/>
        </#list>
    </aside>
</#macro>

<#macro trimPost post len>
    <#assign pt = post.text/>
    <#if (pt?length <= len || len == 0)>
        ${pt}
    <#else>
        ${pt?substring(0,len)}...
    </#if>
</#macro>

<#macro sidePost post>
    <section>
        <div class="header">Post #{post.id}</div>
        <div class="body">
            <@trimPost post 350></@trimPost>
        </div>
        <div class="footer">
            <a href="/post?post_id=${post.id}">View all</a>
        </div>
    </section>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user nameOnly=true>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly>
        <a href=${handleToUri(user.handle)}>${user.name}</a>
    <#else>
        <a class="user ${getColor(user).name()!}" href=${handleToUri(user.handle)}>${user.handle}</a>
    </#if>
</#macro>

<#function getColor user>
    <#return user.color/>
</#function>

<#function findBy items key value="">
    <#list items as item>
        <#if item[key]?string == value?string>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro conditionalAnchor text link="">
    <#if link!="">
        <a href=${link}>${text}</a>
    <#else>
        <a href='#' class='non-active'>${text}</a>
    </#if>
</#macro>

<#function handleToUri handle>
    <#return "/user?handle=${handle}"/>
</#function>

<#function nextUserLink users id>
    <#list users as user>
        <#if user.id == id>
            <#if user_has_next>
                <#return handleToUri(users[user_index + 1].handle)/>
            </#if>
        </#if>
    </#list>
    <#return ""/>
</#function>

<#function prevUserLink users id>
    <#list users as user>
        <#if user.id == id>
            <#if (user_index > 0)>
                <#return handleToUri(users[user_index - 1].handle)/>
            </#if>
        </#if>
    </#list>
    <#return ""/>
</#function>

<#function countPosts user_id>
    <#assign count=0/>
    <#list posts as post>
        <#if post.user_id == user_id>
            <#assign count++/>
        </#if>
    </#list>
    <#return count/>
</#function>