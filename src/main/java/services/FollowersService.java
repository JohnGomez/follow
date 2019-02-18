package services;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FollowersService {

    private Instagram4j instagram;

    public FollowersService(Instagram4j instagram) {
       this.instagram = instagram;
    }

    public List<InstagramUserSummary> searchFollowers(long  id, int followersCount) throws IOException{

        String maxid = "";
        List<InstagramUserSummary> followersResult = new ArrayList<>();

        while (followersResult.size() < followersCount && maxid != null) {
            InstagramGetUserFollowersResult followers = this.instagram.sendRequest(new InstagramGetUserFollowersRequest(id,maxid));
            followersResult.addAll(followers.getUsers());
            maxid = followers.getNext_max_id();
        }
        return followersResult;
    }

    public List<InstagramUserSummary> getFollowing(long id) throws IOException {
        InstagramGetUserFollowersResult following = instagram.sendRequest(new InstagramGetUserFollowingRequest(id));
        return following.getUsers();
    }

    public List<InstagramUserSummary> getNotFollowerList (List<InstagramUserSummary> followers, List<InstagramUserSummary> followings) {
        List<InstagramUserSummary> notFollowing = new ArrayList<>();

        for(InstagramUserSummary fwi : followings) {
            boolean find = false;
            for (InstagramUserSummary fwe : followers) {
                if(fwi.getPk() == fwe.getPk()){
                    find = true;
                    break;
                }
            }
            if(!find) {
                notFollowing.add(fwi);
            }
        }
        return notFollowing;
    }

    public List<InstagramUserSummary> findNotFollowers(InstagramUser user) throws IOException {
        long id = user.getPk();
        int followersCounts = user.getFollower_count();

        List<InstagramUserSummary> followers = searchFollowers(id,followersCounts);
        List<InstagramUserSummary> followings = getFollowing(id);

        return getNotFollowerList(followers, followings);
    }

}
