package com.xiejiadao.girl.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join 框架：
 *  Fork把大任务分成小任务，join最后再汇总
 *
 *  工作窃取算法：
 *      使用双端队列，这样窃取线程从尾部取任务。被窃取线程从头部取任务
 */
@Slf4j
public class ForkAndJoin extends RecursiveTask<Integer> {

    public static final int threadHold = 2;

    private int start;

    private int end;

    public ForkAndJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        return null;
    }
}
