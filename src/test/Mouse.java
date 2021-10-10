package test;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
@MyAnotation("测试注解")
public class Mouse {
    public Mouse(){}
    private Mouse(String name) {
        this.name = name;
    }
    // 私有变量
    private String password = "123456";
    @MyFiledAnotation("字段注解测试")
    public String name = "小小太阳";

    //
    public void say(String msg){
        System.out.println(msg+"叨逼叨叨逼叨...");
    }

    //
    public void run(){
        System.out.println("奔跑吧,向着太阳奔跑...");
    }

    @MyMethodAnotation("方法注解测试")
    private void takeAShower(){
        System.out.println("脱光光，洗白白...");
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


