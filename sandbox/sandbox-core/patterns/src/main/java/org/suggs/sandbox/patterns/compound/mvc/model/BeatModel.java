/*
 * BeatModel.java created on 19 Sep 2007 18:20:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.model;

import org.suggs.sandbox.patterns.compound.mvc.IBeatModel;
import org.suggs.sandbox.patterns.compound.mvc.IBeatObserver;
import org.suggs.sandbox.patterns.compound.mvc.IBpmObserver;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Concrete implementation of the beat model
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public class BeatModel implements IBeatModel, MetaEventListener {

    private static final Log LOG = LogFactory.getLog( BeatModel.class );

    private Sequencer sequencer;
    private List<IBeatObserver> beatObservers = new ArrayList<IBeatObserver>();
    private List<IBpmObserver> bpmObservers = new ArrayList<IBpmObserver>();
    private int bpm = 90;

    private Sequence sequence;
    private Track track;

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#initialise()
     */
    public void initialise() {
        setupMidi();
        buildTrackAndStart();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#on()
     */
    public void on() {
        sequencer.start();
        setBPM( 90 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#off()
     */
    public void off() {
        setBPM( 0 );
        sequencer.stop();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#getBpm()
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#setBPM(int)
     */
    public void setBPM( int aBpm ) {
        bpm = aBpm;
        sequencer.setTempoInBPM( aBpm );
        notifyBpmObservers();
    }

    /**
     * @see javax.sound.midi.MetaEventListener#meta(javax.sound.midi.MetaMessage)
     */
    public void meta( MetaMessage message ) {
        if ( message.getType() == 47 ) {
            beatEvent();
            sequencer.start();
            setBPM( getBpm() );
        }

    }

    /**
     * This is called by the midi
     */
    public void beatEvent() {
        notifyBeatObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void registerObserver( IBeatObserver observer ) {
        beatObservers.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void removeObserver( IBeatObserver observer ) {
        beatObservers.remove( observer );
    }

    /**
     * Notify all of the beat observers
     */
    public void notifyBeatObservers() {
        for ( IBeatObserver b : beatObservers ) {
            b.updateBeat();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void registerObserver( IBpmObserver observer ) {
        bpmObservers.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void removeObserver( IBpmObserver observer ) {
        bpmObservers.remove( observer );
    }

    /**
     * Notify all of the BPM observers
     */
    public void notifyBpmObservers() {
        for ( IBpmObserver b : bpmObservers ) {
            b.updateBpm();
        }
    }

    /**
     * Set up the midi
     */
    public void setupMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener( this );
            sequence = new Sequence( Sequence.PPQ, 4 );
            track = sequence.createTrack();
            sequencer.setTempoInBPM( getBpm() );
        }
        catch ( MidiUnavailableException mue ) {
            LOG.error( "No midi available", mue );
        }
        catch ( InvalidMidiDataException imde ) {
            LOG.error( "Invalid midi data:", imde );
        }
    }

    /**
     * Build a track and start it all going
     */
    public void buildTrackAndStart() {
        int[] trackList = { 35, 0, 46, 0 };
        sequence.deleteTrack( null );
        track = sequence.createTrack();

        makeTracks( trackList );
        track.add( makeEvent( 192, 9, 1, 0, 4 ) );
        try {
            sequencer.setSequence( sequence );
        }
        catch ( InvalidMidiDataException imde ) {
            LOG.error( "Invalid midi data:", imde );
        }
    }

    /**
     * @param aList
     */
    public void makeTracks( int[] aList ) {
        for ( int i = 0; i < aList.length; ++i ) {
            int key = aList[i];
            if ( key != 0 ) {
                track.add( makeEvent( 144, 9, key, 100, i ) );
                track.add( makeEvent( 128, 9, key, 100, i++ ) );
            }
        }
    }

    /**
     * @param aComd
     * @param aChan
     * @param aOne
     * @param aTwo
     * @param aTick
     * @return a new MidiEvent
     */
    public MidiEvent makeEvent( int aComd, int aChan, int aOne, int aTwo, int aTick ) {
        MidiEvent ret = null;

        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage( aComd, aChan, aOne, aTwo );
            ret = new MidiEvent( msg, aTick );
        }
        catch ( InvalidMidiDataException imde ) {
            LOG.error( "Invalid midi data:", imde );
        }

        return ret;
    }
}
