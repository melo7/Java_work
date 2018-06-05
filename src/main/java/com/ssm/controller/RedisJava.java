package com.ssm.controller;


import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisJava {

	public static void main(String[] args) {
		//连接本地jedis服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		
		jedis.set("name", "melon");
		jedis.set("name1", "lily");
		jedis.set("name2", "melon");
//		jedis.lpush("list", "lily");
//		jedis.lpush("list", "melon");
//		jedis.lpush("list", "lisa");
//		jedis.lpush("list", "bobo");
		//查看服务是否运行
//		List<String> list = jedis.lrange("list", 1, 2);
//		for (String string : list) {
//			System.out.println("当前列表为:"+string);
//		}
		System.out.println("删除name1: "+jedis.del("name"));
		System.out.println("获取删除name1的值：lily="+jedis.get("name1"));
		System.out.println("清空库中所有数据："+jedis.flushDB());
		System.out.println("删除name1: "+jedis.del("name"));
		System.out.println("获取删除name1的值：lily="+jedis.get("name1"));
//		System.out.println("服务正在运行: "+jedis.ping());
		
		
		
		
//		// 清空数据 
//        System.out.println("清空库中所有数据："+jedis.flushDB());
//        
//        System.out.println("=============增=============");
//        jedis.set("key001","value001");
//        jedis.set("key002","value002");
//        jedis.set("key003","value003");
//        System.out.println("已新增的3个键值对如下：");
//        System.out.println(jedis.get("key001"));
//        System.out.println(jedis.get("key002"));
//        System.out.println(jedis.get("key003"));
//        
//        System.out.println("=============删=============");  
//        System.out.println("删除key003键值对："+jedis.del("key003"));  
//        System.out.println("获取key003键对应的值："+jedis.get("key003"));
//        
//        System.out.println("=============改=============");
//        //1、直接覆盖原来的数据
//        System.out.println("直接覆盖key001原来的数据："+jedis.set("key001","value001-update"));
//        System.out.println("获取key001对应的新值："+jedis.get("key001"));
//        //2、直接覆盖原来的数据  
//        System.out.println("在key002原来值后面追加："+jedis.append("key002","+appendString"));
//        System.out.println("获取key002对应的新值"+jedis.get("key002")); 
//   
//        System.out.println("=============增，删，查（多个）=============");
//        /** 
//         * mset,mget同时新增，修改，查询多个键值对 
//         * 等价于：
//         * jedis.set("name","ssss"); 
//         * jedis.set("jarorwar","xxxx"); 
//         */  
//        System.out.println("一次性新增key201,key202,key203,key204及其对应值："+jedis.mset("key201","value201",
//                        "key202","value202","key203","value203","key204","value204"));  
//        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
//                        jedis.mget("key201","key202","key203","key204"));
//        System.out.println("一次性删除key201,key202："+jedis.del(new String[]{"key201", "key202"}));
//        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
//                jedis.mget("key201","key202","key203","key204")); 
//        System.out.println();
//                
//            
//        //jedis具备的功能shardedJedis中也可直接使用，下面测试一些前面没用过的方法
//        System.out.println("======================String_2=========================="); 
//        // 清空数据 
//        System.out.println("清空库中所有数据："+jedis.flushDB());       
       
        
	}

}
