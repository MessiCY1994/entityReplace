package com.messiyang.modelreplace.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EntityAnnotationUtil {
    /**
     *批量获取注解值
     * @param o
     * @return
     */
    public static List<EntityCommentModel> getAnnotationValue(Object o){
        try {
            List<EntityCommentModel> result = new ArrayList<>();
            Class<?> entityClass = o.getClass();
            Field[] fields = entityClass.getDeclaredFields();  //获取字段
            for (int i = 0; i < fields.length; i++) {
                boolean fieldHasAnno = fields[i].isAnnotationPresent(EntityComment.class);   //如果指定类型的注释存在于此元素上,否则返回false。
                if (fieldHasAnno) {
                    EntityComment entityComment = fields[i].getAnnotation(EntityComment.class);//返回该元素的指定类型的注释
                    EntityCommentModel entityCommentModel = new EntityCommentModel();
                    entityCommentModel.setComment(entityComment.value());
                    String fieldName = fields[i].getName();
                    String methodName = (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0))).append(fieldName.substring(1)).toString();
                    Method m = (Method) entityClass.getMethod("get" + methodName);
                    try {
                        Object value = m.invoke(o);
                        entityCommentModel.setValue(value);
                    }catch (Exception e){
                        entityCommentModel.setValue("");
                    }
                    result.add(entityCommentModel);
                }
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }


    /**
     *利用反射调用实体类set 的方法
     * @param o
     * @param entityCommentModels
     * @return
     */
    public static Object setObjectValue(Object o,List<EntityCommentModel> entityCommentModels){
        for(EntityCommentModel entityCommentModel : entityCommentModels){
            try {
                PropertyDescriptor pd = new PropertyDescriptor(entityCommentModel.getComment(), o.getClass());
                Method setMethod = pd.getWriteMethod();
                if(setMethod!= null){
                    setMethod.invoke(o, entityCommentModel.getValue());
                }
            }catch (Exception e){
                continue;
            }
        }
        return o;
    }
}
