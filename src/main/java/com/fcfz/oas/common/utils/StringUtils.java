package com.fcfz.oas.common.utils;


import com.fcfz.oas.common.ErrInfo;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
public class StringUtils {

    private static String Uuid;

    private static UUID u = UUID.randomUUID();

    public static String getUuid() {

        String uid = u.toString();

        return uid;
    }

    public static String halfLast(String Uuid) {
        String uid = Uuid.toString();


        String[] splitList = uid.split("-", 3);


        String halfLast = splitList[2];

        return halfLast;
    }

    public static boolean isUkey(String uuid, String halfLast) {
        if (isEmpty(uuid) || isEmpty(halfLast)) {
            throw new ErrInfo("c_0002_uuid", "uuid为空");
        }

        if (!halfLast(uuid).equals(halfLast)) {
            throw new ErrInfo("c_0001_uuid", "随机UKey不正确");
        }


        String[] splitList = uuid.split("-", 3);

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder halfFirst = stringBuilder
                .append(splitList[0])
                .append("-")
                .append(splitList[1]).append("-");

        String appendResult = halfFirst.append(splitList[2]).toString();

        boolean result = false;
        if (appendResult.equals(uuid)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }


    /**
     * 判断一个字符串是否为null
     * 默认是空
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);//== 是地址   equal是值
    }

//    public static boolean isEmpty(Object obj) {
//        Object a = null;
//        return obj == null || a.equals(obj);
//    }

    /**
     * yyyy-MM-dd HH:mm:ss"
     * 日期时间字符串 转date
     */
    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = simpleDateFormat.parse(str);
        return parse;
    }

    /**
     * yyyy年MM月dd日 HH:mm:ss
     */
    public static Date strToDateCN(String str) throws ParseException {
        SimpleDateFormat smpt =
                new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        Date parse = smpt.parse(str);
        return parse;
    }

    /**
     * yyyy-MM-dd HH:mm:ss"
     * 两个时间字符串的差值
     * t2-t1
     * 以分钟的long形式返回
     */
    public static long differenceInMinutes(String t1, String t2) throws ParseException {

        Date firstDate = StringUtils.strToDate(t1);
        Date secondDate = StringUtils.strToDate(t2);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(firstDate);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(secondDate);

        long differenceInMillis = c2.getTimeInMillis() - c1.getTimeInMillis();

        // 将差值转换为所需的时间单位
        long differenceInSeconds = differenceInMillis / 1000; //秒
        long differenceInMinutes = differenceInSeconds / 60;  //分钟
//        long differenceInHours = differenceInMinutes / 60;
//        long differenceInDays = differenceInHours / 24;

        return differenceInMinutes;
    }
}
