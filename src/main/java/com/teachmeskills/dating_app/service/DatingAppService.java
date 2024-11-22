package com.teachmeskills.dating_app.service;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.GenderRepository;
import com.teachmeskills.dating_app.repository.LikesRepository;
import com.teachmeskills.dating_app.repository.RelationRepository;
import com.teachmeskills.dating_app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatingAppService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private RelationRepository relationRepository;
    public List<UserAccount> showMatesForUser(UserAccount userAccount){
        String userRelationType = relationRepository.getUserRelationType(userAccount.getId());
        int userInterestedInGenderId = genderRepository.getInterestedInGenderId(userAccount.getId());
        List<UserAccount> userAccounts = userAccountRepository.findAllByIdAfter(0);
        List<UserAccount> acceptableUserAccounts = userAccounts.stream()
                .filter(ua -> ua.getId() != userAccount.getId())
                .filter(ua -> ua.getUserAccountGenderId() == userInterestedInGenderId)
                .filter(ua -> genderRepository.getInterestedInGenderId(ua.getId()) != userInterestedInGenderId)
                .filter(ua -> relationRepository.getUserRelationType(ua.getId()).equals(userRelationType)).toList();
        return acceptableUserAccounts;
    }

    public void likeUser(int idBy, int idTarget){
        likesRepository.addNewLike(idBy, idTarget);
    }

    public List<UserAccount> showWhoLikeThisUser(UserAccount userAccount){
        List<UserAccount> listOfLikes = null;
        List<Integer> idWhoLike = likesRepository.showAllLikesOfUser(userAccount.getId());
        for (int id:idWhoLike) {
            listOfLikes.add(userAccountRepository.findUserAccountById(id));
        }
        return listOfLikes;
    }

    public List<UserAccount> showLikesFromUsers(UserAccount userAccount){
        List<UserAccount> listOfLikes = null;
        List<Integer> idWhoLike = likesRepository.showAllLikesFromUser(userAccount.getId());
        for (int id:idWhoLike) {
            listOfLikes.add(userAccountRepository.findUserAccountById(id));
        }
        return listOfLikes;
    }

    public List<UserAccount> showMatches(UserAccount userAccount){
        List<UserAccount> listOfAllLikes = null;
        listOfAllLikes.addAll(showLikesFromUsers(userAccount));
        listOfAllLikes.addAll(showWhoLikeThisUser(userAccount));
        List<UserAccount> matchUsers = listOfAllLikes.stream()
                .filter(ua -> Collections.frequency(listOfAllLikes, ua) > 1)
                .collect(Collectors.toList());
        return matchUsers;
    }
}
