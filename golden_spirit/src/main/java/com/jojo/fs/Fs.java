package com.jojo.fs;

import java.lang.reflect.*;

public class Fs {

    private int age;

    private String name;

    private int testint;

    public Fs(int age) {
        this.age = age;
    }

    public Fs(int age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("hello everyone, my name is " + name + ", i am " + age);
    }

    private Fs(String name) {
        this.name = name;
        System.out.println("My name is " + name);
    }

    public Fs() {
    }

    private void welcom(String tips) {
        System.out.println(tips);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // Fs fs = new Fs();
        // Class cl = fs.getClass();
        Class cl = Class.forName("com.jojo.fs.Fs");
        Constructor[] constructors;
        constructors = cl.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.print(Modifier.toString(constructors[i].getModifiers()) + "参数:");
            Class[] parametertypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.print(parametertypes[j].getName() + " ");
            }
            System.out.println("");
        }

        // 获取无参构造函数
        Constructor constructor;
        try {
            constructor = cl.getDeclaredConstructor();
            System.out.println(Modifier.toString(constructor.getModifiers()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 获取指定参数的构造函数
        Constructor constructorWithP;
        Class[] p = {int.class, String.class};
        try {
            constructorWithP = cl.getDeclaredConstructor(p);
            // 使用反射进行实例化
            try {
                constructorWithP.newInstance(24, "ljx");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.print(Modifier.toString(constructorWithP.getModifiers()) + "参数：");
            Class[] parametertypes = constructorWithP.getParameterTypes();
            for (int i = 0; i < parametertypes.length; i ++) {
                System.out.print(parametertypes[i].getName() + " ");
            }
            System.out.println("");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 获取并实例化含参的私有构造函数
        Constructor constructorP;
        Class[] p2 = {String.class};
        try {
            constructorP = cl.getDeclaredConstructor(p2);
            constructorP.setAccessible(true);
            try {
                constructorP.newInstance("ljx");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Class[] p3 = {String.class};
        try {

            Constructor constructorNew = cl.getDeclaredConstructor();
            Fs fs = (Fs) constructorNew.newInstance();
            Field  field = cl.getDeclaredField("name");
            field.setAccessible(true);
            field.set(fs, "你xx");
            System.out.println(field.get(fs).toString());
            Method method = cl.getDeclaredMethod("welcom", p3);
            method.setAccessible(true);
            Object args1[] = {"xx是xx"};
            method.invoke(fs, args1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
