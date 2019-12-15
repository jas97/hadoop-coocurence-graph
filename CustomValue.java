import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class CustomValue implements Writable{
	
	private String followingWord;
	private int frequency;
	
	public CustomValue() {
		
	}
	
	public CustomValue(String followingWord, int frequency) {
		this.followingWord = followingWord;
		this.frequency = frequency;
	}
	
	public String getFollowingWord() {
		return followingWord;
	}
	public void setFollowingWord(String followingWord) {
		this.followingWord = followingWord;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public CustomValue increment() {
		this.frequency++;
		return this;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		followingWord = arg0.readUTF();
		frequency = arg0.readInt();
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		arg0.writeUTF(this.followingWord);
		arg0.writeInt(this.frequency);
		
	}

}
