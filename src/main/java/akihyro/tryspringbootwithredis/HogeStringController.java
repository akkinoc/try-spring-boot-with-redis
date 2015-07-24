package akihyro.tryspringbootwithredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hoge-string")
public class HogeStringController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.PUT)
    public void put(@RequestBody Hoge value) throws Exception {
        redisTemplate.opsForValue()
                .set("hoge-string:string", value.getString());
        redisTemplate.delete("hoge-string:list");
        redisTemplate.opsForList()
                .rightPushAll("hoge-string:list", value.getList().toArray(new String[0]));
        redisTemplate.delete("hoge-string:map");
        redisTemplate.opsForHash()
                .putAll("hoge-string:map", value.getMap());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Hoge get() throws Exception {
        Hoge hoge = new Hoge();
        hoge.setString(redisTemplate.opsForValue().get("hoge-string:string"));
        hoge.setList(redisTemplate.opsForList().range("hoge-string:list", 0, -1));
        hoge.setMap(redisTemplate.<String, String>opsForHash().entries("hoge-string:map"));
        return hoge;
    }

}
