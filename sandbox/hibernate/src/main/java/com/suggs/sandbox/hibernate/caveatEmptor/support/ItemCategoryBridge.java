/*
 * ItemCategoryBridge.java created on 4 Jun 2007 06:01:27 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor.support;


public class ItemCategoryBridge extends AbstractPersistentBaseClass
{

    private long mItemsId_;
    private long mCategoriesId_;

    /**
     * Returns the value of categoriesId.
     * 
     * @return Returns the categoriesId.
     */
    public long getCategoriesId()
    {
        return mCategoriesId_;
    }

    /**
     * Sets the categoriesId field to the specified value.
     * 
     * @param categoriesId
     *            The categoriesId to set.
     */
    public void setCategoriesId( long aCategoriesId )
    {
        mCategoriesId_ = aCategoriesId;
    }

    /**
     * Returns the value of itemsId.
     * 
     * @return Returns the itemsId.
     */
    public long getItemsId()
    {
        return mItemsId_;
    }

    /**
     * Sets the itemsId field to the specified value.
     * 
     * @param itemsId
     *            The itemsId to set.
     */
    public void setItemsId( long aItemsId )
    {
        mItemsId_ = aItemsId;
    }

}
