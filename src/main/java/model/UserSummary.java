package model;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.Objects;

public class UserSummary extends InstagramUserSummary {


    public UserSummary(InstagramUserSummary instagramUserSummary) {
        this.pk = instagramUserSummary.pk;
        this.username = instagramUserSummary.username;
        this.full_name = instagramUserSummary.full_name;
        this.profile_pic_id = instagramUserSummary.profile_pic_id;
        this.profile_pic_url = instagramUserSummary.profile_pic_url;
        this.is_private = instagramUserSummary.is_private;
        this.is_favorite = instagramUserSummary.is_favorite;
        this.is_verified = instagramUserSummary.is_verified;
        this.has_anonymous_profile_picture = instagramUserSummary.has_anonymous_profile_picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || super.getClass() != o.getClass()) return false;
        InstagramUserSummary that = (InstagramUserSummary) o;
        return Objects.equals(super.getPk(), that.getPk());
    }
}
