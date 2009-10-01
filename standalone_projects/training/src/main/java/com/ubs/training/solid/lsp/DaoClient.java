/*
 * DaoClient.java created on 1 Oct 2009 10:10:18 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.lsp;

/**
 * TODO Write javadoc for DaoClient
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class DaoClient
{

    private IBeanDao mDao_;

    public void doSomething()
    {
        PersistableBean readBean = mDao_.readBean();

        @SuppressWarnings("unused")
        String interesting = readBean.getSomethingInteresting();
    }

    public void setDao( IBeanDao aDao )
    {
        mDao_ = aDao;
    }

}
