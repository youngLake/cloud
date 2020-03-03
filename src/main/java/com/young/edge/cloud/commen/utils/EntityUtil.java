package com.young.edge.cloud.commen.utils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tornado Young
 * @date time 2020/3/3 21:15
 */
public class EntityUtil {
    /**
     * 把就对象的值赋值给新对象的空属性
     * @param present 新的
     * @param previous 旧的
     * @param fields 不管新对象的值属性是否为空，这些字段都按照旧对象的属性值赋值
     * @return
     */
    public static void setOldValueForNullField(Object present,Object previous,String... fields){
        List<String> keepPreviousValue = Arrays.stream(fields).collect(Collectors.toList());
        Arrays.stream(present.getClass().getDeclaredFields())
                .forEach(field -> {
                    try {
                        PropertyDescriptor presentDesc=new PropertyDescriptor(field.getName(),present.getClass());
                        Object presentValue = presentDesc.getReadMethod().invoke(present);
                        if (keepPreviousValue.contains(field.getName()) || presentValue==null){
                            PropertyDescriptor previousDesc=new PropertyDescriptor(field.getName(),previous.getClass());
                            presentDesc.getWriteMethod().invoke(present,previousDesc.getReadMethod().invoke(previous));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
    }
}
