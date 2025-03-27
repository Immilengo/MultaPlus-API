package ao.multaplus.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl {
    private final RedisTemplate<String, Object> template;

    public void save(String key, String value, Long expirationTime) {
        template.opsForValue().set(key, value, Duration.ofSeconds( expirationTime));
    }

    public Optional <String > get(String key) {
        return  Optional.ofNullable(Objects.requireNonNull(template.opsForValue().get(key)).toString());
    }

    public void delete(String key) {
        template.delete(key);
    }

    public boolean hasKey(String key) {
        return template.hasKey(key);
    }
}
