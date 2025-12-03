package com.itzpy.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyTools {
    /**
     * 将源对象列表复制到目标对象列表
     * @param sList 源对象列表
     * @param classz 目标对象的类类型
     * @return 复制后的目标对象列表
     */
    public static <T, S> List< T> copyList(List<S> sList, Class<T> classz) {
        List<T> list = new ArrayList<>();
        T t = null;

        for(S s: sList) {
            try {
                t = classz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            BeanUtils.copyProperties(s, t);
            list.add( t);
        }

        return list;
    }


    /**
     * 将单个源对象复制到目标对象
     * @param s 源对象
     * @param classz 目标对象的类类型
     * @return 复制后的目标对象
     */
    public static <T, S> T copy(S s, Class<T> classz) {
        T t = null;
        try {
            t = classz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(s, t);
        return t;
    }
}