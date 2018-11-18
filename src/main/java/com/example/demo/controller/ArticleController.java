package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.model.Response;
import com.example.demo.service.ArticleService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Controller 一般用来映射UI接口 业务逻辑，也就是 在这写接口，写功能函数
 *
 * @RestController这是注解，注解生成的同时，它所定义的包则会被IDE引进
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    void printFunction() {
        System.out.println("you are foolish");

    }

    String printWeb() {
        return "print in web";
    }

    //这样就可以通过链接article/echo调用到下面这个函数，如执行
    @RequestMapping("/echo")
    public String woecho() {
        printFunction();
        return printWeb();
    }


    @PostMapping("/add")
        //增加文章
    Response add_article(Article article) {

        return Response.success();
    }


    @PutMapping("/modify")
    //修改文章
    public Response<Article> modifyArticle(@RequestBody Article article) {
        try {
            articleService.updateArticle(article);
            //返回空
            return Response.success();
        } catch (SQLException e) {
            return Response.failed(String.valueOf(e.getErrorCode()), e.getMessage());
        }
    }


    @GetMapping("/query")
    //查询文章
    public Response<List<Article>> query(@RequestParam(required = false) String query) {

        try {
            List<Article> articles = articleService.queryByTitle(query);
            //把返回的一系列Article list 传给success函数，success 函数会把一系列Article list 封装成response 传给前端，
            //前端就会在URL中显示出这个函数调用及其参数情况，并在浏览器中打出json格式
            return Response.success(articles);
        } catch (SQLException e) {
            return Response.failed(String.valueOf(e.getErrorCode()), e.getMessage());
        }
    }


    @GetMapping("/byId")
    //通过id查询文章
    public Response<Article> queryById(Long id) {
        try {
            return Response.success(articleService.queryById(id));
        } catch (SQLException e) {
            return Response.failed(String.valueOf(e.getErrorCode()), e.getMessage());
        }
    }

    @GetMapping("/delete")
    //删除文章
    public Response delete(Long id) {
        try {
            articleService.deleteArticle(id);
            //返回空
            return Response.success();
        } catch (SQLException e) {
            return Response.failed(String.valueOf(e.getErrorCode()), e.getMessage());
        }

    }


}
