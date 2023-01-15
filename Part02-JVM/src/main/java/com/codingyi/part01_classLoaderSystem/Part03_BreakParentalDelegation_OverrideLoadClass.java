package com.codingyi.part01_classLoaderSystem;

import sun.misc.Launcher;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @Title: Part03_BreakParentalDelegation
 * @Description: 打破双亲委派机制之重写loadClass
 * @Author: CodingYi
 * @Date: 2023/1/15 19:52
 * @Version V1.0
 */
public class Part03_BreakParentalDelegation_OverrideLoadClass extends ClassLoader{
    private String classpath;

    public Part03_BreakParentalDelegation_OverrideLoadClass(String classpath) {
        this.classpath = classpath;
    }

    public static void main(String[] args) throws Exception {

        // 自定义类加载器的加载路径
        Part02_DiyClassLoader diyClassLoader = new Part02_DiyClassLoader("/Users/codingyi/JAVA-Homework/Part02-JVM"); //包名+类名
        System.out.println(diyClassLoader.getClass().getClassLoader().toString());
        Class<?> c = diyClassLoader.loadClass("Part0201_Test");
        if (c != null) {
            Object obj = c.newInstance();
            Method method = c.getMethod("say", null);
            method.invoke(obj, null);
            System.out.println(c.getClassLoader().toString());
        }
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        try {
            // 输入流，通过类的全限定名称加载文件到字节数组
            byte[] classDate = this.getData(className);
            if (classDate != null) {
                // defineClass方法将字节数组数据 转为 字节码对象
                return defineClass(className, classDate, 0, classDate.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(className);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        // First, check if the class has already been loaded
        if (!"Part0201_Test".equals(name)) {
            return super.loadClass(name, resolve);
        }
        System.out.println("重写了 loadClass ");
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
//            try {
//                if (parent != null) {
//                    c = parent.loadClass(name, false);
//                } else {
//                    c = findBootstrapClassOrNull(name);
//                }
//            } catch (ClassNotFoundException e) {
//                // ClassNotFoundException thrown if class not found
//                // from the non-null parent class loader
//            }

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    private byte[] getData(String className) throws IOException {
        String path = this.classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";

        try (InputStream in = new FileInputStream(path);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[2048];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
