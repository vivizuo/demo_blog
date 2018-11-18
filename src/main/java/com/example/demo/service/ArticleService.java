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

    public Article queryById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * from article where id=?");
        ps.setLong(1,id);
        ResultSet rs = ps.executeQuery();
        //next函数触发游标读rs的数据
        rs.next();
        String title = rs.getString("title");
        String content = rs.getString("content");
        String author = rs.getString("author");
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        return article;
    }
    public void deleteArticle(long id) throws SQLException{
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE from article where id=?");
        ps.setLong(1,id);
        // executeUpdate(sql)的返回值是更新的条数
        int a = ps.executeUpdate();
        if (a == 0)
        {
            System.out.println("here has no data to delete");
        }
        else{
            System.out.println("you delete "+a+"article");
        }
    }

    public void updateArticle(Article article) throws SQLException{
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("update article set title=?,content=?,author=? where id=?");
        ps.setString(1,article.getTitle());
        ps.setString(2,article.getContent());
        ps.setString(3,article.getAuthor());
        ps.setLong(4,article.getId());
        int a = ps.executeUpdate();
        if (a == 0)
        {
            System.out.println("NO article was updated");
        }
        else{
            System.out.println("you updated "+a+"article");
        }

    }


}