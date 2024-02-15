package com.leandroadal.vortasks.services.user;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.user.ProgressData;
import com.leandroadal.vortasks.repositories.user.ProgressDataRepository;
import com.leandroadal.vortasks.services.exception.DatabaseException;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class ProgressDataService {

    @Autowired
    private ProgressDataRepository repository;

    @Autowired
    private LogProgressService log;

    public ProgressData getProgressDataByUserId(String userId) {
        try {
            return repository.findByUserId(userId)
                .orElseThrow(() -> new ObjectNotFoundException(userId));
        } catch (ObjectNotFoundException e) {
            log.notFoundProgressByUserId(userId);
            throw e;
        } 
    }

    public ProgressData findProgressById(String progressId) {
        try {
            return repository.findById(progressId)
              .orElseThrow(() -> new ObjectNotFoundException(progressId));
        } catch (ObjectNotFoundException e) {
            log.notFoundProgressById(progressId);
            throw e;
        }
    }

    public ProgressData editProgress(ProgressData data) {
        ProgressData progress = findProgressById(data.getId());
        applyEdit(progress, data);
        save(progress);
        log.editProgress(progress.getId());
        return progress;
    }

    public ProgressData partialEditProgress(ProgressData data) {
        ProgressData progress = findProgressById(data.getId());
        applyPartialEdit(progress, data);
        save(progress);
        log.partialEditProgress(progress.getId());
        return progress;
    }

    public void deleteProgress(String progressId) {
        try {
            ProgressData progress = findProgressById(progressId);
            delete(progress);
            log.deleteProgress(progressId);
        } catch (DataIntegrityViolationException e) {
            log.progressDeleteFailed(progressId);
            throw new DatabaseException(e.getMessage());
        }
    }

    protected ProgressData save(ProgressData progressData) {
        return repository.save(progressData);
    }

    protected void delete(ProgressData progressData) {
        repository.delete(progressData);
    }

    private void applyEdit(ProgressData progress, ProgressData data) {
        progress.setCoins(data.getCoins());
        progress.setGems(data.getGems());
        progress.setLevel(data.getLevel());
        progress.setXp(data.getXp());
    }

    private void applyPartialEdit(ProgressData progress, ProgressData data) {
        updateFieldIfPositive(progress::setGems, data.getGems());
        updateFieldIfPositive(progress::setCoins, data.getCoins());
        updateFieldIfPositive(progress::setLevel, data.getLevel());
        updateFieldIfPositive(progress::setXp, data.getXp());
    }

    private <T extends Number> void updateFieldIfPositive(Consumer<T> setter, T value) {
        if (value != null && value.doubleValue() > 0) {
            setter.accept(value);
        }
    }

}
