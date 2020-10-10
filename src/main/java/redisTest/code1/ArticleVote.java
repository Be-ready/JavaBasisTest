package redisTest.code1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

/** 文章投票示例（参考《Redis实战》1.3章节）
 * @author wss
 * @created 2020/9/22 14:28
 * @since 1.0
 */
public class ArticleVote {

    private static int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static double VOTE_SCORE = 432;
    private static int CONST = 3;
    private Jedis jedis;

    public ArticleVote(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 使用散列（hash）封装文章信息,key的形式为article:123（123为文章id）
     * @param title  标题
     * @param link   链接
     * @param userId 作者id
     * @param time   发布时间
     * @param votes  投票数
     * @return
     */
    private void getArticle(String title, String articleId, String link, String userId, long time, int votes) {

        String key  = "article:" + articleId;
        String key2 = "user:" + userId;
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("poster", key2);
        map.put("time", String.valueOf(time));
        map.put("votes", String.valueOf(votes));
        jedis.hmset(key, map);
    }

    /**
     * 计算文章评分（投票数*常数+当前linux时间）
     * @param votes    投票数
     * @param curTime  当前时间
     * @return
     */
    private double getVotes(int votes, long curTime) {
        return votes * CONST + curTime;
    }

    /**
     * 使用2个Sorted set存储文章（按发布时间排序， 按评分排序）
     * @param articleId   文章id
     * @param votes       投票数
     * @param publishTime 发布时间
     * @return
     */
    private void storageArticle(String articleId, int votes, long publishTime) {

        String key = "article:" + articleId;
        // 根据发布时间排序
        jedis.zadd("time:", publishTime, key);
        // 根据评分排序
        jedis.zadd("score:", getVotes(votes, publishTime), key);
    }

    /**
     *
     * 使用set记录为某篇文章已投票的用户
     * @param userId  用户id
     */
    private void userVoted(String articleId, String userId) {

        String voted = "voted:" + articleId;
        String user = "user:" + userId;
        jedis.sadd(voted, user);
        jedis.expire(voted, ONE_WEEK_IN_SECONDS);           // 设置过期时间（一个星期）
    }

    /**
     * 用户给文章评分
     * @param articleId  文章id
     * @param userId     用户id
     * @return
     */
    public boolean voteArticle(String articleId, String userId) {

        String key1 = "article:" + articleId;
        String key2 = "voted:"   + articleId;
        String key4 = "user:"    + userId;
        String key3 = "score:";
        long curoff = System.currentTimeMillis() - ONE_WEEK_IN_SECONDS;
        // 判断文章的发布时间是否超过一周
        if (jedis.zscore("time:", key1) < curoff) {
            return false;
        }
        // 判断该用户是否是第一次为该篇文章评分
        if (!jedis.sismember(key2, key4)) {               // 判断set中是否有指定member
            // 判断该用户是否为该篇文章的作者
            String uId = jedis.hget(key1, "poster").split(":")[1];
            if (!uId.equals(userId)) {
                jedis.zincrby(key3, VOTE_SCORE, key1);        // zset中评分+VOTE_SCORE
                jedis.hincrBy(key1, "votes", 1);    // 存储文章信息的hash中的投票字段votes的值+1
//                jedis.sadd(key2, key4);                       // 已评分，将该用户存到set中
                userVoted(articleId, userId);                 // 已评分，将该用户存到set中
            }
        }
        return true;
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        jedis.flushAll();
    }

    /**
     * 发布文章
     * @param userId  用户id
     * @param title   文章标题
     * @param link    文章链接
     * @return
     */
    public String publishArticle(String userId, String title, String link) {

        String articleId = String.valueOf(jedis.incr("article:"));  // incr()方法
        long nowTime = System.currentTimeMillis();

        userVoted(articleId, userId);                         // 将作者信息存到set中

        // 将文章信息存储到散列中（key为："article:articleId"）
        int votes = 1;
        getArticle(title, articleId, link, userId, nowTime, votes);

        // 将文章根据发布时间排序和评分排序分别存到一个有序集合中
//        jedis.zadd("score:", nowTime + VOTE_SCORE, "article:" + articleId);
//        jedis.zadd("time:", nowTime , "article:" + articleId);
        storageArticle(articleId, votes, nowTime);
        return articleId;
    }

    /** 得到最新发布或者评分最高的文章列表
     * @param key
     * @return
     */
    public List<Map> getArticles(int start, int end, String key) {
        List<Map> list = new ArrayList<>();
        Set<String> ids = jedis.zrevrange(key, start, end);
        for (String id : ids) {
            Map map = jedis.hgetAll(id);
            map.put("id", id);
            list.add(map);
        }

        return list;
    }

    /**
     * 为文章添加分组
     * @param articleId  文章id
     * @param toAdd      群组列表
     * @param toRemove   待移除的群组列表
     */
    public void groupAddRemove(String articleId, List toAdd, List toRemove) {

        String article = "article:" + articleId;
        for (Object s : toAdd) {
            jedis.sadd("group:" + s, article);
        }
        for (Object s : toRemove) {
            jedis.srem("group:" + s, article);
        }
    }

    /**
     * 从群组中获得文章
     * @param group  群组
     * @param key    按发布时间排序存储或按评分排序存储的有序集合的key（"score:"  "time:"）
     * @return
     */
    public List<Map> getArticlesFromGroup(String group, String key, int start, int end) {
        String key1 = key + group;       // 交集之后的key
        String key2 = "group:" + group;  // 群组group对应的有序集合的key
        if (!jedis.exists(key1)) {
            ZParams zParams = new ZParams();
            zParams.aggregate(ZParams.Aggregate.MAX);
            jedis.zinterstore(key1, zParams, key2, key);
            jedis.expire(key1, 60);
        }

        return getArticles(start, end, key1);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
//        jedis.zadd("time:", System.currentTimeMillis(), "ss");
//        double a = jedis.zscore("time:", "ss");
//        System.out.println(a);
//        jedis.zincrby("time:", VOTE_SCORE, "ss");
//        double b = jedis.zscore("time:", "ss");
//        System.out.println(b-a);
//
//        String key = "article:" + "123";
//        Map<String, String> map = new HashMap<>();
//        map.put("title", "test article title");
//        map.put("votes", "1");
//        jedis.hmset(key, map);
//        System.out.println(jedis.hget(key, "title"));
//        jedis.hincrBy(key, "votes", 1);
//        System.out.println(jedis.hget(key, "votes"));
//
//        jedis.sadd("sSet", "set1", "set2");
//        System.out.println(jedis.sismember("sSet", "set1"));

        ArticleVote av = new ArticleVote(jedis);
//        av.clear();                                // 清除redis所有数据
//        String articleId1 = av.publishArticle("1001", "testTitle1", "www.baidu1.com");
//        String articleId2 = av.publishArticle("1001", "testTitle2", "www.baidu2.com");
//        System.out.println("this is articleId1: " + articleId1);
//        System.out.println("this is articleId2: " + articleId2);
//        av.voteArticle(articleId1, "1002");
//        av.voteArticle(articleId2, "1007");
//        av.voteArticle(articleId2, "1007");  // （同一用户只能对同一篇文章投票一次）articleId2对应的文章的votes不会增加
//        av.voteArticle(articleId2, "1001");  // （用户不能对自己发表的文章投票）articleId2对应的文章的votes不会增加

//        String key = "score:";
//        List<Map> list = av.getArticles(0, 5, key);
//        for (Map m : list) {
//            System.out.println(m);
//        }

        ZParams zParams = new ZParams();
        zParams.aggregate(ZParams.Aggregate.MAX);
        jedis.zinterstore("score:programming", zParams, "groups:programming", "score:");
        System.out.println(jedis.zrange("score:programming", 0, -1));
    }
}
