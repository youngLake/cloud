package com.young.edge.cloud.commen.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author Tornodo Young
 * @date 2020/3/3 10:35
 */
public class ExampleUtil {
    public static <T> T setSpecificField(Class<T> clazz,String field,Object value){
        try {
            PropertyDescriptor descriptor=new PropertyDescriptor(field,clazz);
            Method writeMethod = descriptor.getWriteMethod();
            T t = clazz.newInstance();
            writeMethod.invoke(t,value);
            return t;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
