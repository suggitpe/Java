// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.suggs.roo.pizzashop.domain;

import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.suggs.roo.pizzashop.domain.Base;

privileged aspect BaseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BaseDataOnDemand: @Component;
    
    private Random BaseDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Base> BaseDataOnDemand.data;
    
    public Base BaseDataOnDemand.getNewTransientBase(int index) {
        org.suggs.roo.pizzashop.domain.Base obj = new org.suggs.roo.pizzashop.domain.Base();
        obj.setName("name_" + index);
        return obj;
    }
    
    public Base BaseDataOnDemand.getSpecificBase(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Base obj = data.get(index);
        return Base.findBase(obj.getId());
    }
    
    public Base BaseDataOnDemand.getRandomBase() {
        init();
        Base obj = data.get(rnd.nextInt(data.size()));
        return Base.findBase(obj.getId());
    }
    
    public boolean BaseDataOnDemand.modifyBase(Base obj) {
        return false;
    }
    
    public void BaseDataOnDemand.init() {
        data = new java.util.ArrayList<org.suggs.roo.pizzashop.domain.Base>();
        for (int i = 0; i < 10; i++) {
            org.suggs.roo.pizzashop.domain.Base obj = getNewTransientBase(i);
            obj.persist();
            data.add(obj);
        }
    }
    
}
