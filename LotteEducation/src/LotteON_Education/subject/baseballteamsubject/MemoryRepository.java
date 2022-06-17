package LotteON_Education.subject.baseballteamsubject;

import LotteON_Education.subject.baseballteamsubject.DTO.Human;
import LotteON_Education.subject.baseballteamsubject.Exception.NotExistUser;

import java.util.*;

public class MemoryRepository {
    private static Map<Long, Human> store = new HashMap<>();
    private static long sequence = 0L;

    public static Map<Long, Human> getStore() {
        return store;
    }

    public Human save(Human human){
        human.setId(++sequence);
        store.put(human.getId(), human);
        return human;
    }

    public Long findById(int backNumber, String name){
        List<Long> arr = store.keySet().stream().toList();
        store.entrySet().stream().forEach(tmp -> System.out.println(tmp.toString()));
        for(int i = 0; i < store.size(); i++){
            Long idx = arr.get(i);
            Human human = store.get(idx);
            if(backNumber == human.backNumber && human.name.equals(name)){
                return human.id;
            }
        }
        return -1L;
    }

    public void delete(int backNumber, String name){
        Long byId = findById(backNumber, name);
        if(byId == -1){
            throw new NotExistUser("존재하지 않는 회원입니다");
        }else{
            store.remove(byId);
        }
    }

    public Human findUser(int backNumber, String name) {
        Long byId = findById(backNumber, name);
        Human human = store.get(byId);
        return human;
    }

    public void updatePlayer(int backNumber, String name, String updateName, int updateNumber) {
        Long byId = findById(backNumber, name);
        Human human = store.get(byId);
        human.name = updateName;
        human.backNumber = updateNumber;
        store.put(byId, human);
    }
}
