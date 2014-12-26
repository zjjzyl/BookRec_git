package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class Sub {

	/**
	 * @param args
	 */
	
	public static JedisPool pool;
	 static {
	  JedisPoolConfig jedispool_config = new JedisPoolConfig();
	  pool = new JedisPool(jedispool_config, "182.92.7.5", 6379);
	 }
	 
	public static void main(String[] args) {
		Sub sub= new Sub();
		sub.subscribe_test();
	}
	
	public void subscribe_test() { 
		//Jedis jedis = new Jedis("10.1.1.222");
		Jedis jedis = pool.getResource(); 
        jedis.auth("godinsec");
        System.out.println("Subscribe begin!");
        JedisPubSub jedisPubSub = new JedisPubSub() { 
            @Override 
            public void onUnsubscribe(String channel, int number) { 
                System.out.println("channel: "+channel); 
                System.out.println("number :"+number); 
            } 
            @Override 
            public void onSubscribe(String channel, int number) { 
                System.out.println("channel: "+channel); 
                System.out.println("number :"+number); 
            } 
            @Override 
            public void onPUnsubscribe(String arg0, int arg1) { 
            } 
            @Override 
            public void onPSubscribe(String arg0, int arg1) { 
            } 
            @Override 
            public void onPMessage(String arg0, String arg1, String arg2) { 
            } 
            @Override 
            public void onMessage(String channel, String msg) { 
                System.out.println("收到频道 : 【" + channel +" 】的消息 ：" + msg); 
            } 
        }; 
        jedis.subscribe(jedisPubSub, new String[]{"speedRequestChannel"}); 
        pool.returnResource(jedis); 
    }

}
