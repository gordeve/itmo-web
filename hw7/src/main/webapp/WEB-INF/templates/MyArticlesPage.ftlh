<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Hide/Show</th>
                </tr>
            </thead>
            <tbody>
                <#if articles??>
                    <#list articles as art>
                        <tr>
                            <td>${art.id}</td>
                            <td>${art.title}</td>
                            <td>
                                <button data-articleId="${art.id}" class="hideShowButton">
                                    <#if art.hidden>
                                        Show
                                    <#else>
                                        Hide
                                    </#if>
                                </button>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="3">No articles</td>
                    </tr>
                </#if>
            </tbody>

        </table>

    </div>
    <script>
        $(function () {
            $(".articles tr .hideShowButton").click(function() {
                    ajax(
                        function(response) {
                            if ($(this).text() === "Hide") {
                                $(this).text("Show");
                            } else {
                                $(this).text("Hide");
                            }
                        },
                        "switchHidden",
                        "id"
                    )

            });
        })
    </script>
</@c.page>
