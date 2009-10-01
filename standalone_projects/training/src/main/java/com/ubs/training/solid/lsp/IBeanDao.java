/*
 * IBeanDao.java created on 1 Oct 2009 08:35:26 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.lsp;

/**
 * TODO Write javadoc for IBeanDao
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public interface IBeanDao
{

    public void writeBean( PersistableBean aBean );

    public PersistableBean readBean();

}
