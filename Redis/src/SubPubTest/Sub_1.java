package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class Sub_1 {

	/**
	 * @param args
	 */
	
	public static JedisPool pool;
	 static {
	  JedisPoolConfig jedispool_config = new JedisPoolConfig();
	  jedispool_config.setMinIdle(6);
	  pool = new JedisPool(jedispool_config, "10.1.1.222", 6379, 0);
	 }
	 
	public static void main(String[] args) {
		Sub_1 sub= new Sub_1();
		Jedis jedis = pool.getResource(); 
        jedis.auth("godinsec");
       
		while(true){
			if(!jedis.isConnected()){
	        	System.out.println("jedis is not alive!");
	        	jedis = pool.getResource(); 
	            jedis.auth("godinsec");
	        }
			System.out.println("jedis is alive!");
			sub.subscribe_test(jedis);
		}
	}
	
	public void subscribe_test(Jedis jedis) { 
        
        System.out.println("Subscribe_1 begin!");
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
