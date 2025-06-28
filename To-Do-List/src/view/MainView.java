package view;

import entity.Target;
import services.ListService;
import util.Input;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainView {

    ListService listService = new ListService();

    public void display() {
        while (true) {
            System.out.println("To Do List");
            System.out.println("1. Add Target");
            System.out.println("2. Remove Target");
            System.out.println("3. Show Targets");
            System.out.println("4. Show Target Details");
            System.out.println("5. Edit Target");
            System.out.println("X. Exit");

            String makeYourChoice = Input.getInputString("Make Your Choice");
            switch (makeYourChoice.toLowerCase()) {
                case "1":
                    listService.addTarget(addTarget());
                    break;
                case "2":
                    removeTarget();
                    break;
                case "3":
                    showTargets();
                    break;
                case "4":
                    showTargetDetails();
                    break;
                case "5":
                    editTarget();
                    break;
                case "x" :
                    return;
            }

        }
    }


    public Target addTarget() {
        System.out.println("Please Insert the Target Details");
        String targetTittle = Input.getInputString("Target Tittle ('x' to cancel)");
        if (targetTittle.toLowerCase().equals("x")) {
            return null;
        }
        String targetDescription = Input.getInputString("Target Description");
        String targetDueDate = Input.getInputString("Target Due Date (ex: 2025-06-25 20:20)(Optional)");
        Target target = new Target(targetTittle, targetDescription, LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
        if (targetDueDate != null && !targetDueDate.trim().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                target.setDueAt(LocalDateTime.parse(targetDueDate, formatter));
            } catch (Exception e) {
                System.out.println("Failed to renew due date please insert the correct format (ex 2025-06-25 20:20)");
            }
        }
        return target;
    }


    public void showTargets() {
        ArrayList<Target> targets = listService.getTargets();
        int index = 1;
        System.out.println("========================================================================");
        System.out.println("To Do List");
        for (Target target : targets) {
            System.out.println(index + ". " + target.getTittle());
            index++;
        }
        System.out.println("========================================================================");
    }

    public void showTargetDetails() {
        System.out.println("========================================================================");
        String inputString = Input.getInputString("Insert Index / Target Title ('x' to cancel)");
        if (inputString.toLowerCase().equals("x")) {
            return;
        }
        Target target;
        try {
            int index = Integer.parseInt(inputString);
            if (index >= 0 && index <= listService.getTargets().size()) {
                target = listService.getTarget(index-1);
            }else {
                target = null;
            }

        } catch (NumberFormatException e) {
            target = listService.getTarget(inputString);
        }
        if (target != null) {
            System.out.println("========================================================================");
            System.out.println("Target Details");
            System.out.println("> " + target.getTittle());
            System.out.println("> Description : " + target.getDescription());
            System.out.println("> Updated At : " + target.getUpdatedAt().getYear() + " " + target.getUpdatedAt().getMonth() + " " + target.getUpdatedAt().getDayOfMonth() + " At Hour " + target.getUpdatedAt().getHour() + " Minute " + target.getUpdatedAt().getMinute());
            if (target.getDueAt() != null) {
                System.out.println("> Due At : " + target.getDueAt().getYear() + " " + target.getDueAt().getMonth() + " " + target.getDueAt().getDayOfMonth() + " At Hour " + target.getDueAt().getHour() + " Minute " + target.getDueAt().getMinute());
            }
            System.out.println("========================================================================");
        }else {
            System.out.println("Target not found");
        }
    }

    public void removeTarget() {
        System.out.println("========================================================================");
        String inputString = Input.getInputString("Insert Index / Target Title ('x' to cancel)");
        if (inputString.toLowerCase().equals("x")) {
            return;
        }
        boolean isTargetRemoved;
        try {
            int index = Integer.parseInt(inputString);
            if (index >= 0 && index <= listService.getTargets().size()) {
                isTargetRemoved = listService.removeTarget(index-1);
            }else {
                isTargetRemoved = false;
            }

        } catch (NumberFormatException e) {
            isTargetRemoved = listService.removeTarget(listService.getTarget(inputString));
        }
        if (isTargetRemoved) {
            System.out.println("Todo List Target Has been Removed");
        }else {
            System.out.println("Target not found");
        }
    }

    public void editTarget() {
        System.out.println("========================================================================");
        String inputString = Input.getInputString("Insert Index / Target Title ('x' to cancel)");
        if (inputString.toLowerCase().equals("x")) {
            return;
        }
        Target target;
        try {
            int index = Integer.parseInt(inputString);
            if (index >= 0 && index <= listService.getTargets().size()) {
                target = listService.getTarget(index-1);
            }else {
                target = null;
            }

        } catch (NumberFormatException e) {
            target = listService.getTarget(inputString);
        }
        if (target != null) {
            System.out.println("Please Insert the new Target Details");
            String targetTittle = Input.getInputString("Target Tittle");
            if (targetTittle != null && !targetTittle.trim().isEmpty()) {
                target.setTittle(targetTittle);
            }
            String targetDescription = Input.getInputString("Target Description");
            if (targetDescription != null && !targetDescription.trim().isEmpty()) {
                target.setDescription(targetDescription);
            }
            String targetDueDate = Input.getInputString("Target Due Date (ex: 2025-06-25 20:20)(Optional)");
            target.setUpdatedAt(LocalDateTime.now());
            if (targetDueDate != null && !targetDueDate.trim().isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    target.setDueAt(LocalDateTime.parse(targetDueDate, formatter));
                } catch (Exception e) {
                    System.out.println("Failed to renew due date please insert the correct format (ex 2025-06-25 20:20)");
                }
            }
        }else {
            System.out.println("Target not found");
        }

    }

}
