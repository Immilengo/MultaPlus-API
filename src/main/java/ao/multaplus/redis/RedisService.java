package ao.multaplus.redis;

import java.util.Optional;

public interface RedisService {
    /**
     * Save the key-value pair to the Redis database with the given expiration time.
     *
     * @param key            the key to save
     * @param value          the value to save
     * @param expirationTime the expiration time of the key-value pair
     */
    void save(String key, String value, Long expirationTime);

    /**
     * Get the value of the given key from the Redis database.
     *
     * @param key the key to get the value
     * @return the value of the given  or empty if the key does not exist
     */
    Optional<String> get(String key);
    /**
     * Delete the key-value pair from the Redis database.
     * @param key the key to delete
     *
     * */
    void delete(String key);
    /**
     * Check if the given key exists in the Redis database.
     *
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    boolean hasKey(String key);
}
