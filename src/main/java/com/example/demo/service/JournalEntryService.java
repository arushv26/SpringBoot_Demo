package com.example.demo.service;

import com.example.demo.Repository.JournalEntryRepo;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
       return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId myId){
        return journalEntryRepo.findById(myId);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String userName){
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(myId);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("error in deleting entry",e);
        }
        return removed;
    }
//    public List<JournalEntry> findByUserName(String userName){
//
//    }

}
