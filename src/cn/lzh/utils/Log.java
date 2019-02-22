package cn.lzh.utils;

public final class Log {

    private Log(){
        throw new AssertionError("cannot instantiation");
    }

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

    public static void print(Object obj){
        System.out.print(obj);
    }

    public static void println(Object obj){
        System.out.println(obj);
    }

}
