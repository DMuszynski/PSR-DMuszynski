import redis.clients.jedis.Jedis;

public class ApplicationClient {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 1000);
        jedis.select(2);


        jedis.lpush("myList", "Antonio");
        jedis.lpush("myList", "José");
        jedis.lpush("myList", "Nicolas");
        jedis.lpush("myList", "Zouheir");
        jedis.lpush("myList", "Nicolas");

    }
}
