package SubPubTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class subQueue{
	
	public static JedisPool pool;
	 static {
	  JedisPoolConfig jedispool_config = new JedisPoolConfig();
	  pool = new JedisPool(jedispool_config, "127.0.0.1", 6379);
	 }
	 
	public static void main(String args[]){
		Jedis jedis = pool.getResource(); 
        jedis.auth("godinsec");
		pubsub listener = new pubsub();  
		jedis.psubscribe(listener, new String[]{"speedRequestChannel"});//使用模式匹配的方式设置频道  
		//System.out.println("jjjj"+listener.getMsg());
	}
	
}
class pubsub extends JedisPubSub {
	private String msg;
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// 取得订阅的消息后的处理
	public void onMessage(String channel, String message) {
		System.out.println(channel + "=" + message);
		msg = message;
		System.out.println(msg+"jjjj");
	}

	// 初始化订阅时候的处理
	public void onSubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 取消订阅时候的处理
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取消按表达式的方式订阅时候的处理
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取得按表达式的方式订阅的消息后的处理
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println(pattern + "=" + channel + "=" + message);
	}
	
	
}
