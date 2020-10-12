package redisTest.code2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Queable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 《Redis实战》
 * 2.1 登录和cookie缓存
 * 1.使用hash存储cookie令牌和已登录用户之间的映射(key形式为login:,filed形式为token,value形式为user:userId)
 * 2.使用有序集合保存用户最近浏览的商品（key形式为viewed:token, element形式为item:itemId, score形式为系统当前时间）
 * 3.使用有序集合存储令牌最后一次出现的时间（key形式为recent:, element形式为token, score形式为系统当前时间）
 * 2.2使用redis实现购物车示例
 *
 * @author wss
 * @created 2020/9/23 10:52
 * @since 1.0
 */
public class loginAndCookie {

    private Jedis conn;
    private static final int LIMIT = 10000000;
    private static final boolean QUIT = false;

    public loginAndCookie(Jedis conn) {
        this.conn = conn;
    }

    public void updateToken(String token, String userId, String itemId) {
        long timeStamp = System.currentTimeMillis();
        String user = "user:" + userId;
        conn.hset("login:", token, user);
        conn.zadd("recent:", timeStamp, token);
        if (!"".equals(itemId)) {
            String item = "item:" + itemId;
            String viewedItem = "viewed:" + token;
            conn.zadd(viewedItem, timeStamp, item);
            conn.zremrangeByRank(viewedItem, 0, -26);  // 保留最近浏览的25个商品
        }
    }

    /**
     * @throws InterruptedException
     */
    public void cleanSessions() throws InterruptedException {
        Set<String> tokens;
        String[] sessionKeys = new String[100];
        String[] tokensString = new String[100];  // 保存100个旧的令牌
        String[] cartSession = new String[100];
        while (!QUIT) {
            long size = conn.zcard("recent:");

            // 未超过限制，则休眠
            if (size <= LIMIT) {
                Thread.sleep(1);
                continue;
            }

            // 超过限制
            // 1.存储令牌有序集合删除100个最旧的令牌
            // 2.从存储用户信息的hash中删除令牌对应的用户信息
            // 3.从存储用户最近浏览的商品的有序集合中删除对应的信息
            long end_index = Math.min(size - LIMIT, 100);
            tokens = conn.zrange("recent:", 0, end_index - 1);
            int i = 0;
            for (String token : tokens) {
                sessionKeys[i] = "viewed:" + token;
                cartSession[i] = "cart:" + token;
                tokensString[i] = token;
                i++;
            }
            conn.multi();
            conn.del(sessionKeys);   // 删除旧会话对应的令牌
            conn.del(cartSession);   // 删除旧会话对应的购物车
            conn.hdel("login:", tokensString);
            conn.zrem("recent:", tokensString);

        }
    }

    // 购物车相关功能如下：
    public void addToCart(int count, String session, String itemId) {
        String item = "item:" + itemId;
        if (count <= 0) {
            conn.hdel("cart:" + session, item);
        } else {
            conn.hset("cart:" + session, item, String.valueOf(count));
        }
    }

}
