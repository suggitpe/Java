/*
 * AbstractNotificationBroadcasterPersister.java created on 29 Feb 2008 08:05:09 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.support;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class that adds in the functionality to persist notifications before they are broadcasted.
 */
public abstract class AbstractNotificationBroadcasterPersister extends NotificationBroadcasterSupport {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractNotificationBroadcasterPersister.class );
    private static final String NOTIF_INSERT_SQL = "insert into notifications (message, sequence_number, source, timestamp, type, user_data values (?,?,?,?,?,?)";

    private Connection connection;
    private boolean enable = false;

    public AbstractNotificationBroadcasterPersister( Connection aConnection ) {
        connection = aConnection;
    }

    public void setStorage( boolean aEnable ) {
        enable = aEnable;
    }

    public boolean getStorage() {
        return enable;
    }

    @Override
    public void sendNotification( Notification aNotification ) {
        PreparedStatement stmt = null;
        try {
            // prepare the insert statement
            stmt = connection.prepareStatement( NOTIF_INSERT_SQL );
            stmt.setString( 1, aNotification.getMessage() );
            stmt.setLong( 2, aNotification.getSequenceNumber() );
            if ( aNotification.getSource() instanceof Serializable ) {
                stmt.setObject( 3, aNotification.getSource() );
            }
            else {
                stmt.setObject( 3, "No source" );
            }
            stmt.setLong( 4, aNotification.getTimeStamp() );
            stmt.setString( 5, aNotification.getType() );
            if ( aNotification.getUserData() instanceof Serializable ) {
                stmt.setObject( 6, aNotification.getUserData() );
            }
            else {
                stmt.setObject( 6, "No user data" );
            }

            // execute amnd commit
            stmt.executeUpdate();

            // this should really be managed by the transaction
            // manager and connections
            connection.commit();
        }
        catch ( Exception e ) {
            LOG.error( "Failed to persist notification", e );
        }
        finally {
            try {
                if ( stmt != null ) {
                    stmt.close();
                }
            }
            catch ( SQLException sqle ) {
                LOG.error( "failed to close statement", sqle );
            }
        }
        super.sendNotification( aNotification );
    }
}
