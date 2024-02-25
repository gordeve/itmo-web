<#import "./common.ftlh" as c>

<#macro blogpost users post isFull>
    <article>
        <div class="title"><a href="/post?post_id=${post.id}">${post.title}</a></div>
        <div class="information">By <@c.userlink c.findBy(users, "id", post.user_id) false/></div>
        <div class="body">
            <@c.trimPost post=post len=((1 - isFull) * 350)/>
        </div>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="score">0</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">0</a>
            </div>
        </div>
    </article>
</#macro>