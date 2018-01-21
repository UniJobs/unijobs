package core.service;

import java.util.List;

public interface NotificationService {
    void notificationJobAdded(Integer excluded);
    void notificationStatus(List<Integer> ids);
    void notificationRequested(Integer id);
}
