<#-- @ftlvariable name="user_id" type="long" -->
<#import "./common.ftlh" as c>
<#import "./blog.ftlh" as blog>
<@c.page>
    <#list posts?reverse as post>
        <#if post.user_id == user_id>
            <@blog.blogpost users post 0/>
        </#if>
    </#list>
</@c.page>
