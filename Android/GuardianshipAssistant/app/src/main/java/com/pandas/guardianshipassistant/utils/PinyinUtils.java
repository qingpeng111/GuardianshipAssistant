package com.pandas.guardianshipassistant.utils;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

public class PinyinUtils {
    //汉字转换成拼音
    public static String getPinyin(String str){

        return PinyinHelper.convertToPinyinString(str,"", PinyinFormat.WITHOUT_TONE);
    }
}
