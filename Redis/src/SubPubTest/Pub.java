package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Pub {

	/**
	 * @param args
	 */
	 public static JedisPool pool;
	 static {
	  JedisPoolConfig jedispool_config = new JedisPoolConfig();
	  pool = new JedisPool(jedispool_config, "127.0.0.1", 6379);
	 }
	 
	public static void main(String[] args) {
		Pub pub = new Pub();
		pub.publish_test();
		
	}

	public void publish_test() { 
        Jedis jedis = pool.getResource(); 
        jedis.auth("godinsec");
        System.out.println("Publish begin!");
        long i = jedis.publish("speedRequestChannel", "{\"key\":\"value\"}"); 
        i = jedis.publish("speedRequestChannel", "{key2:value2}"); 
        System.out.println(i+" �������߽��ܵ��� channel1 ��Ϣ"); 
        /*long i = jedis.publish("channel1", "channel1 values"); 
        System.out.println(i+" �������߽��ܵ��� channel1 ��Ϣ"); 
        i = jedis.publish("channel2", "���ѽ����"); 
        System.out.println(i+" �������߽��ܵ��� channel2 ��Ϣ"); 
        i = jedis.publish("channel3", "����Ƶ��3����"); 
        System.out.println(i+" �������߽��ܵ��� channel3 ��Ϣ"); 
        i = jedis.publish("two", "two values"); 
        System.out.println(i+" �������߽��ܵ��� two values ��Ϣ"); */
        pool.returnResource(jedis); 
    }

	 
}
