package com.zonghong.dict.utils;

/**
 * Created by heboot on 2018/2/7.
 */

import java.util.HashMap;
import java.util.Map;

public class SignUtils {


    public static Map<String, Object> getNormalParams() {
        Map<String, Object> params = new HashMap<>();
//        params.put(MKey.ORIGIN, "app_android");

//        if (!StringUtils.isEmpty(UserService.getInstance().getToken())) {
//            params.put(MKey.TOKEN, UserService.getInstance().getToken());
//        }
        return params;
    }

}
