<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <span class="noData">No articles yet!</span>

            <template>
                <article>
                    <div class="title"></div>
                    <div class="information">By </div>
                    <div class="body">

                    </div>
                    <div class="footer">
                        <div class="left">
                            <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                            <span class="positive-score">+0</span>
                            <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                        </div>
                        <div class="right">
                            <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        </div>
                    </div>
                </article>
            </template>
    </div>
    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");
            ajax(
                function (response) {
                    $noData.hide();
                    let curTime = new Date();
                    response["articles"].forEach(function(article) {
                        const $article = $($(".articles template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".body").html(article["text"]);
                        let user = findUser(article["userId"]);
                        let timePassed = dateDiff(new Date(Date.parse(article.creationTime)), curTime);

                        $article.find(".information").append(user.login + ", " + timePassed);
                        $article.find(".footer .right").append(timePassed);
                        $(".articles").append($article);
                    });
                },
                "getArticles"
            )
        });
    </script>
</@c.page>
