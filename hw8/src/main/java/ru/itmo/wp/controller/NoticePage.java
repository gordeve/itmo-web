package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeContent;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static java.util.Objects.isNull;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String noticeGet(Model model, HttpSession httpSession) {
        if (isNotLoggedIn(httpSession)) {
            return "redirect:/";
        }
        model.addAttribute("noticeForm", new NoticeContent());
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String noticePost(@Valid @ModelAttribute("noticeForm") NoticeContent noticeForm,
                             BindingResult bindingResult,
                             HttpSession httpSession) {
        if (isNotLoggedIn(httpSession)) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        noticeService.addNotice(noticeForm.getContent());

        setMessage(httpSession, "New notice created!");
        return "redirect:/notice";
    }
}