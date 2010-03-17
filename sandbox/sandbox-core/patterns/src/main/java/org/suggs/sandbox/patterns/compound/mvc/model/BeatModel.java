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
public class BeatModel implements IBeatModel, MetaEventListener
{

    private static final Log LOG = LogFactory.getLog( BeatModel.class );

    Sequencer mSequencer_;
    List<IBeatObserver> mBeatObservers_ = new ArrayList<IBeatObserver>();
    List<IBpmObserver> mBpmObservers_ = new ArrayList<IBpmObserver>();
    int mBpm_ = 90;

    Sequence mSequence_;
    Track mTrack_;

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#initialise()
     */
    public void initialise()
    {
        setupMidi();
        buildTrackAndStart();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#on()
     */
    public void on()
    {
        mSequencer_.start();
        setBPM( 90 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#off()
     */
    public void off()
    {
        setBPM( 0 );
        mSequencer_.stop();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#getBpm()
     */
    public int getBpm()
    {
        return mBpm_;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#setBPM(int)
     */
    public void setBPM( int bpm )
    {
        mBpm_ = bpm;
        mSequencer_.setTempoInBPM( bpm );
        notifyBpmObservers();
    }

    /**
     * @see javax.sound.midi.MetaEventListener#meta(javax.sound.midi.MetaMessage)
     */
    public void meta( MetaMessage message )
    {
        if ( message.getType() == 47 )
        {
            beatEvent();
            mSequencer_.start();
            setBPM( getBpm() );
        }

    }

    /**
     * This is called by the midi
     */
    public void beatEvent()
    {
        notifyBeatObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void registerObserver( IBeatObserver observer )
    {
        mBeatObservers_.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void removeObserver( IBeatObserver observer )
    {
        mBeatObservers_.remove( observer );
    }

    /**
     * Notify all of the beat observers
     */
    public void notifyBeatObservers()
    {
        for ( IBeatObserver b : mBeatObservers_ )
        {
            b.updateBeat();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void registerObserver( IBpmObserver observer )
    {
        mBpmObservers_.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void removeObserver( IBpmObserver observer )
    {
        mBpmObservers_.remove( observer );
    }

    /**
     * Notify all of the BPM observers
     */
    public void notifyBpmObservers()
    {
        for ( IBpmObserver b : mBpmObservers_ )
        {
            b.updateBpm();
        }
    }

    /**
     * Set up the midi
     */
    public void setupMidi()
    {
        try
        {
            mSequencer_ = MidiSystem.getSequencer();
            mSequencer_.open();
            mSequencer_.addMetaEventListener( this );
            mSequence_ = new Sequence( Sequence.PPQ, 4 );
            mTrack_ = mSequence_.createTrack();
            mSequencer_.setTempoInBPM( getBpm() );
        }
        catch ( MidiUnavailableException mue )
        {
            LOG.error( "No midi available", mue );
        }
        catch ( InvalidMidiDataException imde )
        {
            LOG.error( "Invalid midi data:", imde );
        }
    }

    /**
     * Build a track and start it all going
     */
    public void buildTrackAndStart()
    {
        int[] trackList = { 35, 0, 46, 0 };
        mSequence_.deleteTrack( null );
        mTrack_ = mSequence_.createTrack();

        makeTracks( trackList );
        mTrack_.add( makeEvent( 192, 9, 1, 0, 4 ) );
        try
        {
            mSequencer_.setSequence( mSequence_ );
        }
        catch ( InvalidMidiDataException imde )
        {
            LOG.error( "Invalid midi data:", imde );
        }
    }

    /**
     * @param aList
     */
    public void makeTracks( int[] aList )
    {
        for ( int i = 0; i < aList.length; ++i )
        {
            int key = aList[i];
            if ( key != 0 )
            {
                mTrack_.add( makeEvent( 144, 9, key, 100, i ) );
                mTrack_.add( makeEvent( 128, 9, key, 100, i++ ) );
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
    public MidiEvent makeEvent( int aComd, int aChan, int aOne, int aTwo, int aTick )
    {
        MidiEvent ret = null;

        try
        {
            ShortMessage msg = new ShortMessage();
            msg.setMessage( aComd, aChan, aOne, aTwo );
            ret = new MidiEvent( msg, aTick );
        }
        catch ( InvalidMidiDataException imde )
        {
            LOG.error( "Invalid midi data:", imde );
        }

        return ret;
    }
}
