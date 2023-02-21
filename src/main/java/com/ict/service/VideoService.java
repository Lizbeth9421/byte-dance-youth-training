package com.ict.service;

import com.ict.domain.entity.Video;
import com.ict.domain.model.VideoUploadBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
public interface VideoService {


    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    /**
     * 投稿接口
     *
     * @param uploadBody
     */
    void contribute(VideoUploadBody uploadBody);
}
