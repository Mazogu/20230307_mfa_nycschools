package com.example.a20230307_mfa_nycschools.viewmodel;

import androidx.test.espresso.idling.CountingIdlingResource;

/**
 * Singleton Class to contain an IdleResource
 */
public class IdleResource {
    private final static String RESOURCE = "Idle Time";

    private static CountingIdlingResource resource;


    /**
     * Method to get the singleton instance.
     * @return A singleton instance of CountingIdlingResource.
     */
    public static CountingIdlingResource getResourceInstance(){
        if(resource == null)
           resource = new CountingIdlingResource(RESOURCE);
        return resource;
    }
}
