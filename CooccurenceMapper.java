import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CooccurenceMapper extends Mapper<LongWritable, Text, Text, CustomValue> {
	
	/*
	 * For each word w outputs pair (w, (following_word, 1))
	 */
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		StringTokenizer itr = new StringTokenizer(value.toString());
		int len = itr.countTokens();
		
		// position of the currently processed token
		int currentToken = 0;
		
		String firstWord = "";
		String secondWord;
		
		while(itr.hasMoreTokens()) {
			
			if (currentToken == 0) 
				firstWord = itr.nextToken();
			
			if (currentToken == len - 1) {
				secondWord = "";
			} else {
				secondWord = itr.nextToken();
			}	
			
			Text outKey = new Text();
			outKey.set(firstWord);
			
			context.write(outKey, new CustomValue(secondWord, 1));
			firstWord = secondWord;
			currentToken++;
		}		
	}
	
	public void cleanup(Context context) throws IOException, InterruptedException {
		
	}

}
