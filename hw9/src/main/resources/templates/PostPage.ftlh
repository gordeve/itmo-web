<#-- @ftlvariable name="pagePost.comments" type="ru.itmo.wp.domain.Comment[]" -->
<#-- @ftlvariable name="pagePost" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if pagePost??>
        <@c.post post=pagePost/>
        <div class="comment form-box" style="width: 95%; margin-bottom: 1rem">
            <div class="header">Write comment</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="writeComment"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Your comment:</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text" cols="50"></textarea>
                        </div>
                        <@c.error "commentForm.text"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Post">
                    </div>
                </form>
            </div>
        </div>
        <#list pagePost.comments as comment>
            <div class="comment">
                <img src="https://userpic.codeforces.org/no-title.jpg" style="float: left; height: 5rem">
                <div class="user">
                    <h5>${comment.user.login}</h5>
                    <#list comment.text?split(r'\R', "r")?filter(x -> x?length != 0) as par>
                        <p>${par}</p>
                    </#list>

                </div>
            </div>
        </#list>
    <#else>
        No such post
    </#if>

</@c.page>
