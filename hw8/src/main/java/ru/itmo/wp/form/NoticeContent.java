package ru.itmo.wp.form;

import javax.validation.constraints.*;

@SuppressWarnings("unused")
public class NoticeContent {
    @NotNull
    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
