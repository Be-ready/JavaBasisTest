package redisTest.code4;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;


/**
 * 《Redis实战》 4.4 Redis事务
 * <p>
 * 使用hash保存用户信息（users:userId name userName funds money）
 * 使用set保存用户包裹信息（inventory:userId item1 item2...）
 * 使用zset保存商场所有商品,商品价格作为分数（market: money itemId.userId）
 *
 * @author wss
 * @created 2020/9/25 14:40
 * @since 1.0
 */
public class fakeMarketTest {
    private Jedis conn;

    private fakeMarketTest(Jedis jedis) {
        this.conn = jedis;
        clean(this.conn);  // 初始化之前清除数据
        init(this.conn);
    }

    /**
     * 初始化
     */
    private void init(Jedis conn) {
        String userKey = "users:";
        String marketKey = "market:";
        String inventoryKey = "inventory:";
        String[] s = new String[6];
        s[0] = "A";
        s[1] = "B";
        s[2] = "C";
        s[3] = "D";
        s[4] = "E";
        s[5] = "F";
        for (int i = 1; i <= 10; i++) {
            conn.hset(userKey + i, "name", "A" + i);
            conn.hset(userKey + i, "funds", String.valueOf(i * 100));
            for (int j = 1; j <= 5; j++) {
                conn.sadd(inventoryKey + i, "item" + s[j] + i);
            }
        }
        // 使用split()函数分割字符串"itemC1.2"时，要注意转义字符.
        // System.out.println("itemC1.2".split("\\.")[0]);
        conn.zadd(marketKey, 24, "itemC1.2");
        conn.zadd(marketKey, 34, "itemD2.4");
        conn.zadd(marketKey, 44, "itemB4.1");
        conn.zadd(marketKey, 54, "itemE1.3");
        conn.zadd(marketKey, 37, "itemF3.2");

    }

