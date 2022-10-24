package br.ufpb.dcx.dsc.todolist.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

    public static String getStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
