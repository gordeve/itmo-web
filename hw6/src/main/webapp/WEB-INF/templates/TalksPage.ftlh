<#-- @ftlvariable name="targetUserId" type="java.lang.String" -->
<#-- @ftlvariable name="userRepository" type="ru.itmo.wp.model.repository.UserRepository" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <style>
        html {
            min-width: 750px;
        }
    </style>
    <h1>Talks</h1>
    <div class="form-box" style="width: 40rem">
        <div class="header">Send message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="targetUserId">Send to: </label>
                    </div>
                    <div class="value">
                        <select id="targetUserId" name="targetUserId">
                            <option value=0>(Pick your recipient)</option>
                            <#list users as user>
                                <option value="${user.id}"
                                    <#if targetUserId! == user.id?c>
                                        selected
                                    </#if>
                                    >
                                    ${user.login}
                                </option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Message: </label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" cols=40></textarea>
                    </div>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <br>
    <div class="datatable">
        <div class="caption">Your messages</div>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Message</th>
                    <th>Time</th>
                </tr>
            </thead>
            <tbody>
                <#if talks??>
                    <#list talks as talk>
                        <tr>
                            <td>${talk.id}</td>
                            <td>${talk.sourceLogin}</td>
                            <td>${talk.targetLogin}</td>
                            <td>${talk.text}</td>
                            <td>${talk.creationTime}</td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="5">No messages</td>
                    </tr>
                </#if>
            </tbody>
        </table>
    </div>
</@c.page>