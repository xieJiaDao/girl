package com.xiejiadao.girl.StringTest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试对象传递
 *
 * 观察测试结果发现： people 作为参数传递到方法内的是引用的拷贝copyPeople。
 *
 * 当我们使用copyPeople.set方法的时候，修改字段值其实就是修改原始对象的字段；
 *
 * 当我们把copyPeople指向另一个People对象的时候，原始people 指向的仍然是原始的People对象
 *
 */
@Slf4j
public class TestObjectDelivery {
    public static void main(String[] args) {
        People people = new People();
        people.setName("xyf");
        people.setAge(18);
        //第一次new people 的参数：TestObjectDelivery.People(name=xyf, age=18)
        log.info("第一次new people 的参数：{}", people);
        modifyName(people);
        //第二次modifyName people 的参数：TestObjectDelivery.People(name=xyl, age=18)
        log.info("第二次modifyName people 的参数：{}", people);
        assignmentNewPeople(people);
        //第二次assignmentNewPeople people 的参数：TestObjectDelivery.People(name=xyl, age=18)
        log.info("第二次assignmentNewPeople people 的参数：{}", people);
    }

    @Data
    public static class People {
        String name;
        int age;
    }

    /**
     * 修改copyPeople的字段，实际上就是修改people的字段，因为people 和 copyPeople 指向同一个对象
     * @param copyPeople
     */
    public static void modifyName(People copyPeople) {
        copyPeople.setName("xyl");
    }

    /**
     * copyPeople 重新指向一个新的People对象。但是people 仍然指向新的对象
     * @param copyPeople
     */
    public static void assignmentNewPeople(People copyPeople) {
        copyPeople = new People();
    }







}
