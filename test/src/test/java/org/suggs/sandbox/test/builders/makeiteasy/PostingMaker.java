package org.suggs.sandbox.test.builders.makeiteasy;

import org.suggs.sandbox.test.builders.pojo.Posting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import static com.natpryce.makeiteasy.Property.newProperty;

/**
 * Nat Pryce maker for postings.
 */

public final class PostingMaker {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PostingMaker.class );

    public static final Property<Posting, String> postingName = newProperty();
    public static final Property<Posting, Double> postingValue = newProperty();

    public static final Instantiator<Posting> Posting = new Instantiator<Posting>() {
        @Override
        public Posting instantiate( PropertyLookup<Posting> lookup ) {
            Posting posting = new Posting();
            posting.setPostingName( lookup.valueOf( postingName, "" ) );
            posting.setPostingValue( lookup.valueOf( postingValue, 0.0d ) );
            return posting;
        }
    };
}
