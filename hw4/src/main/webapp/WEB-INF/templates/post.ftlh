<#-- @ftlvariable name="post_id" type="String" -->
<#import "./common.ftlh" as c>
<#import "./blog.ftlh" as blog>

<@c.page>
    <#assign post=c.findBy(posts, "id", post_id)>
    <@blog.blogpost users post 1/>
</@c.page>
