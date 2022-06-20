package com.codeup.kappa.services;

import com.codeup.kappa.models.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormatter {

    public String getDate(Date date) {

        long diff = System.currentTimeMillis() - date.getTime();
        long hours = Math.round(diff / (60 * 60 * 1000));

        if(hours < 1){
            return ((int) hours / 60) + " minutes ago";
        }
         else if(hours == 1) {
            return hours + " hour ago";
        } else if(hours < 24) {
            return hours + " hours ago";
        } else {
            long days = Math.round(diff / (24.0 * 60 * 60 * 1000));
            if (days == 0)
                return "today";
            else if (days == 1)
                return "yesterday";
            else if ((int) (days / 7) == 1)
                return ((int) (days / 7)) + " week ago";
            else if (days < 14)
                return days + " days ago";
            else if (days <= 27)
                return ((int) (days / 7)) + " weeks ago";
            if ((int) (days / 30) == 1)
                return ((int) (days / 30)) + " month ago";
            else if (days < 365)
                return ((int) (days / 30)) + " months ago";
            else if ((int) (days / 365) == 1)
                return ((int) (days / 365)) + " year ago";
            else
                return ((int) (days / 365)) + " years ago";
        }
    }

    public List<String> getDates(List<Date> dates){

        List<String> dateStrings = new ArrayList<>();

        for(Date date : dates){

            dateStrings.add(getDate(date));
//            System.out.println("CHECK " + (getDate(date)));
        }

        return dateStrings;
    }

    public List<Date> getPostDateObjs(List<Post> dates){

        List<Date> dateObjs = new ArrayList<>();

        for (Post post : dates){
            dateObjs.add(post.getCreationDate());
        }
        return dateObjs;
    }

}
