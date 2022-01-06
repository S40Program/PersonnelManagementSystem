package personnel.util.common;

import personnel.pojo.UserVisit;

public class UserVisitSingleton {

    private static volatile UserVisit userVisit;

    private UserVisitSingleton() {}

    public static UserVisit getInstance() {
        if (userVisit == null) {
            synchronized (UserVisit.class) {
                if (userVisit == null) {
                	userVisit = new UserVisit();
                }
            }
        }
        return userVisit;
    }
}
