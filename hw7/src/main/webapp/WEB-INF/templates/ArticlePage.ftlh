<#-- @ftlvariable name="targetUserId" type="java.lang.String" -->
<#-- @ftlvariable name="userRepository" type="ru.itmo.wp.model.repository.UserRepository" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <style>
        html {
            min-width: 750px;
        }
    </style>
    <h1>New article</h1>
    <div class="article form-box" style="width: 40rem">
        <div class="header">Create new article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title </label>
                    </div>
                    <div class="value">
                        <input id="title" name="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text: </label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" cols=40></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
    <script>
        $(function () {
            $(".article form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const $error = $(this).find(".error");
                $error.text("");
                ajax(
                    function (response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        }
                    },
                    "sendArticle",
                    {title, text}
                );
                return false;
            });
        });
    </script>
</@c.page>