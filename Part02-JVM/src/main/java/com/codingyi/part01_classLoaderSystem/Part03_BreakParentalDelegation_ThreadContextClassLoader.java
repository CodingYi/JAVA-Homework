package com.codingyi.part01_classLoaderSystem;

import sun.misc.Launcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Title: Part03_BreakParentalDelegation_ThreadContextClassLoader
 * @Description: 打破双亲委派机制之引入线程上下文类加载器
 * @Author: CodingYi
 * @Date: 2023/1/15 19:57
 * @Version V1.0
 */
public class Part03_BreakParentalDelegation_ThreadContextClassLoader {

    public static void main(String[] args) throws SQLException {
        // 可以观察 ThreadContextClassLoader 何时设置
        // Launcher launcher = new Launcher();
        // ClassLoader classLoader = launcher.getClassLoader();
        // System.out.println("launcher.getClassLoader: " + classLoader);

        // 可以观察 spi 机制
        // Connection conn = DriverManager.getConnection("");

        // 获取 TCCL（线程上下文类加载器）
        // TCCL 默认是 AppClassLoader
        /**
         * try {
         *     this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
         * } catch (IOException var9) {
         *     throw new InternalError("Could not create application class loader", var9);
         * }
         * Thread.currentThread().setContextClassLoader(this.loader);
         */
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("ThreadContextClassLoader: " + contextClassLoader);

    }

}
