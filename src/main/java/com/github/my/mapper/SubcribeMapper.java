package com.github.my.mapper;

import com.github.my.domain.po.Subcribe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubcribeMapper {

    Subcribe selectByUserId(@Param("userId") Integer userId,
                            @Param("year") String year,
                            @Param("month") String month,
                            @Param("status") Integer status);

    List<Subcribe> selectByYearMonth(@Param("year") String year,
                               @Param("month") String month,
                               @Param("verifyCode") String verifyCode);

    int updateUsed(Long id);

    int selectUnique(Integer userId, String verifyCode);

    int update(Subcribe subcribe);

    int insert(Subcribe subcribe);

}