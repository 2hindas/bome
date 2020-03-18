package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BadgesRepository extends JpaRepository<Badges, Long> {


    /**
     * Returns a given default_value.
     *
     * @param badgeId for badge_id.
     * @return DefaultValue - the badge detail- name/ image
     */
    @Query("SELECT a FROM Badges a WHERE badgeId = :badgeId")
    Badges findBadgeDetail(@Param("badgeId") int badgeId);


}
