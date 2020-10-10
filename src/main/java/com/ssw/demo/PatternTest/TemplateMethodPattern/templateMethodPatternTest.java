package com.ssw.demo.PatternTest.TemplateMethodPattern;

import org.junit.Test;

/** 模板方法模式练习
 * 解释：
 * @author wss
 * @created 2020/9/8 10:18
 * @since 1.0
 */
public class templateMethodPatternTest {
    /**
     * 做饭抽象类
     */
    public abstract class Cook {

        abstract void addOil();

        abstract void addEgg();

        abstract void addTomato();

        // 钩子函数，让子类确认是否加油
        boolean isAddOil(){
            return true;
        }

        final void cook() {

            if (this.isAddOil()) {
                this.addOil();
            }
            this.addEgg();
            this.addTomato();
        }
    }

    /**
     * 普通人做饭
     */
    class MyCook extends Cook{

        private boolean addOilFlag = true;

        public void setAddOilFlag(boolean addOilFlag) {
            this.addOilFlag = addOilFlag;
        }

        @Override
        void addOil() {
            System.out.println("加一大勺油");
        }

        @Override
        void addEgg() {
            System.out.println("加10个鸡蛋");
        }

        @Override
        void addTomato() {
            System.out.println("加1个番茄");
        }

        @Override
        boolean isAddOil() {
            return this.addOilFlag;
        }
    }

    /**
     * 大厨做饭
     */
    class OtherCook extends Cook {

        private boolean addOilFlag = true;

        public void setAddOilFlag(boolean addOilFlag) {
            this.addOilFlag = addOilFlag;
        }

        @Override
        boolean isAddOil() {
            return this.addOilFlag;
        }

        @Override
        void addOil() {
            System.out.println("加适量油");
        }

        @Override
        void addEgg() {
            System.out.println("加适量鸡蛋");
        }

        @Override
        void addTomato() {
            System.out.println("加适量番茄");
        }
    }

    @Test
    public void t() {
        MyCook myCook = new MyCook();
        System.out.println("我做饭......");
        myCook.setAddOilFlag(false);  // 不放油
        myCook.cook();
        OtherCook otherCook = new OtherCook();
        System.out.println("大厨做饭......");
        otherCook.cook();
    }
}
