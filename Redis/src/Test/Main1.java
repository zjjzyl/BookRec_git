package Test;

import redis.clients.jedis.Jedis;

public class Main1 {

	/**
	 * @param args
	 */
	public static Jedis jedis = new Jedis("127.0.0.1");
	
	public static void main(String[] args) {

		jedis.auth("godinsec");
		jedis.flushDB();

		String str_1 = "{domainId: '111', domainName: 'www.baidu.com', taskId: '222',"+
				" requestTime: '32432432432', responseTime: '43243243243', areaCode: '10010',"+
				" ISPType: '1', dnsTimes: '22.33', con_times: '45.01', downloadTimes: '120.45',"+
				" downloadSpeed: '33.32'}";
		String str_2 = "{domainId: '222', domainName: 'www.souhu.com', taskId: '333',"+
				" requestTime: '42432432432', responseTime: '53243243243', areaCode: '10010',"+
				" ISPType: '2', dnsTimes: '34.33', con_times: '54.01', downloadTimes: '120.45',"+
				" downloadSpeed: '33.32'}";
		jedis.rpush("speedResultList", str_1);
		jedis.rpush("speedResultList", str_2);



		
	}

}
