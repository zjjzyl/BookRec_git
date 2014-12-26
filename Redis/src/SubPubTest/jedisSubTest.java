package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class jedisSubTest {

	public static void main(String[] args) {
		//Jedis jedis = JedisUtil.getJedisInstance();
		//jedis.set("kk", "hh");
		final Jedis jedis = new Jedis("182.92.7.5", 6379, 0);
		
		jedis.auth("godinsec");
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	        	while(true){
	        		try{
	        		Thread.sleep(3000l);
	        		if(jedis.isConnected()){
	        			System.out.println("connection ok");
	        		}else{
	        			System.out.println("connection close");
	        		}
	        		}catch(InterruptedException ee){
	        			
	        		}
	        	}
	        }
	    }).start();
        try {
            subscribe_test(jedis);
        } catch (Exception e) {
           e.printStackTrace();
        }
		//JedisUtil.returnResource(jedis);
	}
	
public static void subscribe_test(Jedis jedis) { 
        
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
    }

}
