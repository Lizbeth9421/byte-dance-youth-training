package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.domain.dto.CommentInfo;
import com.ict.domain.model.CommentBody;
import com.ict.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public AjaxResult takeComment(CommentBody commentBody) {
        CommentInfo commentInfo = commentService.comment(commentBody);
        return AjaxResult.success().put("comment", commentInfo);
    }

    @GetMapping("/list/")
    public AjaxResult getCommentsList(@RequestParam("token") String token, @RequestParam("video_id") String video_id) {
        List<CommentInfo> list = commentService.getCommentsList(Long.valueOf(video_id));
        return AjaxResult.success("获取成功").put("comment_list", list);
    }
}
