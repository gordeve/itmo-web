window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

ajax = function(success, action = "action", fields = {}, async = true) {
    let data = {action, ...fields};
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        async: async,
        success: function (response) {
            if (response.hasOwnProperty("redirect")) {
                location.href = response["redirect"];
            }
            success(response);
        }
    });
}

dateDiff = function(firstDate, secondDate) {
    let diff = (secondDate.getTime() - firstDate.getTime()) / 1000;
    if (diff >= 86400)  {
        return Math.floor(diff/86400) + " days ago"
    }
    if (diff >= 3600)  {
        return Math.floor(diff/3600) + " hours ago"
    }
    if (diff >= 60)  {
        return Math.floor(diff/60) + " minutes ago"
    }
    return Math.floor(diff) + " seconds ago";
}

findUser = function(id) {
    let user = {};
    ajax(
        function (response) {
            user = response["user"];
        },
        "findUser",
        {userId: id},
        false
    );
    return user;
}