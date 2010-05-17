/*
 * TibcoAdminUtil.java created on 7 Aug 2008 06:13:27 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import java.util.ArrayList;
import java.util.List;

import com.tibco.tibjms.admin.ConnectionFactoryInfo;
import com.tibco.tibjms.admin.DurableInfo;
import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

/**
 * Utility class to help get various admin data from the EMS broker
 * 
 * @author suggitpe
 * @version 1.0 7 Aug 2008
 */
public class TibcoAdminUtil {

    public static List<String> getConnectionFactoryNames() {
        List<String> ret = new ArrayList<String>();
        TibjmsAdmin admin = TibcoAdminConnectionProperties.instance().getAdmin();
        ConnectionFactoryInfo[] cfi = null;
        try {
            cfi = admin.getConnectionFactories();
        }
        catch ( TibjmsAdminException e ) {
            throw new IllegalStateException( "Failed to retrieve connection factories from admin", e );
        }

        for ( ConnectionFactoryInfo info : cfi ) {
            String[] names = info.getJNDINames();
            for ( String s : names ) {
                ret.add( s );
            }
        }
        return ret;
    }

    public static List<String> getDurableNames() {
        List<String> ret = new ArrayList<String>();
        TibjmsAdmin admin = TibcoAdminConnectionProperties.instance().getAdmin();
        DurableInfo[] di = null;
        try {
            di = admin.getDurables();
        }
        catch ( TibjmsAdminException e ) {
            throw new IllegalStateException( "Failed to retrieve durables from admin", e );
        }

        for ( DurableInfo d : di ) {
            ret.add( d.getDurableName() );
        }
        return ret;
    }
}
