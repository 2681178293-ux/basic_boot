package net.wanho;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import net.wanho.po.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /** 方案 C：GenericJacksonJson —— get 后可直接强转 Student */
    @Test
    void testObjectWithGenericJackson() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        redisTemplate.opsForValue().set(student.getId() + "", student, 10, TimeUnit.MINUTES);
        Student stu = (Student) redisTemplate.opsForValue().get(student.getId() + "");
        System.out.println(stu);
    }

    /** 方案 D：StringRedisTemplate + Hutool JSONUtil —— 自己序列化/反序列化 */
    @Test
    void testObjectWithJsonString() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        stringRedisTemplate.opsForValue().set(
                student.getId() + "", JSONUtil.toJsonStr(student), 10, TimeUnit.MINUTES);
        Student stu = JSONUtil.toBean(
                stringRedisTemplate.opsForValue().get(student.getId() + ""), Student.class);
        System.out.println(stu);
    }

    /** String：验证码（带 TTL）+ 浏览量 incr */
    @Test
    void testString() {
        stringRedisTemplate.opsForValue()
                .set("sms:code:13800000000", "654321", 5, TimeUnit.MINUTES);
        System.out.println(stringRedisTemplate.opsForValue().get("sms:code:13800000000"));

        Long views = stringRedisTemplate.opsForValue().increment("article:views:100");
        views = stringRedisTemplate.opsForValue().increment("article:views:100");
        System.out.println("views=" + views);
    }

    /** Hash：学生字段级缓存，可单字段更新 */
    @Test
    void testHash() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        student.setAge(20);
        student.setGender("男");
        student.setAddress("北京");

        String key = "student:hash:" + student.getId();
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(student.getId()));
        map.put("name", student.getName());
        map.put("age", String.valueOf(student.getAge()));
        map.put("gender", student.getGender());
        map.put("address", student.getAddress());
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, 1, TimeUnit.HOURS);

        // 只改地址，不必整对象覆盖
        student.setAddress("深圳");
        stringRedisTemplate.opsForHash().put(key, "address", student.getAddress());
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        System.out.println(entries);
    }

    /** List：最近消息，leftPush + trim 定长 */
    @Test
    void testList() {
        String key = "user:messages:1";
        stringRedisTemplate.opsForList().leftPush(key, "hello");
        stringRedisTemplate.opsForList().leftPush(key, "world");
        stringRedisTemplate.opsForList().trim(key, 0, 99);
        List<String> recent = stringRedisTemplate.opsForList().range(key, 0, 4);
        System.out.println(recent);
    }

    /** Set：文章点赞，天然去重 */
    @Test
    void testSet() {
        String key = "article:likes:100";
        stringRedisTemplate.opsForSet().add(key, "1001", "1002");
        stringRedisTemplate.opsForSet().add(key, "1001");
        System.out.println("count=" + stringRedisTemplate.opsForSet().size(key));
        System.out.println("liked=" + stringRedisTemplate.opsForSet().isMember(key, "1001"));
        stringRedisTemplate.opsForSet().remove(key, "1002");
        System.out.println(stringRedisTemplate.opsForSet().members(key));
    }

    /** ZSet：日榜 TopN / 排名 */
    @Test
    void testZSet() {
        String key = "leaderboard:daily";
        stringRedisTemplate.opsForZSet().add(key, "1001", 98.5);
        stringRedisTemplate.opsForZSet().add(key, "1002", 99);
        Set<String> top = stringRedisTemplate.opsForZSet().reverseRange(key, 0, 9);
        Long rank = stringRedisTemplate.opsForZSet().reverseRank(key, "1001");
        System.out.println("top=" + top + ", rank1001=" + rank);
    }
}
