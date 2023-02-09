package engineer.pol.omnipurpur.common.database;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class RedisClient {  // TODO: Do code async.

    private final JedisPool jedisPool;
    private final ExecutorService executor;
    private final Logger LOGGER = Logger.getLogger("OmniPurpurRedis");

    public RedisClient(String uri) {
        LOGGER.info("Trying to connect to redis");
        JedisPool tempPool = null;
        try {
            tempPool = new JedisPool(new JedisPoolConfig(), new URI(uri));
            LOGGER.info("Pinging redis");
            if (this.ping(tempPool)) {
                LOGGER.info("Connected to MultiPurpur Redis");
            } else {
                throw new RuntimeException("Invalid Ping");
            }
        } catch (URISyntaxException | RuntimeException e) {
            LOGGER.severe("Cannot connect to redis");
            e.printStackTrace();
            System.exit(0);
        }

        this.jedisPool = tempPool;
        this.executor = Executors.newCachedThreadPool();
    }

    private boolean ping(JedisPool pool) {
        boolean pinged = false;
        try (Jedis jedis = (pool == null ? this.jedisPool : pool).getResource()) {
            String ping = jedis.ping("MULTIPURPUR_PING");
            if (ping.equals("MULTIPURPUR_PING")) {
                pinged = true;
            }
        }
        return pinged;
    }

    private void add(String key, String value) {
        this.add(key, value, null);
    }

    /**
     * @param key     the key
     * @param value   the value
     * @param seconds the seconds. Nullable
     */
    private void add(String key, String value, Long seconds) {
        this.executor.execute(() -> {
            try (Jedis jedis = this.jedisPool.getResource()) {
                jedis.set(key, value);
                if (seconds != null && seconds > 0) {
                    jedis.expire(key, seconds);
                }
            }
        });
    }

    private String get(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

}
