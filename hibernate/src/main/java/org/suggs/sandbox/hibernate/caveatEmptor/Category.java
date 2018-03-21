/*
 * Category.java created on 21 Mar 2007 17:43:41 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CE_CATEGORY")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_CATEGORY_SEQ")
@SuppressWarnings("unused")
public class Category extends AbstractPersistentBaseClass {

    @Column(name = "CATEGORY_NAME", nullable = false, length = 64)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_PARENT")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private Set<Category> childCats = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "categories",
            targetEntity = org.suggs.sandbox.hibernate.caveatEmptor.Item.class,
            fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<Item>();

    public Category() {
    }

    public Category(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> aItems) {
        items = aItems;
    }

    public Set<Category> getChildCategories() {
        return childCats;
    }

    public void setChildCategories(Set<Category> aCategories) {
        childCats = aCategories;
    }

    public void addChildCategory(Category aCategory) {
        if (aCategory == null) {
            throw new IllegalArgumentException("Null category passed in");
        }
        if (aCategory.getParentCategory() != null) {
            aCategory.getParentCategory().getChildCategories().remove(aCategory);
        }

        aCategory.setParentCategory(this);
        childCats.add(aCategory);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category aCategory) {
        parentCategory = aCategory;
    }

}
