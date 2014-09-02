package com.hackers.diary.constants;

import android.location.Location;

import java.io.Serializable;

public class LocationData implements Serializable {
    // The name of the place to be shown in UI?
    public String placeName;
    // The latitude and longitude information on the place
    public Location location;
    // TODO(srirajdutt): Figure out what else needs to be stored based on Places API so that the
    // location address can be searched and shown in maps.
}
