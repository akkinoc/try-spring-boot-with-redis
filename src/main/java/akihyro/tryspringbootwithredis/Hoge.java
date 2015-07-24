package akihyro.tryspringbootwithredis;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Hoge {

    private String string;

    private List<String> list;

    private Map<String, String> map;

}
