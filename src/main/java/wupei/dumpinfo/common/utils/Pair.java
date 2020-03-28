package wupei.dumpinfo.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 储存一个键值对， 注意不要滥用，推荐只用来存有对应关系的两个值
 * @param <K>
 * @param <V>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<K, V> {
    private K left = null;
    private V right = null;
}
