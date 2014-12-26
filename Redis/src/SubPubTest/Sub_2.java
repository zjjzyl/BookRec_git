package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class Sub_2 {

	/**
	 * @param args
	 */
	
	public static JedisPool pool;
	 static {
	  JedisPoolConfig jedispool_config = new JedisPoolConfig();
	  pool = new JedisPool(jedispool_config, "10.1.1.215", 6379);
	 }
	 
	public static void main(String[] args) {
		Sub_2 sub= new Sub_2();
		sub.subscribe_test();
	}
	
	public void subscribe_test() { 
        Jedis jedis = pool.getResource(); 
        jedis.auth("godinsec");
        System.out.println("Subscribe_2 begin!");
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
                System.out.println("�յ�Ƶ�� : ��" + channel +" ������Ϣ ��" + msg); 
            } 
        }; 
        jedis.subscribe(jedisPubSub, new String[]{"speedRequestChannel"}); 
        pool.returnResource(jedis); 
    }

}
