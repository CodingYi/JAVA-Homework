package com.codingyi.part01_classLoaderSystem;

/**
 * @Title: Part01_ClassLoadingTime
 * @Description: 类加载时机
 * @Author: CodingYi
 * @Date: 2023/1/15 11:12
 * @Version V1.0
 */
public class Part01_ClassLoadingTime extends Clazz{

    static {
        System.out.println("我初始化了");
    }

    private static int age;
    public static void method () {

    }

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.codingyi.part01_classLoaderSystem.Part01_ClassLoadingTime");

        ClassLoader.getSystemClassLoader().loadClass("com.codingyi.part01_classLoaderSystem.Part01_ClassLoadingTime");

    }
}

class Clazz {
    static {
        System.out.println("我是父类，我初始化了");
    }
}