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
		jedis.psubscribe(listener, new String[]{"speedRequestChannel"});//ʹ��ģʽƥ��ķ�ʽ����Ƶ��  
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

	// ȡ�ö��ĵ���Ϣ��Ĵ���
	public void onMessage(String channel, String message) {
		System.out.println(channel + "=" + message);
		msg = message;
		System.out.println(msg+"jjjj");
	}

	// ��ʼ������ʱ��Ĵ���
	public void onSubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// ȡ������ʱ��Ĵ���
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// ��ʼ�������ʽ�ķ�ʽ����ʱ��Ĵ���
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// ȡ�������ʽ�ķ�ʽ����ʱ��Ĵ���
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// ȡ�ð����ʽ�ķ�ʽ���ĵ���Ϣ��Ĵ���
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println(pattern + "=" + channel + "=" + message);
	}
	
	
}
