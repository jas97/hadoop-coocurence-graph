import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class WordPair {
	
	private String firstWord;
	private String secondWord;
	
	
	public WordPair(String firstWord, String secondWord) {
		this.firstWord = firstWord;
		this.secondWord = secondWord;
		// TODO Auto-generated constructor stub
	}
	
	public WordPair() {
		
	}

	public String getFirstWord() {
		return firstWord;
	}

	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord;
	}

	public String getSecondWord() {
		return secondWord;
	}

	public void setSecondWord(String secondWord) {
		this.secondWord = secondWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstWord == null) ? 0 : firstWord.hashCode());
		result = prime * result + ((secondWord == null) ? 0 : secondWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordPair other = (WordPair) obj;
		if (firstWord == null) {
			if (other.firstWord != null)
				return false;
		} else if (!firstWord.equals(other.firstWord))
			return false;
		if (secondWord == null) {
			if (other.secondWord != null)
				return false;
		} else if (!secondWord.equals(other.secondWord))
			return false;
		return true;
	}
	
	
}
