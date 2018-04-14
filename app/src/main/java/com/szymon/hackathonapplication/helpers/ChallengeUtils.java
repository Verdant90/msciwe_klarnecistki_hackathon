package com.szymon.hackathonapplication.helpers;

import com.szymon.hackathonapplication.models.challenges.AppleTimeChallenge;
import com.szymon.hackathonapplication.models.challenges.Challenge;
import com.szymon.hackathonapplication.models.challenges.FruitTimeChallenge;
import com.szymon.hackathonapplication.models.challenges.PearTimeChallenge;
import com.szymon.hackathonapplication.models.challenges.PlumTimeChallenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeUtils {
    public static List<Challenge> getChallenges(){
        List<Challenge> challengeList = new ArrayList<>();
        challengeList.add(new AppleTimeChallenge());
        challengeList.add(new PearTimeChallenge());
        challengeList.add(new PlumTimeChallenge());
        challengeList.add(new FruitTimeChallenge());
        return challengeList;
    }
}
