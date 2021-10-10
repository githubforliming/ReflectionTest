import test.Mouse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        Class<?> c2 = Class.forName("test.Mouse");

        // 1. 动态创建对象
        // 1.1 直接调用newInstance
        Mouse o1 = (Mouse)c2.newInstance();
        System.out.println(o1);
        // 1.2 通过构造
        Mouse o2 = (Mouse)c2.getConstructor().newInstance();
        System.out.println(o2);

        // can not access a member of class test.Mouse with modifiers "private"
        // 应对策略：setAccessible(true)

        Constructor<?> dc = c2.getDeclaredConstructor(String.class);
        dc.setAccessible(true);
        Mouse o3 = (Mouse)dc.newInstance("构造反射创建对象name字段值");
        System.out.println(o3);

        // 2. 获取属性值/修改属性值
        // 获取password不能直接获取
        // System.out.println(o3.password);
        Field password = c2.getDeclaredField("password");
        // 普通属性不需要设置Accessible
        password.setAccessible(true);
        System.out.println("反射获取私有属性："+ password.get(o3));
        // 修改属性值
        password.setAccessible(true);
        password.set(o3, "----------通过反射修改咯--------");
        System.out.println("反射修改后的属性值："+ password.get(o3));

        // 3. 动态调用方法（这个非常重要了 动态代理就会用到）
        // 3.1 普通方法
        Method say = c2.getDeclaredMethod("say", String.class);
        say.invoke(o3, "说点什么呢:");
        // 3.2 私有方法
        Method takeAShower = c2.getDeclaredMethod("takeAShower");
        // 解决Class Test03 can not access a member of class test.Mouse with modifiers "private"
        takeAShower.setAccessible(true);
        takeAShower.invoke(o3);

    }
}
