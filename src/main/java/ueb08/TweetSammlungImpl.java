package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TweetSammlungImpl implements TweetSammlung{

    List<String> tweets = new LinkedList<>();
    Map<String, Integer> counter = new HashMap<>();

    @Override
    public void setStopwords(File file) throws FileNotFoundException {

    }

    @Override
    public void ingest(String tweet) {
        tweets.add(tweet);

        for(String s : TweetSammlung.tokenize(tweet)){
            if(counter.containsKey(s))
                counter.put(s, counter.get(s) + 1);
            else
                counter.put(s, 1);
        }
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
