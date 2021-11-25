package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음(현재) , 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려 해야한다
 */

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤으로 사용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    //싱글톤을 사용할 때는 생성자를 막아줘야한다. 아무나 사용할 수 없도록
    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());//이렇게 하면 store에 있는 모든값들을 꺼내서 ArrayList에 담아서 리턴해준. store자체를 보호하기 위해서
    }

    public void clearStore(){
        store.clear();
    }


}
