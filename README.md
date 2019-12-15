# Word co-occurence graph

Calculates the word cooccurence graph based on the input text files. This is a directed weighted graph whose nodes are all the words appearing in the text files and there is a link A -> B if in any text word A appears before word B. The weight of the link A -> B representes the probability that word B will appear after word A -- number of times B appears after A divided by the total number of occurences of A. 
