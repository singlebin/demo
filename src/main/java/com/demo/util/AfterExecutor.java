package com.demo.util;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import java.util.concurrent.Executor;

/**
 * @author wub
 * @date 2021/3/6 23:03
 */
public class AfterExecutor extends TransactionSynchronizationAdapter implements Executor {
    @Override
    public void execute(Runnable command) {

    }

    @Override
    public void afterCommit() {
        super.afterCommit();
    }

    @Override
    public void afterCompletion(int status) {
        super.afterCompletion(status);
    }
}
