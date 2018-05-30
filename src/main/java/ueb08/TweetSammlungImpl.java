package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class TweetSammlungImpl implements TweetSammlung{

    List<String> tweets = new LinkedList<>();
    Map<String, Integer> counter = new TreeMap<>();
    Set<String> stop = new TreeSet<>();

    @Override
    public void setStopwords(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
        while(scan.hasNext()){
            stop.add(scan.next());
        }
    }

    @Override
    public void ingest(String tweet) {
        tweets.add(tweet);

        for(String s : TweetSammlung.tokenize(tweet)){
            if(stop.contains(s))
                continue;

            if(counter.containsKey(s)) {
                counter.put(s, counter.get(s) + 1);
            }
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
        List<Map.Entry<String, Integer>> ht = new LinkedList<>();
        for(Map.Entry<String, Integer> e : counter.entrySet()){
            if(e.getKey().startsWith("#"))
                ht.add(e);
        }

        ht.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        List<String> htt = new LinkedList<>();
        for(Map.Entry<String, Integer> e : ht)
            htt.add(e.getKey());

        return htt.iterator();
    }

    public Iterator<String> topHashTagsStream(){
        return counter.entrySet().stream().filter(x -> x.getKey().startsWith("#")).sorted((o1, o2) -> o2.getValue() - o1.getValue()).map(Map.Entry::getKey).iterator();
    }

    @Override
    public Iterator<String> topWords() {
        List<Map.Entry<String, Integer>> ht = new LinkedList<>();
        for(Map.Entry<String, Integer> e : ht){
            if(Character.isAlphabetic(e.getKey().charAt(0)))
                ht.add(e);
        }

        ht.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        List<String> htt = new LinkedList<>();
        for(Map.Entry<String, Integer> e : ht)
            htt.add(e.getKey());

        return htt.iterator();
    }

    public Iterator<String> topWordsStream(){
        return counter.entrySet().stream().filter(x -> Character.isAlphabetic(x.getKey().charAt(0)))
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .map(Map.Entry::getKey).iterator();
    }

    @Override
    public Iterator<Pair<String, Integer>> topTweets() {
        List<Pair<String, Integer>> list = new LinkedList<>();

        for(String tw : tweets) {
            int summe = 0;
            for(String t : TweetSammlung.tokenize(tw)){
                if(stop.contains(t))
                    continue;
                summe += counter.get(t);
            }
            list.add(Pair.of(tw, summe));
        }

        list.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return Integer.compare(o2.getRight(), o1.getRight());
            }
        });

        return list.iterator();
    }
}
