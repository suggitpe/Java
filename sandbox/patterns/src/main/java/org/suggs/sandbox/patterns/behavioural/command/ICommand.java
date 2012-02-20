/*
 * ICommand.java created on 29 Aug 2007 17:00:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command;

/**
 * The high lvel interface for all commands
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public interface ICommand {

    /**
     * Common execute method to be called by an invoker
     */
    void execute();

    /**
     * Undo the last executed command
     */
    void undo();

}
