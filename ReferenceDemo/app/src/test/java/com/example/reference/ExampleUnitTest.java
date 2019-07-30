package com.example.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //测试虚引用
    @Test
    public void testPhantomReference() throws InterruptedException {
        //虚引用不会影响对象的生命周期但是能让程序员知道该对象生命时候被回收
        Object phantomObject = new Object();
        ReferenceQueue<Object> phantomReferenceQueue = new ReferenceQueue<Object>();
        PhantomReference phantomReference = new PhantomReference(phantomObject,phantomReferenceQueue);

        System.out.println("phantomReference : "+phantomReference.get());
        System.out.println("phantomReferenceQueue : "+phantomReferenceQueue.poll());

        phantomObject = null;
        System.gc();
        Thread.sleep(3000);

        System.out.println("phantomReference : "+phantomReference.get());
        System.out.println("phantomReferenceQueue : "+phantomReferenceQueue.poll());

        /**
         *   phantomReference : null
         *   phantomReferenceQueue : null
         *
         *   // 发生Gc以后 虚引用就会被添加到引用队列中
         *
         *  phantomReference : null
         *  phantomReferenceQueue : java.lang.ref.PhantomReference@6ae40994
         */

    }

    @Test
    public void testWeakReference() throws InterruptedException {
        Object weakObject = new Object();
        ReferenceQueue<Object> weakReferenceQueue = new ReferenceQueue<Object>();
        WeakReference weakReference = new WeakReference(weakObject,weakReferenceQueue);

        System.out.println("weakReference : "+weakReference.get());
        System.out.println("weakReferenceQueue : "+weakReferenceQueue.poll());

        weakObject = null;
        System.gc();
        Thread.sleep(3000);

        System.out.println("weakReference : "+weakReference.get());
        System.out.println("weakReferenceQueue : "+weakReferenceQueue.poll());

        /**
         * //可以直接获取的时候
         * weakReference : java.lang.Object@6ae40994
         * weakReferenceQueue : null
         *
         *
         * weakReference : null
         * weakReferenceQueue : java.lang.ref.WeakReference@1a93a7ca
         */
    }


    //虚引用：跟踪GC
    //弱引用：GC扫描到就会去回收
    //软引用：当内存不足的时候，GC会去回收掉

}