package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.domain.dto.CommentInfo;
import com.ict.domain.model.CommentBody;
import com.ict.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/12:04
 */
@RestController
@RequestMapping("/douyin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/action/")
    public AjaxResult takeComment(CommentBody commentBody){
        CommentInfo commentInfo = commentService.comment(commentBody);
        return AjaxResult.success().put("comment", commentInfo);
    }
}
