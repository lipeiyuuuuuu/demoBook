package org.example;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ${NAME}
 * Description: ${Description}
 * date:  ${YEAR}-${MONTH}-${DAY}  ${HOUR}:${MINUTE}
 *
 * @author lipeiyu
 * @version 1.0
 */
public class Main {
    private String name ="main method";

    @Override
    public String toString() {
        return "Main{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


    @Test
    public void test1(){
        add(1,2);
        add((short)1,(short)2);
        add(1L,2L);
    }

    public <T extends Number> double add(T a, T b){
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }


    class Point<T>{
        private T var;

        public T getVar() {
            return var;
        }

        public void setVar(T var) {
            this.var = var;
        }
    }

    @Test
    public void test2(){
        Point<Integer> point = new Point<>();
        point.setVar(1);
        System.out.println(point.getVar());
    }


    class Notepad<K,V>{
        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Notepad{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Test
    public void test3(){
        Notepad<String, Integer> notepad = new Notepad<>();
        notepad.setKey("key");
        notepad.setValue(10);
        System.out.println(notepad.toString());
    }

    /*泛型接口*/
    interface Info<T>{
        public T getVar();
    }

    class InfoImpl<T> implements  Info<T>{
        private T var;

        public InfoImpl(T var) {
            this.var = var;
        }

        public void setVar(T var) {
            this.var = var;
        }

        @Override
        public T getVar() {
            return var;
        }
    }

    @Test
    public void test4(){
        Info<String> info = null;
        info = new InfoImpl<>("Tom");
        System.out.println(info.getVar());
    }

    /*泛型方法*/
    /*
    * 在返回值类型前 加一个<T> 声明这是一个泛型方法
    *   持有一个泛型T，然后才可以用泛型T作为方法的返回值。
    *   为什么要用Class<T> c 呢?
    *       为什么要用变量c来创建对象呢？既然是泛型方法，就代表着我们不知道具体的类型是什么，也不知道构造方法如何，因此没有办法去new一个对象，但可以利用变量c的newInstance方法去创建对象，也就是利用反射创建对象。
    *
    * */
    public <T>T genericMethod1(Class<T> c) throws InstantiationException, IllegalAccessException {
        T t = c.newInstance();
        return t;
    }

    @Test
    public void test5(){
        try {
            Main main = genericMethod1(Main.class);
            System.out.println(main.toString());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static  void funA(A a){

    }

    public static  void fun(B b){
        funA(b);
    }

    // 如下funD方法会报错
    public static void funC(List<? extends A> listA) {
        // ...
    }
    public static void funD(List<B> listB) {
        /*func的参数为List<A> , 对应的类型只可以是A*/
        /*func的参数为List<? extends A> , 编译器知道类型参数的范围，如果传入的实例类型B是在这个范围内的话允许转换，这时只要一次类型转换就可以了，运行时会把对象当做A的实例看待*/
        funC(listB); // Unresolved compilation problem: The method doPrint(List<A>) in the type test is not applicable for the arguments (List<B>)
        // ...
    }

    class C<E> extends  ArrayList<E>{
        @Override
        public boolean add(E e) {
            return super.add(e);
        }
    }

    @Test
    public void test6() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Number> list = new ArrayList<>();

        list.add(new Integer(1));  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
//        list.add("123");
        /*在程序中定义了一个ArrayList泛型类型实例化为Integer对象，如果直接调用add()方法，那么只能存储整数数据，不过当我们利用反射调用add()方法的时候，却可以存储字符串，这说明了Integer泛型实例在编译之后被擦除掉了，只保留了原始类型。*/
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    class Info2<T extends Number>{
        private T var;

        public T getVar() {
            return var;
        }

        public void setVar(T var) {
            this.var = var;
        }

        @Override
        public String toString() {
            return "info{" +
                    "var=" + var +
                    '}';
        }
    }

    @Test
    public  void test7(){
        Info2<Integer> integerInfo = new Info2<>();
    }

    class Info3<T>{
        private T var;

        public T getVar() {
            return var;
        }

        public void setVar(T var) {
            this.var = var;
        }
    }

    public void GenericsDemoFun(Info3<? super String> info){
        System.out.println(info);
    }

    @Test
    public void test8(){
        Info3<String> stringInfo3 = new Info3<>();
        Info3<Object> objectInfo3 = new Info3<>();
        stringInfo3.setVar("123");
        objectInfo3.setVar(new Object());
        GenericsDemoFun(stringInfo3);
        GenericsDemoFun(objectInfo3);
    }



    class Fruit {}
    class Apple extends Fruit {}
    class Orange extends Fruit {}

    @Test
    public void Test9(){
        List<? super  Fruit> fruitList = new ArrayList<Fruit>();
        /**/
        fruitList.add(new Fruit());


        ArrayList<String> list1 = new ArrayList(); //第一种 情况
        ArrayList list2 = new ArrayList<String>(); //第二种 情况
        list2.add(1);
    }

    class ArrayWithTypeToken<T>{
        private T[] array;

        public ArrayWithTypeToken(Class<T> type,int size) {
            this.array = (T[])Array.newInstance(type,size);
        }

        public T[] getArray() {
            return array;
        }

        public void setArray(T[] array) {
            this.array = array;
        }
    }

    /*泛型数组初始化*/
    @Test
    public void test(){
        ArrayWithTypeToken<Integer> array = new ArrayWithTypeToken<>(Integer.class,100);
        Integer[] integers = array.getArray();



    }


}



/*泛型, 上下线*/
class A{
    public <T> void fun1(){}
}
class B extends A{
    @Override
    public <T> void fun1() {
        super.fun1();
    }
};

interface GenericeInterface<T>{
    T getT();
}

class GenericeInterfaceImpl<T> implements  GenericeInterface<T>{
    @Override
    public T getT() {
        return null;
    }
}

class GenericeInterfaceImpl2 implements  GenericeInterface<String>{
    @Override
    public String  getT() {
        return null;
    }
}




