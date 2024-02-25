package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;
import ru.itmo.web.hw4.model.Color;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.RED),
            new User(6, "pashka", "Pavel Mavrin", Color.GREEN),
            new User(9, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Hello World!", "We are finally here!", 1),
            new Post(5, "How to become grandmaster", "You can't.", 11),
            new Post(6, "Public apology", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vestibulum placerat sem. Etiam pretium nisl eu luctus finibus. Integer ullamcorper placerat magna aliquet porta. Aenean nisl turpis, venenatis eu molestie sed, tincidunt a augue. In tincidunt ornare est, ut suscipit quam porta nec. Proin tristique quis tellus vitae sodales. Donec dignissim condimentum molestie. Nunc posuere risus sed nisl varius, id suscipit ex tincidunt. In sollicitudin leo viverra felis sodales, quis egestas ex mattis.\n" +
                    "Maecenas consequat, nibh at pharetra convallis, augue tortor pellentesque risus, vel scelerisque enim nulla a erat. Suspendisse viverra tincidunt vulputate. Sed luctus libero ligula, sagittis ornare arcu accumsan nec. Phasellus vestibulum ante cursus purus semper luctus. Pellentesque varius lectus sed sodales suscipit. Maecenas eget lacus dignissim, porttitor urna sit amet, pellentesque tortor. Etiam in malesuada sem. Morbi nec viverra tortor, vitae ornare elit. Praesent rhoncus felis egestas, sodales leo sed, lacinia elit. Aliquam volutpat massa interdum, feugiat turpis dignissim, euismod lacus. Nam ut arcu libero.", 9),
            new Post(7, "War and Peace", "Так говорила в июле 1805 года известная Анна Павловна Шерер, фрейлина и приближенная императрицы Марии Феодоровны, встречая важного и чиновного князя Василия, первого приехавшего на ее вечер. Анна Павловна кашляла несколько дней, у нее был грипп, как она говорила (грипп был тогда новое слово, употреблявшееся только редкими). В записочках, разосланных утром с красным лакеем, было написано без различия во всех:", 9),
            new Post(10, "AAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 6)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }

        data.put("posts", POSTS);
    }
}
