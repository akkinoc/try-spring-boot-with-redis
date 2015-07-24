package akihyro.tryspringbootwithredis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Hoge implements Serializable {

    private String string;

    private List<String> list;

    private Map<String, String> map;

}
