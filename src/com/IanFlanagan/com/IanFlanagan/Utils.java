package com.IanFlanagan;

import com.rollbar.notifier.Rollbar;
import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;


public class Utils {

    private static Rollbar rollbar;

    public static Rollbar createRollbarInstance(String token, String environment, String codeVersion) {

        /*
        Creates an instance of the rollbar and returns a rollbar object. Note to create a person and server provider see
        my other code example.
         */

        try
        {
            rollbar = Rollbar.init(withAccessToken(token)
                    .environment(environment)
                    .codeVersion(codeVersion)
                    // .person(new MyPersonProvider())
                    //  .server(new ServerProvider())
                    .language(MyConfig.language)
                    .framework(MyConfig.framework)
                    .platform(MyConfig.platform)
                    //.timestamp(Provider<long> timestamp)
                    .handleUncaughtErrors(true)
                    .build());
            System.out.println("Creating an instance of rollbar using this token: " +token+ " Environment: "
                    +environment+ " codeVersion: " +codeVersion);

        } catch (Exception e) {

            System.out.println("Can't create Rollbar Instance: " +e.getMessage());
            e.printStackTrace();
        }

        return rollbar;

    }
    public static void closeRollbar() {
        try
        {
            rollbar.close(true);
        } catch (Exception e) {
            System.out.println("Can't close rollbar instance: " +e.getMessage());
            e.printStackTrace();
        }
    }
}
