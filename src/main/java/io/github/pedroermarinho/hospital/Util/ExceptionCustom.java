package io.github.pedroermarinho.hospital.Util;

public class ExceptionCustom  extends Exception{
    public  ExceptionCustom(String msg){
        super(msg);
    }
    public  ExceptionCustom(String msg, Throwable cause){
        super(msg,cause);
    }
}
