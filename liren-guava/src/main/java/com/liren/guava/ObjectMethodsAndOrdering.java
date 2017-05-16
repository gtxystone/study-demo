package com.liren.guava;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class ObjectMethodsAndOrdering {  
  
    /** 
     * 当一个对象中的字段可以为null时，实现Object.equals方法会很痛苦，因为不得不分别对它们进行null检查。 
     * 使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException。 
     * <p> 
     * 注意：JDK7引入的Objects类提供了一样的方法Objects.equals。 
     */  
    private static void objectsEqual() {  
        Objects.equal("a", "a"); // returns true  
        Objects.equal(null, "a"); // returns false  
        Objects.equal("a", null); // returns false  
        Objects.equal(null, null); // returns true  
  
        Objects.hashCode("a", "b");//  
    }  
  
    /** 
     * 用对象的所有字段作散列[hash]运算应当更简单。 
     * Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。 
     * 你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。 
     * <p> 
     * 注意：JDK7引入的Objects类提供了一样的方法Objects.hash(Object...) 
     */  
    private static void objectsHashCode() {  
        Objects.equal("a", "a"); // returns true  
        Objects.equal(null, "a"); // returns false  
        Objects.equal("a", null); // returns false  
        Objects.equal(null, null); // returns true  
  
        Objects.hashCode("a", "b");//  
    }  
  
  
    /** 
     * 实现一个比较器[Comparator] 
     * JDK 与 Guava的实现的不同 
     */  
    private class Person2compareToTest implements Comparable<Person2compareToTest> {  
        private String lastName;  
        private String firstName;  
        private int zipCode;  
  
        /** 
         * jdk 方式的比较 
         * 
         * @param other 
         * @return 
         */  
        @Override  
        public int compareTo(Person2compareToTest other) {  
            int cmp = lastName.compareTo(other.lastName);  
            if (cmp != 0) {  
                return cmp;  
            }  
            cmp = firstName.compareTo(other.firstName);  
            if (cmp != 0) {  
                return cmp;  
            }  
            return Integer.compare(zipCode, other.zipCode);  
        }  
  
        /** 
         * Guava的实现 
         * <p> 
         * ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。 
         * 
         * @param that 比较对象 
         * @return 比较结果 
         * @see #compareTo(Person2compareToTest) 
         */  
        public int compareToByComparisonChain(Person2compareToTest that) {  
            return ComparisonChain.start()  
                    .compare(this.lastName, that.lastName)  
                    .compare(this.firstName, that.firstName)  
                    .compare(this.zipCode, that.zipCode, Ordering.natural().nullsLast())  
                    .result();  
        }  
    }  
  
  
    /** 
     * Ordering犀利的比较器演示 
     * <p> 
     * jdk8也实现了部分 
     * 
     * @see java.util.Comparators 
     */  
    private static class OrderTest implements Comparable<OrderTest> {  
  
        private int id;  
        private String name;  
  
        public static final OrderTest[] ORDER_TESTS = new OrderTest[]{  
                new OrderTest(1, "yiwa")  
                , new OrderTest(11, "yiwa")  
                , new OrderTest(2, "erwa")  
                , new OrderTest(3, "sanwa")  
                , new OrderTest(4, "siwa")  
                , new OrderTest(5, "wuwa")  
                , new OrderTest(5, "wuwa")  
                , new OrderTest(6, null)  
        };  
  
        OrderTest(int id, String name) {  
            this.id = id;  
            this.name = name;  
        }  
  
        @Override  
        public int compareTo(OrderTest that) {  
            return ComparisonChain.start()  
                    // .compare(this.id, that.id)  
                    .compare(this, that, getOrdering())  
                    .result()  
                    ;  
        }  
  
        /** 
         * 构建Ordering 
         */  
        private static Ordering<OrderTest> getOrdering() {  
  
            return Ordering  
                    .natural()  
                    .nullsFirst()  
                            //返回按String 排序的Comparable  
                            //当阅读链式调用产生的排序器时，应该从后往前读。  
                            // 下面的例子中，排序器首先调用apply方法获取name值，并把name为null的元素都放到最前面，然后把剩下的元素按name进行自然排序。  
                            // 之所以要从后往前读，是因为每次链式调用都是用后面的方法包装了前面的排序器。  
                    .onResultOf(new Function<OrderTest, Comparable>() {  
                        @Override  
                        public Comparable apply(OrderTest input) {  
                            return input.name;  
                        }  
                    })  
                    // .leastOf(Arrays.asList(ORDER_TESTS),2)  
                    ;  
        }  
  
  
        /** 
         * 运用选择器进行一些计算 
         */  
        private static void appleOrdering() {  
            Ordering ordering = getOrdering();  
            List<OrderTest> orderTestList  
                    = Lists.newArrayList(ORDER_TESTS);  
  
            //判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。  
            boolean orderingOrdered = ordering.isOrdered(orderTestList);  
//            LOGGER.info("isOrdered          : <{}>", orderingOrdered);  
  
            //判断可迭代对象是否已按排序器严格排序：不允许有排序值相等的元素。  
            boolean orderingStrictlyOrdered = ordering.isStrictlyOrdered(orderTestList);  
//            LOGGER.info("isStrictlyOrdered  : <{}>", orderingStrictlyOrdered);  
  
            //返回指定的元素作为一个列表的排序副本。immutableSortedCopy返回不可变的排序副本  
            // LOGGER.info("sortedCopy  : <{}>", ordering.sortedCopy(orderTestList));  
  
            //最大的2元素  
//            LOGGER.info("greatestOf 2 index : <{}>", ordering.greatestOf(orderTestList, 4));  
  
            //获取最大值，迭代比较大小后返回  
//            LOGGER.info("orderTestList max  : <{}>", ordering.max(orderTestList));  
        }  
  
        @Override  
        public String toString() {  
            return "OrderTest{" +  
                    "id=" + id +  
                    ", name='" + name + '\'' +  
                    '}';  
        }  
  
    }  
  
//    private static final org.slf4j.Logger LOGGER  = LoggerFactory.getLogger(Optional_.class);  
  
    public static void main(String[] args) {  
  
        Arrays.sort(OrderTest.ORDER_TESTS);  
//        LOGGER.info("---------- ORDER_TESTS after sort start ----------");  
        for (OrderTest orderTest : OrderTest.ORDER_TESTS) {  
//            LOGGER.info("<{}>", orderTest);  
        }  
//        LOGGER.info("---------- ORDER_TESTS after sort end   ----------");  
  
//        LOGGER.info("\n---------- Apple Ordering start ----------");  
        OrderTest.appleOrdering();  
//        LOGGER.info("---------- Apple Ordering end   ----------");  
  
        /* main 函数执行结果 
        ---------- ORDER_TESTS after sort start ---------- 
<OrderTest{id=6, name='null'}> 
<OrderTest{id=2, name='erwa'}> 
<OrderTest{id=3, name='sanwa'}> 
<OrderTest{id=4, name='siwa'}> 
<OrderTest{id=5, name='wuwa'}> 
<OrderTest{id=5, name='wuwa'}> 
<OrderTest{id=1, name='yiwa'}> 
<OrderTest{id=11, name='yiwa'}> 
---------- ORDER_TESTS after sort end   ---------- 
 
---------- Apple Ordering start ---------- 
isOrdered          : <true> 
isStrictlyOrdered  : <false> 
greatestOf 2 index : <[OrderTest{id=1, name='yiwa'}, OrderTest{id=11, name='yiwa'}, 
                      OrderTest{id=5, name='wuwa'}, OrderTest{id=5, name='wuwa'}]> 
orderTestList max  : <OrderTest{id=1, name='yiwa'}> 
---------- Apple Ordering end   ---------- 
 
         */  
    }  
}  