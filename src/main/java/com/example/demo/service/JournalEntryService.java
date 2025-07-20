package com.example.demo.service;

import com.example.demo.Repository.JournalEntryRepo;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
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
    public void deleteById(ObjectId myId, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepo.deleteById(myId);
    }

}
