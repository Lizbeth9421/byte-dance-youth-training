package com.ict.mapper;

import com.ict.domain.entity.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);
}