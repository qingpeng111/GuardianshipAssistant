package com.pandas.guardianshipassistant.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PinyinUtilsTest {

    @Test
    public void getPinyin() {
        System.out.println(PinyinUtils.getPinyin("北京七运会"));
    }
}