//package com.ict;
//
//import com.ict.mapper.UserFavouriteMapper;
//import com.ict.mapper.VideoMapper;
//import com.ict.util.FileNameUtil;
//import com.ict.util.SecurityUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
////@SpringBootTest
//class ByteDanceYouthTrainingApplicationTests {
//
//    @Autowired
//    VideoMapper videoMapper;
//
//    @Autowired
//    private UserFavouriteMapper userFavouriteMapper;
//
//    @Test
//    void contextLoads() {
//        System.out.println(SecurityUtil.encryptPassword("123456"));
//    }
//
//
//    @Test
//    void test1() {
//        System.out.println(videoMapper.getFeedList(1));
//    }
//
//    @Test
//    void test2() {
//        List<Long> list = userFavouriteMapper.getFavouriteList(1L);
//        System.out.println(list);
//        System.out.println(videoMapper.getVideoInfoByVideoId(list));
//    }
//}
