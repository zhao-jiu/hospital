package com.zj.study;

import com.zj.hospital.util.MD5;



/**
 * @author ZhaoJiu
 * @CreateTime: 2021/7/14 10:42
 * @Description:
 */
public class Md5Test {
    public static void main(String[] args) {
        String encrypt = MD5.encrypt("674c4139707728439a6510eae12deea9");
        System.out.println("encrypt = " + encrypt);
    }
}
