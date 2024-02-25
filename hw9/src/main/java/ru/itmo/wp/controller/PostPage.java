package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String posts(@PathVariable String id, Model model) {
        try {
            Long idVal = Long.parseLong(id);
            model.addAttribute("pagePost", postService.findById(idVal));
            model.addAttribute("commentForm", new Comment());
        } catch (NumberFormatException e) {
            // No operations.
        }
        return "PostPage";
    }

    @PostMapping("/{id}")
    public String addComment(@PathVariable String id,
                             @Valid @ModelAttribute("commentForm") Comment comment,
                             BindingResult bindingResult,
                             HttpSession httpSession,
                             Model model) {
        try {
            Long idVal = Long.parseLong(id);
            Post post = postService.findById(idVal);
            model.addAttribute("pagePost", post);
            if (bindingResult.hasErrors()) {
                return "PostPage";
            }
            User user = getUser(httpSession);
            postService.writeComment(user, comment, post);
            putMessage(httpSession, "Successfully left comment!");
        } catch (NumberFormatException e) {
            // No operations.
        }
        return String.format("redirect:/post/%s", id);
    }

    @GetMapping(value={"/", ""})
    public String noPost(Model model) {
        model.addAttribute("commentForm", new Comment());
        return "PostPage";
    }
}
