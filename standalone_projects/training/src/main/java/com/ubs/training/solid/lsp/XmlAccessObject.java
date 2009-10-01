/*
 * XmlAccessorbject.java created on 1 Oct 2009 09:46:52 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.lsp;


/**
 * TODO Write javadoc for XmlAccessorbject
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class XmlAccessObject implements IBeanDao
{

    /**
     * @see com.ubs.training.solid.lsp.IBeanDao#readBean()
     */
    @Override
    public PersistableBean readBean()
    {
        PersistableBean pb = new PersistableBean();
        pb.setId( 23 );
        pb.setName( "foo " );
        pb.setSomethingInteresting( "interesting" );
        return pb;
    }

    /**
     * @see com.ubs.training.solid.lsp.IBeanDao#writeBean(com.ubs.training.solid.lsp.PersistableBean)
     */
    @Override
    public void writeBean( PersistableBean aBean )
    {}
}
