package org.oopp.server.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private DataRepository dataRepository;

    /**
     * Returns the Co2 value of the given activity.
     *
     * @param activityType type of the activity.
     * @return Co2 emission.
     */
    @GetMapping(value = "/co2ofactivity")
    public double getCo2OfActivity(@RequestParam String activityType) {
        Data df = dataRepository.findActivityValueByType(activityType);
        return df.getCo2();
    }

    /**
     * Returns the Water value of the given activity.
     *
     * @param activityType type of the activity.
     * @return water usage.
     */
    @GetMapping(value = "/waterofactivity")
    public double getWaterOfActivity(@RequestParam String activityType) {
        Data df = dataRepository.findActivityValueByType(activityType);
        return df.getWater();
    }

    /**
     * Return the Land value of the given activity.
     *
     * @param activityType type of the activity.
     * @return land usage.
     */
    @GetMapping(value = "/landofactivity")
    public double getLandOfActivity(@RequestParam String activityType) {
        Data df = dataRepository.findActivityValueByType(activityType);
        return df.getLand();
    }


    /**
     * Return the values of the given activity.
     * @param activityType type of the activity.
     * @return Data.
     */
    @GetMapping(value = "/activitystats")
    public Data getActivityStats(@RequestParam String activityType) {
        Data df = dataRepository.findActivityValueByType(activityType);
        return df;
    }

}
