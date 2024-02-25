<#import "commons.ftlh" as c>

<@c.page>
    <style>
        html {
            min-width: 750px;
        }
    </style>
    <h1>New notice</h1>
    <div class="article form-box" style="width: 35rem">
        <div class="header">Create new notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Content: </label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content" cols=30></textarea>
                    </div>
                </div>
                <@c.error "noticeForm.content"/>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>