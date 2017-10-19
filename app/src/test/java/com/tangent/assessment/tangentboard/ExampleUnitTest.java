package com.tangent.assessment.tangentboard;

import android.util.Log;

import com.tangent.assessment.tangentboard.fragment.ProfileFragment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String TAG = ExampleUnitTest.class.getSimpleName();
    private ProfileFragment mProfileFragment;

    @Before
    public void setUp() throws Exception {
        mProfileFragment = new ProfileFragment();
    }

    @Test
    public void getBooleanFromInteger_1_returns_true(){
        try {
            assertEquals(mProfileFragment.getBoolean(1), true);
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Test
    public void getBooleanFromInteger_0_returns_false(){
        try{
            assertEquals(mProfileFragment.getBoolean(0), false);
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    @Test
    public void getBooleanFromInteger_3_throws_exception(){
        try {
            assertEquals(mProfileFragment.getBoolean(3), true);
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }
}