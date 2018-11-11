package com.example.demo.service;

import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service 实现接口的业务逻辑
 */
@Service
public class ArticleService {

    @Autowired
    private DataSource dataSource;

    public List<Article> queryByTitle(String query) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from article where title like ?");
        ps.setString(1, "%" + query + "%");
        ResultSet rs = ps.executeQuery();
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            String title = rs.getString("title");
            String content = rs.getString("content");
            String author = rs.getString("author");
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);
            article.setAuthor(author);
            articles.add(article);
        }
        return articles;
    }

    public Article queryById(Long id) {
        return new Article();
    }
}