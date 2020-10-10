package redisTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author wss
 * @created 2020/9/24 15:37
 * @since 1.0
 */
public class redisCommandTest {

    private static Jedis conn = new Jedis("localhost");

    public static void main(String[] args) {

        Pipeline pipeline = conn.pipelined();       // 事务型的流水线对象
        String info = conn.info();                  // 内存使用率等详细信息
        String[] infoString = info.split("\n");
        System.out.println(infoString.length);
        System.out.println(infoString[1]);
        String bgrewriteaof = conn.bgrewriteaof();  // 移除aof文件的冗余命令，使其大小尽量减小

        System.out.println(conn.zscore("myzet", "v4"));
    }
}
