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
        List<String> words = new LinkedList<>();
        words.addAll(counter.keySet());

        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return words.iterator();
    }

    @Override
    public Iterator<String> topHashTags() {
        List<Map.Entry<String, Integer>> hl = new LinkedList<>();
        for(Map.Entry<String, Integer> e : counter.entrySet()){
            if(e.getKey().startsWith("#"))
                hl.add(e);
        }

        hl.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        List<String> hll = new LinkedList<>();
        for(Map.Entry<String, Integer> e : hl)
            hll.add(e.getKey());

        return hll.iterator();
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
