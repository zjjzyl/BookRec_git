package Test4;

import java.util.List;

import Test.Main1;

public class Main4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=Integer.parseInt(String.valueOf(Main1.jedis.llen("home")));
		while(true){
			 List<String> value = Main1.jedis.blpop(0,"home");
			 System.out.println(value.get(0)+":"+value.get(1));
			 System.out.println(Thread.currentThread().getId());
		}
	}

}
