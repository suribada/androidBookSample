package com.suribada.androidbook.etc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.suribada.androidbook.R;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Noh.Jaechun on 2018. 4. 6..
 */
public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_display);
        TextView status = (TextView) findViewById(R.id.status);

        StringBuilder sb = new StringBuilder();

        Config defaultConfig = ConfigFactory.load();
        String cafeServer = defaultConfig.getString("cafe.server");
        Config cafeConfig = defaultConfig.getConfig("cafe");
        int port = cafeConfig.getInt("port");

        sb.append("cafeServer=" + cafeServer
                    + ", port=" + port + "\n");


        Config config = ConfigFactory.parseResources("overrides.conf")
                .withFallback(defaultConfig).resolve();
        sb.append("overide version\n"
                + "CafeServer=" + config.getString("cafe.server")
                + ", port=" + config.getInt("cafe.port") + "\n");

        sb.append("android developers=" + config.getStringList("androidDevelopers"));
        sb.append(", name=" + config.getString("name"));

        status.setText(sb.toString());
    }

}
