package com.atguigu.juc;
//函数式接口有且只能有一个基本方法
@FunctionalInterface
interface Foo{
    public  void say();
    //默认方法必须有方法体{}
    default  int sa(int a,int b){
        return  a+b;
    };
    //静态方法必须有方法体{}
    static  void sa(){};
}
public class lamdException {
    public static void main(String[] args) {
       Foo foo=()->{System.out.println("hello ");};
         foo.say();
         foo.sa(1,2);
         //静态方法由类调取
         Foo.sa();
    }
}
