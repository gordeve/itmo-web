<#import "./common.ftlh" as c>
<#import "./blog.ftlh" as blog>
<@c.page>
    <#list posts?reverse as post>
        <@blog.blogpost users post 0/>
    </#list>
</@c.page>
