import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.htrace.commons.logging.Log;
import org.apache.htrace.commons.logging.LogFactory;

public class LetterTokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    	StringTokenizer itr = new StringTokenizer(value.toString());
    	while (itr.hasMoreTokens()) {
    		String w = itr.nextToken().toLowerCase();
    		String c = String.valueOf(w.charAt(0));
    		if (Character.isLetter(w.charAt(0))){
    			word.set(c);
    			context.write(word, one);
    		}
    		System.out.println(word.toString());
			
			Log log = LogFactory.getLog(TokenizerMapper.class);
			log.info("Mylog@Mapper: " + word.toString());
    	}
    }

}
