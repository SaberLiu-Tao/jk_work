package jk.lt;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
public class Sync {
    private static final Object obj=new Object();

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }

    //使用join方法  将计算线程join到主线程中 主线程会等待计算线程执行完毕
    private static void test1() throws Exception{
        long start = System.currentTimeMillis();
        newThread newThread = new newThread();
        newThread.start();
        newThread.join();
        int sum = newThread.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class newThread extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum = sum();
        }
    }

    //使用volatile关键字  将退出条件可视化 将主线程休眠一段时间  等待计算线程执行完毕
    private static void test2() throws Exception{
        long start = System.currentTimeMillis();
        newThread2 newThread2 = new newThread2();
        newThread2.start();
        //让主线程休眠100ms 让新线程执行完
        if (!newThread2.getBoolean()){
            TimeUnit.MILLISECONDS.sleep(100);
        }
        int sum = newThread2.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }
    static class newThread2 extends Thread{
        private int sum;
        private volatile boolean ifSuccess=false;
        private boolean getBoolean(){
            return ifSuccess;
        }
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum = sum();
            ifSuccess=true;
        }
    }

    //使用了synchronized 计算线程拿到锁 计算完成后释放
    private static void test3() throws Exception{
        long start = System.currentTimeMillis();
        newThread3 newThread3 = new newThread3();
        newThread3.start();
        TimeUnit.MILLISECONDS.sleep(1);
        synchronized (obj){
        }
        int sum = newThread3.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class newThread3 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            synchronized (obj){
                sum = sum();
            }
        }
    }

    //使用了wait和notifyall方法
    private static void test4() throws Exception{
        long start = System.currentTimeMillis();
        newThread4 newThread4 = new newThread4();
        newThread4.start();
        synchronized (obj){
            obj.wait();
        }
        int sum = newThread4.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class newThread4 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            synchronized (obj){
                sum = sum();
                obj.notifyAll();
            }
        }
    }

    //使用了locksupport的park（）和unpark方法
    private static void test5() throws Exception{
        long start = System.currentTimeMillis();
        NewThread5 newThread5 = new NewThread5();
        newThread5.setThread(Thread.currentThread());
        newThread5.start();
        LockSupport.park();
        int sum = newThread5.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread5 extends Thread{
        private int sum;
        private Thread thread;
        public void setThread(Thread thread){
            this.thread=thread;
        }
        private int getSum(){
            return sum;
        }
        private Thread getThread(){
            return thread;
        }
        @Override
        public void run(){
            sum = sum();
            LockSupport.unpark(getThread());
        }
    }
    private static final ReentrantLock lock=new ReentrantLock(true);

    //使用了ReentrantLock 让计算线程拿到锁 再释放锁 主线程继续执行
    private static void test6() throws Exception{
        long start = System.currentTimeMillis();
        NewThread6 newThread6 = new NewThread6();
        newThread6.start();
        TimeUnit.MILLISECONDS.sleep(1);
        lock.lock();
        lock.unlock();
        int sum = newThread6.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread6 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            lock.lock();
            try {
                sum = sum();
            } finally {
                lock.unlock();
            }
        }
    }

    //使用了ReentrantLock的condition 在主线程中调用await 在计算线程中调用signalAll 唤醒
    private static final Condition condition=lock.newCondition();
    private static void test7() throws Exception{
        long start = System.currentTimeMillis();
        NewThread7 newThread7 = new NewThread7();
        newThread7.start();
        lock.lock();
        condition.await();
        lock.unlock();
        int sum = newThread7.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread7 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            lock.lock();
            try {
                sum = sum();
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    //使用countDownLatch  在主线程中调用await 在计算线程中调用countdown()
    static final CountDownLatch cdl=new CountDownLatch(1);
    private static void test8() throws Exception{
        long start = System.currentTimeMillis();
        NewThread8 newThread8 = new NewThread8();
        newThread8.start();
        cdl.await();
        int sum = newThread8.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread8 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum=sum();
            cdl.countDown();
        }
    }

    //使用semaphore 在主线程中执行acquire 在计算线程中release
    static final Semaphore semaphore=new Semaphore(0);
    private static void test9() throws Exception{
        long start = System.currentTimeMillis();
        NewThread9 newThread9 = new NewThread9();
        newThread9.start();
        semaphore.acquire();
        int sum = newThread9.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread9 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum=sum();
            semaphore.release();
        }
    }

    //使用CyclicBarrier 屏障设置为2 在主线程和计算线程中都执行await方法 解除屏障
    private static final CyclicBarrier barrier=new CyclicBarrier(2);
    private static void test10() throws Exception{
        long start = System.currentTimeMillis();
        NewThread10 newThread10 = new NewThread10();
        newThread10.start();
        barrier.await();
        int sum = newThread10.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread10 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum=sum();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    //使用phaser 设置为2 主线程和计算线程都执行arriveAndAwaitAdvance方法
    private static final Phaser phaser=new Phaser(2);
    private static void test11() throws Exception{
        long start = System.currentTimeMillis();
        NewThread11 newThread11 = new NewThread11();
        newThread11.start();
        phaser.arriveAndAwaitAdvance();
        int sum = newThread11.getSum();
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread11 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum=sum();
            phaser.arriveAndAwaitAdvance();
        }
    }

    //使用exchanger交换数据
    private static final Exchanger<Integer> exchanger=new Exchanger<>();
    private static void test12() throws Exception{
        long start = System.currentTimeMillis();
        NewThread12 newThread12 = new NewThread12();
        newThread12.start();
        Integer sum = exchanger.exchange(0);
        System.out.println("异步计算结果：" + sum);
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread12 extends Thread{
        private int sum;
        private int getSum(){
            return sum;
        }
        @Override
        public void run(){
            sum=sum();
            try {
                exchanger.exchange(sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //使用callable  通过executorservice submit拿到future 通过future.get（）拿到计算线程的结果
    private static void test13() throws Exception{
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> integerCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        };
        Future<Integer> future = executor.submit(integerCallable);
        System.out.println("异步计算结果：" + future.get());
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }


    //同样可以使用futuretask 得到
    private static void test14() throws Exception{
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        NewThread14 newThread14 = new NewThread14();
        FutureTask<Integer> task = new FutureTask<Integer>(newThread14);
        executor.submit(task);
        executor.shutdown();
        System.out.println("异步计算结果：" + task.get());
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread14 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return sum();
        }
    }

    //使用completableFuture 得到计算结果
    private static void test15() throws Exception{
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> task = new CompletableFuture<Integer>();
        NewThread15 newThread15 = new NewThread15(task);
        executor.submit(newThread15);
        executor.shutdown();
        System.out.println("异步计算结果：" + task.get());
        System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
    }

    static class NewThread15 implements Runnable {
        private CompletableFuture<Integer> future;
        public NewThread15(CompletableFuture future){
            this.future=future;
        }
        @Override
        public void run() {
            future.complete(sum());
        }
    }


    public static void main(String[] args) throws Exception {
        test5();
    }

}
