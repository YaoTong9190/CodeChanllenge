package com.henry.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogIngestion {

    static class User{
        int id;
        boolean optedIn;
    }

    static class OptInChange{
        int userId;
        String action;
    }

    public static List<Integer> findUserWithChange(List<User> currentUserList, List<OptInChange> optInChangeLog){
        Map<Integer, Boolean> map = new HashMap<>();
        for (User user:currentUserList){
            map.put(user.id, user.optedIn);
        }

        List<Integer> changedUserList = new ArrayList<>();

        for (OptInChange opt: optInChangeLog){

            if (!map.containsKey(opt.userId)){
                changedUserList.add(opt.userId);
            }else {
                boolean status = map.get(opt.userId);
                if (opt.action.equals("opt_out") && status == true) {
                    changedUserList.add(opt.userId);
                } else if (opt.action.equals("opt_in") && status == false) {
                    changedUserList.add(opt.userId);
                }
            }
        }

        return changedUserList;
    }
}
