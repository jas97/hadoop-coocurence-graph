import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CooccurenceReducer extends Reducer<Text, CustomValue, Text, Text> {
	
	// for each ford pair (w1, w2) counts how many times w2 appears right after w1
	HashMap<WordPair, Integer> followingFrequencies = new HashMap<>();
	WordPair currentWordPair = new WordPair();
	
	public void reduce(Text key, Iterable<CustomValue> values, Context context) throws IOException, InterruptedException {
		// counts how many times the first word appeared in the text
		float totalFirstWord = 0;
		
		for (CustomValue val : values) {
			totalFirstWord += 1;
			
			currentWordPair.setFirstWord(key.toString());
			currentWordPair.setSecondWord(val.getFollowingWord());
			Integer oldFrequency = followingFrequencies.get(currentWordPair);
			if (oldFrequency == null) {
				followingFrequencies.put(new WordPair(key.toString(), val.getFollowingWord()), val.getFrequency());
			} else {
				followingFrequencies.put(new WordPair(key.toString(), val.getFollowingWord()), oldFrequency + val.getFrequency());
			}
		}
		
		for (Entry<WordPair, Integer> e : followingFrequencies.entrySet()) {
			Text outKey = new Text();
			outKey.set(key);
			
			Text outValue = new Text();
			StringBuilder sb = new StringBuilder();
			if (!(e.getKey().getSecondWord().equals(""))) {
				sb.append("-> ").append(e.getKey().getSecondWord()).append(" ").append(e.getValue() / totalFirstWord);
				outValue.set(sb.toString());
				context.write(outKey, outValue);
			}
		}
		
		followingFrequencies.clear();
	}
}
