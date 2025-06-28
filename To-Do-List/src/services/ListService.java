package services;

import entity.Target;

import java.util.ArrayList;

public class ListService {

    ArrayList<Target> targets =  new ArrayList<>();

    public ArrayList<Target> getTargets() {
        return  targets;
    }

    public boolean addTarget(Target target) {
      return targets.add(target);
    };

    public boolean removeTarget(Target target) {
        return targets.remove(target);
    }

    public boolean removeTarget(int index){
        Target remove = targets.remove(index);
        if (remove!=null){
            return true;
        }else {
            return false;
        }
    };

    public Target getTarget(int index) {
        return targets.get(index);
    }

    public Target getTarget(String targetTittle) {
        for (Target target : targets) {
            if (target.getTittle().toLowerCase().equals(targetTittle.toLowerCase())){
                return target;
            }
        }
        return null;
    }

}
