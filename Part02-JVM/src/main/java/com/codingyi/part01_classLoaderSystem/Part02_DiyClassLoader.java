package com.codingyi.part01_classLoaderSystem;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @Title: Part02_DiyClassLoader
 * @Description: 自定义类加载器
 * @Author: CodingYi
 * @Date: 2023/1/15 15:18
 * @Version V1.0
 */
public class Part02_DiyClassLoader extends ClassLoader {

    private String classpath;

    public Part02_DiyClassLoader(String classpath) {
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
