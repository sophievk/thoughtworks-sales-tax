import java.util.*;
import java.io.*;

public class practice{
    public static void main(String[] args){

        Hashtable<String, Integer> h = new Hashtable();
        h.put("chocolate", 1);
        h.put("car", 1);
        h.put("book", 1);
        Enumeration e = h.elements();
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
    }
}
