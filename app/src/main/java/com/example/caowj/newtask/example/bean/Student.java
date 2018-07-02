package com.example.caowj.newtask.example.bean;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/4/27 10:48
 */

public class Student {

    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    /**********构造方法 begin**********/
    //（默认的构造方法）
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }


    //无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法
    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄：" + age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法
    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
    }

    public static void main(String[] args) {
        System.out.println("main方法执行了。。。");
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }

    /***************构造方法 emd***************/


    /**************成员方法 begin***************/
    public void show1(String s) {
        System.out.println("调用了：公有的，String参数的show1(): s = " + s);
    }

    protected void show2() {
        System.out.println("调用了：受保护的，无参的show2()");
    }

    void show3() {
        System.out.println("调用了：默认的，无参的show3()");
    }

    private String show4(int age) {
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd";
    }
    /**************成员方法 end***************/


}