    /**
     * 用户将包裹中的物品放在商场售卖
     *
     * @param itemId   物品Id
     * @param sellerId 用户Id
     * @param price    物品价格
     * @return
     */
    private boolean listItem(Jedis conn, String itemId, String sellerId, long price) {

        String inventoryKey = "inventory:" + sellerId;
        String itemKey = itemId + "." + sellerId;
        long endTime = System.currentTimeMillis() + 5000;  // 加5秒
        Pipeline pipe = conn.pipelined();
        while (System.currentTimeMillis() < endTime) {
            // 监视卖家的包裹
            pipe.watch(inventoryKey);
//            System.out.println(pipe.sismember(inventoryKey, itemId).get());
//            Response<Boolean> s = pipe.sismember(inventoryKey, itemId);
//            System.out.println(s);
//            System.out.println(s.get());
            pipe.sismember(inventoryKey, itemId);
            List<Object> list = pipe.syncAndReturnAll();
            System.out.println(list);  // 输出：[OK, true]
            // 判断指定商品是否在用户包裹中，否则取消监视
            if (list.size() > 0 && (list.get(1) == null || !(boolean) list.get(1))) {
                conn.unwatch();
                return false;
            }
            pipe.multi();
            pipe.zadd("market:", price, itemKey);   // 将该商品加入市场
            pipe.srem(inventoryKey, itemId);            // 从卖家的包裹中移除该商品
            pipe.exec();
            pipe.sync();
            return true;
        }
        try {
            pipe.clear();
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 购买商品
     *
     * @param buyerId 买家Id
     * @param itemKey 待售商品key(形式为itemId.userId)
     * @return
     */
    private boolean purchaseItem(Jedis conn, String buyerId, String itemKey) {

        String marketKey = "market:";                    // 商场key
        String buyerKey = "users:" + buyerId;           // 购买者key
        String sellerKey = "users:" + itemKey.split("\\.")[1];  // 售者key
        String itemId = itemKey.split("\\.")[0];  // 待售商品id
        String inventoryKey = "inventory:" + buyerId;       // 购买者包裹key
        long end = System.currentTimeMillis() + 10000;  // 加10秒
        Pipeline pipe = conn.pipelined();
        // 如果失败，进行重试，最大重试时间为10秒
        while (System.currentTimeMillis() < end) {
            // 监视商场和买家包裹信息
            pipe.watch(marketKey, buyerKey);
            // 获取欲购买的商品价格
//            double price = pipe.zscore(marketKey, itemKey).get();  // 会报错
            pipe.zscore(marketKey, itemKey);  // double类型
            // 获取购买者的金币余额
//            int funds = Integer.valueOf(pipe.hget(buyerId, "funds").get());
            pipe.hget(buyerKey, "funds"); // long类型
            List<Object> list = pipe.syncAndReturnAll();  // 将查询结果按顺序存储在列表中
//            System.out.println(list);
//            Object price = list.get(1);
//            Object funds = list.get(2);

            // 判断，前者条件判断商店是否有该商品，后者判断是否有该用户
            if (list.get(1) == null || list.get(2) == null) {
                return false;
            }

            long price = new Double((double) list.get(1)).longValue();
//            System.out.println("money:" + price);
            long funds = Long.valueOf((String) list.get(2));

            // 如果购买者金币小于售价或者商店已无该件商品，取消监视，返回false
            if (price > funds) {
                conn.unwatch();
                return false;
            }
            pipe.multi();
            // 购买者的金币减少
            pipe.hincrBy(buyerKey, "funds", -price);
            // 售卖者的金币增加
            pipe.hincrBy(sellerKey, "funds", price);
            // 将该商品放入购买者的包裹
            pipe.sadd(inventoryKey, itemId);
            // 将该商品从商场删除
            pipe.zrem("market:", itemKey);
            pipe.exec();
            pipe.sync();
        }
        pipe.clear();
        try {
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 清除数据
     */
    private void clean(Jedis conn) {
        conn.flushAll();
    }

    /**
     * 商品拍卖
     *
     * @param conn
     * @param saleItemsKey
     * @param seconds
     * @return
     */
    private boolean saleByLimitTime(Jedis conn, String saleItemsKey, Integer seconds) {


        return true;
    }

    /**
     * 添加待拍卖的商品
     * 使用hash存储待拍卖的商品信息（saleItems:itemId name sum price）
     *
     * @param conn
     * @param itemId
     * @param name
     * @param sum
     * @param price
     * @param seconds
     */
    private void saleItem(Jedis conn, String itemId, String name, int sum, long price, Integer seconds) {
        String saleItemsKey = "saleItems:" + itemId;
        conn.hset(saleItemsKey, "name", name);
        conn.hset(saleItemsKey, "sum", String.valueOf(sum));
        conn.hset(saleItemsKey, "price", String.valueOf(price));
        if (seconds != null) {
            conn.expire(saleItemsKey, seconds);
        }
        conn.expire(saleItemsKey, 10 * 60);  // 默认竞拍时长为十分钟
    }

    public static void main(String[] args) {

        Jedis conn = new Jedis("localhost");
        fakeMarketTest t = new fakeMarketTest(conn);
        // 打印用户id为1的用户信息(售卖者)
        System.out.println("用户1的信息：" + conn.hgetAll("users:1"));
        // 购买者
        System.out.println("用户2的信息：" + conn.hgetAll("users:2"));
        // 打印用户id为1的用户包裹信息
        System.out.println("用户1的包裹：" + conn.smembers("inventory:1"));
        System.out.println("用户2的包裹：" + conn.smembers("inventory:2"));
        // 打印商店货架上的商品id(id形式为：商品id.用户id)
        System.out.println("商店信息：" + conn.zrange("market:", 0, -1));

        // id=1的用户将itemC1商品放入商店售卖(该商品在商店的id设置为itemC1.1)
        t.listItem(conn, "itemC1", "1", 99);
        System.out.println("用户1的信息：" + conn.hgetAll("users:1"));
        System.out.println("用户1的包裹：" + conn.smembers("inventory:1"));
        System.out.println("商店信息：" + conn.zrange("market:", 0, -1));

        // id=2的用户购买id=1的用户在商场售卖的id=itemC1的商品(该商品在商店的id=itemC1.1)
        t.purchaseItem(conn, "2", "itemC1.1");
        System.out.println("用户1的信息：" + conn.hgetAll("users:1"));
        System.out.println("用户2的信息：" + conn.hgetAll("users:2"));
        System.out.println("用户2的包裹：" + conn.smembers("inventory:2"));
        System.out.println("商店信息：" + conn.zrange("market:", 0, -1));

    }
}
