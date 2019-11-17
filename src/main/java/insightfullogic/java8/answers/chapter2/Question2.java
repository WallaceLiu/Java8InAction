package insightfullogic.java8.answers.chapter2;

import java.text.SimpleDateFormat;

public class Question2 {

     public final static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy") );

}
