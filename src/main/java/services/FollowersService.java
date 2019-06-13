package services;

import model.UserSummary;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUnfollowRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FollowersService {

    private Instagram4j instagram;

    public FollowersService(Instagram4j instagram) {
       this.instagram = instagram;
    }

    public List<UserSummary> searchFollowers(long  id, int followersCount) throws IOException{

        String maxid = "";
        List<InstagramUserSummary> followersResult = new ArrayList<>();

        /** ALERTA DE GAMBIARRA: A busca na Api do instagram é paginada, assim pra eu trazer tudo de uma vez fiz esse belo código abaixo. *
         *  Obs: Preciso de todos os registro de uma vez para poder fazer a comparação entra listas de seguidores e seguindo. Nâo me julguem!
         */
        while (followersResult.size() < followersCount && maxid != null) {
            InstagramGetUserFollowersResult followers = this.instagram.sendRequest(new InstagramGetUserFollowersRequest(id,maxid));
            followersResult.addAll(followers.getUsers());
            maxid = followers.getNext_max_id();
        }

        return  followersResult.stream().map(fr -> new UserSummary(fr)).collect(Collectors.toList());
    }

    public List<UserSummary> getFollowing(long id) throws IOException {
        InstagramGetUserFollowersResult following = instagram.sendRequest(new InstagramGetUserFollowingRequest(id));
        return following.getUsers().stream().map(u -> new UserSummary(u)).collect(Collectors.toList());
    }

    public List<UserSummary> getNotFollowerList (List<UserSummary> followers, List<UserSummary> followings) {
        return followings.stream().filter(fwi -> !followers.contains(fwi)).collect(Collectors.toList());
    }

    public List<UserSummary> findNotFollowers(InstagramUser user) throws IOException {
        long id = user.getPk();
        int followersCounts = user.getFollower_count();

        List<UserSummary> followers = searchFollowers(id,followersCounts);
        List<UserSummary> followings = getFollowing(id);

        return getNotFollowerList(followers, followings);
    }

    public void unfollow(long userId) throws IOException {
        instagram.sendRequest(new InstagramUnfollowRequest(userId));
    }

}
