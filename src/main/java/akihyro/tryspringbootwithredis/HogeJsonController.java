package akihyro.tryspringbootwithredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hoge-json")
public class HogeJsonController {

    @Autowired
    @Qualifier("jsonRedisTemplate")
    private RedisTemplate<String, Hoge> redisTemplate;

    @RequestMapping(method = RequestMethod.PUT)
    public void put(@RequestBody Hoge value) throws Exception {
        redisTemplate.opsForValue().set("hoge-json", value);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Hoge get() throws Exception {
        return redisTemplate.opsForValue().get("hoge-json");
    }

}
