package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Title can not be empty");
        }
        if (title.length() > 256) {
            throw new ValidationException("Title can not be longer than 256 symbols");
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Article can not be empty");
        }
    }

    public Article save(String title, String text, User user) {
        Article article = new Article();
        article.setTitle(title);
        article.setText(text);
        article.setUserId(user.getId());
        articleRepository.save(article);
        return article;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getUserArticles(User user) {
        return articleRepository.findByUserId(user.getId());
    }
}
