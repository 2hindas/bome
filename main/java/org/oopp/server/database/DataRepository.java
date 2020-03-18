package org.oopp.server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<Data, Long> {

    /**
     * Returns a given default_value.
     *
     * @param activityType type of the activity.
     * @return DefaultValue - the activity that matches the given type
     */
    @Query("SELECT a FROM Data a WHERE activityType = :at")
    Data findActivityValueByType(@Param("at") String activityType);

}