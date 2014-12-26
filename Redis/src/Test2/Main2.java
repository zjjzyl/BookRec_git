package Test2;

import java.util.List;
import Test.Main1;


public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main1.jedis.auth("godinsec");
		int n=Integer.parseInt(String.valueOf(Main1.jedis.llen("home")));
		while(true){
			 List<String> value = Main1.jedis.blpop(0,"home");
			 System.out.println(value.size());
			 System.out.println(value.get(0)+":"+value.get(1));
			 System.out.println(Thread.currentThread().getId());
		}
	}

}
