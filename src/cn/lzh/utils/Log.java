package cn.lzh.utils;

public class Log {

    public static boolean isDebug;

    public static void debug(String format, Object... args){
        if(isDebug){
            if(args == null || args.length == 0){
                System.out.println(format);
            }else{
                System.out.printf(format, args);
            }
        }
    }

    public static void info(String format, Object... args){
        if(args == null || args.length == 0){
            System.out.println(format);
        }else{
            System.out.printf(format, args);
        }
    }

}
