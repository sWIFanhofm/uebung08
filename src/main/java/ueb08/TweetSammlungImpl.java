package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class TweetSammlungImpl implements TweetSammlung{

    @Override
    public void setStopwords(File file) throws FileNotFoundException {

    }

    @Override
    public void ingest(String tweet) {

    }

    @Override
    public Iterator<String> vocabIterator() {
        return null;
    }

    @Override
    public Iterator<String> topHashTags() {
        return null;
    }

    @Override
    public Iterator<String> topWords() {
        return null;
    }

    @Override
    public Iterator<Pair<String, Integer>> topTweets() {
        return null;
    }
}
