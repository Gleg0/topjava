package main.java.ru.javawebinar.topjava.util;

import main.java.ru.javawebinar.topjava.model.UserMeal;
import main.java.ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Boolean> exceed = new HashMap<>();
        Map<LocalDate, Integer> totalCallories = new HashMap<>();

        for (int i = 0; i < mealList.size(); i++) {
            UserMeal userMeal = mealList.get(i);
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                if (!totalCallories.containsKey(userMeal.getDateTime().toLocalDate())) {
                    totalCallories.put(userMeal.getDateTime().toLocalDate(),userMeal.getCalories());
                    //totalCallories.put()
                    if (totalCallories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay ){
                        exceed.put(userMeal.getDateTime().toLocalDate(),true);
                    }else{
                        exceed.put(userMeal.getDateTime().toLocalDate(),false);
                    }
                }else{
                    totalCallories.remove(userMeal.getDateTime().toLocalDate(),totalCallories.get(userMeal.getDateTime().toLocalDate())+userMeal.getCalories());
                    if (totalCallories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay ){
                        exceed.remove(userMeal.getDateTime().toLocalDate(),true);
                    }else {
                        exceed.remove(userMeal.getDateTime().toLocalDate(),false);
                    }
                }


                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed.get(userMeal.getDateTime().toLocalDate())));
            }
        }
        return result;
    }

}



