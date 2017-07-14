package com.iaid.webservice.general;

/**
 * Created by Crawlers on 4/26/2016.
 */
public class FirstAidChecker {
  /**
   * @param agrs Object values for null check
   * @return True if any object passed is null
   */
  public static boolean isNull(Object... agrs) {
    boolean result = false;
    for (Object str : agrs) {
      System.out.println(":" + str);
      if (str == null) {
        result = true;
        break;
      }
    }
    return result;
  }

  /**
   * @param args Object values for empty check
   * @return True if any object passed is empty
   */
  public static boolean isEmpty(String... args) {
    boolean result = false;
    for (Object str : args) {
      if (str == "") {
        result = true;
        break;
      }
    }
    return result;
  }



    /*public static boolean isListEmpty(ArrayList... args){
        boolean result = false;
        for (ArrayList str: args){
            if (str.isEmpty()){
                result = true;
                break;
            }
        }
        return result;
    }*/
}
