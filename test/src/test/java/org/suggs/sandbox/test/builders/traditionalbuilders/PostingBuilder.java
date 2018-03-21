package org.suggs.sandbox.test.builders.traditionalbuilders;

import org.suggs.sandbox.test.builders.pojo.Balance;
import org.suggs.sandbox.test.builders.pojo.Company;
import org.suggs.sandbox.test.builders.pojo.Posting;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder for posting pojo objects.
 */

public class PostingBuilder implements Builder<Posting> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PostingBuilder.class );

    private Posting posting;

    private PostingBuilder() {
        posting = new Posting();
    }

    public static final PostingBuilder aPosting() {
        return new PostingBuilder();
    }

    public PostingBuilder withName( String aPostingName ) {
        posting.setPostingName( aPostingName );
        return this;
    }

    public PostingBuilder withPostingValidDateFrom( Date aDate ) {
        posting.setPostingValidFromDate( aDate );
        return this;
    }

    public PostingBuilder withPostingValue( double aValue ) {
        posting.setPostingValue( aValue );
        return this;
    }

    public PostingBuilder withCompany( Company aCompany ) {
        posting.setCompany( aCompany );
        return this;
    }

    public PostingBuilder withBalance( Balance aBalance ) {
        posting.setBalance( aBalance );
        return this;
    }

    @Override
    public Posting build() {
        return posting;
    }
}
