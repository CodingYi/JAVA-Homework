package com.codingyi;

public class JolTest {

    public static void main(String[] args) {

//        System.out.println(ByteOrder.nativeOrder());

//        System.out.println("vm: " + VM.current().details());

//        Part0201_Test part0201_test = new Part0201_Test();
//        System.out.println("part0201_test_parseInstance: " + ClassLayout.parseInstance(part0201_test).toPrintable());

//        System.out.println("String_parseClass: " + ClassLayout.parseClass(String.class).toPrintable());
//        String str = "Instance";
//        System.out.println("String_parseInstance: " + ClassLayout.parseInstance(str).toPrintable());
//        synchronized (str) {
//            System.out.println("String_parseInstance: " + ClassLayout.parseInstance(str).toPrintable());
//        }

//        System.out.println(ClassLayout.parseClass(Long.class).toPrintable());
//        System.out.println(ClassLayout.parseInstance(12345L).toPrintable());

//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("name", "codingyi");
//        hashMap.put("name1", "codingyi");
//        hashMap.put("name2", "codingyi");
//        System.out.println(ClassLayout.parseClass(hashMap.getClass()).toPrintable());
//        System.out.println(GraphLayout.parseInstance(hashMap).toPrintable());
    }

}

/**
 String: java.lang.String object internals:
 OFF  SZ     TYPE DESCRIPTION               VALUE
   0   8          (object header: mark)     N/A
   8   4          (object header: class)    N/A
  12   4   char[] String.value              N/A
  16   4      int String.hash               N/A
  20   4          (object alignment gap)
 Instance size: 24 bytes
 Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 */