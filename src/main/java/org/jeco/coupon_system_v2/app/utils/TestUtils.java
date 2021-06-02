package org.jeco.coupon_system_v2.app.utils;

public class TestUtils {
    private static int count = 1;

    /**
     * primary service heading
     * @param content
     */
    public static void testPrimaryInfo(String content) {
       // System.out.println("---------------------------------------------");
        System.out.println(String.format("\r\n\r\n*********    Test  %s    ***********    ", content));
        //System.out.println("---------------------------------------------");

    }

    /**
     * secondary service heading
     * @param content
     */
    public static void testSecondaryInfo(String content) {
       // System.out.println("---------------------------------------------");
        System.out.println(String.format("\r\n                 Test #%d : %s              ", count++,content));
        System.out.println("---------------------------------------------");

    }

    /**
     * print end of test
     */
    public static void testSecondaryEnd() {
        System.out.println("\r\n-----------------end test--------------------");


    }

}





