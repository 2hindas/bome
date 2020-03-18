package org.oopp.server.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BadgesController {

    @Autowired
    private BadgesRepository badgesRepository;

    /**
     * Returns the badge name of the given badge id.
     *
     * @param badgeId for badge if.
     * @return badge name.
     */

    @GetMapping(value = "/badgename")
    public String getBadgeName(@RequestParam int badgeId) {
        Badges badges = badgesRepository.findBadgeDetail(badgeId);
        return badges.getBadgeName();
    }


    /**
     * Returns the badge image of the given badge id.
     *
     * @param badgeId for badge id.
     * @return badge image.
     */


    @GetMapping(value = "/badgeimage")
    public String getBadgeimage(@RequestParam int badgeId) {
        Badges badges = badgesRepository.findBadgeDetail(badgeId);
        return badges.getBadgeImage();
    }


    /**
     * Returns the badge of the given badge id.
     *
     * @param badgeId for badge id.
     * @return Badges.
     */


    @GetMapping(value = "/badgevalue")
    public Badges getBadgeValue(@RequestParam int badgeId) {
        Badges badges = badgesRepository.findBadgeDetail(badgeId);
        return badges;
    }

}

